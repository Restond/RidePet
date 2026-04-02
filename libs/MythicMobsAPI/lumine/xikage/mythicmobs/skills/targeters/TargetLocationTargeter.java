/*    */ package lumine.xikage.mythicmobs.skills.targeters;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.MythicMobs;
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractLocation;
/*    */ import io.lumine.xikage.mythicmobs.adapters.bukkit.BukkitAdapter;
/*    */ import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
/*    */ import io.lumine.xikage.mythicmobs.mobs.ActiveMob;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillCaster;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillMetadata;
/*    */ import io.lumine.xikage.mythicmobs.skills.targeters.ILocationSelector;
/*    */ import io.lumine.xikage.mythicmobs.util.annotations.MythicTargeter;
/*    */ import java.util.HashSet;
/*    */ import org.bukkit.entity.Player;
/*    */ 
/*    */ @MythicTargeter(author = "Ashijin", name = "targetLocation", aliases = {"targetLoc", "TL"}, description = "Targets the location the caster is targeting")
/*    */ public class TargetLocationTargeter
/*    */   extends ILocationSelector
/*    */ {
/*    */   double yOffset;
/*    */   
/*    */   public TargetLocationTargeter(MythicLineConfig mlc) {
/* 22 */     super(mlc);
/*    */     
/* 24 */     this.yOffset = mlc.getDouble("yoffset", 0.0D);
/* 25 */     this.yOffset = mlc.getDouble("y", this.yOffset);
/*    */   }
/*    */ 
/*    */   
/*    */   public HashSet<AbstractLocation> getLocations(SkillMetadata data) {
/* 30 */     SkillCaster caster = data.getCaster();
/* 31 */     HashSet<AbstractLocation> targets = new HashSet<>();
/*    */     
/* 33 */     AbstractLocation l = null;
/*    */     
/* 35 */     if (caster instanceof ActiveMob) {
/* 36 */       ActiveMob am = (ActiveMob)caster;
/*    */       
/* 38 */       if (am.hasThreatTable()) {
/* 39 */         if (am.getThreatTable().getTopThreatHolder() != null) {
/* 40 */           l = am.getThreatTable().getTopThreatHolder().getLocation();
/*    */         }
/*    */       }
/* 43 */       else if (am.getEntity().isCreature()) {
/* 44 */         if (am.getEntity().getTarget() != null) {
/* 45 */           l = am.getEntity().getTarget().getLocation();
/*    */         }
/*    */       } else {
/* 48 */         l = am.getLastAggroCause().getLocation();
/*    */       }
/*    */     
/*    */     }
/* 52 */     else if (caster.getEntity().isPlayer()) {
/* 53 */       l = BukkitAdapter.adapt(((Player)BukkitAdapter.adapt(data.getCaster().getEntity())).getTargetBlock(MythicMobs.inst().getConfigManager().getTransparentBlocks(), 64).getLocation());
/*    */     } 
/*    */ 
/*    */     
/* 57 */     if (l != null) {
/* 58 */       l.add(0.0D, this.yOffset, 0.0D);
/* 59 */       targets.add(l);
/*    */     } 
/*    */     
/* 62 */     return targets;
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\skills\targeters\TargetLocationTargeter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */