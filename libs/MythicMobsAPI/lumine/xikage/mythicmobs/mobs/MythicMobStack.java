/*    */ package lumine.xikage.mythicmobs.mobs;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.MythicMobs;
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractLocation;
/*    */ import io.lumine.xikage.mythicmobs.mobs.ActiveMob;
/*    */ import io.lumine.xikage.mythicmobs.mobs.MythicMob;
/*    */ import io.lumine.xikage.mythicmobs.mobs.MythicMobStack;
/*    */ import java.util.LinkedList;
/*    */ import java.util.Queue;
/*    */ 
/*    */ public class MythicMobStack implements Comparable<MythicMobStack> {
/*    */   private String internalName;
/*    */   private String stackString;
/*    */   private String file;
/*    */   private MythicMob base;
/* 17 */   private Queue<MythicMob> stack = new LinkedList<>();
/*    */   
/*    */   private boolean enabled = true;
/*    */   
/*    */   public MythicMobStack(String name, String stackString, String file) {
/* 22 */     this.internalName = name;
/* 23 */     this.stackString = stackString;
/* 24 */     this.file = file;
/*    */     
/* 26 */     String[] part = this.stackString.split(",");
/*    */     
/* 28 */     MythicMob mm = MythicMobs.inst().getMobManager().getMythicMob(part[0]);
/*    */     
/* 30 */     if (mm == null) {
/* 31 */       MythicMobs.throwSevere("error-mythicmobstack-load-invalidtype", "Could not load MythicMobStack {0}! Invalid type(s) specified.", new Object[] { this.internalName });
/* 32 */       this.stack.clear();
/* 33 */       this.enabled = false;
/*    */       
/*    */       return;
/*    */     } 
/* 37 */     this.base = mm;
/*    */     
/* 39 */     for (int i = 1; i < part.length; i++) {
/* 40 */       mm = MythicMobs.inst().getMobManager().getMythicMob(part[i]);
/* 41 */       if (mm == null) {
/* 42 */         MythicMobs.throwSevere("error-mythicmobstack-load-invalidtype", "Could not load MythicMobStack {0}! Invalid type(s) specified.", new Object[] { this.internalName });
/* 43 */         this.stack.clear();
/* 44 */         this.enabled = false;
/*    */         return;
/*    */       } 
/* 47 */       this.stack.add(mm);
/*    */     } 
/*    */   }
/*    */   
/*    */   public boolean isValid() {
/* 52 */     return this.enabled;
/*    */   }
/*    */   
/*    */   public String getName() {
/* 56 */     return this.internalName;
/*    */   }
/*    */   
/*    */   public String getFile() {
/* 60 */     return this.file;
/*    */   }
/*    */   
/*    */   public String getStackString() {
/* 64 */     return this.stackString;
/*    */   }
/*    */   
/*    */   public ActiveMob spawn(AbstractLocation l, int level) {
/* 68 */     if (!this.enabled) return null;
/*    */     
/* 70 */     ActiveMob base = this.base.spawn(l, level);
/* 71 */     AbstractEntity f = base.getEntity();
/*    */     
/* 73 */     for (MythicMob mm : this.stack) {
/* 74 */       AbstractEntity e = mm.spawn(l, level).getEntity();
/* 75 */       f.setPassenger(e);
/*    */       
/* 77 */       f = e;
/*    */     } 
/*    */     
/* 80 */     return base;
/*    */   }
/*    */ 
/*    */   
/*    */   public int compareTo(MythicMobStack o) {
/* 85 */     return this.internalName.compareTo(o.getName());
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\mobs\MythicMobStack.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */