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

package me.ardacraft.blocksapi.blockstate;

import cpw.mods.fml.common.registry.GameRegistry;
import me.ardacraft.blocksapi.Meta;
import me.ardacraft.blocksapi.block.ACBlock;
import me.ardacraft.blocksapi.block.ACBlockFactory;
import me.ardacraft.blocksapi.block.CustomSlab;
import me.ardacraft.blocksapi.helper.BlockHelper;
import me.ardacraft.blocksapi.helper.LangHelper;
import me.ardacraft.blocksapi.inventory.ACTab;
import me.ardacraft.blocksapi.item.CustomItemSlab;
import me.ardacraft.blocksapi.item.args.CustomItemSlabArgs;
import net.minecraft.block.Block;
import net.minecraft.block.BlockSlab;
import net.minecraft.item.Item;
import org.apache.commons.lang3.text.WordUtils;

/**
 * @author dags_ <dags@dags.me>
 */

public class BlockState
{

    private String name;
    private BaseBlock sound;
    private TextureWrapper textures;
    private BlockType[] blockTypes;
    private String tab = "";

    public BlockState()
    {
    }

    public BlockState(boolean defaults)
    {
        if (defaults)
        {
            name = "ACBlocks_Ex";
            sound = BaseBlock.STONE;
            textures = new TextureWrapper();
            textures.setDefaults();
            blockTypes = new BlockType[]{BlockType.CROPS};
        }
    }

    public void setTab(String s)
    {
        tab = s;
    }

    // Registers all the block shapes
    // Should probably check that it isn't added two blocks with the same name as that causes a crash, but it doesn't
    public void registerBlocks()
    {
        name = WordUtils.capitalizeFully(name.replace("_", " ")).replace(" ", "_");
        Block b = BlockHelper.getBaseBlock(sound);
        ACTab ct = Meta.getTab(tab);
        for (BlockType bt : blockTypes)
        {
            float hardness = BlockType.getHardness(bt);
            switch (bt)
            {
                case SLAB:
                    BlockSlab slab = new CustomSlab(b, name, textures, false);
                    BlockSlab doubleSlab = new CustomSlab(b, name, textures, true);
                    registerSlab(slab, doubleSlab, hardness, ct);
                    break;
                case DOOR:
                    ACBlock doorBlock = ACBlockFactory.getBlock(b, name, textures, bt, tab);
                    registerDoor(doorBlock, hardness);
                    break;
                case TORCH:
                case LIGHT_WEB:
                    registerLightEmitter(b, bt, ct);
                    break;
                default:
                    ACBlock block = ACBlockFactory.getBlock(b, name, textures, bt, tab);
                    registerBlock(block, hardness, ct);
                    break;
            }
        }
    }

    // Slabs need a half and double block registering, also needs an item
    // Only adds the half-slab to creative inventory
    private void registerSlab(BlockSlab slab, BlockSlab doubleSlab, float hardness, ACTab ct)
    {
        slab.setHardness(hardness);
        doubleSlab.setHardness(hardness);

        CustomItemSlabArgs ia = new CustomItemSlabArgs((CustomSlab) slab, (CustomSlab) doubleSlab, tab);
        GameRegistry.registerBlock(slab, CustomItemSlab.class, slab.getUnlocalizedName(), ia);
        GameRegistry.registerBlock(doubleSlab, CustomItemSlab.class, doubleSlab.getUnlocalizedName(), ia);
        LangHelper.addTranslation("item." + slab.getUnlocalizedName().substring(5));
        LangHelper.addTranslation("item." + doubleSlab.getUnlocalizedName().substring(5));
        if (Meta.CLIENT)
        {
            slab.setCreativeTab(ct);
            if (ACTab.isTabIcon(slab.getUnlocalizedName()))
            {
                ct.setTabItem(Item.getItemFromBlock(slab));
            }
        }
    }

    private void registerDoor(ACBlock block, float hardness)
    {
        if (block == null)
        {
            return;
        }
        Block b = block.toBlock().setHardness(hardness);
        GameRegistry.registerBlock(b, b.getUnlocalizedName().substring(5));
    }

    // Because I'm lazy - registers 3 blocks with different light intensities.
    private void registerLightEmitter(Block b, BlockType bt, ACTab ct)
    {
        for (float f = 0.98F, i = 0; f > 0.45; f -= 0.24F, i++)
        {
            Block light = ACBlockFactory.getBlock(b, name, textures, bt, tab).toBlock();
            light.setLightLevel(f);
            light.setBlockName(light.getLocalizedName().substring(5) + "_" + (int) i);
            GameRegistry.registerBlock(light, light.getUnlocalizedName().substring(5));
            LangHelper.addTranslation(light.getUnlocalizedName());
            if (Meta.CLIENT)
            {
                light.setCreativeTab(ct);
                if (ACTab.isTabIcon(light.getUnlocalizedName()))
                {
                    ct.setTabItem(Item.getItemFromBlock(light));
                }
            }
        }
    }

    // Registers pretty much everything else
    private void registerBlock(ACBlock block, float hardness, ACTab ct)
    {
        if (block == null)
        {
            return;
        }
        Block bb = block.toBlock().setHardness(hardness);
        GameRegistry.registerBlock(bb, bb.getUnlocalizedName().substring(5));
        LangHelper.addTranslation(bb.getUnlocalizedName());
        if (Meta.CLIENT)
        {
            bb.setCreativeTab(ct);
            if (ACTab.isTabIcon(bb.getUnlocalizedName()))
            {
                ct.setTabItem(Item.getItemFromBlock(bb));
            }
        }
    }
}
