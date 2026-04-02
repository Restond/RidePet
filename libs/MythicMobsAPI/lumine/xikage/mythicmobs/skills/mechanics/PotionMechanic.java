/*    */ package lumine.xikage.mythicmobs.skills.mechanics;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.MythicMobs;
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
/*    */ import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
/*    */ import io.lumine.xikage.mythicmobs.logging.MythicLogger;
/*    */ import io.lumine.xikage.mythicmobs.skills.ITargetedEntitySkill;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillMechanic;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillMetadata;
/*    */ import io.lumine.xikage.mythicmobs.skills.placeholders.PlaceholderMeta;
/*    */ import io.lumine.xikage.mythicmobs.skills.placeholders.parsers.PlaceholderInt;
/*    */ import io.lumine.xikage.mythicmobs.skills.placeholders.parsers.PlaceholderString;
/*    */ import io.lumine.xikage.mythicmobs.util.annotations.MythicMechanic;
/*    */ import org.bukkit.entity.LivingEntity;
/*    */ import org.bukkit.potion.PotionEffect;
/*    */ import org.bukkit.potion.PotionEffectType;
/*    */ 
/*    */ @MythicMechanic(author = "Ashijin", name = "potion", description = "Applies a potion effect to the target entity")
/*    */ public class PotionMechanic extends SkillMechanic implements ITargetedEntitySkill {
/*    */   protected PlaceholderString effect;
/*    */   protected PlaceholderInt duration;
/*    */   protected PlaceholderInt lvl;
/*    */   protected boolean overwrite;
/*    */   protected boolean ambientParticles;
/*    */   protected boolean hasParticles;
/*    */   protected boolean hasIcon;
/*    */   
/*    */   public PotionMechanic(String line, MythicLineConfig mlc) {
/* 29 */     super(line, mlc);
/* 30 */     this.ASYNC_SAFE = false;
/*    */     
/* 32 */     this.effect = mlc.getPlaceholderString(new String[] { "type", "effect", "t" }, "SLOW", new String[0]);
/* 33 */     this.duration = mlc.getPlaceholderInteger(new String[] { "duration", "d" }, 100, new String[0]);
/* 34 */     this.lvl = mlc.getPlaceholderInteger(new String[] { "level", "lvl", "l" }, 1, new String[0]);
/* 35 */     this.overwrite = mlc.getBoolean(new String[] { "overwrite", "ow", "override", "or", "force" }, false);
/*    */     
/* 37 */     this.ambientParticles = mlc.getBoolean(new String[] { "ambientparticles", "ambient", "a" }, false);
/* 38 */     this.hasParticles = mlc.getBoolean(new String[] { "hasparticles", "particles", "p" }, true);
/* 39 */     this.hasIcon = mlc.getBoolean(new String[] { "hasicon", "icon", "i" }, true);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean castAtEntity(SkillMetadata data, AbstractEntity target) {
/*    */     PotionEffect effect;
/* 46 */     String potion = this.effect.get((PlaceholderMeta)data, target);
/* 47 */     int duration = this.duration.get((PlaceholderMeta)data, target);
/* 48 */     int lvl = this.lvl.get((PlaceholderMeta)data, target);
/*    */     
/*    */     try {
/* 51 */       if (MythicMobs.inst().getMinecraftVersion() >= 13) {
/* 52 */         effect = new PotionEffect(PotionEffectType.getByName(potion), duration, lvl, this.ambientParticles, this.hasParticles, this.hasIcon);
/*    */       } else {
/* 54 */         effect = new PotionEffect(PotionEffectType.getByName(potion), duration, lvl);
/*    */       } 
/* 56 */     } catch (Exception ex) {
/* 57 */       MythicLogger.errorMechanicConfig(this, this.config, "The 'type' attribute must be a valid potion type.");
/* 58 */       return false;
/*    */     } 
/*    */     
/* 61 */     if (this.overwrite) {
/* 62 */       LivingEntity l = (LivingEntity)target.getBukkitEntity();
/* 63 */       l.addPotionEffect(effect, true);
/*    */     } else {
/* 65 */       target.addPotionEffect(effect);
/*    */     } 
/* 67 */     return true;
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\skills\mechanics\PotionMechanic.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */