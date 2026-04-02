/*    */ package lumine.xikage.mythicmobs.drops.droppables;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.MythicMobs;
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractItemStack;
/*    */ import io.lumine.xikage.mythicmobs.adapters.bukkit.BukkitItemStack;
/*    */ import io.lumine.xikage.mythicmobs.drops.Drop;
/*    */ import io.lumine.xikage.mythicmobs.drops.DropMetadata;
/*    */ import io.lumine.xikage.mythicmobs.drops.IItemDrop;
/*    */ import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillString;
/*    */ import io.lumine.xikage.mythicmobs.util.types.RandomDouble;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ 
/*    */ public class ItemDrop
/*    */   extends Drop
/*    */   implements IItemDrop {
/*    */   private final BukkitItemStack item;
/*    */   
/*    */   public ItemDrop(String line, MythicLineConfig config, String item) {
/* 21 */     super(line, config);
/* 22 */     this.item = new BukkitItemStack(item);
/*    */     
/* 24 */     String display = config.getString(new String[] { "name", "display", "n" }, null, new String[0]);
/* 25 */     int data = config.getInteger(new String[] { "data", "d" }, 0);
/* 26 */     int amount = config.getInteger(new String[] { "amount", "a" }, 1);
/* 27 */     String lore = config.getString(new String[] { "lore", "l" }, null, new String[0]);
/* 28 */     String color = config.getString(new String[] { "color", "c" }, null, new String[0]);
/*    */     
/* 30 */     if (display != null) {
/* 31 */       if (display.startsWith("\"")) {
/* 32 */         display = display.substring(1, display.length() - 1);
/*    */       }
/*    */       
/* 35 */       display = SkillString.parseMessageSpecialChars(display);
/* 36 */       this.item.display(display);
/*    */     } 
/* 38 */     if (data > 0) {
/* 39 */       this.item.data((byte)data);
/*    */     }
/* 41 */     if (amount != 1) {
/* 42 */       this.item.amount(amount);
/*    */     }
/* 44 */     if (lore != null) {
/*    */       try {
/* 46 */         List<String> loreList = new ArrayList<>();
/*    */         
/* 48 */         for (String s : lore.split(",")) {
/* 49 */           String lores = s.substring(1, s.length() - 1);
/* 50 */           lores = SkillString.parseMessageSpecialChars(lores);
/* 51 */           loreList.add(lores);
/* 52 */           MythicMobs.debug(2, "-- Loaded ItemDrop Lore " + lores);
/*    */         } 
/* 54 */         this.item.lore(loreList);
/* 55 */       } catch (Exception ex) {
/* 56 */         MythicMobs.inst().handleException(ex);
/*    */       } 
/*    */     }
/* 59 */     if (color != null) {
/* 60 */       this.item.color(color);
/*    */     }
/*    */   }
/*    */   
/*    */   public ItemDrop(String line, MythicLineConfig config, BukkitItemStack item) {
/* 65 */     super(line, config);
/* 66 */     this.item = item;
/*    */   }
/*    */   
/*    */   public ItemDrop(String line, MythicLineConfig config, BukkitItemStack item, RandomDouble amount) {
/* 70 */     super(line, config, amount);
/* 71 */     this.item = item;
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractItemStack getDrop(DropMetadata metadata) {
/* 76 */     int amount = (int)(this.item.getAmount() * getAmount());
/*    */     
/* 78 */     return (AbstractItemStack)this.item.clone().amount(amount);
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 83 */     return "ItemDrop{" + this.item.toString() + "}";
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\drops\droppables\ItemDrop.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */