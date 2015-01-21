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

package me.ardacraft.blocksapi.update;

import com.google.gson.annotations.Expose;
import me.ardacraft.blocksapi.helper.FileHelper;
import me.ardacraft.blocksapi.helper.LogHelper;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;

/**
 * @author dags_ <dags@dags.me>
 */

public class Update
{

    @Expose
    private UpdateUrl[] urls;

    public Update()
    {}

    public Update(boolean dummy)
    {
        if (dummy)
        {
            urls = new UpdateUrl[1];
            urls[0] = new UpdateUrl(true);
        }
    }

    public void downloadLatestVersions()
    {
        File[] files = FileHelper.getBlockPackFolder().listFiles();
        for (UpdateUrl u : urls)
        {
            if (files == null || files.length == 0)
            {
                download(u);
                continue;
            }
            String newName = u.getName();
            for (File f : files)
            {
                if (!f.getName().startsWith("blockpack-"))
                {
                    continue;
                }
                String[] split = f.getName().replace("blockpack-", "").split("-");
                if (split.length == 2)
                {
                    String currentName = split[0];
                    String version = split[1];
                    if (newName.equals(currentName))
                    {
                        LogHelper.log("New: " + newName + "-" + u.getVersion() + " Current: " + currentName + "-" + version);
                        String latest = FileHelper.getLatest(u.getVersion(), version);
                        if (latest.equals("SAME") || currentName.equals(latest))
                        {
                            continue;
                        }
                        LogHelper.log("DOWNLOADING: " + u.getFileName() + "...");
                        download(u);
                    }
                }
            }
        }
    }

    public void download(UpdateUrl u)
    {
        URL url = u.getUrl();
        if (url == null)
        {
            return;
        }
        try
        {
            File download = FileHelper.getOrCreateFile(FileHelper.getBlockPackFolder(), u.getFileName());
            FileUtils.copyURLToFile(url, download);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

}
