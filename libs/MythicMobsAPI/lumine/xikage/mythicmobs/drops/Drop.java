/*     */ package lumine.xikage.mythicmobs.drops;
/*     */ 
/*     */ import io.lumine.utils.collections.AWeightedItem;
/*     */ import io.lumine.xikage.mythicmobs.MythicMobs;
/*     */ import io.lumine.xikage.mythicmobs.drops.DropMetadata;
/*     */ import io.lumine.xikage.mythicmobs.drops.droppables.CustomDrop;
/*     */ import io.lumine.xikage.mythicmobs.drops.droppables.DropTableDrop;
/*     */ import io.lumine.xikage.mythicmobs.drops.droppables.ExperienceDrop;
/*     */ import io.lumine.xikage.mythicmobs.drops.droppables.HeroesExperienceDrop;
/*     */ import io.lumine.xikage.mythicmobs.drops.droppables.McMMODrop;
/*     */ import io.lumine.xikage.mythicmobs.drops.droppables.MythicDropsDrop;
/*     */ import io.lumine.xikage.mythicmobs.drops.droppables.MythicItemDrop;
/*     */ import io.lumine.xikage.mythicmobs.drops.droppables.NothingDrop;
/*     */ import io.lumine.xikage.mythicmobs.drops.droppables.PhatLootsDrop;
/*     */ import io.lumine.xikage.mythicmobs.drops.droppables.SkillAPIDrop;
/*     */ import io.lumine.xikage.mythicmobs.drops.droppables.VaultDrop;
/*     */ import io.lumine.xikage.mythicmobs.io.ConfigManager;
/*     */ import io.lumine.xikage.mythicmobs.io.GenericConfig;
/*     */ import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
/*     */ import io.lumine.xikage.mythicmobs.items.LegacyItemConverter;
/*     */ import io.lumine.xikage.mythicmobs.logging.MythicLogger;
/*     */ import io.lumine.xikage.mythicmobs.skills.placeholders.parsers.PlaceholderDouble;
/*     */ import io.lumine.xikage.mythicmobs.skills.placeholders.parsers.RandomDouble;
/*     */ import io.lumine.xikage.mythicmobs.skills.placeholders.parsers.StaticDouble;
/*     */ import io.lumine.xikage.mythicmobs.util.MathParser;
/*     */ import io.lumine.xikage.mythicmobs.util.types.RandomDouble;
/*     */ import org.bukkit.Material;
/*     */ 
/*     */ public abstract class Drop extends AWeightedItem implements Cloneable {
/*     */   private final String line;
/*     */   
/*  32 */   public String getLine() { return this.line; } private final GenericConfig config; public GenericConfig getConfig() {
/*  33 */     return this.config;
/*     */   }
/*  35 */   protected String dropVar = null; public String getDropVar() { return this.dropVar; }
/*  36 */    private PlaceholderDouble dropAmount = (PlaceholderDouble)new StaticDouble("1");
/*     */   
/*  38 */   private double amount = 0.0D; public double getAmount() { return this.amount; } public void setAmount(double amount) { this.amount = amount; }
/*     */   
/*     */   public Drop(String line, MythicLineConfig config) {
/*  41 */     this.line = line;
/*  42 */     this.config = (GenericConfig)config;
/*     */     
/*     */     try {
/*  45 */       String[] split = line.split(" ");
/*     */       
/*  47 */       if (split[0].contains(":")) {
/*  48 */         String[] split2 = split[0].split(":");
/*  49 */         this.dropVar = split2[1];
/*     */       } 
/*     */       
/*  52 */       if (split.length == 2) {
/*  53 */         if (RandomDouble.matches(split[1]) || split[1].startsWith("'")) {
/*  54 */           this.dropAmount = PlaceholderDouble.of(split[1]);
/*     */         } else {
/*  56 */           this.dropVar = split[1];
/*     */         } 
/*  58 */       } else if (split.length == 3) {
/*  59 */         if (RandomDouble.matches(split[1]) || split[1].startsWith("'")) {
/*  60 */           this.dropAmount = PlaceholderDouble.of(split[1]);
/*  61 */           this.weight = MathParser.evalChance(split[2]);
/*     */         } else {
/*  63 */           this.dropVar = split[1];
/*  64 */           this.dropAmount = PlaceholderDouble.of(split[2]);
/*     */         } 
/*  66 */       } else if (split.length >= 4) {
/*  67 */         this.dropVar = split[1];
/*  68 */         this.dropAmount = PlaceholderDouble.of(split[2]);
/*  69 */         this.weight = MathParser.evalChance(split[3]);
/*     */       } 
/*  71 */     } catch (Exception ex) {
/*  72 */       MythicLogger.errorDropConfig(this, config, "Invalid Syntax");
/*  73 */       if (ConfigManager.debugLevel > 0) {
/*  74 */         ex.printStackTrace();
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Drop(String line, MythicLineConfig config, double amount) {
/*  82 */     this(line, config);
/*  83 */     this.dropAmount = (PlaceholderDouble)new StaticDouble(amount);
/*     */   }
/*     */   
/*     */   public Drop(String line, MythicLineConfig config, RandomDouble amount) {
/*  87 */     this(line, config);
/*  88 */     this.dropAmount = (PlaceholderDouble)new RandomDouble(amount);
/*     */   }
/*     */   
/*     */   public boolean rollChance() {
/*  92 */     if (this.weight != 1.0D && MythicMobs.r.nextFloat() > this.weight) {
/*  93 */       return false;
/*     */     }
/*  95 */     return true;
/*     */   }
/*     */   
/*     */   public void rollAmount(DropMetadata data) {
/*  99 */     this.amount = this.dropAmount.get((PlaceholderMeta)data);
/*     */   }
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 104 */     return getClass().getName().hashCode();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean equals(Object o) {
/* 109 */     if (o == null) return false; 
/* 110 */     return o.getClass().equals(getClass());
/*     */   }
/*     */ 
/*     */   
/*     */   public io.lumine.xikage.mythicmobs.drops.Drop addAmount(io.lumine.xikage.mythicmobs.drops.Drop other) {
/* 115 */     this.amount += other.getAmount();
/*     */     
/* 117 */     return clone();
/*     */   }
/*     */ 
/*     */   
/*     */   public static io.lumine.xikage.mythicmobs.drops.Drop getDrop(String fileName, String drop) {
/* 122 */     if (drop.contains("}")) {
/*     */       
/* 124 */       String sp1 = drop.substring(0, drop.indexOf("}"));
/* 125 */       String sp2 = drop.substring(drop.indexOf("}"));
/* 126 */       String ns = sp1.replace(" ", "") + sp2;
/* 127 */       drop = ns;
/* 128 */       MythicMobs.debug(4, "------ Normalized Drop string to: " + drop);
/*     */     } 
/*     */     
/* 131 */     String[] s = drop.split(" ");
/*     */     
/* 133 */     String name = null;
/*     */     
/* 135 */     MythicLineConfig mlc = new MythicLineConfig(fileName, s[0]);
/*     */     
/* 137 */     if (s[0].contains("{")) {
/* 138 */       name = s[0].substring(0, s[0].indexOf("{"));
/*     */     } else {
/* 140 */       name = s[0];
/*     */     } 
/*     */     
/* 143 */     String oName = name.toUpperCase();
/* 144 */     byte oData = 0;
/*     */     
/* 146 */     if (name.contains(":")) {
/* 147 */       oName = name.split(":")[0].toUpperCase();
/*     */       try {
/* 149 */         oData = Byte.valueOf(name.split(":")[1]).byteValue();
/* 150 */       } catch (Exception exception) {}
/*     */       
/* 152 */       name = name.split(":")[0];
/*     */     } 
/*     */     
/* 155 */     if (MythicMobs.inst().getMinecraftVersion() >= 13) {
/*     */       
/* 157 */       Material material = null;
/*     */       try {
/* 159 */         material = (oData == 0) ? Material.valueOf(oName) : LegacyItemConverter.getMaterial(oName, oData);
/*     */         
/* 161 */         if (material == null && oData > 0) {
/* 162 */           material = Material.valueOf(oName);
/*     */         }
/* 164 */       } catch (Exception ex) {
/* 165 */         material = LegacyItemConverter.getMaterial(oName, oData);
/*     */         
/* 167 */         if (material == null && oData > 0) {
/*     */           try {
/* 169 */             material = Material.valueOf(oName);
/* 170 */           } catch (Exception exception) {}
/*     */         }
/*     */       } finally {
/* 173 */         if (material != null && material != Material.AIR) {
/* 174 */           name = material.toString();
/*     */         }
/*     */       } 
/*     */     } else {
/* 178 */       Material material = Material.AIR;
/*     */       
/* 180 */       if (name.matches("[0-9]*")) {
/* 181 */         material = LegacyItemConverter.getMaterial(oName, (byte)0);
/*     */       } else {
/*     */         try {
/* 184 */           material = Material.valueOf(oName);
/* 185 */         } catch (Exception exception) {}
/*     */       } 
/*     */       
/* 188 */       if (material != null && material != Material.AIR) {
/* 189 */         name = material.toString();
/*     */       }
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 195 */     MythicMobs.debug(3, "------ Matching MythicDrop type to " + name);
/*     */     
/*     */     try {
/* 198 */       if (Material.matchMaterial(name) != null)
/* 199 */         return (io.lumine.xikage.mythicmobs.drops.Drop)new ItemDrop(drop, mlc, name); 
/* 200 */       if (MythicMobs.inst().getDropManager().getDropTable(name).isPresent())
/* 201 */         return (io.lumine.xikage.mythicmobs.drops.Drop)new DropTableDrop(drop, name, mlc); 
/* 202 */       if (MythicMobs.inst().getItemManager().getItem(name).isPresent()) {
/* 203 */         return (io.lumine.xikage.mythicmobs.drops.Drop)new MythicItemDrop(drop, name, mlc);
/*     */       }
/* 205 */       switch (name.toUpperCase()) { case "NOTHING": case "NONE": case "NULL":
/* 206 */           return (io.lumine.xikage.mythicmobs.drops.Drop)new NothingDrop(drop, mlc);
/* 207 */         case "COMMAND": case "CMD": return (io.lumine.xikage.mythicmobs.drops.Drop)new CommandDrop(drop, mlc);
/* 208 */         case "EXPERIENCE": case "EXP": case "XP": return (io.lumine.xikage.mythicmobs.drops.Drop)new ExperienceDrop(drop, mlc);
/* 209 */         case "VAULT": case "MONEY": case "CURRENCY": return (io.lumine.xikage.mythicmobs.drops.Drop)new VaultDrop(drop, mlc);
/* 210 */         case "HEROESEXP": case "HEROES-EXP": return (io.lumine.xikage.mythicmobs.drops.Drop)new HeroesExperienceDrop(drop, mlc);
/* 211 */         case "MCMMOEXP": case "MCMMO-EXP": return (io.lumine.xikage.mythicmobs.drops.Drop)new McMMODrop(drop, mlc);
/* 212 */         case "MYTHICDROP": case "MD": return (io.lumine.xikage.mythicmobs.drops.Drop)new MythicDropsDrop(drop, mlc);
/* 213 */         case "PHATLOOT": case "PHATLOOTS": case "PL": return (io.lumine.xikage.mythicmobs.drops.Drop)new PhatLootsDrop(drop, mlc);
/* 214 */         case "SKILLAPIEXP": case "SKILLAPI-EXP": return (io.lumine.xikage.mythicmobs.drops.Drop)new SkillAPIDrop(drop, mlc); }
/*     */ 
/*     */ 
/*     */       
/* 218 */       return (io.lumine.xikage.mythicmobs.drops.Drop)new CustomDrop(name, drop, mlc);
/* 219 */     } catch (Exception ex) {
/* 220 */       return (io.lumine.xikage.mythicmobs.drops.Drop)new InvalidDrop(drop, mlc);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public io.lumine.xikage.mythicmobs.drops.Drop clone() {
/*     */     try {
/* 227 */       return (io.lumine.xikage.mythicmobs.drops.Drop)super.clone();
/* 228 */     } catch (CloneNotSupportedException e) {
/* 229 */       e.printStackTrace();
/* 230 */       return null;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\drops\Drop.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */