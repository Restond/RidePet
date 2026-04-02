/*    */ package lumine.xikage.mythicmobs.skills.conditions.all;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
/*    */ import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillCondition;
/*    */ import io.lumine.xikage.mythicmobs.skills.conditions.IEntityCondition;
/*    */ import io.lumine.xikage.mythicmobs.util.annotations.MythicCondition;
/*    */ import io.lumine.xikage.mythicmobs.util.annotations.MythicField;
/*    */ import java.util.HashSet;
/*    */ import java.util.Set;
/*    */ import org.bukkit.entity.EntityType;
/*    */ 
/*    */ @MythicCondition(author = "Ashijin", name = "entityType", aliases = {"mobtype"}, description = "Tests the entity type of the target")
/*    */ public class EntityTypeCondition
/*    */   extends SkillCondition
/*    */   implements IEntityCondition {
/*    */   @MythicField(name = "type", aliases = {"types", "t"}, description = "A list of entity types to match")
/* 18 */   protected Set<EntityType> types = new HashSet<>();
/*    */ 
/*    */   
/*    */   public EntityTypeCondition(String line, MythicLineConfig mlc) {
/* 22 */     super(line);
/*    */     
/* 24 */     String t = mlc.getString(new String[] { "types", "type", "t" }, this.conditionVar, new String[0]);
/*    */     
/* 26 */     for (String tt : t.split(",")) {
/*    */       try {
/* 28 */         EntityType type = EntityType.valueOf(tt.toUpperCase());
/* 29 */         this.types.add(type);
/* 30 */       } catch (Exception exception) {}
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean check(AbstractEntity e) {
/* 36 */     return this.types.contains(e.getBukkitEntity().getType());
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\skills\conditions\all\EntityTypeCondition.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */