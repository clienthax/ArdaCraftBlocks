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
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

/**
 * @author dags_ <dags@dags.me>
 */

public class CustomHalfDoor extends BaseCustomDoor
{

    @SideOnly(Side.CLIENT)
    private static IIcon top;
    @SideOnly(Side.CLIENT)
    private String textureName;
    @SideOnly(Side.CLIENT)
    private IIcon bottom;

    protected CustomHalfDoor(Block b, String name, TextureWrapper tw)
    {
        super(b);
        this.setBlockName(name + "_Half-Door");
        this.setStepSound(b.stepSound);
        this.setLightOpacity(0);
        if (Meta.CLIENT)
            this.textureName = tw.getTextureNameForSide(0);
    }

    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int meta)
    {
        if (meta >= 4)
        {
            return top;
        }

        return bottom;
    }

    @SideOnly(Side.CLIENT)
    public IIcon getIcon(IBlockAccess ib, int x, int y, int z, int side)
    {
        int i1 = this.func_150012_g(ib, x, y, z);
        boolean flag2 = (i1 & 8) != 0;
        return flag2 ? top : this.bottom;
    }

    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister reg)
    {
        bottom = reg.registerIcon(textureName);
        top = reg.registerIcon(Meta.MOD_ID + ":" + "invisible");
    }

    @Override
    public boolean getBlocksMovement(IBlockAccess ib, int x, int y, int z)
    {
        if (isTopHalf(ib, x, y, z))
        {
            return false;
        }
        int l = this.func_150012_g(ib, x, y, z);
        return (l & 4) != 0;
    }

    @SideOnly(Side.CLIENT)
    public AxisAlignedBB getSelectedBoundingBoxFromPool(World w, int x, int y, int z)
    {
        if (isTopHalf(w, x, y, z))
        {
            return getBox(x, y, z);
        }
        this.setBlockBoundsBasedOnState(w, x, y, z);
        return super.getSelectedBoundingBoxFromPool(w, x, y, z);
    }

    @Override
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World w, int x, int y, int z)
    {
        if (isTopHalf(w, x, y, z))
        {
            return null;
        }
        return super.getCollisionBoundingBoxFromPool(w, x, y, z);
    }

    public boolean onBlockActivated(World w, int x, int y, int z, EntityPlayer e, int xx, float yy, float f1, float f2)
    {
        if (isTopHalf(w, x, y, z))
        {
            return false;
        }
        return super.onBlockActivated(w, x, y, z, e, xx, yy, f1, f2);
    }

    @SideOnly(Side.CLIENT)
    public AxisAlignedBB getBox(int x, int y, int z)
    {
        return AxisAlignedBB.getBoundingBox((double) x + 0, (double) y + 0, (double) z + 0, (double) x + 0, (double) y + 0, (double) z + 0);
    }

    private boolean isTopHalf(IBlockAccess ib, int x, int y, int z)
    {
        return ib.getBlockMetadata(x, y, z) > 7;
    }

    @Override
    public void onEntityCollidedWithBlock(World w, int x, int y, int z, Entity e)
    {
        if (isTopHalf(w, x, y, z))
        {
            return;
        }
        super.onEntityCollidedWithBlock(w, x, y, z, e);
    }

    @Override
    public boolean canPlaceBlockAt(World p_149742_1_, int p_149742_2_, int p_149742_3_, int p_149742_4_)
    {
        return true;
    }

}
