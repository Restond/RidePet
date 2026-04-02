/*     */ package saukiya.sxattribute.data.attribute.sub.damage;
/*     */ 
/*     */ import github.saukiya.sxattribute.SXAttribute;
/*     */ import github.saukiya.sxattribute.data.attribute.SXAttributeType;
/*     */ import github.saukiya.sxattribute.data.attribute.SubAttribute;
/*     */ import github.saukiya.sxattribute.data.eventdata.EventData;
/*     */ import github.saukiya.sxattribute.data.eventdata.sub.DamageEventData;
/*     */ import github.saukiya.sxattribute.data.eventdata.sub.UpdateEventData;
/*     */ import github.saukiya.sxattribute.util.Config;
/*     */ import github.saukiya.sxattribute.util.Message;
/*     */ import java.util.Arrays;
/*     */ import java.util.List;
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.attribute.Attribute;
/*     */ import org.bukkit.entity.LivingEntity;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.event.entity.EntityDamageByEntityEvent;
/*     */ import org.spigotmc.SpigotConfig;
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
/*     */ 
/*     */ public class DamageAttribute
/*     */   extends SubAttribute
/*     */ {
/*     */   public DamageAttribute() {
/*  38 */     super("Damage", 6, new SXAttributeType[] { SXAttributeType.DAMAGE, SXAttributeType.UPDATE });
/*     */   }
/*     */ 
/*     */   
/*     */   public void eventMethod(EventData eventData) {
/*  43 */     if (eventData instanceof DamageEventData) {
/*  44 */       DamageEventData damageEventData = (DamageEventData)eventData;
/*  45 */       EntityDamageByEntityEvent event = damageEventData.getEvent();
/*  46 */       if (!Config.isDamageGauges() || event.getDamager() instanceof org.bukkit.entity.Projectile) {
/*  47 */         damageEventData.addDamage(getAttribute());
/*  48 */       } else if (event.getDamager() instanceof Player) {
/*  49 */         damageEventData.addDamage(getAttribute() - getAttributes()[0]);
/*     */       } else {
/*  51 */         damageEventData.addDamage(getAttribute());
/*     */       } 
/*  53 */       if (event.getEntity() instanceof Player) {
/*  54 */         damageEventData.addDamage(getPVPAttribute());
/*     */       } else {
/*  56 */         damageEventData.addDamage(getPVEAttribute());
/*     */       } 
/*  58 */     } else if (eventData instanceof UpdateEventData && ((UpdateEventData)eventData).getEntity() instanceof Player) {
/*  59 */       LivingEntity entity = ((UpdateEventData)eventData).getEntity();
/*  60 */       if (Config.isDamageGauges()) {
/*  61 */         entity.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(getAttributes()[0]);
/*  62 */       } else if (getAttribute() == 0.0D) {
/*  63 */         entity.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(1.0D);
/*     */       } else {
/*  65 */         entity.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(0.01D);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public String getPlaceholder(Player player, String string) {
/*  72 */     if (string.equalsIgnoreCase("MinDamage"))
/*  73 */       return getDf().format(getAttributes()[0]); 
/*  74 */     if (string.equalsIgnoreCase("MaxDamage"))
/*  75 */       return getDf().format(getAttributes()[1]); 
/*  76 */     if (string.equalsIgnoreCase("Damage"))
/*  77 */       return (getAttributes()[0] == getAttributes()[1]) ? getDf().format(getAttributes()[0]) : (getDf().format(getAttributes()[0]) + " - " + getDf().format(getAttributes()[1])); 
/*  78 */     if (string.equalsIgnoreCase("PvpMinDamage"))
/*  79 */       return getDf().format(getAttributes()[2]); 
/*  80 */     if (string.equalsIgnoreCase("PvpMaxDamage"))
/*  81 */       return getDf().format(getAttributes()[3]); 
/*  82 */     if (string.equalsIgnoreCase("PvpDamage"))
/*  83 */       return (getAttributes()[2] == getAttributes()[3]) ? getDf().format(getAttributes()[2]) : (getDf().format(getAttributes()[2]) + " - " + getDf().format(getAttributes()[3])); 
/*  84 */     if (string.equalsIgnoreCase("PveMinDamage"))
/*  85 */       return getDf().format(getAttributes()[4]); 
/*  86 */     if (string.equalsIgnoreCase("PveMaxDamage"))
/*  87 */       return getDf().format(getAttributes()[5]); 
/*  88 */     if (string.equalsIgnoreCase("PveDamage")) {
/*  89 */       return (getAttributes()[4] == getAttributes()[5]) ? getDf().format(getAttributes()[4]) : (getDf().format(getAttributes()[4]) + " - " + getDf().format(getAttributes()[5]));
/*     */     }
/*  91 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public List<String> getPlaceholders() {
/*  97 */     return Arrays.asList(new String[] { "MinDamage", "MaxDamage", "Damage", "PvpMinDamage", "PvpMaxDamage", "PvpDamage", "PveMinDamage", "PveMaxDamage", "PveDamage" });
/*     */   }
/*     */   
/*     */   private double getAttribute() {
/* 101 */     return getAttributes()[0] + SXAttribute.getRandom().nextDouble() * (getAttributes()[1] - getAttributes()[0]);
/*     */   }
/*     */   
/*     */   private double getPVPAttribute() {
/* 105 */     return getAttributes()[2] + SXAttribute.getRandom().nextDouble() * (getAttributes()[3] - getAttributes()[2]);
/*     */   }
/*     */   
/*     */   private double getPVEAttribute() {
/* 109 */     return getAttributes()[4] + SXAttribute.getRandom().nextDouble() * (getAttributes()[5] - getAttributes()[4]);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean loadAttribute(String lore) {
/* 114 */     String[] loreSplit = lore.split("-");
/* 115 */     if (lore.contains(Config.getConfig().getString("Attribute.PVEDamage.Name"))) {
/* 116 */       getAttributes()[4] = getAttributes()[4] + Double.valueOf(getNumber(loreSplit[0])).doubleValue();
/* 117 */       getAttributes()[5] = getAttributes()[5] + Double.valueOf(getNumber((loreSplit.length > 1) ? loreSplit[1] : loreSplit[0])).doubleValue();
/* 118 */     } else if (lore.contains(Config.getConfig().getString("Attribute.PVPDamage.Name"))) {
/* 119 */       getAttributes()[2] = getAttributes()[2] + Double.valueOf(getNumber(loreSplit[0])).doubleValue();
/* 120 */       getAttributes()[3] = getAttributes()[3] + Double.valueOf(getNumber((loreSplit.length > 1) ? loreSplit[1] : loreSplit[0])).doubleValue();
/* 121 */     } else if (lore.contains(Config.getConfig().getString("Attribute.Damage.Name"))) {
/* 122 */       getAttributes()[0] = getAttributes()[0] + Double.valueOf(getNumber(loreSplit[0])).doubleValue();
/* 123 */       getAttributes()[1] = getAttributes()[1] + Double.valueOf(getNumber((loreSplit.length > 1) ? loreSplit[1] : loreSplit[0])).doubleValue();
/*     */     } else {
/* 125 */       return false;
/*     */     } 
/* 127 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public void correct() {
/* 132 */     if (getAttributes()[0] <= 0.0D) getAttributes()[0] = Config.isDamageGauges() ? 1.0D : 0.0D; 
/* 133 */     if (getAttributes()[0] > Double.MAX_VALUE) getAttributes()[0] = Double.MAX_VALUE; 
/* 134 */     if (getAttributes()[0] > SpigotConfig.attackDamage) {
/* 135 */       Bukkit.getConsoleSender().sendMessage(Message.getMessagePrefix() + "§cPlease set attackDamage to spigot.yml §8[§4" + getAttributes()[0] + "§7 > §4" + SpigotConfig.attackDamage + "§8]");
/* 136 */       getAttributes()[0] = SpigotConfig.attackDamage;
/*     */     } 
/* 138 */     if (getAttributes()[1] < getAttributes()[0]) getAttributes()[1] = getAttributes()[0]; 
/* 139 */     if (getAttributes()[2] <= 0.0D) getAttributes()[2] = 0.0D; 
/* 140 */     if (getAttributes()[3] < getAttributes()[2]) getAttributes()[3] = getAttributes()[2]; 
/* 141 */     if (getAttributes()[4] <= 0.0D) getAttributes()[4] = 0.0D; 
/* 142 */     if (getAttributes()[5] < getAttributes()[4]) getAttributes()[5] = getAttributes()[4];
/*     */   
/*     */   }
/*     */   
/*     */   public double getValue() {
/* 147 */     double value = (getAttributes()[0] + getAttributes()[1]) / 2.0D * Config.getConfig().getInt("Attribute.Damage.Value");
/* 148 */     value += (getAttributes()[2] + getAttributes()[3]) / 2.0D * Config.getConfig().getInt("Attribute.PVEDamage.Value");
/* 149 */     value += (getAttributes()[4] + getAttributes()[5]) / 2.0D * Config.getConfig().getInt("Attribute.PVPDamage.Value");
/* 150 */     return value;
/*     */   }
/*     */ }


/* Location:              D:\JavaProject\RidePet\libs\SXAttributeAPI\!\saukiya\sxattribute\data\attribute\sub\damage\DamageAttribute.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */