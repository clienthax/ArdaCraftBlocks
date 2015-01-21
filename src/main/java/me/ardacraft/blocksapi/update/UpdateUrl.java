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

import java.net.MalformedURLException;
import java.net.URL;

/**
 * @author dags_ <dags@dags.me>
 */

public class UpdateUrl
{

    @Expose
    private String name;
    @Expose
    private String version;
    @Expose
    private String url;

    public UpdateUrl()
    {
    }

    public UpdateUrl(boolean dummy)
    {
        if (dummy)
        {
            name = "acblocks";
            version = "1.0";
            url = "http://f.ardacraft.me/.update/updates.json";
        }
    }

    public String getName()
    {
        return name;
    }

    public String getVersion()
    {
        return version;
    }

    public String getFileName()
    {
        return "blockpack-" + name + "-" + version + ".zip";
    }

    public URL getUrl()
    {
        try
        {
            return new URL(url);
        }
        catch (MalformedURLException e)
        {
            return null;
        }
    }
}
