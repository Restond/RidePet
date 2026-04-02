/*    */ package lumine.xikage.mythicmobs.skills.mechanics;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.MythicMobs;
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractLocation;
/*    */ import io.lumine.xikage.mythicmobs.drops.DropMetadata;
/*    */ import io.lumine.xikage.mythicmobs.drops.DropTable;
/*    */ import io.lumine.xikage.mythicmobs.drops.LootBag;
/*    */ import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
/*    */ import io.lumine.xikage.mythicmobs.logging.MythicLogger;
/*    */ import io.lumine.xikage.mythicmobs.skills.ITargetedEntitySkill;
/*    */ import io.lumine.xikage.mythicmobs.skills.ITargetedLocationSkill;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillMechanic;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillMetadata;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillString;
/*    */ import io.lumine.xikage.mythicmobs.util.annotations.MythicMechanic;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import java.util.Optional;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @MythicMechanic(author = "Ashijin", name = "dropitem", aliases = {"drop", "dropitems", "itemdrop"}, description = "Drops an item or droptable")
/*    */ public class DropItemMechanic
/*    */   extends SkillMechanic
/*    */   implements ITargetedEntitySkill, ITargetedLocationSkill
/*    */ {
/*    */   private String itemString;
/* 32 */   private List<String> items = new ArrayList<>();
/*    */   private DropTable dropTable;
/*    */   
/*    */   public DropItemMechanic(String skill, MythicLineConfig mlc) {
/* 36 */     super(skill, mlc);
/* 37 */     this.ASYNC_SAFE = false;
/*    */     
/* 39 */     this.itemString = mlc.getString(new String[] { "items", "item", "i" });
/*    */     
/* 41 */     Optional<DropTable> maybeTable = getPlugin().getDropManager().getDropTable(this.itemString);
/*    */     
/* 43 */     if (maybeTable.isPresent()) {
/* 44 */       this.dropTable = maybeTable.get();
/*    */       
/*    */       return;
/*    */     } 
/* 48 */     if (this.itemString.startsWith("\"")) {
/*    */       try {
/* 50 */         this.itemString = this.itemString.substring(1, this.itemString.length() - 1);
/* 51 */       } catch (Exception ex) {
/* 52 */         MythicLogger.errorMechanicConfig(this, mlc, "The 'items' attribute is required.");
/* 53 */         MythicMobs.inst().handleException(ex);
/*    */       } 
/* 55 */       this.itemString = SkillString.parseMessageSpecialChars(this.itemString);
/*    */     } 
/*    */     
/* 58 */     for (String s : this.itemString.split(",")) {
/* 59 */       this.items.add(s);
/*    */     }
/*    */     
/* 62 */     this.dropTable = new DropTable("DropItemMechanic", "DropItemMechanic", this.items);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean castAtEntity(SkillMetadata data, AbstractEntity target) {
/* 67 */     LootBag loot = this.dropTable.generate(new DropMetadata(data.getCaster(), data.getCaster().getEntity()));
/* 68 */     loot.drop(target.getLocation());
/*    */     
/* 70 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean castAtLocation(SkillMetadata data, AbstractLocation target) {
/* 75 */     LootBag loot = this.dropTable.generate(new DropMetadata(data.getCaster(), data.getCaster().getEntity()));
/* 76 */     loot.drop(target);
/*    */     
/* 78 */     return true;
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\skills\mechanics\DropItemMechanic.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */