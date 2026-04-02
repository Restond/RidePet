/*     */ package lumine.utils.menu;
/*     */ import io.lumine.utils.menu.Icon;
/*     */ import io.lumine.utils.menu.IconBuilder;
/*     */ import io.lumine.utils.menu.Menu;
/*     */ import io.lumine.utils.menu.MonitoredState;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.function.Function;
/*     */ 
/*     */ public class MenuBuilder<T> {
/*  11 */   private final Map<Integer, Icon<? super T>> icons = new HashMap<>();
/*     */   private Function<T, String> titleFunc;
/*     */   private Function<T, MonitoredState> monitorFunc;
/*  14 */   private int curIconPos = -1;
/*  15 */   private List<Integer> sortSlots = null;
/*  16 */   private List<Integer> blinkSlots = new ArrayList<>();
/*     */   
/*     */   public static <T> io.lumine.utils.menu.MenuBuilder<T> create() {
/*  19 */     return new io.lumine.utils.menu.MenuBuilder<>();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public io.lumine.utils.menu.MenuBuilder<T> title(String title) {
/*  25 */     this.titleFunc = (_state -> paramString);
/*  26 */     return this;
/*     */   }
/*     */   
/*     */   public io.lumine.utils.menu.MenuBuilder<T> title(Function<T, String> titleFunc) {
/*  30 */     this.titleFunc = titleFunc;
/*  31 */     return this;
/*     */   }
/*     */   
/*     */   public io.lumine.utils.menu.MenuBuilder<T> addIcon(Icon<? super T> icon) {
/*     */     do {
/*  36 */       this.curIconPos++;
/*  37 */     } while (this.icons.containsKey(Integer.valueOf(this.curIconPos)));
/*     */     
/*  39 */     this.icons.put(Integer.valueOf(this.curIconPos), icon);
/*  40 */     return this;
/*     */   }
/*     */   
/*     */   public io.lumine.utils.menu.MenuBuilder<T> addIcon(int slot, Icon<? super T> icon) {
/*  44 */     this.icons.put(Integer.valueOf(slot), icon);
/*  45 */     if (icon.isBlinkingIcon()) this.blinkSlots.add(Integer.valueOf(slot)); 
/*  46 */     return this;
/*     */   }
/*     */   
/*     */   public io.lumine.utils.menu.MenuBuilder<T> addAllIcons(List<Icon<T>> iconList) {
/*  50 */     for (Icon<? super T> icon : iconList) {
/*     */       do {
/*  52 */         this.curIconPos++;
/*  53 */       } while (this.icons.containsKey(Integer.valueOf(this.curIconPos)));
/*     */       
/*  55 */       this.icons.put(Integer.valueOf(this.curIconPos), icon);
/*  56 */       if (icon.isBlinkingIcon()) this.blinkSlots.add(Integer.valueOf(this.curIconPos)); 
/*     */     } 
/*  58 */     return this;
/*     */   }
/*     */   
/*     */   public io.lumine.utils.menu.MenuBuilder<T> addAllIcons(Map<Integer, Icon<T>> icons) {
/*  62 */     icons.entrySet().stream().forEach(integerIconEntry -> {
/*     */           this.icons.put((Integer)integerIconEntry.getKey(), (Icon<? super T>)integerIconEntry.getValue()); if (((Icon)integerIconEntry.getValue()).isBlinkingIcon())
/*     */             this.blinkSlots.add((Integer)integerIconEntry.getKey()); 
/*     */         });
/*  66 */     return this;
/*     */   }
/*     */   
/*     */   public io.lumine.utils.menu.MenuBuilder<T> createIcon(Function<IconBuilder<T>, IconBuilder<T>> builderFunc) {
/*  70 */     addIcon(((IconBuilder)builderFunc.apply(IconBuilder.create())).build());
/*  71 */     return this;
/*     */   }
/*     */   
/*     */   public io.lumine.utils.menu.MenuBuilder<T> createIcon(int slot, Function<IconBuilder<T>, IconBuilder<T>> builderFunc) {
/*  75 */     addIcon(slot, ((IconBuilder)builderFunc.apply(IconBuilder.create())).build());
/*  76 */     return this;
/*     */   }
/*     */   
/*     */   public io.lumine.utils.menu.MenuBuilder<T> monitor(Function<T, MonitoredState> func) {
/*  80 */     this.monitorFunc = func;
/*  81 */     return this;
/*     */   }
/*     */   
/*     */   public io.lumine.utils.menu.MenuBuilder<T> staticMenu() {
/*  85 */     this.monitorFunc = (_obj -> ());
/*  86 */     return this;
/*     */   }
/*     */   
/*     */   public Menu<T> build() {
/*  90 */     if (this.titleFunc == null) {
/*  91 */       throw new IllegalStateException("Title must be specified!");
/*     */     }
/*  93 */     if (this.sortSlots == null) {
/*  94 */       return new Menu(this.titleFunc, this.monitorFunc, this.icons, this.blinkSlots);
/*     */     }
/*  96 */     return new Menu(this.titleFunc, this.monitorFunc, this.icons, this.sortSlots, this.blinkSlots);
/*     */   }
/*     */ 
/*     */   
/*     */   public io.lumine.utils.menu.MenuBuilder<T> sortByAmount(List<Integer> sortable) {
/* 101 */     this.sortSlots = sortable;
/* 102 */     return this;
/*     */   }
/*     */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumin\\utils\menu\MenuBuilder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */