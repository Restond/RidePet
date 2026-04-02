/*     */ package lumine.xikage.mythicmobs.skills.mechanics;
/*     */ 
/*     */ import io.lumine.xikage.mythicmobs.MythicMobs;
/*     */ import io.lumine.xikage.mythicmobs.adapters.AbstractLocation;
/*     */ import io.lumine.xikage.mythicmobs.adapters.AbstractVector;
/*     */ import io.lumine.xikage.mythicmobs.adapters.bukkit.BukkitAdapter;
/*     */ import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
/*     */ import io.lumine.xikage.mythicmobs.skills.ITargetedLocationSkill;
/*     */ import io.lumine.xikage.mythicmobs.skills.SkillMechanic;
/*     */ import io.lumine.xikage.mythicmobs.skills.SkillMetadata;
/*     */ import io.lumine.xikage.mythicmobs.util.annotations.MythicMechanic;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import org.bukkit.Location;
/*     */ import org.bukkit.Material;
/*     */ 
/*     */ @MythicMechanic(author = "Ashijin", name = "blockWave", aliases = {"effect:blockWave", "e:blockWave"}, description = "Temporarily masks a block as a different block")
/*     */ public class BlockWaveEffect
/*     */   extends SkillMechanic
/*     */   implements ITargetedLocationSkill {
/*     */   protected double velocity;
/*     */   protected int radius;
/*     */   protected int radiusY;
/*     */   protected int duration;
/*     */   protected double randomness;
/*     */   protected boolean sphere;
/*     */   protected Material material;
/*     */   protected boolean hideSourceBlock;
/*     */   private int radiusSq;
/*     */   
/*     */   public BlockWaveEffect(String skill, MythicLineConfig mlc) {
/*  32 */     super(skill, mlc);
/*  33 */     this.ASYNC_SAFE = false;
/*     */     
/*  35 */     this.velocity = mlc.getDouble(new String[] { "velocity", "v" }, 0.2D);
/*  36 */     this.radius = mlc.getInteger(new String[] { "radius", "r" }, 2);
/*  37 */     this.radiusY = mlc.getInteger(new String[] { "radiusy", "ry" }, this.radius);
/*  38 */     this.randomness = mlc.getDouble(new String[] { "noise", "n" }, 0.0D);
/*  39 */     this.duration = mlc.getInteger(new String[] { "duration", "d" }, 15);
/*  40 */     this.hideSourceBlock = mlc.getBoolean(new String[] { "hidesourceblock", "hidesource", "hsb", "hs" }, true);
/*     */     
/*  42 */     String shape = mlc.getString(new String[] { "shape", "s" }, "SPHERE", new String[0]).toUpperCase();
/*     */     
/*  44 */     if (shape.equals("SPHERE")) {
/*  45 */       this.sphere = true;
/*     */     } else {
/*  47 */       this.sphere = false;
/*     */     } 
/*     */     
/*  50 */     String strMaterial = mlc.getString(new String[] { "material", "m" }, null, new String[0]);
/*     */     
/*  52 */     if (strMaterial == null) {
/*  53 */       this.material = null;
/*     */     } else {
/*  55 */       this.material = Material.valueOf(strMaterial.toUpperCase());
/*     */     } 
/*     */     
/*  58 */     if (this.radius < 0) this.radius = 0; 
/*  59 */     this.radiusSq = this.radius * this.radius;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean castAtLocation(SkillMetadata data, AbstractLocation target) {
/*  64 */     playEffect(target);
/*  65 */     return true;
/*     */   }
/*     */   
/*     */   public void playEffect(AbstractLocation loc) {
/*  69 */     if (this.radius == 0) {
/*  70 */       getPlugin().getVolatileCodeHandler().getWorldHandler().doBlockTossEffect(loc, this.material, new AbstractVector(0.0D, this.velocity, 0.0D), this.duration, this.hideSourceBlock);
/*     */     } else {
/*  72 */       for (AbstractLocation target : getBlocksInRadius(loc, true)) {
/*  73 */         getPlugin().getVolatileCodeHandler().getWorldHandler().doBlockTossEffect(target, this.material, new AbstractVector(0.0D, this.velocity, 0.0D), this.duration, this.hideSourceBlock);
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   private List<AbstractLocation> getBlocksInRadius(AbstractLocation location, boolean randomize) {
/*  79 */     List<AbstractLocation> blocks = new ArrayList<>();
/*  80 */     Location loc = BukkitAdapter.adapt(location);
/*  81 */     for (int x = -this.radius; x <= this.radius; x++) {
/*  82 */       for (int z = -this.radius; z <= this.radius; z++) {
/*  83 */         for (int y = this.radiusY; y >= -this.radiusY; y--) {
/*  84 */           Location newloc = new Location(loc.getWorld(), (loc.getBlockX() + x), (loc.getBlockY() + y), (loc.getBlockZ() + z));
/*     */           
/*  86 */           if (this.sphere != true || 
/*  87 */             loc.distanceSquared(newloc) <= this.radiusSq)
/*     */           {
/*  89 */             if (!randomize || this.randomness <= 0.0D || 
/*  90 */               this.randomness >= MythicMobs.r.nextDouble())
/*     */             {
/*     */               
/*  93 */               if (newloc.getBlock().getType() != Material.AIR) {
/*  94 */                 blocks.add(BukkitAdapter.adapt(newloc));
/*     */                 break;
/*     */               }  }  } 
/*     */         } 
/*     */       } 
/*     */     } 
/* 100 */     return blocks;
/*     */   }
/*     */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\skills\mechanics\BlockWaveEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */