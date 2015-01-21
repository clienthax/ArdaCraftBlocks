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

import me.ardacraft.blocksapi.Meta;
import me.ardacraft.blocksapi.item.args.CustomItemSlabArgs;
import net.minecraft.block.Block;
import net.minecraft.block.BlockSlab;
import net.minecraft.item.ItemSlab;
import net.minecraft.item.ItemStack;

/**
 * @author dags_ <dags@dags.me>
 */

public class CustomItemSlab extends ItemSlab
{

    private String name;

    public CustomItemSlab(Block base, BlockSlab slab, BlockSlab doubleSlab, String tab)
    {
        super(base, slab, doubleSlab, false);
        this.init(slab, tab);
    }

    private void init(Block b, String tab)
    {
        this.setMaxDamage(0);
        this.setCreativeTab(Meta.getTab(tab));
        this.name = "item." + b.getUnlocalizedName().substring(5);
    }

    public CustomItemSlab(Block b, CustomItemSlabArgs ia)
    {
        super(b, ia.customSlab, ia.customDoubleSlab, false);
        this.init(ia.customSlab, ia.tab);
    }

    @Override
    public String getUnlocalizedName(ItemStack p_77667_1_)
    {
        return this.name;
    }

}
