/*    */ package lumine.xikage.mythicmobs.adapters.bukkit.entities;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.io.MythicConfig;
/*    */ import io.lumine.xikage.mythicmobs.mobs.MythicMob;
/*    */ import io.lumine.xikage.mythicmobs.mobs.entities.MythicEntity;
/*    */ import io.lumine.xikage.mythicmobs.mobs.entities.properties.AgeableProperty;
/*    */ import org.bukkit.Location;
/*    */ import org.bukkit.entity.Entity;
/*    */ import org.bukkit.entity.EntityType;
/*    */ import org.bukkit.entity.Parrot;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BukkitParrot
/*    */   extends MythicEntity
/*    */ {
/*    */   protected static final int height = 1;
/*    */   private AgeableProperty ageableProperty;
/*    */   private Parrot.Variant variant;
/*    */   
/*    */   public void instantiate(MythicConfig mc) {
/* 22 */     this.ageableProperty = new AgeableProperty(mc);
/* 23 */     String strcolor = mc.getString("Options.Color", "BLUE");
/* 24 */     strcolor = mc.getString("Options.Variant", strcolor);
/*    */     
/* 26 */     this.variant = Parrot.Variant.valueOf(strcolor);
/*    */   }
/*    */ 
/*    */   
/*    */   public Entity spawn(MythicMob mm, Location location) {
/* 31 */     Entity e = location.getWorld().spawnEntity(location, EntityType.PARROT);
/*    */     
/* 33 */     e = applyOptions(e);
/*    */     
/* 35 */     return e;
/*    */   }
/*    */ 
/*    */   
/*    */   public Entity spawn(Location location) {
/* 40 */     Entity e = location.getWorld().spawnEntity(location, EntityType.PARROT);
/*    */     
/* 42 */     return e;
/*    */   }
/*    */ 
/*    */   
/*    */   public Entity applyOptions(Entity entity) {
/* 47 */     Parrot e = (Parrot)entity;
/*    */     
/* 49 */     this.ageableProperty.applyProperties(entity);
/* 50 */     e.setVariant(this.variant);
/*    */     
/* 52 */     return (Entity)e;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean compare(Entity e) {
/* 57 */     return e instanceof Parrot;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getHeight() {
/* 62 */     return 1;
/*    */   }
/*    */   
/*    */   public Parrot.Variant getVariant() {
/* 66 */     return this.variant;
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\adapters\bukkit\entities\BukkitParrot.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */