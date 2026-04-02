/*     */ package lumine.xikage.mythicmobs.drops;
/*     */ 
/*     */ import io.lumine.utils.collections.AWeightedItem;
/*     */ import io.lumine.utils.collections.WeightedCollection;
/*     */ import io.lumine.xikage.mythicmobs.MythicMobs;
/*     */ import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
/*     */ import io.lumine.xikage.mythicmobs.drops.Drop;
/*     */ import io.lumine.xikage.mythicmobs.drops.DropMetadata;
/*     */ import io.lumine.xikage.mythicmobs.drops.LootBag;
/*     */ import io.lumine.xikage.mythicmobs.io.MythicConfig;
/*     */ import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
/*     */ import io.lumine.xikage.mythicmobs.logging.MythicLogger;
/*     */ import io.lumine.xikage.mythicmobs.skills.SkillCaster;
/*     */ import io.lumine.xikage.mythicmobs.skills.SkillCondition;
/*     */ import io.lumine.xikage.mythicmobs.util.types.RandomDouble;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ 
/*     */ public class DropTable {
/*     */   private final String fileName;
/*     */   
/*  22 */   public String getFileName() { return this.fileName; } private final String internalName; public String getInternalName() {
/*  23 */     return this.internalName;
/*     */   }
/*  25 */   protected List<SkillCondition> conditions = new ArrayList<>();
/*  26 */   protected List<SkillCondition> conditionsTrigger = new ArrayList<>();
/*     */   
/*  28 */   private WeightedCollection<Drop> drops = new WeightedCollection();
/*  29 */   private int minItems = -1; private int maxItems = -1;
/*  30 */   private RandomDouble bonusLevelItems = new RandomDouble("0"); private RandomDouble bonusLuckItems = new RandomDouble("0");
/*     */   
/*     */   private boolean hasConditions = false;
/*     */   
/*  34 */   private List<MythicEquipable> equipablesList = new ArrayList<>();
/*     */   
/*     */   public DropTable(String file, String name, MythicConfig mc) {
/*  37 */     this.fileName = file;
/*  38 */     this.internalName = name;
/*     */     
/*  40 */     int totalItems = mc.getInteger("TotalItems", -1);
/*  41 */     this.maxItems = mc.getInteger("MaxItems", totalItems);
/*  42 */     this.minItems = mc.getInteger("MinItems", totalItems);
/*  43 */     this.bonusLevelItems = new RandomDouble(mc.getString("BonusLevelItems", "0"));
/*  44 */     this.bonusLuckItems = new RandomDouble(mc.getString("BonusLuckItems", "0"));
/*     */     
/*  46 */     List<String> strDrops = mc.getStringList("Drops");
/*     */     
/*  48 */     MythicMobs.inst().getDropManager().queueSecondPass(() -> {
/*     */           for (String s : paramList) {
/*     */             s = MythicLineConfig.unparseBlock(s);
/*     */ 
/*     */             
/*     */             Drop drop = Drop.getDrop(paramString, s);
/*     */ 
/*     */             
/*     */             if (!(drop instanceof io.lumine.xikage.mythicmobs.drops.InvalidDrop)) {
/*     */               this.drops.add((AWeightedItem)drop);
/*     */             }
/*     */           } 
/*     */         });
/*     */     
/*  62 */     List<String> nTConditions = mc.getStringList("Conditions");
/*  63 */     for (String s : nTConditions) {
/*  64 */       s = MythicLineConfig.unparseBlock(s);
/*  65 */       SkillCondition sc = SkillCondition.getCondition(s);
/*  66 */       if (!(sc instanceof io.lumine.xikage.mythicmobs.skills.conditions.InvalidCondition)) {
/*  67 */         this.conditions.add(sc);
/*     */       }
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  74 */     nTConditions = mc.getStringList("TriggerConditions");
/*  75 */     for (String s : nTConditions) {
/*  76 */       s = MythicLineConfig.unparseBlock(s);
/*  77 */       SkillCondition sc = SkillCondition.getCondition(s);
/*  78 */       if (!(sc instanceof io.lumine.xikage.mythicmobs.skills.conditions.InvalidCondition)) {
/*  79 */         this.conditionsTrigger.add(sc);
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   public DropTable(String file, String name, List<String> drops) {
/*  85 */     this(file, name, drops, false);
/*     */   }
/*     */   
/*     */   public DropTable(String file, String name, List<String> drops, boolean delayed) {
/*  89 */     this.fileName = file;
/*  90 */     this.internalName = name;
/*     */     
/*  92 */     if (delayed) {
/*  93 */       MythicMobs.inst().getDropManager().queueSecondPass(() -> {
/*     */             for (String s : paramList) {
/*     */               s = MythicLineConfig.unparseBlock(s);
/*     */               
/*     */               Drop drop = Drop.getDrop(paramString, s);
/*     */               if (!(drop instanceof io.lumine.xikage.mythicmobs.drops.InvalidDrop)) {
/*     */                 this.drops.add((AWeightedItem)drop);
/*     */               }
/*     */             } 
/*     */           });
/*     */     } else {
/* 104 */       for (String s : drops) {
/* 105 */         s = MythicLineConfig.unparseBlock(s);
/* 106 */         Drop drop = Drop.getDrop(file, s);
/*     */         
/* 108 */         if (!(drop instanceof io.lumine.xikage.mythicmobs.drops.InvalidDrop)) {
/* 109 */           this.drops.add((AWeightedItem)drop);
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean hasDrops() {
/* 116 */     return (this.drops.size() > 0);
/*     */   }
/*     */   
/*     */   public LootBag generate(DropMetadata meta) {
/* 120 */     MythicLogger.debug(MythicLogger.DebugLevel.SKILL, "Generating LootBag", new Object[0]);
/* 121 */     LootBag drops = new LootBag(meta);
/*     */     
/* 123 */     if (this.conditions.size() > 0) {
/* 124 */       for (SkillCondition mc : this.conditions) {
/* 125 */         if (!mc.evaluateDropper(meta)) {
/* 126 */           return drops;
/*     */         }
/*     */       } 
/*     */     }
/* 130 */     if (this.conditionsTrigger.size() > 0) {
/* 131 */       for (SkillCondition mc : this.conditionsTrigger) {
/* 132 */         if (!mc.evaluateDropCause(meta)) {
/* 133 */           return drops;
/*     */         }
/*     */       } 
/*     */     }
/*     */     
/* 138 */     int amountModifiers = 0;
/* 139 */     double bonusLevelMod = this.bonusLevelItems.get();
/* 140 */     double bonusLuckMod = this.bonusLuckItems.get();
/*     */     
/* 142 */     if (bonusLevelMod != 0.0D) {
/* 143 */       MythicLogger.debug(MythicLogger.DebugLevel.SKILL_CHECK, "BonusLevelMod: {0} - Mob Level: {1}", new Object[] { Double.valueOf(bonusLuckMod), Integer.valueOf(((SkillCaster)meta.getDropper().get()).getLevel()) });
/* 144 */       amountModifiers += meta.getDropper().isPresent() ? (int)(((SkillCaster)meta.getDropper().get()).getLevel() * bonusLevelMod) : 0;
/*     */     } 
/* 146 */     if (bonusLuckMod != 0.0D) {
/* 147 */       MythicLogger.debug(MythicLogger.DebugLevel.SKILL_CHECK, "BonusLuckMod: {0} - Killer Luck: {1}", new Object[] { Double.valueOf(bonusLuckMod), Integer.valueOf(((AbstractEntity)meta.getCause().get()).getLuck()) });
/* 148 */       amountModifiers += meta.getCause().isPresent() ? (int)(((AbstractEntity)meta.getCause().get()).getLuck() * bonusLuckMod) : 0;
/*     */     } 
/*     */     
/* 151 */     if (this.minItems > 0 && this.maxItems > 0) {
/*     */       
/* 153 */       int amount, diff = this.maxItems - this.minItems;
/*     */       
/* 155 */       if (diff > 0) {
/* 156 */         amount = MythicMobs.r.nextInt(diff) + this.minItems + amountModifiers;
/*     */       } else {
/* 158 */         amount = this.maxItems + amountModifiers;
/*     */       } 
/* 160 */       MythicLogger.debug(MythicLogger.DebugLevel.SKILL_INFO, "Amount (Rand {1} to {2}): {0}", new Object[] { Integer.valueOf(amount), Integer.valueOf(this.minItems), Integer.valueOf(this.maxItems) });
/* 161 */       Collection<Drop> d = this.drops.get(amount);
/*     */       
/* 163 */       for (Drop drop : d) {
/* 164 */         if (!handleDrop(meta, drops, drop)) return drops; 
/*     */       } 
/* 166 */     } else if (this.minItems > 0) {
/*     */       
/* 168 */       int amount, diff = this.drops.size() - this.minItems;
/*     */       
/* 170 */       if (diff > 0) {
/* 171 */         amount = MythicMobs.r.nextInt(diff) + this.minItems + amountModifiers;
/*     */       } else {
/* 173 */         amount = this.minItems + amountModifiers;
/*     */       } 
/* 175 */       MythicLogger.debug(MythicLogger.DebugLevel.SKILL_INFO, "Amount (Min {1}): {0}", new Object[] { Integer.valueOf(amount), Integer.valueOf(this.minItems) });
/* 176 */       for (Drop drop : this.drops.get(amount)) {
/* 177 */         if (!handleDrop(meta, drops, drop)) return drops; 
/*     */       } 
/* 179 */     } else if (this.maxItems > 0) {
/* 180 */       int items = 0 - amountModifiers;
/* 181 */       for (Drop drop : this.drops.getView()) {
/* 182 */         if (drop.rollChance()) {
/* 183 */           items++;
/* 184 */           if (!handleDrop(meta, drops, drop)) return drops; 
/*     */         } 
/* 186 */         if (items >= this.maxItems)
/*     */           break; 
/* 188 */       }  MythicLogger.debug(MythicLogger.DebugLevel.SKILL_INFO, "Amount (Max {1}): {0}", new Object[] { Integer.valueOf(items), Integer.valueOf(this.maxItems) });
/*     */     } else {
/* 190 */       for (Drop drop : this.drops.getView()) {
/* 191 */         if (drop.rollChance() && 
/* 192 */           !handleDrop(meta, drops, drop)) return drops;
/*     */       
/*     */       } 
/*     */     } 
/* 196 */     return drops;
/*     */   }
/*     */   
/*     */   private boolean handleDrop(DropMetadata meta, LootBag drops, Drop drop) {
/* 200 */     drop.rollAmount(meta);
/* 201 */     if (drop instanceof IMultiDrop) {
/* 202 */       if (meta.tick() > 100) {
/* 203 */         return false;
/*     */       }
/* 205 */       for (int i = 0; i < drop.getAmount(); i++) {
/* 206 */         drops.add(meta, ((IMultiDrop)drop).get(meta));
/*     */       }
/*     */     } else {
/* 209 */       MythicLogger.debug(MythicLogger.DebugLevel.SKILL_INFO, "Handling Item {0}", new Object[] { drop.getLine() });
/* 210 */       drops.add(meta, drop);
/*     */     } 
/* 212 */     return true;
/*     */   }
/*     */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\drops\DropTable.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */