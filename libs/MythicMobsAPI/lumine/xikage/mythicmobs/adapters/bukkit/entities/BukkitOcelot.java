/*    */ package lumine.xikage.mythicmobs.adapters.bukkit.entities;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.MythicMobs;
/*    */ import io.lumine.xikage.mythicmobs.io.MythicConfig;
/*    */ import io.lumine.xikage.mythicmobs.mobs.MythicMob;
/*    */ import io.lumine.xikage.mythicmobs.mobs.entities.MythicEntity;
/*    */ import io.lumine.xikage.mythicmobs.mobs.entities.properties.AgeableProperty;
/*    */ import org.bukkit.Location;
/*    */ import org.bukkit.entity.Entity;
/*    */ import org.bukkit.entity.EntityType;
/*    */ import org.bukkit.entity.Ocelot;
/*    */ 
/*    */ 
/*    */ public class BukkitOcelot
/*    */   extends MythicEntity
/*    */ {
/*    */   private static final int height = 1;
/*    */   private AgeableProperty ageableProperty;
/*    */   private boolean tameable = true;
/* 20 */   private String catType = "WILD_OCELOT";
/*    */ 
/*    */   
/*    */   public void instantiate(MythicConfig mc) {
/* 24 */     this.ageableProperty = new AgeableProperty(mc);
/*    */     
/* 26 */     this.catType = mc.getString("Options.Ocelot", "WILD_OCELOT");
/* 27 */     this.catType = mc.getString("Options.CatType", this.catType);
/* 28 */     this.catType = mc.getString("Options.OcelotType", this.catType);
/*    */     
/* 30 */     this.tameable = mc.getBoolean("Options.Tameable", false);
/*    */   }
/*    */ 
/*    */   
/*    */   public Entity spawn(MythicMob mm, Location location) {
/* 35 */     Entity e = location.getWorld().spawnEntity(location, EntityType.OCELOT);
/*    */     
/* 37 */     e = applyOptions(e);
/*    */     
/* 39 */     return e;
/*    */   }
/*    */ 
/*    */   
/*    */   public Entity spawn(Location location) {
/* 44 */     Entity e = location.getWorld().spawnEntity(location, EntityType.OCELOT);
/*    */     
/* 46 */     return e;
/*    */   }
/*    */ 
/*    */   
/*    */   public Entity applyOptions(Entity entity) {
/* 51 */     Ocelot e = (Ocelot)entity;
/*    */     
/* 53 */     this.ageableProperty.applyProperties(entity);
/*    */     
/* 55 */     if (MythicMobs.inst().getMinecraftVersion() < 14) {
/* 56 */       e.setCatType(Ocelot.Type.valueOf(this.catType));
/*    */     }
/*    */     
/* 59 */     return (Entity)e;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean compare(Entity e) {
/* 64 */     return e instanceof org.bukkit.entity.Cow;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getHeight() {
/* 69 */     return 1;
/*    */   }
/*    */   
/*    */   public boolean isTameable() {
/* 73 */     return this.tameable;
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\adapters\bukkit\entities\BukkitOcelot.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */