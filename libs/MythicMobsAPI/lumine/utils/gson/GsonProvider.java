/*     */ package lumine.utils.gson;
/*     */ 
/*     */ import com.google.gson.Gson;
/*     */ import com.google.gson.GsonBuilder;
/*     */ import com.google.gson.JsonElement;
/*     */ import com.google.gson.JsonObject;
/*     */ import com.google.gson.JsonParser;
/*     */ import com.google.gson.TypeAdapterFactory;
/*     */ import io.lumine.utils.gson.adapters.BukkitSerializableAdapterFactory;
/*     */ import io.lumine.utils.gson.adapters.GsonSerializableAdapterFactory;
/*     */ import java.io.Reader;
/*     */ import java.util.Objects;
/*     */ import javax.annotation.Nonnull;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class GsonProvider
/*     */ {
/*  22 */   private static final Gson STANDARD_GSON = (new GsonBuilder())
/*  23 */     .registerTypeAdapterFactory((TypeAdapterFactory)GsonSerializableAdapterFactory.INSTANCE)
/*  24 */     .registerTypeAdapterFactory((TypeAdapterFactory)BukkitSerializableAdapterFactory.INSTANCE)
/*  25 */     .serializeNulls()
/*  26 */     .disableHtmlEscaping()
/*  27 */     .create();
/*     */   
/*  29 */   private static final Gson PRETTY_PRINT_GSON = (new GsonBuilder())
/*  30 */     .registerTypeAdapterFactory((TypeAdapterFactory)GsonSerializableAdapterFactory.INSTANCE)
/*  31 */     .registerTypeAdapterFactory((TypeAdapterFactory)BukkitSerializableAdapterFactory.INSTANCE)
/*  32 */     .serializeNulls()
/*  33 */     .disableHtmlEscaping()
/*  34 */     .setPrettyPrinting()
/*  35 */     .create();
/*     */   
/*  37 */   private static final JsonParser PARSER = new JsonParser();
/*     */   
/*     */   @Nonnull
/*     */   public static Gson standard() {
/*  41 */     return STANDARD_GSON;
/*     */   }
/*     */   
/*     */   @Nonnull
/*     */   public static Gson prettyPrinting() {
/*  46 */     return PRETTY_PRINT_GSON;
/*     */   }
/*     */   
/*     */   @Nonnull
/*     */   public static JsonParser parser() {
/*  51 */     return PARSER;
/*     */   }
/*     */   
/*     */   @Nonnull
/*     */   public static JsonObject readObject(@Nonnull Reader reader) {
/*  56 */     return PARSER.parse(reader).getAsJsonObject();
/*     */   }
/*     */   
/*     */   @Nonnull
/*     */   public static JsonObject readObject(@Nonnull String s) {
/*  61 */     return PARSER.parse(s).getAsJsonObject();
/*     */   }
/*     */   
/*     */   public static void writeObject(@Nonnull Appendable writer, @Nonnull JsonObject object) {
/*  65 */     standard().toJson((JsonElement)object, writer);
/*     */   }
/*     */   
/*     */   public static void writeObjectPretty(@Nonnull Appendable writer, @Nonnull JsonObject object) {
/*  69 */     prettyPrinting().toJson((JsonElement)object, writer);
/*     */   }
/*     */   
/*     */   public static void writeElement(@Nonnull Appendable writer, @Nonnull JsonElement element) {
/*  73 */     standard().toJson(element, writer);
/*     */   }
/*     */   
/*     */   public static void writeElementPretty(@Nonnull Appendable writer, @Nonnull JsonElement element) {
/*  77 */     prettyPrinting().toJson(element, writer);
/*     */   }
/*     */   
/*     */   @Nonnull
/*     */   public static String toString(@Nonnull JsonElement element) {
/*  82 */     return Objects.<String>requireNonNull(standard().toJson(element));
/*     */   }
/*     */   
/*     */   @Nonnull
/*     */   public static String toStringPretty(@Nonnull JsonElement element) {
/*  87 */     return Objects.<String>requireNonNull(prettyPrinting().toJson(element));
/*     */   }
/*     */   
/*     */   private GsonProvider() {
/*  91 */     throw new UnsupportedOperationException("This class cannot be instantiated");
/*     */   }
/*     */   
/*     */   @Nonnull
/*     */   @Deprecated
/*     */   public static Gson get() {
/*  97 */     return standard();
/*     */   }
/*     */   
/*     */   @Nonnull
/*     */   @Deprecated
/*     */   public static Gson getPrettyPrinting() {
/* 103 */     return prettyPrinting();
/*     */   }
/*     */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumin\\utils\gson\GsonProvider.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */