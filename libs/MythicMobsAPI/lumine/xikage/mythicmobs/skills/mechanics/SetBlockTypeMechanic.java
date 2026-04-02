/*    */ package lumine.xikage.mythicmobs.skills.mechanics;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.MythicMobs;
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractLocation;
/*    */ import io.lumine.xikage.mythicmobs.adapters.bukkit.BukkitAdapter;
/*    */ import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
/*    */ import io.lumine.xikage.mythicmobs.items.LegacyItemConverter;
/*    */ import io.lumine.xikage.mythicmobs.logging.MythicLogger;
/*    */ import io.lumine.xikage.mythicmobs.skills.ITargetedEntitySkill;
/*    */ import io.lumine.xikage.mythicmobs.skills.ITargetedLocationSkill;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillMechanic;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillMetadata;
/*    */ import org.bukkit.Location;
/*    */ import org.bukkit.Material;
/*    */ 
/*    */ public class SetBlockTypeMechanic
/*    */   extends SkillMechanic implements ITargetedEntitySkill, ITargetedLocationSkill {
/* 19 */   private Material material = Material.DIRT;
/* 20 */   private byte materialData = 0;
/*    */   
/*    */   public SetBlockTypeMechanic(String skill, MythicLineConfig mlc) {
/* 23 */     super(skill, mlc);
/* 24 */     this.ASYNC_SAFE = false;
/*    */     
/* 26 */     String strMaterial = mlc.getString(new String[] { "material", "mat", "m" }, "DIRT", new String[0]).toUpperCase();
/* 27 */     this.materialData = (byte)mlc.getInteger(new String[] { "materialdata", "md", "data", "dv" }, 0);
/*    */     
/* 29 */     if (MythicMobs.inst().getMinecraftVersion() >= 13) {
/*    */       try {
/* 31 */         this.material = (this.materialData == 0) ? Material.valueOf(strMaterial) : LegacyItemConverter.getMaterial(strMaterial, this.materialData);
/* 32 */       } catch (Exception ex) {
/* 33 */         this.material = LegacyItemConverter.getMaterial(strMaterial, this.materialData);
/*    */       } 
/*    */     } else {
/*    */       try {
/* 37 */         this.material = Material.valueOf(strMaterial);
/* 38 */       } catch (Exception ex) {
/* 39 */         MythicLogger.errorMechanicConfig(this, mlc, "'Material' must be a valid Material type.");
/* 40 */         this.material = Material.GRAVEL;
/* 41 */         MythicMobs.inst().handleException(ex);
/*    */       } 
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean castAtEntity(SkillMetadata data, AbstractEntity target) {
/* 48 */     playEffect(target.getLocation());
/* 49 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean castAtLocation(SkillMetadata data, AbstractLocation target) {
/* 54 */     playEffect(target);
/* 55 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public void playEffect(AbstractLocation location) {
/* 60 */     Location l = BukkitAdapter.adapt(location);
/* 61 */     l.getBlock().setType(this.material);
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\skills\mechanics\SetBlockTypeMechanic.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */