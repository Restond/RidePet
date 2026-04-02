/*    */ package lumine.xikage.mythicmobs.skills.conditions.all;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.MythicMobs;
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
/*    */ import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
/*    */ import io.lumine.xikage.mythicmobs.mobs.ActiveMob;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillCondition;
/*    */ import io.lumine.xikage.mythicmobs.skills.conditions.IEntityCondition;
/*    */ import io.lumine.xikage.mythicmobs.util.annotations.MythicCondition;
/*    */ import io.lumine.xikage.mythicmobs.util.annotations.MythicField;
/*    */ import io.lumine.xikage.mythicmobs.util.types.RangedDouble;
/*    */ 
/*    */ @MythicCondition(author = "Ashijin", name = "playerKills", aliases = {}, description = "Matches how many players the target mob has killed")
/*    */ public class PlayerKillsCondition
/*    */   extends SkillCondition implements IEntityCondition {
/*    */   @MythicField(name = "kills", aliases = {"k"}, description = "The number range to match")
/*    */   private RangedDouble kills;
/*    */   
/*    */   public PlayerKillsCondition(String line, MythicLineConfig mlc) {
/* 20 */     super(line);
/*    */     
/* 22 */     String k = mlc.getString(new String[] { "kills", "k" }, "0", new String[] { this.conditionVar });
/* 23 */     this.kills = new RangedDouble(k);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean check(AbstractEntity entity) {
/* 28 */     if (MythicMobs.inst().getMobManager().isActiveMob(entity)) {
/* 29 */       ActiveMob am = MythicMobs.inst().getMobManager().getMythicMobInstance(entity);
/* 30 */       return this.kills.equals(Integer.valueOf(am.getPlayerKills()));
/*    */     } 
/* 32 */     return false;
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\skills\conditions\all\PlayerKillsCondition.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */