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

package me.ardacraft.blocksapi.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import me.ardacraft.blocksapi.block.CustomTallPlant;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

/**
 * @author dags_ <dags@dags.me>
 */

public class CustomItemPlant extends Item
{
    private CustomTallPlant plantBlock;

    public CustomItemPlant(CustomTallPlant b)
    {
        plantBlock = b;
        this.setUnlocalizedName(b.getUnlocalizedName().substring(5));
        this.setTextureName("fern");
    }

    @SideOnly(Side.CLIENT)
    public IIcon getIconFromDamage(int p_77617_1_)
    {
        return plantBlock.getIcon(0, 0);
    }

    @SideOnly(Side.CLIENT)
    public IIcon getIconIndex(ItemStack p_77650_1_)
    {
        return this.getIconFromDamage(0);
    }

    @SideOnly(Side.CLIENT)
    public int getSpriteNumber()
    {
        return 0;
    }

    @Override
    public boolean onItemUse(ItemStack item, EntityPlayer ep, World world, int x, int y, int z, int p_77648_7_, float p_77648_8_, float p_77648_9_, float p_77648_10_)
    {
        if (p_77648_7_ != 1)
        {
            return false;
        }
        else
        {
            ++y;
            Block block = plantBlock;
            if (ep.canPlayerEdit(x, y, z, p_77648_7_, item) && ep.canPlayerEdit(x, y + 1, z, p_77648_7_, item))
            {
                if (!block.canPlaceBlockAt(world, x, y, z))
                {
                    return false;
                }
                else
                {
                    world.setBlock(x, y, z, block);
                    world.setBlockMetadataWithNotify(x, y, z, 0, 0);
                    world.setBlock(x, y + 1, z, block);
                    world.setBlockMetadataWithNotify(x, y+1, z, 1, 1);
                    --item.stackSize;
                    return true;
                }
            }
            else
            {
                return false;
            }
        }
    }
}
