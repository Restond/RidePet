/*    */ package lumine.xikage.mythicmobs.skills.mechanics;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractLocation;
/*    */ import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
/*    */ import io.lumine.xikage.mythicmobs.skills.ITargetedEntitySkill;
/*    */ import io.lumine.xikage.mythicmobs.skills.ITargetedLocationSkill;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillMechanic;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillMetadata;
/*    */ import io.lumine.xikage.mythicmobs.skills.placeholders.PlaceholderMeta;
/*    */ import io.lumine.xikage.mythicmobs.skills.placeholders.parsers.PlaceholderString;
/*    */ import io.lumine.xikage.mythicmobs.util.annotations.MythicMechanic;
/*    */ 
/*    */ @MythicMechanic(author = "Ashijin", name = "effect:sound", aliases = {"sound", "s", "e:sound", "e:s"}, description = "Plays a sound at the target location")
/*    */ public class SoundEffect
/*    */   extends SkillMechanic implements ITargetedEntitySkill, ITargetedLocationSkill {
/*    */   private PlaceholderString sound;
/*    */   private float pitch;
/*    */   
/*    */   public SoundEffect(String skill, MythicLineConfig mlc) {
/* 21 */     super(skill, mlc);
/*    */     
/* 23 */     this.sound = mlc.getPlaceholderString(new String[] { "sound", "s" }, "entity.zombie.attack_iron_door", new String[0]);
/* 24 */     this.pitch = mlc.getFloat(new String[] { "pitch", "p" }, 1.0F);
/* 25 */     this.volume = mlc.getFloat(new String[] { "volume", "v" }, 1.0F);
/* 26 */     this.radiusSq = mlc.getDouble(new String[] { "radius", "r" }, (this.volume * 16.0F));
/* 27 */     this.radiusSq = Math.pow(this.radiusSq, 2.0D);
/*    */   }
/*    */   private float volume; private double radiusSq;
/*    */   
/*    */   public boolean castAtLocation(SkillMetadata data, AbstractLocation target) {
/* 32 */     getPlugin().getVolatileCodeHandler().PlaySoundAtLocation(target, this.sound.get((PlaceholderMeta)data), this.volume, this.pitch);
/* 33 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean castAtEntity(SkillMetadata data, AbstractEntity target) {
/* 38 */     getPlugin().getVolatileCodeHandler().getWorldHandler().playSoundAtLocation(target.getLocation(), this.sound.get((PlaceholderMeta)data, target), this.volume, this.pitch, this.radiusSq);
/* 39 */     return false;
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\skills\mechanics\SoundEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */