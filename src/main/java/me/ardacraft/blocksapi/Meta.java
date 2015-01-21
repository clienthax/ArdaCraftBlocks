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

package me.ardacraft.blocksapi;

import me.ardacraft.blocksapi.inventory.ACTab;
import net.minecraft.creativetab.CreativeTabs;

import java.util.HashMap;

/**
 * @author dags_ <dags@dags.me>
 */

public class Meta
{
    public static final String MOD_ID = "ACBlocks";
    public static final String VERSION = "1.3";
    private static final HashMap<String, ACTab> tabs = new HashMap<String, ACTab>();
    public static ACTab AC_TAB;
    public static boolean CLIENT = true;

    public static ACTab getTab(String s)
    {
        addTab(s);
        if (tabs.containsKey(s.toLowerCase()))
        {
            return tabs.get(s.toLowerCase());
        }
        if (AC_TAB == null)
        {
            AC_TAB = new ACTab(CreativeTabs.getNextID(), "ACBlocks Tab");
        }
        return AC_TAB;
    }

    public static void addTab(String s)
    {
        if (tabs.containsKey(s.toLowerCase()) || s.length() < 2)
        {
            return;
        }
        ACTab tab = new ACTab(CreativeTabs.getNextID(), s);
        tabs.put(s.toLowerCase(), tab);
    }
}
