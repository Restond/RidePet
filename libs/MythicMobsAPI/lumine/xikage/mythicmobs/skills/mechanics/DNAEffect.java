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
/*    */ 
/*    */ public class DNAEffect extends SkillMechanic implements ITargetedEntitySkill, ITargetedLocationSkill {
/*    */   ParticleEffect particleHelix;
/*    */   ParticleEffect particleBase1;
/*    */   ParticleEffect particleBase2;
/*    */   float radiusN;
/*    */   float radials;
/*    */   float growth;
/* 19 */   double velocity = 1.0D; float length; int radius; int amountH; int amountB; int baseinterval; int interval; int iterations;
/*    */   
/*    */   public DNAEffect(String skill, MythicLineConfig mlc) {
/* 22 */     super(skill, mlc);
/* 23 */     if (CompatibilityManager.EffectLib == null)
/*    */       return; 
/* 25 */     String strParticleHelix = mlc.getString("particlehelix", "reddust");
/* 26 */     strParticleHelix = mlc.getString("particleh", strParticleHelix);
/* 27 */     strParticleHelix = mlc.getString("ph", strParticleHelix);
/*    */     
/* 29 */     this.particleHelix = CompatibilityManager.EffectLib.getParticleEffect(strParticleHelix);
/*    */     
/* 31 */     String strParticleBase1 = mlc.getString("particlebase1", "reddust");
/* 32 */     strParticleBase1 = mlc.getString("particleb1", strParticleBase1);
/* 33 */     strParticleBase1 = mlc.getString("pb1", strParticleBase1);
/*    */     
/* 35 */     this.particleBase1 = CompatibilityManager.EffectLib.getParticleEffect(strParticleBase1);
/*    */     
/* 37 */     String strParticleBase2 = mlc.getString("particlebase2", "reddust");
/* 38 */     strParticleBase2 = mlc.getString("particleb2", strParticleBase2);
/* 39 */     strParticleBase2 = mlc.getString("pb2", strParticleBase2);
/*    */     
/* 41 */     this.particleBase2 = CompatibilityManager.EffectLib.getParticleEffect(strParticleBase2);
/*    */     
/* 43 */     this.amountH = mlc.getInteger("amounthelix", 50);
/* 44 */     this.amountH = mlc.getInteger("amounth", this.amountH);
/* 45 */     this.amountH = mlc.getInteger("aph", this.amountH);
/* 46 */     this.amountH = mlc.getInteger("ah", this.amountH);
/*    */     
/* 48 */     this.amountB = mlc.getInteger("amountbase", 100);
/* 49 */     this.amountB = mlc.getInteger("amountb", this.amountB);
/* 50 */     this.amountB = mlc.getInteger("apb", this.amountB);
/* 51 */     this.amountB = mlc.getInteger("ab", this.amountB);
/*    */     
/* 53 */     this.radius = mlc.getInteger("radius", 4);
/* 54 */     this.radius = mlc.getInteger("r", this.radius);
/*    */     
/* 56 */     this.radials = mlc.getFloat("radials", 0.0F);
/* 57 */     this.radials = mlc.getFloat("rad", this.radials);
/*    */     
/* 59 */     this.iterations = mlc.getInteger("ticks", 1);
/* 60 */     this.iterations = mlc.getInteger("t", this.iterations);
/*    */     
/* 62 */     this.interval = mlc.getInteger("interval", 10);
/* 63 */     this.interval = mlc.getInteger("in", this.interval);
/*    */     
/* 65 */     this.baseinterval = mlc.getInteger("baseinterval", 0);
/* 66 */     this.baseinterval = mlc.getInteger("bin", this.baseinterval);
/*    */     
/* 68 */     this.growth = mlc.getFloat("growth", 0.0F);
/* 69 */     this.growth = mlc.getFloat("g", this.growth);
/*    */     
/* 71 */     this.length = mlc.getFloat("length", 10.0F);
/* 72 */     this.length = mlc.getFloat("l", this.length);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean castAtLocation(SkillMetadata data, AbstractLocation target) {
/* 79 */     if (CompatibilityManager.EffectLib == null) return false;
/*    */     
/* 81 */     CompatibilityManager.EffectLib.doDNAEffect(BukkitAdapter.adapt(data.getOrigin()), BukkitAdapter.adapt(target), this.particleHelix, this.amountH, this.particleBase1, this.particleBase2, this.amountB, this.radials, this.radius, this.length, this.growth, this.baseinterval, this.interval, this.iterations);
/* 82 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean castAtEntity(SkillMetadata data, AbstractEntity target) {
/* 87 */     if (CompatibilityManager.EffectLib == null) return false;
/*    */     
/* 89 */     CompatibilityManager.EffectLib.doDNAEffect(BukkitAdapter.adapt(data.getOrigin()), BukkitAdapter.adapt(target).getLocation(), this.particleHelix, this.amountH, this.particleBase1, this.particleBase2, this.amountB, this.radials, this.radius, this.length, this.growth, this.baseinterval, this.interval, this.iterations);
/* 90 */     return true;
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\skills\mechanics\DNAEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */