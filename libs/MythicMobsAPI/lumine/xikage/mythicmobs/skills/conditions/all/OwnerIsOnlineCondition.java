/*    */ package lumine.xikage.mythicmobs.skills.conditions.all;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.MythicMobs;
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
/*    */ import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
/*    */ import io.lumine.xikage.mythicmobs.mobs.ActiveMob;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillCondition;
/*    */ import io.lumine.xikage.mythicmobs.skills.conditions.IEntityCondition;
/*    */ import io.lumine.xikage.mythicmobs.util.annotations.MythicCondition;
/*    */ import org.bukkit.Bukkit;
/*    */ 
/*    */ @MythicCondition(author = "Ashijin", name = "ownerIsOnline", aliases = {}, description = "Checks if the owner of the target mob is online, if the owner is a player")
/*    */ public class OwnerIsOnlineCondition
/*    */   extends SkillCondition
/*    */   implements IEntityCondition {
/*    */   public OwnerIsOnlineCondition(String line, MythicLineConfig mlc) {
/* 17 */     super(line);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean check(AbstractEntity target) {
/* 22 */     ActiveMob am = MythicMobs.inst().getMobManager().getMythicMobInstance(target);
/*    */     
/* 24 */     if (am == null) {
/* 25 */       return false;
/*    */     }
/* 27 */     if (am.getOwner().isPresent()) {
/* 28 */       return (Bukkit.getPlayer(am.getOwner().get()) != null);
/*    */     }
/* 30 */     return false;
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\skills\conditions\all\OwnerIsOnlineCondition.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */