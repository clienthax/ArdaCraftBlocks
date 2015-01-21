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

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @author dags_ <dags@dags.me>
 */

public class FolderSearch
{

    private File root;
    private List<File> files;

    // Can't remember where I use this. Recursively searches a folder
    public FolderSearch(File f)
    {
        root = f;
    }

    public List<File> listFiles()
    {
        scanFiles();
        return files;
    }

    private void scanFiles()
    {
        files = new ArrayList<File>();
        if (root.isDirectory() || root.listFiles() != null)
        {
            listFiles(root);
        }
    }

    private void listFiles(File folder)
    {
        if (folder.isDirectory())
        {
            File[] list = folder.listFiles();
            if (list == null)
            {
                return;
            }
            for (File f : list)
            {
                if (f.isFile())
                {
                    files.add(f);
                    continue;
                }
                listFiles(f);
            }
        }
    }
}
