/*     */ package lumine.xikage.mythicmobs.volatilecode.v1_8_R3;
/*     */ 
/*     */ import com.google.common.base.Predicate;
/*     */ import io.lumine.xikage.mythicmobs.MythicMobs;
/*     */ import io.lumine.xikage.mythicmobs.util.MythicUtil;
/*     */ import io.lumine.xikage.mythicmobs.volatilecode.v1_8_R3.VolatileAIHandler_v1_8_R3;
/*     */ import java.util.Collections;
/*     */ import java.util.Comparator;
/*     */ import java.util.List;
/*     */ import net.minecraft.server.v1_8_R3.EntityCreature;
/*     */ import net.minecraft.server.v1_8_R3.EntityLiving;
/*     */ import net.minecraft.server.v1_8_R3.PathfinderGoalNearestAttackableTarget;
/*     */ import org.bukkit.entity.Entity;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PathfinderGoalNearestAttackableSpecificFactionTarget
/*     */   extends PathfinderGoalNearestAttackableTarget
/*     */ {
/*     */   private final String faction;
/*     */   
/*     */   public PathfinderGoalNearestAttackableSpecificFactionTarget(VolatileAIHandler_v1_8_R3 this$0, EntityCreature entitycreature, Class oclass, String faction, int i, boolean flag) {
/* 475 */     this(entitycreature, oclass, faction, i, flag, false);
/*     */   }
/*     */   
/*     */   public PathfinderGoalNearestAttackableSpecificFactionTarget(VolatileAIHandler_v1_8_R3 this$0, EntityCreature entitycreature, Class oclass, String faction, int i, boolean flag, boolean flag1) {
/* 479 */     this(entitycreature, oclass, faction, i, flag, flag1, (Predicate)null);
/*     */   }
/*     */ 
/*     */   
/*     */   public PathfinderGoalNearestAttackableSpecificFactionTarget(EntityCreature entitycreature, Class oclass, String faction, int i, boolean flag, boolean flag1, Predicate ientityselector) {
/* 484 */     super(entitycreature, oclass, i, flag, flag1, ientityselector);
/* 485 */     this.faction = faction;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean a() {
/* 492 */     int pB = ((Integer)MythicUtil.getPrivateField("g", PathfinderGoalNearestAttackableTarget.class, this)).intValue();
/*     */     
/* 494 */     if (pB > 0 && MythicMobs.r.nextInt(pB) != 0) {
/* 495 */       return false;
/*     */     }
/*     */     
/* 498 */     Class pA = (Class)MythicUtil.getPrivateField("a", PathfinderGoalNearestAttackableTarget.class, this);
/* 499 */     PathfinderGoalNearestAttackableTarget.DistanceComparator pE = (PathfinderGoalNearestAttackableTarget.DistanceComparator)MythicUtil.getPrivateField("b", PathfinderGoalNearestAttackableTarget.class, this);
/* 500 */     Predicate pF = (Predicate)MythicUtil.getPrivateField("c", PathfinderGoalNearestAttackableTarget.class, this);
/*     */     
/* 502 */     double d0 = f();
/* 503 */     List<?> list = this.e.world.a(pA, this.e.getBoundingBox().grow(d0, 4.0D, d0), pF);
/* 504 */     list.remove(this.c);
/*     */     
/* 506 */     if (list.isEmpty()) return false;
/*     */     
/* 508 */     Collections.sort(list, (Comparator<?>)pE);
/*     */ 
/*     */ 
/*     */     
/* 512 */     for (int i = 0; i < list.size(); i++) {
/* 513 */       EntityLiving el = (EntityLiving)list.get(i);
/*     */       
/* 515 */       if (MythicMobs.inst().getMobManager().isActiveMob(el.getBukkitEntity().getUniqueId()) && 
/* 516 */         MythicMobs.inst().getMobManager().getMythicMobInstance((Entity)el.getBukkitEntity()).hasFaction() && 
/* 517 */         MythicMobs.inst().getMobManager().getMythicMobInstance((Entity)el.getBukkitEntity()).getFaction().equals(this.faction)) {
/* 518 */         MythicUtil.setPrivateField("d", PathfinderGoalNearestAttackableTarget.class, this, el);
/* 519 */         return true;
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 524 */     return false;
/*     */   }
/*     */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\volatilecode\v1_8_R3\VolatileAIHandler_v1_8_R3$PathfinderGoalNearestAttackableSpecificFactionTarget.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */