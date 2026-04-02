/*    */ package lumine.xikage.mythicmobs.drops.droppables;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.MythicMobs;
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractPlayer;
/*    */ import io.lumine.xikage.mythicmobs.adapters.bukkit.BukkitAdapter;
/*    */ import io.lumine.xikage.mythicmobs.compatibility.CompatibilityManager;
/*    */ import io.lumine.xikage.mythicmobs.drops.Drop;
/*    */ import io.lumine.xikage.mythicmobs.drops.DropMetadata;
/*    */ import io.lumine.xikage.mythicmobs.drops.IIntangibleDrop;
/*    */ import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class HeroesExperienceDrop
/*    */   extends Drop
/*    */   implements IIntangibleDrop
/*    */ {
/*    */   public HeroesExperienceDrop(String line, MythicLineConfig config) {
/* 20 */     super(line, config);
/*    */   }
/*    */   
/*    */   public HeroesExperienceDrop(String line, MythicLineConfig config, double amount) {
/* 24 */     super(line, config, amount);
/*    */   }
/*    */ 
/*    */   
/*    */   public void giveDrop(AbstractPlayer target, DropMetadata metadata) {
/* 29 */     MythicMobs.debug(2, "Granting " + getAmount() + " Heroes EXP to " + target.getName());
/* 30 */     if (CompatibilityManager.Heroes != null)
/* 31 */       CompatibilityManager.Heroes.giveHeroesExp(metadata.getDropper().get(), BukkitAdapter.adapt(target.asPlayer()), (int)getAmount()); 
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\drops\droppables\HeroesExperienceDrop.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */