/*    */ package lumine.xikage.mythicmobs.drops.droppables;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractLocation;
/*    */ import io.lumine.xikage.mythicmobs.adapters.bukkit.BukkitAdapter;
/*    */ import io.lumine.xikage.mythicmobs.drops.Drop;
/*    */ import io.lumine.xikage.mythicmobs.drops.DropMetadata;
/*    */ import io.lumine.xikage.mythicmobs.drops.ILocationDrop;
/*    */ import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
/*    */ import org.bukkit.Location;
/*    */ import org.bukkit.entity.ExperienceOrb;
/*    */ 
/*    */ public class ExperienceDrop
/*    */   extends Drop
/*    */   implements ILocationDrop
/*    */ {
/*    */   public ExperienceDrop(String line, MythicLineConfig config) {
/* 17 */     super(line, config);
/*    */   }
/*    */   
/*    */   public ExperienceDrop(String line, MythicLineConfig config, double amount) {
/* 21 */     super(line, config, amount);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void drop(AbstractLocation target, DropMetadata metadata) {
/* 27 */     Location loc = BukkitAdapter.adapt(target);
/*    */     
/* 29 */     ((ExperienceOrb)loc.getWorld().spawn(loc, ExperienceOrb.class)).setExperience((int)getAmount());
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\drops\droppables\ExperienceDrop.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */