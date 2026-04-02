/*    */ package lumine.xikage.mythicmobs.volatilecode.v1_12_R1.ai;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
/*    */ import io.lumine.xikage.mythicmobs.mobs.ai.PathfinderAdapter;
/*    */ import net.minecraft.server.v1_12_R1.EntityInsentient;
/*    */ import net.minecraft.server.v1_12_R1.PathfinderGoal;
/*    */ import org.bukkit.craftbukkit.v1_12_R1.entity.CraftLivingEntity;
/*    */ 
/*    */ public interface PathfinderHolder
/*    */   extends PathfinderAdapter
/*    */ {
/*    */   static EntityInsentient getNMSEntity(AbstractEntity entity) {
/* 13 */     return (EntityInsentient)((CraftLivingEntity)entity.getBukkitEntity()).getHandle();
/*    */   }
/*    */   
/*    */   PathfinderGoal create();
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\volatilecode\v1_12_R1\ai\PathfinderHolder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */