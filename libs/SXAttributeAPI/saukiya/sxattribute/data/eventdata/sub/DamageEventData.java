/*     */ package saukiya.sxattribute.data.eventdata.sub;
/*     */ 
/*     */ import github.saukiya.sxattribute.data.attribute.SXAttributeData;
/*     */ import github.saukiya.sxattribute.data.attribute.SubAttribute;
/*     */ import github.saukiya.sxattribute.data.eventdata.EventData;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import org.bukkit.entity.LivingEntity;
/*     */ import org.bukkit.event.entity.EntityDamageByEntityEvent;
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
/*     */ public class DamageEventData
/*     */   extends EventData
/*     */ {
/*     */   private final LivingEntity entity;
/*     */   private final LivingEntity damager;
/*     */   private final String entityName;
/*     */   private final String damagerName;
/*     */   private final SXAttributeData entityData;
/*     */   private final SXAttributeData damagerData;
/*     */   private final EntityDamageByEntityEvent event;
/*  35 */   private final List<String> EffectiveAttributeList = new ArrayList<>(); private double damage;
/*     */   private boolean crit;
/*  37 */   private final List<String> holoList = new ArrayList<>(); public List<String> getHoloList() { return this.holoList; }
/*     */ 
/*     */   
/*  40 */   public double getDamage() { return this.damage; } public void setDamage(double damage) {
/*  41 */     this.damage = damage;
/*     */   }
/*     */   
/*  44 */   public boolean isCrit() { return this.crit; } public void setCrit(boolean crit) {
/*  45 */     this.crit = crit;
/*     */   } private boolean cancelled = false;
/*     */   public boolean isCancelled() {
/*  48 */     return this.cancelled;
/*     */   }
/*     */   
/*     */   public DamageEventData(LivingEntity entity, LivingEntity damager, String entityName, String damagerName, SXAttributeData entityData, SXAttributeData damagerData, EntityDamageByEntityEvent event) {
/*  52 */     this.entity = entity;
/*  53 */     this.damager = damager;
/*  54 */     this.entityName = entityName;
/*  55 */     this.damagerName = damagerName;
/*  56 */     this.entityData = entityData;
/*  57 */     this.damagerData = damagerData;
/*  58 */     this.event = event;
/*  59 */     this.damage = event.getDamage();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void sendHolo(String message) {
/*  68 */     if (!message.contains("Null Message: ")) {
/*  69 */       this.holoList.add(message);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public double[] getEntityAttributeDoubles(String attributeName) {
/*  80 */     SubAttribute attribute = this.entityData.getSubAttribute(attributeName);
/*  81 */     return (attribute != null) ? attribute.getAttributes() : new double[12];
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public double[] getDamagerAttributeDoubles(String attributeName) {
/*  91 */     SubAttribute attribute = this.damagerData.getSubAttribute(attributeName);
/*  92 */     return (attribute != null) ? attribute.getAttributes() : new double[12];
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addDamage(double addDamage) {
/* 101 */     this.damage += addDamage;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void takeDamage(double takeDamage) {
/* 110 */     this.damage -= takeDamage;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public LivingEntity getEntity() {
/* 119 */     return this.entity;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public LivingEntity getDamager() {
/* 128 */     return this.damager;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getEntityName() {
/* 137 */     return this.entityName;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getDamagerName() {
/* 146 */     return this.damagerName;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public EntityDamageByEntityEvent getEvent() {
/* 156 */     return this.event;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List<String> getEffectiveAttributeList() {
/* 165 */     return this.EffectiveAttributeList;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setCancelled(boolean cancelled) {
/* 174 */     this.cancelled = cancelled;
/*     */   }
/*     */ }


/* Location:              D:\JavaProject\RidePet\libs\SXAttributeAPI\!\saukiya\sxattribute\data\eventdata\sub\DamageEventData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */