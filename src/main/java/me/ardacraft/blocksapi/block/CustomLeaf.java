package me.ardacraft.blocksapi.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import me.ardacraft.blocksapi.Meta;
import me.ardacraft.blocksapi.blockstate.TextureWrapper;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLeavesBase;
import net.minecraft.client.renderer.IconFlipped;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.Facing;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class CustomLeaf extends BlockLeavesBase implements ACBlock
{

	@SideOnly(Side.CLIENT)
	private TextureWrapper textures;
	@SideOnly(Side.CLIENT)
	private IIcon flipped;
	@SideOnly(Side.CLIENT)
	protected boolean ctm;

	public CustomLeaf(Block b, String name, TextureWrapper tw)
	{
		super(b.getMaterial(), true);
		this.setBlockName(name + "_Leaf");
		this.setStepSound(b.stepSound);
		if (Meta.CLIENT)
		{
			textures = tw;
			ctm = false;
		}
	}

	@Override
	public Block toBlock()
	{
		return this;
	}

	@SideOnly(Side.CLIENT)
	public IIcon getIcon(IBlockAccess ib, int x, int y, int z, int side)
	{
		return getIcon(side, 0);
	}

	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta)
	{
		if (!ctm && (side == 4 || side == 3))
		{
			return flipped;
		}
		return textures.getIcon(side);
	}

	@SideOnly(Side.CLIENT)
	public IIcon func_149735_b(int side, int p_149735_2_)
	{
		return getIcon(side, p_149735_2_);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void registerBlockIcons(IIconRegister register)
	{
		textures.registerIcons(register);
		flipped = new IconFlipped(register.registerIcon(textures.getDefault()), true, false);
	}

	@Override
	public boolean canPlaceBlockAt(World p_149742_1_, int p_149742_2_, int p_149742_3_, int p_149742_4_)
	{
		return true;
	}

	public ACBlock setCtm(boolean b)
	{
		if (Meta.CLIENT)
			ctm = b;
		return this;
	}

}
