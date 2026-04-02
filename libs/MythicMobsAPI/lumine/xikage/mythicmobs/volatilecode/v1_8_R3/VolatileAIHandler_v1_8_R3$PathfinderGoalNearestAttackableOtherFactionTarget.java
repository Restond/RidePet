/*     */ package lumine.xikage.mythicmobs.volatilecode.v1_8_R3;
/*     */ 
/*     */ import com.google.common.base.Predicate;
/*     */ import io.lumine.xikage.mythicmobs.MythicMobs;
/*     */ import io.lumine.xikage.mythicmobs.mobs.ActiveMob;
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
/*     */ public class PathfinderGoalNearestAttackableOtherFactionTarget
/*     */   extends PathfinderGoalNearestAttackableTarget
/*     */ {
/*     */   public PathfinderGoalNearestAttackableOtherFactionTarget(VolatileAIHandler_v1_8_R3 this$0, EntityCreature entitycreature, Class oclass, int i, boolean flag) {
/* 406 */     this(entitycreature, oclass, i, flag, false);
/*     */   }
/*     */   
/*     */   public PathfinderGoalNearestAttackableOtherFactionTarget(VolatileAIHandler_v1_8_R3 this$0, EntityCreature entitycreature, Class oclass, int i, boolean flag, boolean flag1) {
/* 410 */     this(entitycreature, oclass, i, flag, flag1, (Predicate)null);
/*     */   }
/*     */ 
/*     */   
/*     */   public PathfinderGoalNearestAttackableOtherFactionTarget(EntityCreature entitycreature, Class oclass, int i, boolean flag, boolean flag1, Predicate ientityselector) {
/* 415 */     super(entitycreature, oclass, i, flag, flag1, ientityselector);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean a() {
/* 422 */     int pB = ((Integer)MythicUtil.getPrivateField("g", PathfinderGoalNearestAttackableTarget.class, this)).intValue();
/*     */     
/* 424 */     if (pB > 0 && MythicMobs.r.nextInt(pB) != 0) {
/* 425 */       return false;
/*     */     }
/*     */     
/* 428 */     Class pA = (Class)MythicUtil.getPrivateField("a", PathfinderGoalNearestAttackableTarget.class, this);
/* 429 */     PathfinderGoalNearestAttackableTarget.DistanceComparator pE = (PathfinderGoalNearestAttackableTarget.DistanceComparator)MythicUtil.getPrivateField("b", PathfinderGoalNearestAttackableTarget.class, this);
/* 430 */     Predicate pF = (Predicate)MythicUtil.getPrivateField("c", PathfinderGoalNearestAttackableTarget.class, this);
/*     */     
/* 432 */     double d0 = f();
/* 433 */     List<?> list = this.e.world.a(pA, this.e.getBoundingBox().grow(d0, 4.0D, d0), pF);
/* 434 */     list.remove(this.c);
/*     */     
/* 436 */     if (list.isEmpty()) return false;
/*     */     
/* 438 */     Collections.sort(list, (Comparator<?>)pE);
/*     */     
/* 440 */     ActiveMob am = MythicMobs.inst().getMobManager().getMythicMobInstance((Entity)this.e.getBukkitEntity());
/*     */     
/* 442 */     if (am.hasFaction()) {
/*     */       
/* 444 */       for (int i = 0; i < list.size(); i++) {
/* 445 */         EntityLiving el = (EntityLiving)list.get(i);
/*     */         
/* 447 */         if (MythicMobs.inst().getMobManager().isActiveMob(el.getBukkitEntity().getUniqueId()) && 
/* 448 */           MythicMobs.inst().getMobManager().getMythicMobInstance((Entity)el.getBukkitEntity()).hasFaction()) {
/* 449 */           if (!MythicMobs.inst().getMobManager().getMythicMobInstance((Entity)el.getBukkitEntity()).getFaction().equals(am.getFaction())) {
/* 450 */             MythicUtil.setPrivateField("d", PathfinderGoalNearestAttackableTarget.class, this, el);
/* 451 */             return true;
/*     */           }
/*     */         
/*     */         }
/*     */         else {
/*     */           
/* 457 */           MythicUtil.setPrivateField("d", PathfinderGoalNearestAttackableTarget.class, this, el);
/* 458 */           return true;
/*     */         } 
/* 460 */       }  return false;
/*     */     } 
/* 462 */     MythicUtil.setPrivateField("d", PathfinderGoalNearestAttackableTarget.class, this, list.get(0));
/* 463 */     return true;
/*     */   }
/*     */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\volatilecode\v1_8_R3\VolatileAIHandler_v1_8_R3$PathfinderGoalNearestAttackableOtherFactionTarget.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */