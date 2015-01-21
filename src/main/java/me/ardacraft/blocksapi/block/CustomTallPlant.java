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

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import me.ardacraft.blocksapi.Meta;
import me.ardacraft.blocksapi.blockstate.TextureWrapper;
import net.minecraft.block.Block;
import net.minecraft.block.BlockDoublePlant;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author dags_ <dags@dags.me>
 */

public class CustomTallPlant extends BlockDoublePlant implements ACBlock
{

    @SideOnly(Side.CLIENT)
    private String topT;
    @SideOnly(Side.CLIENT)
    private String bottomT;
    @SideOnly(Side.CLIENT)
    private IIcon top;
    @SideOnly(Side.CLIENT)
    private IIcon bottom;

    public CustomTallPlant(Block b, String name, TextureWrapper tw)
    {
        super();
        this.setBlockName(name + "_Tall-Plant");
        this.setStepSound(b.stepSound);
        if (Meta.CLIENT)
        {
            topT = tw.getTextureNameForSide(1);
            bottomT = tw.getTextureNameForSide(0);
        }
    }

    @Override
    public boolean canPlaceBlockAt(World p_149742_1_, int p_149742_2_, int p_149742_3_, int p_149742_4_)
    {
        return true;
    }

    public boolean canBlockStay(World w, int x, int y, int z)
    {
        if (w.getBlockMetadata(x, y, z) == 0)
        {
            return w.getBlock(x, y + 1, z) instanceof CustomTallPlant;
        }
        return true;
    }

    public Item getItemDropped(int p_149650_1_, Random p_149650_2_, int p_149650_3_)
    {
        return null;
    }

    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int meta)
    {
        return meta == 0 ? this.bottom : this.top;
    }

    @SideOnly(Side.CLIENT)
    public IIcon func_149888_a(boolean p_149888_1_, int meta)
    {
        return getIcon(meta, meta);
    }

    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister reg)
    {
        top = reg.registerIcon(this.topT);
        bottom = reg.registerIcon(this.bottomT);
    }

    @SideOnly(Side.CLIENT)
    public void getSubBlocks(Item p_149666_1_, CreativeTabs p_149666_2_, List p_149666_3_)
    {}

    @Override
    public boolean isShearable(ItemStack item, IBlockAccess world, int x, int y, int z)
    {
        return false;
    }

    @Override
    public ArrayList<ItemStack> onSheared(ItemStack item, IBlockAccess world, int x, int y, int z, int fortune)
    {
        return new ArrayList<ItemStack>();
    }

    @Override
    public Block toBlock()
    {
        return this;
    }

    @SideOnly(Side.CLIENT)
    public Item getItem(World p_149694_1_, int p_149694_2_, int p_149694_3_, int p_149694_4_)
    {
        return Item.getItemFromBlock(this);
    }

    @Override
    public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z)
    {
        Item i = GameRegistry.findItem(Meta.MOD_ID, this.getUnlocalizedName());
        return new ItemStack(i);
    }
}
