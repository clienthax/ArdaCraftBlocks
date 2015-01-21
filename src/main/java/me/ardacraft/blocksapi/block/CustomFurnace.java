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
import net.minecraft.block.BlockFurnace;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

/**
 * @author dags_ <dags@dags.me>
 */

public class CustomFurnace extends BlockFurnace implements ACBlock
{

    // TOP == TOP
    // BOTTOM == FRONT
    // SIDES == SIDES
    @SideOnly(Side.CLIENT)
    private TextureWrapper textures;
    @SideOnly(Side.CLIENT)
    private IIcon furnaceFace;

    protected CustomFurnace(Block b, String name, TextureWrapper tw)
    {
        super(name.contains("_On"));
        if (name.contains("_On"))
        {
            name = name.replace("_On", "");
            this.setBlockName((name + "_Furnace_On"));
        }
        else
        {
            this.setBlockName(name + "_Furnace");
        }
        this.setStepSound(b.stepSound);
        if (Meta.CLIENT)
            textures = tw;
    }

    @Override
    public Block toBlock()
    {
        return this;
    }

    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int meta)
    {
        // if side is top or bottom, get 'top'    // if side != meta get side icon, else get face
        return side <= 1 ? textures.getIcon(1) : (side != meta ? textures.getIcon(2) : furnaceFace);
    }

    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister register)
    {
        textures.registerIcons(register);
        furnaceFace = register.registerIcon(textures.getBottom());
    }

    public boolean onBlockActivated(World p_149727_1_, int p_149727_2_, int p_149727_3_, int p_149727_4_, EntityPlayer p_149727_5_, int p_149727_6_, float p_149727_7_, float p_149727_8_, float p_149727_9_)
    {
        return false;
    }

    @SideOnly(Side.CLIENT)
    public Item getItem(World p_149694_1_, int p_149694_2_, int p_149694_3_, int p_149694_4_)
    {
        return Item.getItemFromBlock(this);
    }

}
