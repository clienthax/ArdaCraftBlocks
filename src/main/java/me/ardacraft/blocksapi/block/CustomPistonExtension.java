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
import net.minecraft.block.BlockPistonExtension;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

/**
 * @author dags_ <dags@dags.me>
 */

public class CustomPistonExtension extends BlockPistonExtension implements ACBlock
{

    @SideOnly(Side.CLIENT)
    private TextureWrapper textures;

    public CustomPistonExtension(Block b, String name, TextureWrapper tw)
    {
        super();
        super.setBlockName(name + "_Piston-Extension");
        if (Meta.CLIENT)
            textures = tw;
    }

    @Override
    public void onBlockPlacedBy(World p_149689_1_, int p_149689_2_, int p_149689_3_, int p_149689_4_, EntityLivingBase p_149689_5_, ItemStack p_149689_6_)
    {
        int var7 = determineOrientation(p_149689_1_, p_149689_2_, p_149689_3_, p_149689_4_, p_149689_5_);
        p_149689_1_.setBlockMetadataWithNotify(p_149689_2_, p_149689_3_, p_149689_4_, var7, 2);
    }

    public static int determineOrientation(World w, int x, int y, int z, EntityLivingBase ep)
    {
        if (MathHelper.abs((float) ep.posX - (float) x) < 2.0F && MathHelper.abs((float) ep.posZ - (float) z) < 2.0F)
        {
            double var5 = ep.posY + 1.82D - (double) ep.yOffset;

            if (var5 - (double) y > 2.0D)
            {
                return 1;
            }

            if ((double) y - var5 > 0.0D)
            {
                return 0;
            }
        }
        int var7 = MathHelper.floor_double((double) (ep.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;
        return var7 == 0 ? 2 : (var7 == 1 ? 5 : (var7 == 2 ? 3 : (var7 == 3 ? 4 : 0)));
    }

    public void breakBlock(World p_149749_1_, int p_149749_2_, int p_149749_3_, int p_149749_4_, Block p_149749_5_, int p_149749_6_)
    {

    }

    @SideOnly(Side.CLIENT)
    public IIcon func_149735_b(int p_149735_1_, int p_149735_2_)
    {
        return getIcon(p_149735_1_, p_149735_2_);
    }

    @SideOnly(Side.CLIENT)
    public IIcon getIcon(IBlockAccess ib, int p_149673_2_, int p_149673_3_, int p_149673_4_, int side)
    {
        return getIcon(side, 0);
    }

    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int meta)
    {
        return textures.getIdefault();
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void registerBlockIcons(IIconRegister register)
    {
        textures.registerIcons(register);
    }

    @Override
    public boolean canPlaceBlockAt(World p_149742_1_, int p_149742_2_, int p_149742_3_, int p_149742_4_)
    {
        return true;
    }

    @Override
    public boolean canPlaceBlockOnSide(World p_149707_1_, int p_149707_2_, int p_149707_3_, int p_149707_4_, int p_149707_5_)
    {
        return true;
    }

    @Override
    public void onNeighborBlockChange(World p_149695_1_, int p_149695_2_, int p_149695_3_, int p_149695_4_, Block p_149695_5_)
    {

    }

    public Block toBlock()
    {
        return this;
    }

}
