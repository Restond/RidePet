/*    */ package lumine.xikage.mythicmobs.adapters.bukkit.entities;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.io.MythicConfig;
/*    */ import io.lumine.xikage.mythicmobs.mobs.MythicMob;
/*    */ import io.lumine.xikage.mythicmobs.mobs.entities.MythicEntity;
/*    */ import io.lumine.xikage.mythicmobs.mobs.entities.properties.AgeableProperty;
/*    */ import org.bukkit.Location;
/*    */ import org.bukkit.entity.Entity;
/*    */ import org.bukkit.entity.EntityType;
/*    */ import org.bukkit.entity.Rabbit;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BukkitRabbit
/*    */   extends MythicEntity
/*    */ {
/*    */   private static final int height = 1;
/*    */   private AgeableProperty ageableProperty;
/*    */   private boolean isAngry = false;
/*    */   private boolean isBaby = false;
/*    */   private Rabbit.Type type;
/*    */   
/*    */   public void instantiate(MythicConfig mc) {
/* 24 */     this.ageableProperty = new AgeableProperty(mc);
/*    */     
/* 26 */     this.isAngry = mc.getBoolean("Options.IsKillerBunny", false);
/* 27 */     this.isAngry = mc.getBoolean("Options.Angry", this.isAngry);
/* 28 */     this.isBaby = mc.getBoolean("Options.Baby", false);
/*    */     
/* 30 */     String t = mc.getString("Options.RabbitType", null);
/*    */     
/* 32 */     if (t != null) {
/* 33 */       this.type = Rabbit.Type.valueOf(t.toUpperCase());
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public Entity spawn(MythicMob mm, Location location) {
/* 39 */     Entity e = location.getWorld().spawnEntity(location, EntityType.RABBIT);
/*    */     
/* 41 */     e = applyOptions(e);
/*    */     
/* 43 */     return e;
/*    */   }
/*    */ 
/*    */   
/*    */   public Entity spawn(Location location) {
/* 48 */     Entity e = location.getWorld().spawnEntity(location, EntityType.RABBIT);
/*    */     
/* 50 */     return e;
/*    */   }
/*    */ 
/*    */   
/*    */   public Entity applyOptions(Entity entity) {
/* 55 */     Rabbit e = (Rabbit)entity;
/*    */     
/* 57 */     this.ageableProperty.applyProperties(entity);
/*    */     
/* 59 */     if (this.isBaby) {
/* 60 */       e.setBaby();
/*    */     }
/*    */     
/* 63 */     if (this.type != null) {
/* 64 */       e.setRabbitType(this.type);
/*    */     }
/*    */     
/* 67 */     if (this.isAngry) {
/* 68 */       e.setRabbitType(Rabbit.Type.THE_KILLER_BUNNY);
/*    */     }
/*    */     
/* 71 */     return (Entity)e;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean compare(Entity e) {
/* 76 */     return e instanceof Rabbit;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getHeight() {
/* 81 */     return 1;
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\adapters\bukkit\entities\BukkitRabbit.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */