package me.ardacraft.blocksapi.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import me.ardacraft.blocksapi.blockstate.TextureWrapper;
import me.ardacraft.blocksapi.entity.EntityCustomPainting;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityHanging;
import net.minecraft.entity.item.EntityPainting;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Direction;
import net.minecraft.world.World;

/**
 * Created by clienthax on 18/5/2015.
 */
public class CustomItemPainting extends Item {

	TextureWrapper textureWrapper;

	public CustomItemPainting(String name, TextureWrapper textures) {
		setUnlocalizedName(name);
		setTextureName("painting");
		textureWrapper = textures;
	}

	@Override
	public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
		if (side == 0)
		{
			return false;
		}
		else if (side == 1)
		{
			return false;
		}
		else
		{
			int i1 = Direction.facingToDirection[side];
			EntityCustomPainting entityhanging = new EntityCustomPainting(world, x, y, z, i1, textureWrapper);

			if (!player.canPlayerEdit(x, y, z, side, stack))
			{
				return false;
			}
			else
			{
				if(1 == 1)//if (entityhanging.onValidSurface())
				{
					if (!world.isRemote)
					{
						world.spawnEntityInWorld(entityhanging);
					}

					--stack.stackSize;
				}

				return true;
			}
		}
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void registerIcons(IIconRegister register)
	{
		super.registerIcons(register);
		textureWrapper.registerIcons(register);
	}

}
