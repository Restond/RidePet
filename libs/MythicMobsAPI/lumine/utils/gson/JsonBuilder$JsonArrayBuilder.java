/*     */ package lumine.utils.gson;
/*     */ 
/*     */ import com.google.gson.JsonArray;
/*     */ import com.google.gson.JsonElement;
/*     */ import io.lumine.utils.gson.GsonSerializable;
/*     */ import io.lumine.utils.gson.JsonBuilder;
/*     */ import java.util.Objects;
/*     */ import java.util.function.Consumer;
/*     */ import java.util.stream.Stream;
/*     */ import javax.annotation.Nullable;
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
/*     */ public interface JsonArrayBuilder
/*     */   extends Consumer<JsonElement>
/*     */ {
/*     */   default void accept(JsonElement value) {
/* 550 */     add(value);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   default JsonArrayBuilder add(@Nullable JsonElement value) {
/* 556 */     return add(value, false);
/*     */   }
/*     */   
/*     */   default JsonArrayBuilder add(@Nullable GsonSerializable serializable) {
/* 560 */     return (serializable == null) ? add((JsonElement)JsonBuilder.nullValue()) : add(serializable.serialize());
/*     */   }
/*     */   
/*     */   default JsonArrayBuilder add(@Nullable String value) {
/* 564 */     return add(JsonBuilder.primitive(value));
/*     */   }
/*     */   
/*     */   default JsonArrayBuilder add(@Nullable Number value) {
/* 568 */     return add(JsonBuilder.primitive(value));
/*     */   }
/*     */   
/*     */   default JsonArrayBuilder add(@Nullable Boolean value) {
/* 572 */     return add(JsonBuilder.primitive(value));
/*     */   }
/*     */   
/*     */   default JsonArrayBuilder add(@Nullable Character value) {
/* 576 */     return add(JsonBuilder.primitive(value));
/*     */   }
/*     */   
/*     */   default <T extends JsonElement> JsonArrayBuilder addAll(Iterable<T> iterable, boolean copy) {
/* 580 */     Objects.requireNonNull(iterable, "iterable");
/* 581 */     for (JsonElement jsonElement : iterable) {
/* 582 */       add(jsonElement, copy);
/*     */     }
/* 584 */     return this;
/*     */   }
/*     */   
/*     */   default <T extends JsonElement> JsonArrayBuilder addAll(Iterable<T> iterable) {
/* 588 */     return addAll(iterable, false);
/*     */   }
/*     */   
/*     */   default <T extends JsonElement> JsonArrayBuilder addAll(Stream<T> stream, boolean copy) {
/* 592 */     Objects.requireNonNull(stream, "iterable");
/* 593 */     stream.forEach(e -> add(e, paramBoolean));
/* 594 */     return this;
/*     */   }
/*     */   
/*     */   default <T extends JsonElement> JsonArrayBuilder addAll(Stream<T> stream) {
/* 598 */     return addAll(stream, false);
/*     */   }
/*     */   
/*     */   default <T extends GsonSerializable> JsonArrayBuilder addSerializables(Iterable<T> iterable) {
/* 602 */     Objects.requireNonNull(iterable, "iterable");
/* 603 */     for (GsonSerializable gsonSerializable : iterable) {
/* 604 */       add(gsonSerializable);
/*     */     }
/* 606 */     return this;
/*     */   }
/*     */   
/*     */   default JsonArrayBuilder addStrings(Iterable<String> iterable) {
/* 610 */     Objects.requireNonNull(iterable, "iterable");
/* 611 */     for (String e : iterable) {
/* 612 */       add(e);
/*     */     }
/* 614 */     return this;
/*     */   }
/*     */   
/*     */   default <T extends Number> JsonArrayBuilder addNumbers(Iterable<T> iterable) {
/* 618 */     Objects.requireNonNull(iterable, "iterable");
/* 619 */     for (Number number : iterable) {
/* 620 */       add(number);
/*     */     }
/* 622 */     return this;
/*     */   }
/*     */   
/*     */   default JsonArrayBuilder addBooleans(Iterable<Boolean> iterable) {
/* 626 */     Objects.requireNonNull(iterable, "iterable");
/* 627 */     for (Boolean e : iterable) {
/* 628 */       add(e);
/*     */     }
/* 630 */     return this;
/*     */   }
/*     */   
/*     */   default JsonArrayBuilder addCharacters(Iterable<Character> iterable) {
/* 634 */     Objects.requireNonNull(iterable, "iterable");
/* 635 */     for (Character e : iterable) {
/* 636 */       add(e);
/*     */     }
/* 638 */     return this;
/*     */   }
/*     */   
/*     */   JsonArrayBuilder add(@Nullable JsonElement paramJsonElement, boolean paramBoolean);
/*     */   
/*     */   JsonArray build();
/*     */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumin\\utils\gson\JsonBuilder$JsonArrayBuilder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */