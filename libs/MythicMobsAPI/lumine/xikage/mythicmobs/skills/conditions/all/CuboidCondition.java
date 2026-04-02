/*    */ package lumine.xikage.mythicmobs.skills.conditions.all;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
/*    */ import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
/*    */ import io.lumine.xikage.mythicmobs.logging.MythicLogger;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillCondition;
/*    */ import io.lumine.xikage.mythicmobs.skills.conditions.IEntityComparisonCondition;
/*    */ import io.lumine.xikage.mythicmobs.util.annotations.MythicCondition;
/*    */ import io.lumine.xikage.mythicmobs.util.annotations.MythicField;
/*    */ import org.bukkit.util.Vector;
/*    */ 
/*    */ @MythicCondition(author = "jaylawl", name = "cuboid", aliases = {"incuboid"}, version = "4.5", description = "Whether the target is within the given cuboid between location1 x location2")
/*    */ public class CuboidCondition extends SkillCondition implements IEntityComparisonCondition {
/*    */   @MythicField(name = "location1", aliases = {"loc1", "l1", "a"}, description = "x,y,z coordinates for the 1st point")
/*    */   protected String coords1;
/*    */   @MythicField(name = "location2", aliases = {"loc2", "l2", "b"}, description = "x,y,z coordinates for the 2nd point")
/*    */   protected String coords2;
/*    */   @MythicField(name = "relative", aliases = {"r"}, description = "Whether or not the coordinates should be relative to the caster")
/*    */   protected Boolean relative;
/*    */   protected double x1;
/*    */   protected double x2;
/*    */   protected double xmin;
/*    */   protected double y1;
/*    */   protected double y2;
/* 25 */   protected double xmax = 0.0D; protected double ymin;
/* 26 */   protected double ymax = 0.0D; protected double z1;
/* 27 */   protected double zmax = 0.0D; protected double z2; protected double zmin;
/*    */   
/*    */   public CuboidCondition(String line, MythicLineConfig mlc) {
/* 30 */     super(line);
/* 31 */     this.coords1 = mlc.getString(new String[] { "location1", "loc1", "l1", "a" }, null, new String[0]);
/* 32 */     this.coords2 = mlc.getString(new String[] { "location2", "loc2", "l2", "b" }, null, new String[0]);
/* 33 */     this.relative = Boolean.valueOf(mlc.getBoolean(new String[] { "relative", "r" }, false));
/*    */     
/* 35 */     if (this.coords1 != null && this.coords2 != null) {
/* 36 */       String[] split1 = this.coords1.split(",");
/* 37 */       String[] split2 = this.coords2.split(",");
/*    */       try {
/* 39 */         this.x1 = Double.parseDouble(split1[0]);
/* 40 */         this.y1 = Double.parseDouble(split1[1]);
/* 41 */         this.z1 = Double.parseDouble(split1[2]);
/* 42 */         this.x2 = Double.parseDouble(split2[0]);
/* 43 */         this.y2 = Double.parseDouble(split2[1]);
/* 44 */         this.z2 = Double.parseDouble(split2[2]);
/* 45 */         this.xmin = Math.min(this.x1, this.x2);
/* 46 */         this.ymin = Math.min(this.y1, this.y2);
/* 47 */         this.zmin = Math.min(this.z1, this.z2);
/* 48 */         this.xmax = Math.max(this.x1, this.x2);
/* 49 */         this.ymax = Math.max(this.y1, this.y2);
/* 50 */         this.zmax = Math.max(this.z1, this.z2);
/* 51 */       } catch (Exception ex) {
/* 52 */         MythicLogger.errorConditionConfig(this, mlc, "The location attribute must be in the format \"location=x,y,z.\"");
/*    */       } 
/*    */     } else {
/* 55 */       MythicLogger.errorConditionConfig(this, mlc, "Two locations are required");
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean check(AbstractEntity e, AbstractEntity t) {
/* 61 */     Vector v = new Vector(t.getLocation().getX(), t.getLocation().getY(), t.getLocation().getZ());
/* 62 */     if (this.relative.booleanValue()) {
/* 63 */       this.xmin += e.getLocation().getX();
/* 64 */       this.ymin += e.getLocation().getY();
/* 65 */       this.zmin += e.getLocation().getZ();
/* 66 */       this.xmax += e.getLocation().getX();
/* 67 */       this.ymax += e.getLocation().getY();
/* 68 */       this.zmax += e.getLocation().getZ();
/*    */     } 
/* 70 */     return v.isInAABB(new Vector(this.xmin, this.ymin, this.zmin), new Vector(this.xmax, this.ymax, this.zmax));
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\skills\conditions\all\CuboidCondition.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */