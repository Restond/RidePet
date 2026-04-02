/*    */ package lumine.xikage.mythicmobs.adapters.bukkit.entities;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.MythicMobs;
/*    */ import io.lumine.xikage.mythicmobs.io.MythicConfig;
/*    */ import io.lumine.xikage.mythicmobs.mobs.MythicMob;
/*    */ import io.lumine.xikage.mythicmobs.mobs.entities.MythicEntity;
/*    */ import org.bukkit.Location;
/*    */ import org.bukkit.Material;
/*    */ import org.bukkit.entity.Entity;
/*    */ import org.bukkit.entity.FallingBlock;
/*    */ 
/*    */ public class BukkitFallingBlock
/*    */   extends MythicEntity
/*    */ {
/*    */   private static final int height = 1;
/* 16 */   private Material type = Material.STONE;
/* 17 */   private int data = 0;
/*    */   
/*    */   private boolean dropsItem = true;
/*    */   
/*    */   private boolean hurtsEntities = true;
/*    */   
/*    */   public void instantiate(MythicConfig mc) {
/* 24 */     String mat = mc.getString("Options.Block", "STONE");
/*    */     
/*    */     try {
/* 27 */       this.type = Material.getMaterial(mat.toUpperCase());
/* 28 */     } catch (Exception ex) {
/* 29 */       MythicMobs.error("Invalid material specified");
/*    */     } 
/*    */     
/* 32 */     this.data = mc.getInt("Options.BlockData", 0);
/*    */     
/* 34 */     this.dropsItem = mc.getBoolean("Options.DropsItem", true);
/* 35 */     this.hurtsEntities = mc.getBoolean("Options.HurtsEntities", true);
/*    */   }
/*    */ 
/*    */   
/*    */   public Entity spawn(MythicMob mm, Location location) {
/*    */     Entity entity;
/* 41 */     FallingBlock fallingBlock = location.getWorld().spawnFallingBlock(location, this.type, (byte)this.data);
/*    */     
/* 43 */     if (MythicMobs.inst().getMinecraftVersion() >= 8) {
/* 44 */       entity = applyOptions((Entity)fallingBlock);
/*    */     }
/*    */     
/* 47 */     return entity;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public Entity spawn(Location location) {
/* 53 */     return (Entity)location.getWorld().spawnFallingBlock(location, this.type, (byte)this.data);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Entity applyOptions(Entity entity) {
/* 60 */     FallingBlock e = (FallingBlock)entity;
/* 61 */     if (!this.dropsItem) {
/* 62 */       e.setDropItem(false);
/*    */     }
/* 64 */     if (!this.hurtsEntities) {
/* 65 */       e.setHurtEntities(false);
/*    */     }
/* 67 */     return (Entity)e;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean compare(Entity e) {
/* 72 */     return e instanceof FallingBlock;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getHeight() {
/* 77 */     return 1;
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\adapters\bukkit\entities\BukkitFallingBlock.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */