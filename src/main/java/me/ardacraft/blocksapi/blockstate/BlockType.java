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

package me.ardacraft.blocksapi.blockstate;

/**
 * @author dags_ <dags@dags.me>
 */

public enum BlockType
{
    // All the available block shapes that can be added
    BLOCK,
    SLAB,
    STAIRS,
    FENCE,
    WALL,
    PISTON_EXTENSION,
    TRAP_DOOR,
    GHOST_BLOCK,
    LOG,
    PILLAR,
    PANE,
    GHOST_PANE,
    STAINED_PANE,
    WEB,
    LADDER,
    TORCH,
    TRUNK,
    GLASS,
    GLASS_CTM,
    STAINED_GLASS,
    STAINED_GLASS_CTM,
    DAY_SENSOR,
    DOOR,
    HALF_DOOR,
    PLATE,
    FENCE_GATE,
    ICE,
    ANVIL,
    CARPET,
    CAULDRON,
    CROPS,
    PLANT,
    SLIM_SLAB,
    LIGHT_WEB,
    POT,
    BUTTON,
    FURNACE,
    CHAIR,
    SHORT_CHAIR,
    DOUBLE_PLANT,
    LEAF,
    PAINTING;

    // Some blocks destroy instantly
    public static float getHardness(BlockType bt)
    {
        switch (bt)
        {
            case PISTON_EXTENSION:
            case GHOST_BLOCK:
            case GHOST_PANE:
            case WEB:
            case TORCH:
            case PLATE:
            case CARPET:
                return 0F;
            default:
                return 1F;
        }
    }
}
