/*     */ package lumine.xikage.mythicmobs.skills.conditions.all;
/*     */ 
/*     */ import io.lumine.xikage.mythicmobs.MythicMobs;
/*     */ import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
/*     */ import io.lumine.xikage.mythicmobs.adapters.bukkit.BukkitItemStack;
/*     */ import io.lumine.xikage.mythicmobs.drops.EquipSlot;
/*     */ import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
/*     */ import io.lumine.xikage.mythicmobs.items.MythicItem;
/*     */ import io.lumine.xikage.mythicmobs.logging.MythicLogger;
/*     */ import io.lumine.xikage.mythicmobs.skills.SkillCondition;
/*     */ import io.lumine.xikage.mythicmobs.skills.conditions.IEntityCondition;
/*     */ import io.lumine.xikage.mythicmobs.util.annotations.MythicCondition;
/*     */ import io.lumine.xikage.mythicmobs.util.annotations.MythicField;
/*     */ import java.util.Optional;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.entity.LivingEntity;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ import org.bukkit.inventory.meta.ItemMeta;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @MythicCondition(author = "jaylawl", name = "wearing", aliases = {"iswearing", "wielding", "iswielding"}, version = "4.5", description = "Tests what the target entity has equipped.")
/*     */ public class WearingCondition
/*     */   extends SkillCondition
/*     */   implements IEntityCondition
/*     */ {
/*     */   @MythicField(name = "armorslot", aliases = {"slot", "s"}, description = "The item slot to check")
/*     */   private EquipSlot slot;
/*     */   @MythicField(name = "material", aliases = {"mmitem", "m"}, description = "A material or MythicItem name to check for")
/*     */   private ItemStack item;
/*     */   @MythicField(name = "checklore", aliases = {"cl"}, description = "Whether to strictly match item lore")
/*     */   private boolean checklore;
/*     */   
/*     */   public WearingCondition(String line, MythicLineConfig mlc) {
/*  36 */     super(line);
/*     */     
/*  38 */     String s = mlc.getString(new String[] { "armorslot", "slot", "s" }, "HEAD", new String[0]).toUpperCase();
/*  39 */     String item = mlc.getString(new String[] { "material", "mat", "m", "mythicmobsitem", "mmitem", "mmi", "item" }, "DIRT", new String[] { this.conditionVar });
/*  40 */     this.checklore = mlc.getBoolean(new String[] { "checklore", "cl" }, false);
/*     */     
/*  42 */     this.slot = EquipSlot.of(s);
/*     */     
/*  44 */     Optional<MythicItem> maybeItem = MythicMobs.inst().getItemManager().getItem(item);
/*     */     
/*  46 */     if (maybeItem.isPresent()) {
/*  47 */       this.item = ((BukkitItemStack)((MythicItem)maybeItem.get()).generateItemStack(1)).build();
/*     */     } else {
/*     */       try {
/*  50 */         this.item = new ItemStack(Material.valueOf(item.toUpperCase()));
/*  51 */       } catch (Exception ex) {
/*  52 */         this.item = new ItemStack(Material.DIRT);
/*  53 */         MythicLogger.errorConditionConfig(this, mlc, "Item Type not found (or not supported by this version of MythicMobs");
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean check(AbstractEntity e) {
/*  60 */     MythicLogger.debug(MythicLogger.DebugLevel.CONDITION, "Checking WEARING Condition...", new Object[0]);
/*  61 */     ItemStack slotitem = null;
/*     */     
/*  63 */     if (e.isLiving()) {
/*  64 */       switch (null.$SwitchMap$io$lumine$xikage$mythicmobs$drops$EquipSlot[this.slot.ordinal()]) {
/*     */         case 1:
/*  66 */           slotitem = ((LivingEntity)e.getBukkitEntity()).getEquipment().getHelmet();
/*     */           break;
/*     */         
/*     */         case 2:
/*  70 */           slotitem = ((LivingEntity)e.getBukkitEntity()).getEquipment().getChestplate();
/*     */           break;
/*     */         
/*     */         case 3:
/*  74 */           slotitem = ((LivingEntity)e.getBukkitEntity()).getEquipment().getLeggings();
/*     */           break;
/*     */         
/*     */         case 4:
/*  78 */           slotitem = ((LivingEntity)e.getBukkitEntity()).getEquipment().getBoots();
/*     */           break;
/*     */         
/*     */         case 5:
/*  82 */           slotitem = ((LivingEntity)e.getBukkitEntity()).getEquipment().getItemInMainHand();
/*     */           break;
/*     */         
/*     */         case 6:
/*  86 */           slotitem = ((LivingEntity)e.getBukkitEntity()).getEquipment().getItemInOffHand();
/*     */           break;
/*     */         
/*     */         case 7:
/*  90 */           slotitem = null;
/*  91 */           MythicLogger.debug(MythicLogger.DebugLevel.CONDITION, "! Invalid slot used: {0}", new Object[] { this.slot });
/*     */           break;
/*     */       } 
/*     */ 
/*     */       
/*  96 */       if (slotitem == null) {
/*  97 */         MythicLogger.debug(MythicLogger.DebugLevel.CONDITION, "! Slot item was null, returning false", new Object[0]);
/*  98 */         return false;
/*     */       } 
/*     */       
/* 101 */       if (!this.item.getType().equals(slotitem.getType())) {
/* 102 */         MythicLogger.debug(MythicLogger.DebugLevel.CONDITION, "! Type doesn't match", new Object[0]);
/* 103 */         return false;
/*     */       } 
/*     */       
/* 106 */       if (this.item.hasItemMeta() != slotitem.hasItemMeta()) {
/* 107 */         MythicLogger.debug(MythicLogger.DebugLevel.CONDITION, "! Meta state doesn't match", new Object[0]);
/* 108 */         return false;
/*     */       } 
/*     */       
/* 111 */       if (this.item.hasItemMeta()) {
/* 112 */         ItemMeta meta = this.item.getItemMeta();
/* 113 */         ItemMeta meta2 = slotitem.getItemMeta();
/*     */         
/* 115 */         if (meta.hasDisplayName() != meta2.hasDisplayName()) {
/* 116 */           MythicLogger.debug(MythicLogger.DebugLevel.CONDITION, "! Display doesn't match", new Object[0]);
/* 117 */           return false;
/*     */         } 
/*     */         
/* 120 */         if (meta.hasDisplayName() && !meta.getDisplayName().equals(meta2.getDisplayName())) {
/* 121 */           MythicLogger.debug(MythicLogger.DebugLevel.CONDITION, "! Display doesn't match", new Object[0]);
/* 122 */           return false;
/*     */         } 
/*     */         
/* 125 */         if (getPlugin().getMinecraftVersion() >= 14) {
/*     */           
/* 127 */           if (meta.hasCustomModelData() != meta2.hasCustomModelData()) {
/* 128 */             MythicLogger.debug(MythicLogger.DebugLevel.CONDITION, "! CustomModelData doesn't match", new Object[0]);
/* 129 */             return false;
/*     */           } 
/*     */           
/* 132 */           if (meta.hasCustomModelData() && meta.getCustomModelData() != meta2.getCustomModelData()) {
/* 133 */             MythicLogger.debug(MythicLogger.DebugLevel.CONDITION, "! CustomModelData doesn't match", new Object[0]);
/* 134 */             return false;
/*     */           } 
/*     */         } 
/*     */       } 
/* 138 */       MythicLogger.debug(MythicLogger.DebugLevel.CONDITION, "+ Item matches, returning true", new Object[0]);
/* 139 */       return true;
/*     */     } 
/*     */     
/* 142 */     return false;
/*     */   }
/*     */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\skills\conditions\all\WearingCondition.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */