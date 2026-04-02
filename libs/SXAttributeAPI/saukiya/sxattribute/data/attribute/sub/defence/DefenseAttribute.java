/*     */ package saukiya.sxattribute.data.attribute.sub.defence;
/*     */ 
/*     */ import github.saukiya.sxattribute.SXAttribute;
/*     */ import github.saukiya.sxattribute.data.attribute.SXAttributeType;
/*     */ import github.saukiya.sxattribute.data.attribute.SubAttribute;
/*     */ import github.saukiya.sxattribute.data.eventdata.EventData;
/*     */ import github.saukiya.sxattribute.data.eventdata.sub.DamageEventData;
/*     */ import github.saukiya.sxattribute.util.Config;
/*     */ import java.util.Arrays;
/*     */ import java.util.List;
/*     */ import org.bukkit.entity.Player;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class DefenseAttribute
/*     */   extends SubAttribute
/*     */ {
/*     */   public DefenseAttribute() {
/*  30 */     super("Defense", 6, new SXAttributeType[] { SXAttributeType.DEFENCE });
/*     */   }
/*     */ 
/*     */   
/*     */   public void eventMethod(EventData eventData) {
/*  35 */     if (eventData instanceof DamageEventData) {
/*  36 */       DamageEventData damageEventData = (DamageEventData)eventData;
/*  37 */       if (!damageEventData.getEffectiveAttributeList().contains("Real")) {
/*  38 */         damageEventData.takeDamage(getAttribute());
/*  39 */         if (damageEventData.getDamager() instanceof Player) {
/*  40 */           damageEventData.takeDamage(getPVPAttribute());
/*     */         } else {
/*  42 */           damageEventData.takeDamage(getPVEAttribute());
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public String getPlaceholder(Player player, String string) {
/*  50 */     if (string.equalsIgnoreCase("MinDefense"))
/*  51 */       return getDf().format(getAttributes()[0]); 
/*  52 */     if (string.equalsIgnoreCase("MaxDefense"))
/*  53 */       return getDf().format(getAttributes()[1]); 
/*  54 */     if (string.equalsIgnoreCase("Defense"))
/*  55 */       return (getAttributes()[0] == getAttributes()[1]) ? getDf().format(getAttributes()[0]) : (getDf().format(getAttributes()[0]) + " - " + getDf().format(getAttributes()[1])); 
/*  56 */     if (string.equalsIgnoreCase("PvpMinDefense"))
/*  57 */       return getDf().format(getAttributes()[2]); 
/*  58 */     if (string.equalsIgnoreCase("PvpMaxDefense"))
/*  59 */       return getDf().format(getAttributes()[3]); 
/*  60 */     if (string.equalsIgnoreCase("PvpDefense"))
/*  61 */       return (getAttributes()[2] == getAttributes()[3]) ? getDf().format(getAttributes()[2]) : (getDf().format(getAttributes()[2]) + " - " + getDf().format(getAttributes()[3])); 
/*  62 */     if (string.equalsIgnoreCase("PveMinDefense"))
/*  63 */       return getDf().format(getAttributes()[4]); 
/*  64 */     if (string.equalsIgnoreCase("PveMaxDefense"))
/*  65 */       return getDf().format(getAttributes()[5]); 
/*  66 */     if (string.equalsIgnoreCase("PveDefense")) {
/*  67 */       return (getAttributes()[4] == getAttributes()[5]) ? getDf().format(getAttributes()[4]) : (getDf().format(getAttributes()[4]) + " - " + getDf().format(getAttributes()[5]));
/*     */     }
/*  69 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public List<String> getPlaceholders() {
/*  75 */     return Arrays.asList(new String[] { "MinDefense", "MaxDefense", "Defense", "PvpMinDefense", "PvpMaxDefense", "PvpDefense", "PveMinDefense", "PveMaxDefense", "PveDefense" });
/*     */   }
/*     */   
/*     */   private double getAttribute() {
/*  79 */     return getAttributes()[0] + SXAttribute.getRandom().nextDouble() * (getAttributes()[1] - getAttributes()[0]);
/*     */   }
/*     */   
/*     */   private double getPVPAttribute() {
/*  83 */     return getAttributes()[2] + SXAttribute.getRandom().nextDouble() * (getAttributes()[3] - getAttributes()[2]);
/*     */   }
/*     */   
/*     */   private double getPVEAttribute() {
/*  87 */     return getAttributes()[4] + SXAttribute.getRandom().nextDouble() * (getAttributes()[5] - getAttributes()[4]);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean loadAttribute(String lore) {
/*  92 */     String[] loreSplit = lore.split("-");
/*  93 */     if (lore.contains(Config.getConfig().getString("Attribute.PVEDefense.Name"))) {
/*  94 */       getAttributes()[4] = getAttributes()[4] + Double.valueOf(getNumber(loreSplit[0])).doubleValue();
/*  95 */       getAttributes()[5] = getAttributes()[5] + Double.valueOf(getNumber((loreSplit.length > 1) ? loreSplit[1] : loreSplit[0])).doubleValue();
/*  96 */     } else if (lore.contains(Config.getConfig().getString("Attribute.PVPDefense.Name"))) {
/*  97 */       getAttributes()[2] = getAttributes()[2] + Double.valueOf(getNumber(loreSplit[0])).doubleValue();
/*  98 */       getAttributes()[3] = getAttributes()[3] + Double.valueOf(getNumber((loreSplit.length > 1) ? loreSplit[1] : loreSplit[0])).doubleValue();
/*  99 */     } else if (lore.contains(Config.getConfig().getString("Attribute.Defense.Name"))) {
/* 100 */       getAttributes()[0] = getAttributes()[0] + Double.valueOf(getNumber(loreSplit[0])).doubleValue();
/* 101 */       getAttributes()[1] = getAttributes()[1] + Double.valueOf(getNumber((loreSplit.length > 1) ? loreSplit[1] : loreSplit[0])).doubleValue();
/*     */     } else {
/* 103 */       return false;
/*     */     } 
/* 105 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public void correct() {
/* 110 */     if (getAttributes()[0] <= 0.0D) getAttributes()[0] = 0.0D; 
/* 111 */     if (getAttributes()[1] < getAttributes()[0]) getAttributes()[1] = getAttributes()[0]; 
/* 112 */     if (getAttributes()[2] <= 0.0D) getAttributes()[2] = 0.0D; 
/* 113 */     if (getAttributes()[3] < getAttributes()[2]) getAttributes()[3] = getAttributes()[2]; 
/* 114 */     if (getAttributes()[4] <= 0.0D) getAttributes()[4] = 0.0D; 
/* 115 */     if (getAttributes()[5] < getAttributes()[4]) getAttributes()[5] = getAttributes()[4];
/*     */   
/*     */   }
/*     */   
/*     */   public double getValue() {
/* 120 */     double value = (getAttributes()[0] + getAttributes()[1]) / 2.0D * Config.getConfig().getInt("Attribute.Defense.Value");
/* 121 */     value += (getAttributes()[2] + getAttributes()[3]) / 2.0D * Config.getConfig().getInt("Attribute.PVPDefense.Value");
/* 122 */     value += (getAttributes()[4] + getAttributes()[5]) / 2.0D * Config.getConfig().getInt("Attribute.PVEDefense.Value");
/* 123 */     return value;
/*     */   }
/*     */ }


/* Location:              D:\JavaProject\RidePet\libs\SXAttributeAPI\!\saukiya\sxattribute\data\attribute\sub\defence\DefenseAttribute.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */