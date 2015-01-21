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

package me.ardacraft.blocksapi.commands;

import net.minecraft.client.Minecraft;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;

import java.util.List;

/**
 * @author dags_ <dags@dags.me>
 */

public class EjectCommand implements ICommand
{

    @Override
    public int compareTo(Object o)
    {
        return 0;
    }

    @Override
    public String getCommandName()
    {
        return "eject";
    }

    @Override
    public String getCommandUsage(ICommandSender p_71518_1_)
    {
        return "/eject";
    }

    @Override
    public List getCommandAliases()
    {
        return null;
    }

    @Override
    public void processCommand(ICommandSender cs, String[] a)
    {
        EntityPlayer ep = Minecraft.getMinecraft().thePlayer;
        if (ep.ridingEntity != null)
        {
            final Entity e = ep.ridingEntity;
            if (e.riddenByEntity.getEntityId() == ep.getEntityId())
            {
                ep.dismountEntity(ep.ridingEntity);
                e.riddenByEntity = null;
                e.worldObj.removeEntity(e);
            }
        }
    }

    @Override
    public boolean canCommandSenderUseCommand(ICommandSender p_71519_1_)
    {
        return true;
    }

    @Override
    public List addTabCompletionOptions(ICommandSender c, String[] a)
    {
        return null;
    }

    @Override
    public boolean isUsernameIndex(String[] p_82358_1_, int p_82358_2_)
    {
        return false;
    }
}
