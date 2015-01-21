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

package me.ardacraft.blocksapi.block;

import cpw.mods.fml.common.registry.GameRegistry;
import me.ardacraft.blocksapi.Meta;
import me.ardacraft.blocksapi.blockstate.BlockType;
import me.ardacraft.blocksapi.blockstate.TextureWrapper;
import me.ardacraft.blocksapi.helper.LangHelper;
import me.ardacraft.blocksapi.item.CustomItemDoor;
import me.ardacraft.blocksapi.item.CustomItemPlant;
import net.minecraft.block.Block;

/**
 * @author dags_ <dags@dags.me>
 */

public class ACBlockFactory
{

    public static ACBlock getBlock(Block base, String name, TextureWrapper tw, BlockType type, String tab)
    {
        switch (type)
        {
            case BLOCK:
                return getBaseBlock(base, name, tw);
            case STAIRS:
                return new BaseCustomStair(base, name, tw);
            case FENCE:
                return new CustomFence(base, name, tw);
            case FENCE_GATE:
                return new CustomFenceGate(base, name, tw);
            case GLASS:
                return new CustomGlass(base, name, tw);
            case GLASS_CTM:
                return new CustomGlass(base, name, tw).setCtm(true);
            case STAINED_GLASS:
                return new CustomGlassStained(base, name, tw);
            case STAINED_GLASS_CTM:
                return new CustomGlassStained(base, name, tw).setCtm(true);
            case WALL:
                return new CustomWall(base, name, tw);
            case PISTON_EXTENSION:
                return new CustomPistonExtension(base, name, tw);
            case TRAP_DOOR:
                return new CustomTrapDoor(base, name, tw);
            case GHOST_BLOCK:
                return new CustomGhostBlock(base, name, tw);
            case LOG:
                return new CustomLog(base, name, tw);
            case PILLAR:
                return new BaseCustomDirectional(base, name, tw);
            case PANE:
                return new BaseCustomPane(base, name, tw);
            case STAINED_PANE:
                return new CustomPaneStained(base, name, tw);
            case GHOST_PANE:
                return new CustomGhostPane(base, name, tw);
            case WEB:
                return new CustomWeb(base, name, tw);
            case LADDER:
                return new CustomLadder(base, name, tw);
            case TORCH:
                return new CustomTorch(base, name, tw);
            case TRUNK:
                return new CustomTrunk(base, name, tw);
            case DAY_SENSOR:
                return new CustomDaySensor(base, name, tw);
            case DOOR:
                BaseCustomDoor door = new CustomDoor(base, name, tw);
                CustomItemDoor itemDoor = new CustomItemDoor(door);
                if (Meta.CLIENT)
                {
                    itemDoor.setCreativeTab(Meta.getTab(tab));
                }
                GameRegistry.registerItem(itemDoor, door.getUnlocalizedName());
                LangHelper.addTranslation(itemDoor.getUnlocalizedName());
                return door;
            case HALF_DOOR:
                CustomHalfDoor halfDoor = new CustomHalfDoor(base, name, tw);
                CustomItemDoor itemHalfDoor = new CustomItemDoor(halfDoor);
                if (Meta.CLIENT)
                {
                    itemHalfDoor.setCreativeTab(Meta.getTab(tab));
                }
                GameRegistry.registerItem(itemHalfDoor, halfDoor.getUnlocalizedName());
                LangHelper.addTranslation(itemHalfDoor.getUnlocalizedName());
                return halfDoor;
            case PLATE:
                return new CustomPressurePlate(base, name, tw);
            case ICE:
                return new CustomIce(base, name, tw);
            case ANVIL:
                return new CustomAnvil(base, name, tw);
            case CARPET:
                return new CustomCarpet(base, name, tw);
            case CAULDRON:
                return new CustomCauldron(base, name, tw);
            case CROPS:
                return new CustomCrops(base, name, tw);
            case PLANT:
                return new CustomPlant(base, name, tw);
            case SLIM_SLAB:
                return new CustomSlimSlab(base, name, tw);
            case LIGHT_WEB:
                return new CustomLightWeb(base, name, tw);
            case POT:
                return new CustomPot(base, name, tw);
            case BUTTON:
                return new CustomButton(base, name, tw);
            case FURNACE:
                return new CustomFurnace(base, name, tw);
            case SHORT_CHAIR:
                return new CustomShortChair(base, name, tw);
            case CHAIR:
                return new CustomChair(base, name, tw);
            case DOUBLE_PLANT:
                CustomTallPlant tallPlant = new CustomTallPlant(base, name, tw);
                CustomItemPlant plant = new CustomItemPlant(tallPlant);
                if (Meta.CLIENT)
                {
                    plant.setCreativeTab(Meta.getTab(tab));
                }
                LangHelper.addTranslation(plant.getUnlocalizedName());
                GameRegistry.registerItem(plant, tallPlant.getUnlocalizedName());
                return tallPlant;
            default:
                return null;
        }
    }

    public static ACBlock getBaseBlock(Block b, String name, TextureWrapper tw)
    {
        return new BaseCustomBlock(b, name, tw);
    }

}
