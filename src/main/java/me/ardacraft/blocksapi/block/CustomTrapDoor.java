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
import net.minecraft.block.BlockTrapDoor;
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

public class CustomTrapDoor extends BlockTrapDoor implements ACBlock
{

    @SideOnly(Side.CLIENT)
    private TextureWrapper textures;

    protected boolean top;

    protected CustomTrapDoor(Block b, String name, TextureWrapper tw)
    {
        super(b.getMaterial());
        this.setBlockName(name + "_Trap-Door");
        this.setStepSound(b.stepSound);
        this.setLightOpacity(0);
        if (Meta.CLIENT)
        {
            textures = tw;
            top = false;
        }
    }

    @SideOnly(Side.CLIENT)
    public IIcon getIcon(IBlockAccess ib, int p_149673_2_, int p_149673_3_, int p_149673_4_, int side)
    {
        return getIcon(side, 0);
    }

    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int meta)
    {
        return textures.getIcon(side);
    }

    @Override
    public boolean canPlaceBlockAt(World p_149742_1_, int p_149742_2_, int p_149742_3_, int p_149742_4_)
    {
        return true;
    }

    @Override
    public void onBlockPlacedBy(World w, int x, int y, int z, EntityLivingBase e, ItemStack is)
    {
        if (top)
        {
            int var7 = determineOrientation(e) + 8;
            w.setBlockMetadataWithNotify(x, y, z, var7, 2);
        }
        else
        {
            int var7 = determineOrientation(e);
            w.setBlockMetadataWithNotify(x, y, z, var7, 2);
        }
    }

    private static int determineOrientation(EntityLivingBase e)
    {
        int var7 = MathHelper.floor_double((double) (e.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;
        switch (var7)
        {
            case 0:
                return 0;
            case 1:
                return 3;
            case 2:
                return 1;
            case 3:
                return 2;
        }
        return var7;
    }

    @SideOnly(Side.CLIENT)
    public IIcon func_149735_b(int p_149735_1_, int p_149735_2_)
    {
        return getIcon(p_149735_1_, p_149735_2_);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void registerBlockIcons(IIconRegister register)
    {
        textures.registerIcons(register);
    }

    @Override
    public Block toBlock()
    {
        return this;
    }

    @Override
    public void onNeighborBlockChange(World p_149695_1_, int p_149695_2_, int p_149695_3_, int p_149695_4_, Block p_149695_5_)
    {

    }

    @Override
    public int onBlockPlaced(World w, int x, int y, int z, int p_149660_5_, float xx, float yy, float zz, int p_149660_9_)
    {
        if ((p_149660_5_ != 1 && p_149660_5_ != 0 && yy > 0.5F) || yy == 0)
        {
            top = true;
        }
        else
        {
            top = false;
        }
        return 0;
    }

    @Override
    public boolean canPlaceBlockOnSide(World p_149707_1_, int p_149707_2_, int p_149707_3_, int p_149707_4_, int p_149707_5_)
    {
        return true;
    }

}
