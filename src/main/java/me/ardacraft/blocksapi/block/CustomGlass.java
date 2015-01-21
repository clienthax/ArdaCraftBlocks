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
import net.minecraft.block.BlockGlass;
import net.minecraft.client.renderer.IconFlipped;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.Facing;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

/**
 * @author dags_ <dags@dags.me>
 */

public class CustomGlass extends BlockGlass implements ACBlock
{

    @SideOnly(Side.CLIENT)
    private TextureWrapper textures;
    @SideOnly(Side.CLIENT)
    private IIcon flipped;
    @SideOnly(Side.CLIENT)
    protected boolean ctm;

    public CustomGlass(Block b, String name, TextureWrapper tw)
    {
        super(b.getMaterial(), true);
        this.setBlockName(name + "_Glass");
        this.setStepSound(b.stepSound);
        if (Meta.CLIENT)
        {
            textures = tw;
            ctm = false;
        }
    }

    @Override
    public Block toBlock()
    {
        return this;
    }

    @SideOnly(Side.CLIENT)
    public IIcon getIcon(IBlockAccess ib, int x, int y, int z, int side)
    {
        return getIcon(side, 0);
    }

    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int meta)
    {
        if (!ctm && (side == 4 || side == 3))
        {
            return flipped;
        }
        return textures.getIcon(side);
    }

    @SideOnly(Side.CLIENT)
    public IIcon func_149735_b(int side, int p_149735_2_)
    {
        return getIcon(side, p_149735_2_);
    }

    @SideOnly(Side.CLIENT)
    public boolean shouldSideBeRendered(IBlockAccess ib, int x, int y, int z, int side)
    {
        Block block = ib.getBlock(x, y, z);
        Block offset = ib.getBlock(x - Facing.offsetsXForSide[side], y - Facing.offsetsYForSide[side], z - Facing.offsetsZForSide[side]);
        if (block instanceof CustomGlass && offset instanceof CustomGlass)
        {
            return false;
        }
        return true;
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void registerBlockIcons(IIconRegister register)
    {
        textures.registerIcons(register);
        flipped = new IconFlipped(register.registerIcon(textures.getDefault()), true, false);
    }

    @Override
    public boolean canPlaceBlockAt(World p_149742_1_, int p_149742_2_, int p_149742_3_, int p_149742_4_)
    {
        return true;
    }

    public ACBlock setCtm(boolean b)
    {
        if (Meta.CLIENT)
            ctm = b;
        return this;
    }

}
