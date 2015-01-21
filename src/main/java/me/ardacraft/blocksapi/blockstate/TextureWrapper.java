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

import com.google.gson.annotations.Expose;
import me.ardacraft.blocksapi.Meta;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;

/**
 * @author dags_ <dags@dags.me>
 */

public class TextureWrapper
{
    // Holds the texture names
    // Face names aren't always obvious for certain blocks (see furnace)

    @Expose
    private String normal;
    @Expose
    private String top;
    @Expose
    private String bottom;
    @Expose
    private String sides;

    private IIcon idefault;
    private IIcon itop;
    private IIcon ibottom;
    private IIcon isides;

    public TextureWrapper()
    {
    }

    public TextureWrapper setDefaults()
    {
        normal = "ctm_default";
        return this;
    }

    public IIcon getIcon(int side)
    {
        switch (side)
        {
            case 0:
                if (hasBottom())
                    return ibottom;
                return idefault;
            case 1:
                if (hasTop())
                    return itop;
                return idefault;
            case 2:
            case 3:
            case 4:
            case 5:
                if (hasSides())
                    return isides;
                return idefault;
            default:
                return idefault;
        }
    }

    public boolean hasBottom()
    {
        return bottom != null;
    }

    public boolean hasTop()
    {
        return top != null;
    }

    public boolean hasSides()
    {
        return sides != null;
    }

    public IIcon getIdefault()
    {
        if (idefault == null)
        {
            idefault = determineDefault();
        }
        return idefault;
    }

    private IIcon determineDefault()
    {
        if (isides != null)
        {
            return isides;
        }
        if (itop != null)
        {
            return itop;
        }
        if (ibottom != null)
        {
            return ibottom;
        }
        return idefault;
    }

    public String getTextureNameForSide(int side)
    {
        switch (side)
        {
            case 0:
                if (hasBottom())
                    return getBottom();
                return getDefault();
            case 1:
                if (hasTop())
                    return getTop();
                return getDefault();
            case 2:
            case 3:
            case 4:
            case 5:
                if (hasSides())
                    return getSides();
                return getDefault();
            default:
                return getDefault();
        }
    }

    public String getBottom()
    {
        return Meta.MOD_ID + ":" + bottom;
    }

    public String getDefault()
    {
        if (normal == null)
        {
            if (hasSides())
            {
                return getSides();
            }
            else if (hasTop())
            {
                return getTop();
            }
            else if (hasBottom())
            {
                return getBottom();
            }
        }
        return Meta.MOD_ID + ":" + normal;
    }

    public String getTop()
    {
        return Meta.MOD_ID + ":" + top;
    }

    public String getSides()
    {
        return Meta.MOD_ID + ":" + sides;
    }

    public void registerIcons(IIconRegister register)
    {
        if (hasBottom())
        {
            ibottom = register.registerIcon(getBottom());
        }
        if (hasTop())
        {
            itop = register.registerIcon(getTop());
        }
        if (hasSides())
        {
            isides = register.registerIcon(getSides());
        }
        idefault = register.registerIcon(getDefault());
    }

}
