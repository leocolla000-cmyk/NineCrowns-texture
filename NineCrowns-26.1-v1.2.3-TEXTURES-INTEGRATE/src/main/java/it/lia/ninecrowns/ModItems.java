package it.lia.ninecrowns;
import net.minecraft.core.*;
import net.minecraft.core.component.*;
import net.minecraft.core.registries.*;
import net.minecraft.resources.*;
import net.minecraft.world.item.*;
import net.minecraft.world.item.component.*;
import net.minecraft.world.item.equipment.*;
import net.minecraft.world.item.enchantment.*;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.*;
import java.util.function.Function;

public final class ModItems {
 public static Item SWORD, SPEAR, EMPTY_CROWN, CROWN;
 private static Item register(String name, Function<Item.Properties,Item> factory){
  var id=NineCrowns.id(name);var p=new Item.Properties().setId(ResourceKey.create(Registries.ITEM,id));
  p.rarity(Rarity.EPIC).stacksTo(1);
  return Registry.register(BuiltInRegistries.ITEM,id,factory.apply(p));
 }
 public static void init(){
  SWORD=register("op_sword",p->new Item(p.sword(ToolMaterial.NETHERITE,6,-2.4f).fireResistant().attributes(weapon(10,-2.4))));
  SPEAR=register("op_spear",p->new SpearItem(p.spear(ToolMaterial.NETHERITE,1.05f,1.075f,.5f,3f,10f,6.5f,5.1f,10f,4.6f).attributes(weapon(9,-3)).component(DataComponents.MINIMUM_ATTACK_CHARGE,1f)));
  EMPTY_CROWN=register("empty_crown",p->new Item(p.humanoidArmor(ArmorMaterials.GOLD,ArmorType.HELMET).component(DataComponents.EQUIPPABLE,crownEquipment("empty_crown")).rarity(Rarity.RARE)));
  CROWN=register("emperor_crown",p->new Item(p.humanoidArmor(ArmorMaterials.NETHERITE,ArmorType.HELMET).fireResistant().component(DataComponents.EQUIPPABLE,crownEquipment("emperor_crown"))));
 }
 private static Equippable crownEquipment(String asset){return Equippable.builder(EquipmentSlot.HEAD).setAsset(ResourceKey.create(EquipmentAssets.ROOT_ID,NineCrowns.id(asset))).build();}
 private static ItemAttributeModifiers weapon(double damage,double speed){
  return ItemAttributeModifiers.builder()
   .add(Attributes.ATTACK_DAMAGE,new AttributeModifier(Item.BASE_ATTACK_DAMAGE_ID,damage,AttributeModifier.Operation.ADD_VALUE),EquipmentSlotGroup.MAINHAND)
   .add(Attributes.ATTACK_SPEED,new AttributeModifier(Item.BASE_ATTACK_SPEED_ID,speed,AttributeModifier.Operation.ADD_VALUE),EquipmentSlotGroup.MAINHAND).build();
 }
 public static ItemStack enchanted(Item item,RegistryAccess access){
  var stack=new ItemStack(item);var ench=access.lookupOrThrow(Registries.ENCHANTMENT);
  if(item==SWORD)stack.set(DataComponents.ATTRIBUTE_MODIFIERS,weapon(NineCrowns.config.swordDamage-4,-2.4));
  if(item==SPEAR)stack.set(DataComponents.ATTRIBUTE_MODIFIERS,weapon(NineCrowns.config.jabDamage-1,20.0/NineCrowns.config.jabCooldownTicks-4));
  if(item==SWORD)stack.enchant(ench.getOrThrow(Enchantments.SHARPNESS),5);
  if(item==SPEAR)stack.enchant(ench.getOrThrow(Enchantments.LUNGE),5);
  if(item==CROWN){stack.enchant(ench.getOrThrow(Enchantments.PROTECTION),8);stack.enchant(ench.getOrThrow(Enchantments.UNBREAKING),3);stack.enchant(ench.getOrThrow(Enchantments.MENDING),1);}
  return stack;
 }
}
