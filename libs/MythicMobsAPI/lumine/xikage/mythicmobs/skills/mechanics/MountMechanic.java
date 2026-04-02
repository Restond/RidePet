/*    */ package lumine.xikage.mythicmobs.skills.mechanics;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
/*    */ import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
/*    */ import io.lumine.xikage.mythicmobs.logging.MythicLogger;
/*    */ import io.lumine.xikage.mythicmobs.mobs.ActiveMob;
/*    */ import io.lumine.xikage.mythicmobs.mobs.MythicMob;
/*    */ import io.lumine.xikage.mythicmobs.skills.INoTargetSkill;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillMechanic;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillMetadata;
/*    */ import io.lumine.xikage.mythicmobs.skills.placeholders.PlaceholderMeta;
/*    */ import io.lumine.xikage.mythicmobs.skills.placeholders.parsers.PlaceholderString;
/*    */ import io.lumine.xikage.mythicmobs.util.annotations.MythicMechanic;
/*    */ 
/*    */ @MythicMechanic(author = "Ashijin", name = "mount", aliases = {"vehicle"}, description = "Summons a vehicle for the caster")
/*    */ public class MountMechanic
/*    */   extends SkillMechanic implements INoTargetSkill {
/*    */   public MountMechanic(String skill, MythicLineConfig mlc) {
/* 19 */     super(skill, mlc);
/* 20 */     this.ASYNC_SAFE = false;
/*    */     
/* 22 */     this.strType = mlc.getPlaceholderString(new String[] { "mob", "m", "type", "t" }, "", new String[0]);
/*    */   }
/*    */   private PlaceholderString strType;
/*    */   
/*    */   public boolean cast(SkillMetadata data) {
/* 27 */     MythicMob mount = getPlugin().getMobManager().getMythicMob(this.strType.get((PlaceholderMeta)data));
/*    */     
/* 29 */     if (mount == null) {
/* 30 */       MythicLogger.errorMechanicConfig(this, this.config, "The 'mob' attribute must be a valid MythicMob.");
/* 31 */       return false;
/*    */     } 
/*    */     
/* 34 */     AbstractEntity me = mount.spawn(data.getCaster().getEntity().getLocation(), data.getCaster().getLevel()).getEntity();
/*    */     
/* 36 */     if (me == null) return false;
/*    */     
/* 38 */     me.setPassenger(data.getCaster().getEntity());
/*    */     
/* 40 */     ((ActiveMob)getPlugin().getMobManager().getActiveMob(me.getUniqueId()).get()).setOwner(data.getCaster().getEntity().getUniqueId());
/* 41 */     ((ActiveMob)getPlugin().getMobManager().getActiveMob(me.getUniqueId()).get()).setParent(data.getCaster());
/* 42 */     return true;
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\skills\mechanics\MountMechanic.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */