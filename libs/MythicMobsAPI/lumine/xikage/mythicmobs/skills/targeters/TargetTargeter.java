/*    */ package lumine.xikage.mythicmobs.skills.targeters;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
/*    */ import io.lumine.xikage.mythicmobs.adapters.bukkit.BukkitAdapter;
/*    */ import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
/*    */ import io.lumine.xikage.mythicmobs.mobs.ActiveMob;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillCaster;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillMetadata;
/*    */ import io.lumine.xikage.mythicmobs.skills.targeters.IEntitySelector;
/*    */ import io.lumine.xikage.mythicmobs.util.MythicUtil;
/*    */ import io.lumine.xikage.mythicmobs.util.annotations.MythicTargeter;
/*    */ import java.util.HashSet;
/*    */ import org.bukkit.entity.Entity;
/*    */ import org.bukkit.entity.LivingEntity;
/*    */ import org.bukkit.entity.Player;
/*    */ 
/*    */ @MythicTargeter(author = "Ashijin", name = "target", aliases = {"T"}, description = "Targets the caster's target")
/*    */ public class TargetTargeter
/*    */   extends IEntitySelector {
/*    */   public TargetTargeter(MythicLineConfig mlc) {
/* 21 */     super(mlc);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public HashSet<AbstractEntity> getEntities(SkillMetadata data) {
/* 27 */     HashSet<AbstractEntity> targets = new HashSet<>();
/*    */     
/* 29 */     SkillCaster caster = data.getCaster();
/* 30 */     if (caster instanceof ActiveMob) {
/* 31 */       ActiveMob am = (ActiveMob)caster;
/* 32 */       if (am.hasThreatTable()) {
/* 33 */         targets.add(am.getThreatTable().getTopThreatHolder());
/*    */       } else {
/* 35 */         targets.add(am.getEntity().getTarget());
/*    */       } 
/* 37 */     } else if (caster.getEntity().isPlayer()) {
/* 38 */       LivingEntity le = MythicUtil.getTargetedEntity((Player)BukkitAdapter.adapt(caster.getEntity()));
/*    */       
/* 40 */       if (le != null) {
/* 41 */         targets.add(BukkitAdapter.adapt((Entity)le));
/*    */       }
/*    */     } 
/*    */     
/* 45 */     return targets;
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\skills\targeters\TargetTargeter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */