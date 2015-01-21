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

package me.ardacraft.blocksapi.resourcecontainers;

import com.google.common.eventbus.EventBus;
import cpw.mods.fml.client.FMLFolderResourcePack;
import cpw.mods.fml.common.LoadController;
import cpw.mods.fml.common.MetadataCollection;
import cpw.mods.fml.common.ModContainer;
import cpw.mods.fml.common.ModMetadata;
import cpw.mods.fml.common.versioning.ArtifactVersion;
import cpw.mods.fml.common.versioning.VersionRange;
import me.ardacraft.blocksapi.helper.FileHelper;

import java.io.File;
import java.security.cert.Certificate;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author dags_ <dags@dags.me>
 */

public class Language implements ModContainer
{

    @Override
    public String getModId()
    {
        return "ACBlocks Lang";
    }

    @Override
    public String getName()
    {
        return "ACBlocks Lang";
    }

    @Override
    public String getVersion()
    {
        return "1.0.0";
    }

    @Override
    public File getSource()
    {
        return FileHelper.getConfigFolder();
    }

    @Override
    public ModMetadata getMetadata()
    {
        return null;
    }

    @Override
    public void bindMetadata(MetadataCollection mc)
    {
    }

    @Override
    public void setEnabledState(boolean enabled)
    {

    }

    @Override
    public Set<ArtifactVersion> getRequirements()
    {
        return null;
    }

    @Override
    public List<ArtifactVersion> getDependencies()
    {
        return null;
    }

    @Override
    public List<ArtifactVersion> getDependants()
    {
        return null;
    }

    @Override
    public String getSortingRules()
    {
        return null;
    }

    @Override
    public boolean registerBus(EventBus bus, LoadController controller)
    {
        return false;
    }

    @Override
    public boolean matches(Object mod)
    {
        return false;
    }

    @Override
    public Object getMod()
    {
        return null;
    }

    @Override
    public ArtifactVersion getProcessedVersion()
    {
        return null;
    }

    @Override
    public boolean isImmutable()
    {
        return false;
    }

    @Override
    public String getDisplayVersion()
    {
        return null;
    }

    @Override
    public VersionRange acceptableMinecraftVersionRange()
    {
        return null;
    }

    @Override
    public Certificate getSigningCertificate()
    {
        return null;
    }

    @Override
    public Map<String, String> getCustomModProperties()
    {
        return null;
    }

    @Override
    public Class<?> getCustomResourcePackClass()
    {
        return FMLFolderResourcePack.class;
    }

    @Override
    public Map<String, String> getSharedModDescriptor()
    {
        return null;
    }

    @Override
    public Disableable canBeDisabled()
    {
        return null;
    }

    @Override
    public String getGuiClassName()
    {
        return null;
    }

    @Override
    public List<String> getOwnedPackages()
    {
        return null;
    }
}
