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

package me.ardacraft.blocksapi.inventory;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;

import java.util.*;

/**
 * @author dags_ <dags@dags.me>
 */

public class ACTab extends CreativeTabs
{
    private static Set<String> tabIcons = new HashSet<String>();
    private String name;
    private Item tabItem;

    public ACTab(int id, String label)
    {
        super(id, label);
        name = label.substring(0, 1).toUpperCase() + label.substring(1);
    }

    public static void addTabIcon(String iconName)
    {
        tabIcons.add(iconName);
    }

    public static boolean isTabIcon(String name)
    {
        return tabIcons.contains(name);
    }

    public static void clearTabIconList()
    {
        tabIcons.clear();
        tabIcons = null;
    }

    // This is pants
    private Comparator<Item> SORTED_ITEM = new Comparator<Item>()
    {
        @Override
        public int compare(Item i1, Item i2)
        {
            String name1 = i1.getUnlocalizedName().substring(5);
            String name2 = i2.getUnlocalizedName().substring(5);
            int ind1 = 1;
            int ind2 = 1;
            if (name1.contains("Light_Web") || name1.contains("Torch"))
            {
                ind1 = 2;
            }
            if (name2.contains("Light_Web") || name2.contains("Torch"))
            {
                ind2 = 2;
            }
            String[] s1 = name1.split("_");
            String[] s2 = name2.split("_");
            int res = String.CASE_INSENSITIVE_ORDER.compare(s1[s1.length - ind1], s2[s2.length - ind2]);
            if (res == 0)
            {
                res = name1.compareTo(name2);
            }
            return res;
        }
    };

    public void setTabItem(Item i)
    {
        if (!hasTabItem())
        {
            tabItem = i;
        }
    }

    public boolean hasTabItem()
    {
        return tabItem != null;
    }

    @SideOnly(Side.CLIENT)
    public String getTranslatedTabLabel()
    {
        return name;
    }

    @Override
    public Item getTabIconItem()
    {
        if (hasTabItem())
        {
            return tabItem;
        }
        return Items.ender_eye;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void displayAllReleventItems(List list)
    {
        Iterator iterator = Item.itemRegistry.iterator();
        List<Item> sorted = new ArrayList<Item>();
        while (iterator.hasNext())
        {
            Item item = (Item) iterator.next();
            if (item == null)
            {
                continue;
            }
            sorted.add(item);
        }
        Collections.sort(sorted, SORTED_ITEM);
        for (Item i : sorted)
        {
            for (CreativeTabs tab : i.getCreativeTabs())
            {
                if (tab == this)
                {
                    i.getSubItems(i, this, list);
                }
            }
        }

        if (this.func_111225_m() != null)
        {
            this.addEnchantmentBooksToList(list, this.func_111225_m());
        }
    }

}
