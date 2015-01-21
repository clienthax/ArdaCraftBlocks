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

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author dags_ <dags@dags.me>
 */

public class LogHelper
{

    private static LogHelper instance;
    private final File logFile = FileHelper.getOrCreateFile(FileHelper.getConfigFolder(), "log-" + ".txt");
    private final DateFormat dateFormat = new SimpleDateFormat("[dd.MM.yyyy hh:mm:ss] ");
    private Logger logger;

    private LogHelper()
    {
        instance = this;
    }

    public static void setLogger(Logger l)
    {
        if (i().logger == null)
        {
            i().logger = l;
        }
    }

    private static LogHelper i()
    {
        if (instance == null)
        {
            instance = new LogHelper();
        }
        return instance;
    }

    public static void log(String msg)
    {
        i().logger.log(Level.INFO, msg);
        i().write(msg);
    }

    private void write(String msg)
    {
        if (logFile == null)
        {
            return;
        }
        try
        {
            FileWriter f = new FileWriter(logFile, true);
            f.write(dateFormat.format(new Date()) + ": " + msg + "\n");
            f.flush();
            f.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

}
