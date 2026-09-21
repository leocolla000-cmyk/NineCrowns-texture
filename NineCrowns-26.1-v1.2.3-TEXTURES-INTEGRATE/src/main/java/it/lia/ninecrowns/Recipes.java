package it.lia.ninecrowns;
import java.util.*;
import net.minecraft.world.item.*;
import net.minecraft.core.component.DataComponents;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.item.crafting.CraftingInput;
public final class Recipes {
 public static Item resultForCenter(ItemStack s){
  if(s.is(Items.NETHERITE_SWORD))return ModItems.SWORD;
  if(s.is(Items.NETHERITE_SPEAR))return ModItems.SPEAR;
  if(s.is(ModItems.EMPTY_CROWN))return ModItems.CROWN;
  return null;
 }
 public static boolean opShape(CraftingInput in){
  if(in.width()!=3||in.height()!=3||resultForCenter(in.getItem(4))==null)return false;
  for(int i=0;i<9;i++)if(i!=4&&!in.getItem(i).is(Items.PLAYER_HEAD))return false;
  return true;
 }
 public static ItemStack craft(ServerPlayer player,CraftingContainer grid){
  if(grid.getWidth()!=3||grid.getHeight()!=3)return ItemStack.EMPTY;
  Item result=resultForCenter(grid.getItem(4));if(result==null)return ItemStack.EMPTY;
  List<UUID> heads=new ArrayList<>();
  for(int i=0;i<9;i++){
   if(i==4)continue;var stack=grid.getItem(i);if(!stack.is(Items.PLAYER_HEAD))return ItemStack.EMPTY;
   var profile=stack.get(DataComponents.PROFILE);if(profile==null)return ItemStack.EMPTY;
   heads.add(profile.partialProfile().id());
  }
  if(NineCrowns.data==null||!NineCrowns.data.roster.validHeads(player.getUUID(),heads))return ItemStack.EMPTY;
  return ModItems.enchanted(result,player.registryAccess());
 }
}
