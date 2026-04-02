/*     */ package lumine.xikage.mythicmobs.volatilecode;
/*     */ 
/*     */ import io.lumine.xikage.mythicmobs.volatilecode.VolatileCodeHandler;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public enum Attributes
/*     */ {
/* 101 */   DAMAGE(0, "generic.attackDamage"),
/* 102 */   MOVEMENT_SPEED(2, "generic.movementSpeed"),
/* 103 */   KNOCKBACK_RESISTANCE(2, "generic.knockbackResistance"),
/* 104 */   MAX_HEALTH(0, "generic.maxHealth"),
/* 105 */   FOLLOW_RANGE(0, "generic.followRange");
/*     */   
/*     */   public int op;
/*     */   public String name;
/*     */   
/*     */   Attributes(int op, String name) {
/* 111 */     this.op = op;
/* 112 */     this.name = name;
/*     */   }
/*     */   
/*     */   public static Attributes get(String s) {
/* 116 */     for (Attributes a : values()) {
/* 117 */       if (a.name().toLowerCase().equalsIgnoreCase(s)) return a; 
/*     */     } 
/* 119 */     return null;
/*     */   }
/*     */   
/*     */   public static Attributes getByMCName(String s) {
/* 123 */     for (Attributes a : values()) {
/* 124 */       if (a.name.equalsIgnoreCase(s)) return a; 
/*     */     } 
/* 126 */     return null;
/*     */   }
/*     */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\volatilecode\VolatileCodeHandler$Attributes.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */