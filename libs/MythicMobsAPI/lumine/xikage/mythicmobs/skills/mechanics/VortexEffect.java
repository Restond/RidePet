/*    */ package lumine.xikage.mythicmobs.skills.mechanics;
/*    */ import de.slikey.effectlib.util.ParticleEffect;
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractLocation;
/*    */ import io.lumine.xikage.mythicmobs.adapters.bukkit.BukkitAdapter;
/*    */ import io.lumine.xikage.mythicmobs.compatibility.CompatibilityManager;
/*    */ import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
/*    */ import io.lumine.xikage.mythicmobs.skills.ITargetedEntitySkill;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillMechanic;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillMetadata;
/*    */ import org.bukkit.Location;
/*    */ 
/*    */ public class VortexEffect extends SkillMechanic implements ITargetedEntitySkill, ITargetedLocationSkill {
/*    */   ParticleEffect particleHelix;
/*    */   ParticleEffect particleBase1;
/*    */   ParticleEffect particleBase2;
/*    */   float radiusN;
/*    */   float radials;
/*    */   float growth;
/* 20 */   double velocity = 1.0D; float length; int radius; int amountH; int amountB; int baseinterval; int interval; int iterations;
/*    */   
/*    */   public VortexEffect(String skill, MythicLineConfig mlc) {
/* 23 */     super(skill, mlc);
/* 24 */     if (CompatibilityManager.EffectLib == null)
/*    */       return; 
/* 26 */     String strParticleHelix = mlc.getString("particlehelix", "reddust");
/* 27 */     strParticleHelix = mlc.getString("particleh", strParticleHelix);
/* 28 */     strParticleHelix = mlc.getString("ph", strParticleHelix);
/*    */     
/* 30 */     this.particleHelix = CompatibilityManager.EffectLib.getParticleEffect(strParticleHelix);
/*    */     
/* 32 */     String strParticleBase1 = mlc.getString("particlebase1", "reddust");
/* 33 */     strParticleBase1 = mlc.getString("particleb1", strParticleBase1);
/* 34 */     strParticleBase1 = mlc.getString("pb1", strParticleBase1);
/*    */     
/* 36 */     this.particleBase1 = CompatibilityManager.EffectLib.getParticleEffect(strParticleBase1);
/*    */     
/* 38 */     String strParticleBase2 = mlc.getString("particlebase2", "reddust");
/* 39 */     strParticleBase2 = mlc.getString("particleb2", strParticleBase2);
/* 40 */     strParticleBase2 = mlc.getString("pb2", strParticleBase2);
/*    */     
/* 42 */     this.particleBase2 = CompatibilityManager.EffectLib.getParticleEffect(strParticleBase2);
/*    */     
/* 44 */     this.amountH = mlc.getInteger("amounthelix", 50);
/* 45 */     this.amountH = mlc.getInteger("amounth", this.amountH);
/* 46 */     this.amountH = mlc.getInteger("aph", this.amountH);
/* 47 */     this.amountH = mlc.getInteger("ah", this.amountH);
/*    */     
/* 49 */     this.amountB = mlc.getInteger("amountbase", 100);
/* 50 */     this.amountB = mlc.getInteger("amountb", this.amountB);
/* 51 */     this.amountB = mlc.getInteger("apb", this.amountB);
/* 52 */     this.amountB = mlc.getInteger("ab", this.amountB);
/*    */     
/* 54 */     this.radius = mlc.getInteger("radius", 4);
/* 55 */     this.radius = mlc.getInteger("r", this.radius);
/*    */     
/* 57 */     this.radials = mlc.getFloat("radials", 0.0F);
/* 58 */     this.radials = mlc.getFloat("rad", this.radials);
/*    */     
/* 60 */     this.iterations = mlc.getInteger("ticks", 1);
/* 61 */     this.iterations = mlc.getInteger("t", this.iterations);
/*    */     
/* 63 */     this.interval = mlc.getInteger("interval", 10);
/* 64 */     this.interval = mlc.getInteger("in", this.interval);
/*    */     
/* 66 */     this.baseinterval = mlc.getInteger("baseinterval", 0);
/* 67 */     this.baseinterval = mlc.getInteger("bin", this.baseinterval);
/*    */     
/* 69 */     this.growth = mlc.getFloat("growth", 0.0F);
/* 70 */     this.growth = mlc.getFloat("g", this.growth);
/*    */     
/* 72 */     this.length = mlc.getFloat("length", 10.0F);
/* 73 */     this.length = mlc.getFloat("l", this.length);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean castAtLocation(SkillMetadata data, AbstractLocation target) {
/* 80 */     if (CompatibilityManager.EffectLib == null) return false;
/*    */     
/* 82 */     Location source = BukkitAdapter.adapt(data.getOrigin());
/* 83 */     Location location = BukkitAdapter.adapt(target);
/*    */     
/* 85 */     CompatibilityManager.EffectLib.doDNAEffect(source, location, this.particleHelix, this.amountH, this.particleBase1, this.particleBase2, this.amountB, this.radials, this.radius, this.length, this.growth, this.baseinterval, this.interval, this.iterations);
/* 86 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean castAtEntity(SkillMetadata data, AbstractEntity target) {
/* 91 */     if (CompatibilityManager.EffectLib == null) return false;
/*    */     
/* 93 */     Location source = BukkitAdapter.adapt(data.getOrigin());
/* 94 */     Location location = BukkitAdapter.adapt(target).getLocation();
/* 95 */     CompatibilityManager.EffectLib.doDNAEffect(source, location, this.particleHelix, this.amountH, this.particleBase1, this.particleBase2, this.amountB, this.radials, this.radius, this.length, this.growth, this.baseinterval, this.interval, this.iterations);
/* 96 */     return true;
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\skills\mechanics\VortexEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */