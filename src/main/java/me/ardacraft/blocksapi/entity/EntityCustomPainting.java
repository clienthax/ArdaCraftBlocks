package me.ardacraft.blocksapi.entity;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import me.ardacraft.blocksapi.blockstate.TextureWrapper;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityHanging;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

/**
 * Created by clienthax on 18/5/2015.
 */
public class EntityCustomPainting extends EntityHanging {

	TextureWrapper textureWrapper;

	public EntityCustomPainting(World world, int x, int y, int z, int side, TextureWrapper textureWrapper) {
		super(world, x, y, z, side);
		this.textureWrapper = textureWrapper;
	}

	public ResourceLocation getResource() {
		return new ResourceLocation(textureWrapper.getDefault());
	}

	@Override
	public int getWidthPixels() {
		return textureWrapper.getIdefault().getIconWidth();
	}

	@Override
	public int getHeightPixels() {
		return textureWrapper.getIdefault().getIconHeight();
	}

	@Override
	public void onBroken(Entity painting) {

	}

	@Override
	public boolean onValidSurface() {
		return true;
	}

}
