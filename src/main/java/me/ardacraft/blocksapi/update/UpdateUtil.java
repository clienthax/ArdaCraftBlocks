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

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import me.ardacraft.blocksapi.Config;
import me.ardacraft.blocksapi.helper.FileHelper;
import me.ardacraft.blocksapi.helper.LogHelper;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * @author dags_ <dags@dags.me>
 */

public class UpdateUtil
{
    public static void update()
    {
        Config config = FileHelper.getOrCreateConfig();
        if (config.isAutoUpdateEnabled())
        {
            LogHelper.log("### Running ACBlocks update!");
            Update update = null;
            try
            {
                URL url = new URL(config.getUpdateUrl());
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                BufferedInputStream bis = new BufferedInputStream(connection.getInputStream());
                Reader r = new InputStreamReader(bis);
                Gson gson = new GsonBuilder().create();
                update = gson.fromJson(r, Update.class);
            }
            catch (MalformedURLException e)
            {
                e.printStackTrace();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
            if (update != null)
            {
                update.downloadLatestVersions();
            }
        }
    }
}
