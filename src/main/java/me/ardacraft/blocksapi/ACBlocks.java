/*
 * Copyright (c) 2014, dags_ <dags@dags.me>
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package me.ardacraft.blocksapi;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.IFMLSidedHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.ModContainer;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import cpw.mods.fml.server.FMLServerHandler;
import me.ardacraft.blocksapi.commands.EjectCommand;
import me.ardacraft.blocksapi.entity.ChairManager;
import me.ardacraft.blocksapi.entity.EntityChair;
import me.ardacraft.blocksapi.entity.EntityCustomPainting;
import me.ardacraft.blocksapi.entity.renderers.CustomPaintingRenderer;
import me.ardacraft.blocksapi.helper.*;
import me.ardacraft.blocksapi.helper.Timer;
import me.ardacraft.blocksapi.inventory.ACTab;
import me.ardacraft.blocksapi.resourcecontainers.BlockPack;
import me.ardacraft.blocksapi.resourcecontainers.Language;
import me.ardacraft.blocksapi.update.UpdateUtil;
import net.minecraftforge.client.ClientCommandHandler;

import java.io.File;
import java.util.*;

@SuppressWarnings("unused")
@Mod(modid = Meta.MOD_ID, version = Meta.VERSION)
public class ACBlocks
{
    @Mod.Instance
    public static ACBlocks instance;
    public static File configFolder;
    private List<ModContainer> containers;

    @SideOnly(Side.CLIENT)
    @EventHandler
    public void clientInit(FMLPreInitializationEvent e)
    {
        sharedInit(e, FMLClientHandler.instance());
        // Register clientside command to eject from chair entity
        ClientCommandHandler.instance.registerCommand(new EjectCommand());
    }

    @SideOnly(Side.SERVER)
    @EventHandler
    public void serverInit(FMLPreInitializationEvent e)
    {
        // Is not client
        Meta.CLIENT = false;
        sharedInit(e, FMLServerHandler.instance());
        // Register this thing - periodically removes stray Chair Entities
        FMLCommonHandler.instance().bus().register(new ChairManager());
    }

    public void sharedInit(FMLPreInitializationEvent e, IFMLSidedHandler handler)
    {
        configFolder = e.getModConfigurationDirectory();
        LogHelper.setLogger(e.getModLog());
        // Check web for latest blockpack files - downloads latest version if the server/client doesn't have them
        UpdateUtil.update();
        // Scan the acblockpack/ folder for available blockpacks
        containers = findBlockPacks();
        // Adds blockpack resources so our blocks have textures
        for (ModContainer mc : containers)
        {
            if (mc.getSource().exists())
            {
                handler.addModAsResource(mc);
            }
        }
    }

    @EventHandler
    public void init(FMLPostInitializationEvent e)
    {
        EntityRegistry.registerModEntity(EntityChair.class, "EntityChair", 0, ACBlocks.instance, 80, 1, false);
	    EntityRegistry.registerModEntity(EntityCustomPainting.class, "EntityCustomPainting", 1, ACBlocks.instance, 80, 1, false);
        if (containers.isEmpty())
        {
            return;
        }
        // Loop trough blockpacks
        for (ModContainer blockPack : containers)
        {
            Timer t = new Timer("Load time");
            int numLoaded;
            if (blockPack.getSource().isFile())
            {
                numLoaded = BlockHelper.loadZipFromFile(blockPack.getSource());
            }
            else
            {
                numLoaded = BlockHelper.loadFromFolder(blockPack, blockPack.getSource());
            }
            t.finish();
            LogHelper.log("## Blocks loaded from " + blockPack.getName() + "-" + blockPack.getVersion() + ": " + numLoaded + " in: " + t.printMs());
        }
        if (Meta.CLIENT)
        {
            // LangHelper writes user-friendly blocknames to a .lang file and is loaded as a resourcepack
            // Writes to /config/acblocks/ because...
            LangHelper.writeLangFile();
            ModContainer mc = new Language();
            FMLClientHandler.instance().addModAsResource(mc);
            ACTab.clearTabIconList();
	        RenderingRegistry.registerEntityRenderingHandler(EntityCustomPainting.class, new CustomPaintingRenderer());
        }
        containers.clear();
    }

    private List<ModContainer> findBlockPacks()
    {
        Map<String, ModContainer> containerMap = new LinkedHashMap<String, ModContainer>();
        File[] listedFiles = FileHelper.getBlockPackFolder().listFiles();
        if (listedFiles != null)
        {
            List<File> contents = new ArrayList<File>();
            Collections.addAll(contents, listedFiles);
            // Don't remember why they needed sorting.
            Collections.sort(contents);
            for (File f : contents)
            {
                String name = f.getName();
                LogHelper.log("Scanning file: " + f.getAbsolutePath());
                if (name.startsWith("blockpack-"))
                {
                    ModContainer mc = new BlockPack(name);
                    LogHelper.log("Found BlockPack: " + mc.getName());
                    // Tries to determine the latest version of a blockpack in-case multiple versions exist
                    if (containerMap.containsKey(mc.getName().toLowerCase()))
                    {
                        ModContainer existing = containerMap.get(mc.getName().toLowerCase());
                        String ver = FileHelper.getLatest(mc.getVersion(), existing.getVersion());
                        mc = ver.equals("SAME") ? existing : ver.equals(existing.getVersion()) ? existing : mc;
                        LogHelper.log("Found duped blockpack: " + mc.getName() + ". Adding latest version: " + mc.getVersion());
                    }
                    containerMap.put(mc.getName().toLowerCase(), mc);
                } else {
	                LogHelper.log("Found folder in blockpack that isn't prefixed with \"blockpack-\", :"+name);
                }
            }
        }
        return new ArrayList<ModContainer>(containerMap.values());
    }
}
