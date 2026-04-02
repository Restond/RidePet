/*    */ package lumine.xikage.mythicmobs.skills.targeters;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
/*    */ import io.lumine.xikage.mythicmobs.adapters.bukkit.BukkitAdapter;
/*    */ import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillCaster;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillMetadata;
/*    */ import io.lumine.xikage.mythicmobs.skills.targeters.IEntitySelector;
/*    */ import java.util.HashSet;
/*    */ import org.bukkit.entity.Entity;
/*    */ import org.bukkit.entity.Wolf;
/*    */ 
/*    */ public class WolfOwnerTargeter
/*    */   extends IEntitySelector
/*    */ {
/*    */   public WolfOwnerTargeter(MythicLineConfig mlc) {
/* 17 */     super(mlc);
/*    */   }
/*    */ 
/*    */   
/*    */   public HashSet<AbstractEntity> getEntities(SkillMetadata data) {
/* 22 */     SkillCaster am = data.getCaster();
/* 23 */     HashSet<AbstractEntity> targets = new HashSet<>();
/*    */     
/* 25 */     if (am.getEntity().getBukkitEntity() instanceof Wolf) {
/* 26 */       Wolf w = (Wolf)am.getEntity().getBukkitEntity();
/*    */       
/* 28 */       if (w.getOwner() != null) {
/* 29 */         targets.add(BukkitAdapter.adapt((Entity)w.getOwner()));
/*    */       }
/*    */     } 
/*    */     
/* 33 */     return targets;
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\skills\targeters\WolfOwnerTargeter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */