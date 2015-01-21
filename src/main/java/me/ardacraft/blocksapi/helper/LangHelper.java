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

package me.ardacraft.blocksapi.helper;

import me.ardacraft.blocksapi.Meta;
import org.apache.commons.lang3.text.WordUtils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author dags_ <dags@dags.me>
 */

public class LangHelper
{

    private static List<String> entries = new ArrayList<String>();

    public static void addTranslation(String key)
    {
        if (Meta.CLIENT)
            entries.add(key + ".name=" + getNeatName(key));
    }

    // Make names user friendly
    private static String getNeatName(String s)
    {
        String name = s.substring(5).replace(".name", "").replace("_", " ");
        if (name.endsWith(" Block"))
        {
            return name.replace(" Block", "");
        }
        return WordUtils.capitalizeFully(name);
    }

    public static void writeLangFile()
    {
        File out = FileHelper.externalConfigFile("assets/acblocks/lang", "en_US.lang");
        try
        {
            FileWriter writer = new FileWriter(out);
            Collections.sort(entries);
            for (String s : entries)
            {
                writer.write(s);
                writer.append("\n");
            }
            writer.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        entries.clear();
    }

}
