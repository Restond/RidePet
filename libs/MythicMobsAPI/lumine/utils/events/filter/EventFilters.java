/*     */ package lumine.utils.events.filter;
/*     */ 
/*     */ import io.lumine.utils.metadata.Metadata;
/*     */ import io.lumine.utils.metadata.MetadataKey;
/*     */ import java.util.function.Predicate;
/*     */ import javax.annotation.Nonnull;
/*     */ import org.bukkit.event.Cancellable;
/*     */ import org.bukkit.event.entity.EntityEvent;
/*     */ import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
/*     */ import org.bukkit.event.player.PlayerEvent;
/*     */ import org.bukkit.event.player.PlayerLoginEvent;
/*     */ import org.bukkit.event.player.PlayerMoveEvent;
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
/*     */ public final class EventFilters
/*     */ {
/*     */   private static final Predicate<? extends Cancellable> IGNORE_CANCELLED;
/*     */   
/*     */   static {
/*  48 */     IGNORE_CANCELLED = (e -> !e.isCancelled());
/*  49 */   } private static final Predicate<? extends Cancellable> IGNORE_UNCANCELLED = Cancellable::isCancelled; static {
/*  50 */     IGNORE_DISALLOWED_LOGIN = (e -> (e.getResult() == PlayerLoginEvent.Result.ALLOWED));
/*  51 */     IGNORE_DISALLOWED_PRE_LOGIN = (e -> (e.getLoginResult() == AsyncPlayerPreLoginEvent.Result.ALLOWED));
/*     */     
/*  53 */     IGNORE_SAME_BLOCK = (e -> 
/*  54 */       (e.getFrom().getBlockX() != e.getTo().getBlockX() || e.getFrom().getBlockZ() != e.getTo().getBlockZ() || e.getFrom().getBlockY() != e.getTo().getBlockY() || !e.getFrom().getWorld().equals(e.getTo().getWorld())));
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  59 */     IGNORE_SAME_BLOCK_AND_Y = (e -> 
/*  60 */       (e.getFrom().getBlockX() != e.getTo().getBlockX() || e.getFrom().getBlockZ() != e.getTo().getBlockZ() || !e.getFrom().getWorld().equals(e.getTo().getWorld())));
/*     */ 
/*     */ 
/*     */     
/*  64 */     IGNORE_SAME_CHUNK = (e -> 
/*  65 */       (e.getFrom().getBlockX() >> 4 != e.getTo().getBlockX() >> 4 || e.getFrom().getBlockZ() >> 4 != e.getTo().getBlockZ() >> 4 || !e.getFrom().getWorld().equals(e.getTo().getWorld())));
/*     */   }
/*     */ 
/*     */   
/*     */   private static final Predicate<? extends PlayerLoginEvent> IGNORE_DISALLOWED_LOGIN;
/*     */   private static final Predicate<? extends AsyncPlayerPreLoginEvent> IGNORE_DISALLOWED_PRE_LOGIN;
/*     */   private static final Predicate<? extends PlayerMoveEvent> IGNORE_SAME_BLOCK;
/*     */   private static final Predicate<? extends PlayerMoveEvent> IGNORE_SAME_BLOCK_AND_Y;
/*     */   private static final Predicate<? extends PlayerMoveEvent> IGNORE_SAME_CHUNK;
/*     */   
/*     */   @Nonnull
/*     */   public static <T extends Cancellable> Predicate<T> ignoreCancelled() {
/*  77 */     return (Predicate)IGNORE_CANCELLED;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Nonnull
/*     */   public static <T extends Cancellable> Predicate<T> ignoreNotCancelled() {
/*  88 */     return (Predicate)IGNORE_UNCANCELLED;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Nonnull
/*     */   public static <T extends PlayerLoginEvent> Predicate<T> ignoreDisallowedLogin() {
/*  99 */     return (Predicate)IGNORE_DISALLOWED_LOGIN;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Nonnull
/*     */   public static <T extends AsyncPlayerPreLoginEvent> Predicate<T> ignoreDisallowedPreLogin() {
/* 110 */     return (Predicate)IGNORE_DISALLOWED_PRE_LOGIN;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Nonnull
/*     */   public static <T extends PlayerMoveEvent> Predicate<T> ignoreSameBlock() {
/* 121 */     return (Predicate)IGNORE_SAME_BLOCK;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Nonnull
/*     */   public static <T extends PlayerMoveEvent> Predicate<T> ignoreSameBlockAndY() {
/* 133 */     return (Predicate)IGNORE_SAME_BLOCK_AND_Y;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Nonnull
/*     */   public static <T extends PlayerMoveEvent> Predicate<T> ignoreSameChunk() {
/* 144 */     return (Predicate)IGNORE_SAME_CHUNK;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Nonnull
/*     */   public static <T extends EntityEvent> Predicate<T> entityHasMetadata(MetadataKey<?> key) {
/* 156 */     return e -> Metadata.provideForEntity(e.getEntity()).has(paramMetadataKey);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Nonnull
/*     */   public static <T extends PlayerEvent> Predicate<T> playerHasMetadata(MetadataKey<?> key) {
/* 168 */     return e -> Metadata.provideForPlayer(e.getPlayer()).has(paramMetadataKey);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Nonnull
/*     */   public static <T extends PlayerEvent> Predicate<T> playerHasPermission(String permission) {
/* 180 */     return e -> e.getPlayer().hasPermission(paramString);
/*     */   }
/*     */   
/*     */   private EventFilters() {
/* 184 */     throw new UnsupportedOperationException("This class cannot be instantiated");
/*     */   }
/*     */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumin\\utils\events\filter\EventFilters.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */