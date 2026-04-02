/*    */ package lumine.xikage.mythicmobs.skills.conditions.all;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
/*    */ import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillCondition;
/*    */ import io.lumine.xikage.mythicmobs.skills.conditions.IEntityCondition;
/*    */ import io.lumine.xikage.mythicmobs.util.annotations.MythicCondition;
/*    */ import io.lumine.xikage.mythicmobs.util.annotations.MythicField;
/*    */ import io.lumine.xikage.mythicmobs.util.types.RangedDouble;
/*    */ import org.bukkit.entity.Entity;
/*    */ 
/*    */ 
/*    */ @MythicCondition(author = "Ashijin", name = "altitude", aliases = {"heightfromsurface"}, description = "Tests how far above the ground the target entity is")
/*    */ public class AltitudeCondition
/*    */   extends SkillCondition
/*    */   implements IEntityCondition
/*    */ {
/*    */   @MythicField(name = "height", aliases = {"altitude", "a", "h"}, description = "The height range to check")
/*    */   private RangedDouble height;
/*    */   
/*    */   public AltitudeCondition(String line, MythicLineConfig mlc) {
/* 22 */     super(line);
/*    */     
/* 24 */     this.height = new RangedDouble(mlc.getString(new String[] { "height", "h", "altitude", "a" }, this.conditionVar, new String[0]));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean check(AbstractEntity target) {
/* 30 */     Entity e = target.getBukkitEntity();
/* 31 */     int ydiff = e.getLocation().getBlockY() - e.getWorld().getHighestBlockYAt(e.getLocation());
/*    */     
/* 33 */     return this.height.equals(Integer.valueOf(ydiff));
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\skills\conditions\all\AltitudeCondition.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */