/*     */ package lumine.xikage.mythicmobs.skills.mechanics;
/*     */ 
/*     */ import io.lumine.xikage.mythicmobs.MythicMobs;
/*     */ import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
/*     */ import io.lumine.xikage.mythicmobs.adapters.AbstractLocation;
/*     */ import io.lumine.xikage.mythicmobs.adapters.bukkit.BukkitAdapter;
/*     */ import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
/*     */ import io.lumine.xikage.mythicmobs.skills.ITargetedEntitySkill;
/*     */ import io.lumine.xikage.mythicmobs.skills.ITargetedLocationSkill;
/*     */ import io.lumine.xikage.mythicmobs.skills.SkillMechanic;
/*     */ import io.lumine.xikage.mythicmobs.skills.SkillMetadata;
/*     */ import io.lumine.xikage.mythicmobs.util.annotations.MythicMechanic;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @MythicMechanic(author = "Ashijin", name = "effect:firework", aliases = {"firework", "e:firework"}, description = "Shoots a firework")
/*     */ public class FireworkEffect
/*     */   extends SkillMechanic
/*     */   implements ITargetedEntitySkill, ITargetedLocationSkill
/*     */ {
/*     */   protected int type;
/*     */   protected int flightDuration;
/*     */   protected int[] colors;
/*     */   protected int[] fadeColors;
/*     */   protected boolean flicker;
/*     */   protected boolean trail;
/*     */   
/*     */   public FireworkEffect(String skill, MythicLineConfig mlc) {
/*  30 */     super(skill, mlc);
/*  31 */     this.ASYNC_SAFE = false;
/*     */     
/*  33 */     this.type = mlc.getInteger("type", 0);
/*  34 */     this.type = mlc.getInteger("t", this.type);
/*     */     
/*  36 */     this.flightDuration = mlc.getInteger("duration", 0);
/*  37 */     this.flightDuration = mlc.getInteger("d", this.flightDuration);
/*     */     
/*  39 */     this.flicker = mlc.getBoolean("flicker", false);
/*  40 */     this.flicker = mlc.getBoolean("f", this.flicker);
/*     */     
/*  42 */     this.trail = mlc.getBoolean("trail", false);
/*  43 */     this.trail = mlc.getBoolean("tr", this.trail);
/*     */     
/*  45 */     String colors = mlc.getString("colors");
/*  46 */     colors = mlc.getString("c", colors);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  52 */     this.colors = new int[] { 16711680 };
/*     */     
/*  54 */     if (colors != null) {
/*  55 */       String[] c = colors.split(",");
/*  56 */       this.colors = new int[c.length];
/*  57 */       for (int i = 0; i < c.length; i++) {
/*  58 */         this.colors[i] = Integer.parseInt(c[i], 16);
/*     */       }
/*     */     } 
/*     */     
/*  62 */     String fadeColors = mlc.getString("fadecolors");
/*  63 */     fadeColors = mlc.getString("fc", colors);
/*     */     
/*  65 */     this.fadeColors = new int[] { 16711680 };
/*     */     
/*  67 */     if (fadeColors != null) {
/*  68 */       String[] c = colors.split(",");
/*  69 */       this.fadeColors = new int[c.length];
/*  70 */       for (int i = 0; i < c.length; i++) {
/*  71 */         this.fadeColors[i] = Integer.parseInt(c[i], 16);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean castAtLocation(SkillMetadata data, AbstractLocation target) {
/*  78 */     playEffect(target);
/*  79 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean castAtEntity(SkillMetadata data, AbstractEntity target) {
/*  84 */     playEffect(target.getLocation());
/*  85 */     return false;
/*     */   }
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void playEffect(AbstractLocation l) {
/* 109 */     MythicMobs.inst().getVolatileCodeHandler().CreateFireworksExplosion(BukkitAdapter.adapt(l), this.flicker, this.trail, this.type, this.colors, this.fadeColors, this.flightDuration);
/*     */   }
/*     */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\skills\mechanics\FireworkEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */