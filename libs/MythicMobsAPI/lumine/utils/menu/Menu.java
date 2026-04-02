/*     */ package lumine.utils.menu;
/*     */ 
/*     */ import io.lumine.utils.menu.Icon;
/*     */ import io.lumine.utils.menu.MenuHelper;
/*     */ import io.lumine.utils.menu.MonitoredState;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.Comparator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Optional;
/*     */ import java.util.function.Function;
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.inventory.Inventory;
/*     */ 
/*     */ public class Menu<T>
/*     */ {
/*     */   private final Function<T, String> titleFunc;
/*  20 */   private int size = -1; private final Function<T, MonitoredState> monitorFunc; private final Map<Integer, Icon<? super T>> icons;
/*  21 */   private List<Integer> sortSlots = null; private int minSort;
/*     */   private int maxSort;
/*  23 */   private List<Integer> blinkSlots = null;
/*     */   
/*     */   protected Menu(Function<T, String> titleFunc, Function<T, MonitoredState> monitorFunc, Map<Integer, Icon<? super T>> icons, List<Integer> blinking) {
/*  26 */     this.titleFunc = titleFunc;
/*  27 */     this.monitorFunc = monitorFunc;
/*  28 */     this.icons = icons;
/*     */     
/*  30 */     if (blinking != null && blinking.size() > 0) {
/*  31 */       this.blinkSlots = blinking;
/*     */     }
/*     */   }
/*     */   
/*     */   protected Menu(Function<T, String> titleFunc, Function<T, MonitoredState> monitorFunc, Map<Integer, Icon<? super T>> icons, List<Integer> sortable, List<Integer> blinking) {
/*  36 */     this(titleFunc, monitorFunc, icons, blinking);
/*  37 */     this.sortSlots = sortable;
/*     */     
/*  39 */     Optional<Integer> maybeMinSort = sortable.stream().min(Comparator.naturalOrder());
/*  40 */     Optional<Integer> maybeMaxSort = sortable.stream().max(Comparator.naturalOrder());
/*     */     
/*  42 */     if (maybeMinSort.isPresent()) {
/*  43 */       this.minSort = ((Integer)maybeMinSort.get()).intValue();
/*     */     } else {
/*  45 */       this.sortSlots = null;
/*     */       return;
/*     */     } 
/*  48 */     if (maybeMaxSort.isPresent()) {
/*  49 */       this.maxSort = ((Integer)maybeMaxSort.get()).intValue();
/*     */     } else {
/*  51 */       this.sortSlots = null;
/*     */       return;
/*     */     } 
/*     */   }
/*     */   
/*     */   public io.lumine.utils.menu.Menu<T> setSize(int size) {
/*  57 */     this.size = size;
/*  58 */     return this;
/*     */   }
/*     */   
/*     */   public void open(Player player, T state) {
/*  62 */     if (this.icons.isEmpty()) {
/*  63 */       throw new IllegalStateException("Tried to open an empty menu!");
/*     */     }
/*     */     
/*  66 */     int max = ((Integer)this.icons.keySet().stream().sorted(Comparator.reverseOrder()).findFirst().get()).intValue();
/*  67 */     int size = this.size;
/*  68 */     if (size < 0) {
/*  69 */       size = (int)Math.ceil((max + 1) / 9.0D) * 9;
/*     */     }
/*     */     
/*  72 */     Inventory inventory = Bukkit.createInventory(null, size, this.titleFunc.apply(state));
/*     */     
/*  74 */     sort(state);
/*  75 */     this.icons.forEach((slot, icon) -> paramInventory.setItem(slot.intValue(), icon.build(paramObject)));
/*     */     
/*  77 */     player.openInventory(inventory);
/*     */ 
/*     */     
/*  80 */     MenuHelper.registerOpen(player, this, state);
/*     */   }
/*     */   
/*     */   protected Map<Integer, Icon<? super T>> getIcons() {
/*  84 */     return this.icons;
/*     */   }
/*     */   
/*     */   protected Optional<Icon<? super T>> getIcon(int slot) {
/*  88 */     return Optional.ofNullable(this.icons.get(Integer.valueOf(slot)));
/*     */   }
/*     */   
/*     */   protected Optional<List<Integer>> getBlinkingSlots() {
/*  92 */     return Optional.ofNullable(this.blinkSlots);
/*     */   }
/*     */   
/*     */   protected Optional<MonitoredState> getMonitoredState(T state) {
/*  96 */     if (this.monitorFunc == null) {
/*  97 */       return Optional.empty();
/*     */     }
/*  99 */     return Optional.of(this.monitorFunc.apply(state));
/*     */   }
/*     */   
/*     */   protected void sort(T state) {
/* 103 */     if (this.sortSlots == null)
/*     */       return; 
/* 105 */     List<Icon<? super T>> sort = new ArrayList<>(); int i;
/* 106 */     for (i = this.minSort; i <= this.maxSort; i++) {
/* 107 */       if (getIcon(i).isPresent()) {
/* 108 */         sort.add(getIcon(i).get());
/*     */       }
/*     */     } 
/* 111 */     Collections.sort(sort, (Comparator<? super Icon<? super T>>)new Object(this, state));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 121 */     i = this.minSort;
/* 122 */     for (Icon<? super T> icon : sort) {
/* 123 */       this.icons.put(Integer.valueOf(i), icon);
/* 124 */       i++;
/*     */     } 
/*     */   }
/*     */   
/*     */   protected int getMinSortedSlot() {
/* 129 */     return this.minSort;
/*     */   }
/*     */   
/*     */   protected int getMaxSortedSlot() {
/* 133 */     return this.maxSort;
/*     */   }
/*     */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumin\\utils\menu\Menu.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */