/*    */ package lumine.xikage.mythicmobs.skills.mechanics;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.MythicMobs;
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
/*    */ import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
/*    */ import io.lumine.xikage.mythicmobs.items.LegacyItemConverter;
/*    */ import io.lumine.xikage.mythicmobs.skills.ITargetedEntitySkill;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillMechanic;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillMetadata;
/*    */ import io.lumine.xikage.mythicmobs.util.annotations.MythicMechanic;
/*    */ import org.bukkit.Material;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @MythicMechanic(author = "Ashijin", name = "prison", description = "Traps the target entity in blocks")
/*    */ public class PrisonMechanic
/*    */   extends SkillMechanic
/*    */   implements ITargetedEntitySkill
/*    */ {
/*    */   protected int duration;
/*    */   protected boolean breakable;
/* 34 */   protected Material material = Material.AIR;
/*    */   protected byte materialData;
/*    */   
/*    */   public PrisonMechanic(String line, MythicLineConfig mlc) {
/* 38 */     super(line, mlc);
/* 39 */     this.ASYNC_SAFE = false;
/*    */     
/* 41 */     this.duration = mlc.getInteger(new String[] { "duration", "d" }, 100);
/* 42 */     this.breakable = mlc.getBoolean(new String[] { "breakable", "b" }, false);
/* 43 */     String mat = mlc.getString(new String[] { "material", "m", "type", "t" }, "ICE", new String[0]);
/* 44 */     this.materialData = (byte)mlc.getInteger(new String[] { "materialdata", "md" }, 0);
/*    */     
/* 46 */     if (MythicMobs.inst().getMinecraftVersion() >= 13) {
/*    */       try {
/* 48 */         this.material = (this.materialData == 0) ? Material.valueOf(mat) : LegacyItemConverter.getMaterial(mat, this.materialData);
/* 49 */       } catch (Exception ex) {
/* 50 */         this.material = LegacyItemConverter.getMaterial(mat, this.materialData);
/*    */       } 
/*    */     } else {
/* 53 */       this.material = Material.getMaterial(mat.toUpperCase());
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean castAtEntity(SkillMetadata data, AbstractEntity target) {
/* 59 */     if (this.material == null) return false;
/*    */     
/* 61 */     new Prison(this, target);
/*    */     
/* 63 */     return true;
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\skills\mechanics\PrisonMechanic.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */