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

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import me.ardacraft.blocksapi.ACBlocks;
import me.ardacraft.blocksapi.Config;

import java.io.*;
import java.util.regex.Pattern;

/**
 * @author dags_ <dags@dags.me>
 */

public class FileHelper
{

    public static File getBlockPackFolder()
    {
        return getOrCreateFolder(new File(ACBlocks.configFolder.getParent()), "acblockspack");
    }

    public static File getFile(File folder, String fileName)
    {
        File file = new File(folder, fileName);
        if (folder.exists() && file.exists())
        {
            return file;
        }
        return null;
    }

    public static File getOrCreateFile(File folder, String fileName)
    {
        File file = new File(folder, fileName);
        if (folder.exists() && file.exists())
        {
            return file;
        }
        try
        {
            file.createNewFile();
            return file;
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    public static File getOrCreateFolder(File dir, String folderName)
    {
        File folder = new File(dir, folderName);
        if (folder.exists() && folder.exists())
        {
            return folder;
        }
        folder.mkdir();
        return folder;
    }

    public static boolean configFileExists(String subFolder, String name)
    {
        File f = externalConfigFile(subFolder, name);
        return f.exists();
    }

    public static File externalConfigFile(String folder, String fileName)
    {
        return new File(configSubFolder(folder), fileName);
    }

    private static File configSubFolder(String name)
    {
        File folder = new File(getConfigFolder(), name);
        if (name.length() == 0)
        {
            folder = getConfigFolder();
        }
        if (!folder.exists())
        {
            folder.mkdirs();
        }
        return folder;
    }

    public static File getConfigFolder()
    {
        File folder = new File(ACBlocks.configFolder, "acblocks");
        if (!folder.exists())
        {
            folder.mkdirs();
        }
        return folder;
    }

    // Could probably just use String.compareTo()...
    public static String getLatest(String ver1, String ver2)
    {
        String[] split1 = ver1.split(Pattern.quote("."));
        String[] split2 = ver2.split(Pattern.quote("."));
        int s1Length = split1.length;
        int s2Length = split2.length;
        int length = s1Length > s2Length ? s1Length : s2Length;
        for (int i = 0; i < length; i++)
        {
            int n1 = toInt(i < s1Length ? split1[i] : "0");
            int n2 = toInt(i < s2Length ? split2[i] : "0");
            if (n1 == n2)
            {
                continue;
            }
            return n1 > n2 ? ver1 : ver2;
        }
        return "SAME";
    }

    public static Config getOrCreateConfig()
    {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        File configFile = new File(getConfigFolder(), "config.json");
        try
        {
            if (configFile.exists())
            {
                return gson.fromJson(new InputStreamReader(new FileInputStream(configFile)), Config.class);
            }
            else
            {
                Config config = new Config();
                FileWriter fw = new FileWriter(configFile);
                gson.toJson(config, fw);
                fw.flush();
                fw.close();
                return config;
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return new Config();
    }

    private static int toInt(String s)
    {
        try
        {
            return Integer.parseInt(s);
        }
        catch (NumberFormatException e)
        {
            return -1;
        }
    }

}
