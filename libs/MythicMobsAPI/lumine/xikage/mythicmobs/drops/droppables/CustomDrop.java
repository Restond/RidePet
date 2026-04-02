/*    */ package lumine.xikage.mythicmobs.drops.droppables;
/*    */ 
/*    */ import io.lumine.utils.Schedulers;
/*    */ import io.lumine.xikage.mythicmobs.MythicMobs;
/*    */ import io.lumine.xikage.mythicmobs.api.bukkit.events.MythicDropLoadEvent;
/*    */ import io.lumine.xikage.mythicmobs.drops.Drop;
/*    */ import io.lumine.xikage.mythicmobs.drops.DropMetadata;
/*    */ import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
/*    */ import io.lumine.xikage.mythicmobs.logging.MythicLogger;
/*    */ import java.util.Optional;
/*    */ import org.bukkit.Bukkit;
/*    */ import org.bukkit.event.Event;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class CustomDrop
/*    */   extends Drop
/*    */ {
/*    */   protected final String dropName;
/* 23 */   protected Drop drop = null;
/*    */   protected boolean loaded = false;
/*    */   
/*    */   public CustomDrop(String drop, String line, MythicLineConfig mlc) {
/* 27 */     super(line, mlc);
/* 28 */     this.dropName = drop;
/*    */     
/* 30 */     MythicMobs.debug(1, "---- Loading CustomDrop with name " + drop);
/* 31 */     Schedulers.sync().run(() -> {
/*    */           MythicMobs.debug(3, "Attempting to Register CustomDrop: " + paramString);
/*    */           MythicDropLoadEvent event = new MythicDropLoadEvent(this, this.dropName, paramMythicLineConfig);
/*    */           Bukkit.getServer().getPluginManager().callEvent((Event)event);
/*    */           if (event.getDrop().isPresent()) {
/*    */             this.drop = event.getDrop().get();
/*    */             this.loaded = true;
/*    */           } else {
/*    */             MythicLogger.errorDropConfig(this, paramMythicLineConfig, "Drop type not found.");
/*    */           } 
/*    */         });
/*    */   }
/*    */ 
/*    */   
/*    */   public String getConfigLine() {
/* 46 */     return getLine();
/*    */   }
/*    */   
/*    */   public Optional<Drop> getDrop() {
/* 50 */     return Optional.ofNullable(this.drop);
/*    */   }
/*    */   
/*    */   public String getDropArgument() {
/* 54 */     return this.dropVar;
/*    */   }
/*    */ 
/*    */   
/*    */   public void rollAmount(DropMetadata data) {
/* 59 */     super.rollAmount(data);
/* 60 */     double amount = getAmount();
/* 61 */     if (this.drop != null)
/* 62 */       this.drop.setAmount(amount); 
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\drops\droppables\CustomDrop.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */