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

package me.ardacraft.blocksapi.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import me.ardacraft.blocksapi.Meta;
import me.ardacraft.blocksapi.blockstate.TextureWrapper;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.IconFlipped;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

/**
 * @author dags_ <dags@dags.me>
 */

public class CustomDoor extends BaseCustomDoor
{

    @SideOnly(Side.CLIENT)
    TextureWrapper textures;
    @SideOnly(Side.CLIENT)
    private IIcon lowerFlipped;
    @SideOnly(Side.CLIENT)
    private IIcon upperFlipped;

    protected CustomDoor(Block b, String name, TextureWrapper tw)
    {
        super(b);
        this.setBlockName(name + "_Door");
        this.setStepSound(b.stepSound);
        this.setLightOpacity(0);
        if (Meta.CLIENT)
            textures = tw;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon func_149735_b(int p_149735_1_, int p_149735_2_)
    {
        return getIcon(p_149735_1_, p_149735_2_);
    }

    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int meta)
    {
        if (meta > 7)
        {
            return textures.getIcon(1);
        }
        return textures.getIcon(0);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(IBlockAccess ib, int x, int y, int z, int side)
    {
        if (side != 1 && side != 0)
        {
            int i1 = this.func_150012_g(ib, x, y, z);
            int j1 = i1 & 3;
            boolean flag = (i1 & 4) != 0;
            boolean flag1 = false;
            boolean flag2 = (i1 & 8) != 0;
            if (flag)
            {
                if (j1 == 0 && side == 2)
                {
                    flag1 = !flag1;
                }
                else if (j1 == 1 && side == 5)
                {
                    flag1 = !flag1;
                }
                else if (j1 == 2 && side == 3)
                {
                    flag1 = !flag1;
                }
                else if (j1 == 3 && side == 4)
                {
                    flag1 = !flag1;
                }
            }
            else
            {
                if (j1 == 0 && side == 5)
                {
                    flag1 = !flag1;
                }
                else if (j1 == 1 && side == 3)
                {
                    flag1 = !flag1;
                }
                else if (j1 == 2 && side == 4)
                {
                    flag1 = !flag1;
                }
                else if (j1 == 3 && side == 2)
                {
                    flag1 = !flag1;
                }
                if ((i1 & 16) != 0)
                {
                    flag1 = !flag1;
                }
            }
            return flag2 ? (flag1 ? upperFlipped : textures.getIcon(1)) : (flag1 ? lowerFlipped : textures.getIcon(0));
        }
        else
        {
            return this.textures.getIcon(0);
        }
    }

    @Override
    public boolean canPlaceBlockAt(World p_149742_1_, int p_149742_2_, int p_149742_3_, int p_149742_4_)
    {
        return true;
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void registerBlockIcons(IIconRegister register)
    {
        textures.registerIcons(register);
        lowerFlipped = new IconFlipped(textures.getIcon(0), true, false);
        upperFlipped = new IconFlipped(textures.getIcon(1), true, false);
    }

}
