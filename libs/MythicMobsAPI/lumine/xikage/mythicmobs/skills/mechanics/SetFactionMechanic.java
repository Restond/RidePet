/*    */ package lumine.xikage.mythicmobs.skills.mechanics;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
/*    */ import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
/*    */ import io.lumine.xikage.mythicmobs.mobs.ActiveMob;
/*    */ import io.lumine.xikage.mythicmobs.skills.ITargetedEntitySkill;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillMechanic;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillMetadata;
/*    */ import io.lumine.xikage.mythicmobs.skills.placeholders.PlaceholderMeta;
/*    */ import io.lumine.xikage.mythicmobs.skills.placeholders.parsers.PlaceholderString;
/*    */ import io.lumine.xikage.mythicmobs.util.annotations.MythicMechanic;
/*    */ 
/*    */ @MythicMechanic(author = "Ashijin", name = "setfaction", description = "Sets the target mob's faction")
/*    */ public class SetFactionMechanic extends SkillMechanic implements ITargetedEntitySkill {
/*    */   private PlaceholderString faction;
/*    */   
/*    */   public SetFactionMechanic(String skill, MythicLineConfig mlc) {
/* 18 */     super(skill, mlc);
/*    */     
/* 20 */     this.faction = mlc.getPlaceholderString(new String[] { "faction", "f" }, null, new String[0]);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean castAtEntity(SkillMetadata data, AbstractEntity target) {
/* 25 */     if (getPlugin().getMobManager().isActiveMob(target.getUniqueId())) {
/* 26 */       ActiveMob at = getPlugin().getMobManager().getActiveMob(target.getUniqueId()).get();
/* 27 */       at.setFaction(this.faction.get((PlaceholderMeta)data, target));
/* 28 */       return true;
/*    */     } 
/* 30 */     return true;
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\skills\mechanics\SetFactionMechanic.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */