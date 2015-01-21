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

import me.ardacraft.blocksapi.blockstate.TextureWrapper;
import me.ardacraft.blocksapi.entity.EntityChair;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

/**
 * @author dags_ <dags@dags.me>
 */

public class CustomShortChair extends CustomDaySensor
{
    public CustomShortChair(Block b, String name, TextureWrapper tw)
    {
        super(b, name, tw);
        this.setBlockName(name + "_Short_Chair");
    }

    @Override
    public boolean onBlockActivated(World w, int x, int y, int z, EntityPlayer ep, int side, float xLook, float yLook, float zLook)
    {
        return EntityChair.onInteractChair(w, x, y, z, ep, side, xLook, yLook, zLook, 0.63D);
    }
}
