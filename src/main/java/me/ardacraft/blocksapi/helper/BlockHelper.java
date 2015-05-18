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

package me.ardacraft.blocksapi.helper;

import cpw.mods.fml.common.ModContainer;
import me.ardacraft.blocksapi.Meta;
import me.ardacraft.blocksapi.blockstate.BaseBlock;
import me.ardacraft.blocksapi.blockstate.BlockState;
import me.ardacraft.blocksapi.inventory.ACTab;
import me.ardacraft.blocksapi.inventory.TabIcon;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/**
 * @author dags_ <dags@dags.me>
 */

public class BlockHelper
{
    // Blocks are sorted into CreativeTabs by their topmost folder within BLOCKS_PATH/
    // ie. all block json under BLOCKS_PATH/bip/ will be sorted into a Creative Tab called 'bip'

    private static final String BLOCKS_PATH = "assets/acblocks/blocks/";

    public static int loadZipFromFile(File f)
    {
        int loaded = 0;
        try
        {
            ZipFile zip = new ZipFile(f);
            if (Meta.CLIENT)
            {
                // Tab icons are where you define the texture to be used for a given tab
                scanForTabIcons(null, zip);
            }
            Enumeration<? extends ZipEntry> entries = zip.entries();
            // Fairly self explanatory
            while (entries.hasMoreElements())
            {
                ZipEntry ze = entries.nextElement();
                String name = ze.getName();
                if (name.startsWith(BLOCKS_PATH) && name.endsWith(".json"))
                {
                    BlockState bs = JsonHelper.getBlockState(zip.getInputStream(ze));
                    if (bs != null)
                    {
                        // Chops off the common path leaving just the relative folders
                        name = name.replace(BLOCKS_PATH, "");
                        // Uses topmost folder name to name the creative tab
                        bs.setTab(name.split("/", 2)[0]);
                        bs.registerBlocksAndItems();
                        loaded++;
                    }
                }
            }
            zip.close();
        }
        catch (IOException e)
        {
            return 0;
        }
        return loaded;
    }

    // Similar to loadFromZip
    public static int loadFromFolder(ModContainer mc, File file)
    {
        int loaded = 0;
        try
        {
            String replace = mc.getSource().getPath() + File.separator;
            FolderSearch folderContents = new FolderSearch(file);
            if (Meta.CLIENT)
            {
                scanForTabIcons(folderContents, null);
            }
            for (File f : folderContents.listFiles())
            {
                String name = f.getPath().replace(replace, "").replace("\\", "/");
                if (name.startsWith(BLOCKS_PATH) && name.endsWith(".json"))
                {
                    BlockState bs = JsonHelper.getBlockState(new FileInputStream(f));
                    if (bs != null)
                    {
                        name = name.replace(BLOCKS_PATH, "");
                        bs.setTab(name.split("/", 2)[0]);
                        bs.registerBlocksAndItems();
                        loaded++;
                    }
                }
            }
        }
        catch (FileNotFoundException e)
        {
            return 0;
        }
        catch (IOException e)
        {
            return 0;
        }
        return loaded;
    }

    private static void scanForTabIcons(FolderSearch folderContents, ZipFile zipFile) throws IOException
    {
        if (zipFile != null)
        {
            Enumeration<? extends ZipEntry> entries = zipFile.entries();
            while (entries.hasMoreElements())
            {
                ZipEntry ze = entries.nextElement();
                String name = ze.getName();
                if (name.endsWith("icon.tabicon"))
                {
                    TabIcon tabIcon = JsonHelper.getTabIcon(zipFile.getInputStream(ze));
                    ACTab.addTabIcon(tabIcon.getTabIcon());
                }
            }
        }
        else if (folderContents != null)
        {
            for (File f : folderContents.listFiles())
            {
                if (f.getName().endsWith("icon.tabicon"))
                {
                    TabIcon tabIcon = JsonHelper.getTabIcon(new FileInputStream(f));
                    ACTab.addTabIcon(tabIcon.getTabIcon());
                }
            }
        }
    }

    // Usually used to pass into a modblock constructor - used for basic material properties like stepsound
    public static Block getBaseBlock(BaseBlock bb)
    {
        switch (bb)
        {
            case STONE:
                return Blocks.stone;
            case WOOD:
                return Blocks.planks;
            case DIRT:
                return Blocks.dirt;
            case SNOW:
                return Blocks.snow;
            case SAND:
                return Blocks.sand;
            case WOOL:
                return Blocks.wool;
            case GLASS:
                return Blocks.glass;
            case SOULSAND:
                return Blocks.soul_sand;
            case SPONGE:
                return Blocks.sponge;
            case GRAVEL:
                return Blocks.gravel;
            case CROP:
                return Blocks.wheat;
            default:
                return Blocks.stone;
        }
    }

}
