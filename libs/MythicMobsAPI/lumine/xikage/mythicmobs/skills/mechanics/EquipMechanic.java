/*    */ package lumine.xikage.mythicmobs.skills.mechanics;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.MythicMobs;
/*    */ import io.lumine.xikage.mythicmobs.drops.DropMetadata;
/*    */ import io.lumine.xikage.mythicmobs.drops.DropTable;
/*    */ import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
/*    */ import io.lumine.xikage.mythicmobs.logging.MythicLogger;
/*    */ import io.lumine.xikage.mythicmobs.skills.INoTargetSkill;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillMechanic;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillMetadata;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillString;
/*    */ import io.lumine.xikage.mythicmobs.util.annotations.MythicMechanic;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ 
/*    */ @MythicMechanic(author = "Ashijin", name = "equip", description = "Causes the caster to equip an item or droptable")
/*    */ public class EquipMechanic
/*    */   extends SkillMechanic
/*    */   implements INoTargetSkill {
/*    */   private String equipString;
/* 21 */   private List<String> equipment = new ArrayList<>();
/*    */   private DropTable equipmentTable;
/*    */   
/*    */   public EquipMechanic(String skill, MythicLineConfig mlc) {
/* 25 */     super(skill, mlc);
/* 26 */     this.ASYNC_SAFE = false;
/*    */     
/* 28 */     this.equipString = mlc.getString(new String[] { "items", "item", "i", "equipment", "equip", "e" }, null, new String[0]);
/*    */     
/* 30 */     if (this.equipString.startsWith("\"")) {
/*    */       try {
/* 32 */         this.equipString = this.equipString.substring(1, this.equipString.length() - 1);
/* 33 */       } catch (Exception ex) {
/* 34 */         MythicLogger.errorMechanicConfig(this, mlc, "The 'items' attribute is required.");
/* 35 */         MythicMobs.inst().handleException(ex);
/*    */       } 
/* 37 */       this.equipString = SkillString.parseMessageSpecialChars(this.equipString);
/*    */     } 
/*    */     
/* 40 */     for (String s : this.equipString.split(",")) {
/* 41 */       this.equipment.add(s);
/*    */     }
/* 43 */     this.equipmentTable = new DropTable("EquipMechanic", "EquipMechanic", this.equipment);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean cast(SkillMetadata data) {
/* 49 */     this.equipmentTable.generate(new DropMetadata(data.getCaster(), data.getCaster().getEntity())).equip(data.getCaster().getEntity());
/*    */     
/* 51 */     return true;
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\skills\mechanics\EquipMechanic.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */