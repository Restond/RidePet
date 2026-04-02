/*     */ package lumine.utils.gson;
/*     */ 
/*     */ import com.google.gson.JsonElement;
/*     */ import com.google.gson.JsonObject;
/*     */ import io.lumine.utils.gson.GsonSerializable;
/*     */ import io.lumine.utils.gson.JsonBuilder;
/*     */ import java.util.Map;
/*     */ import java.util.Objects;
/*     */ import java.util.function.BiConsumer;
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
/*     */ public interface JsonObjectBuilder
/*     */   extends BiConsumer<String, JsonElement>, Consumer<Map.Entry<String, JsonElement>>
/*     */ {
/*     */   default void accept(Map.Entry<String, JsonElement> entry) {
/* 290 */     Objects.requireNonNull(entry, "entry");
/* 291 */     add(entry.getKey(), entry.getValue());
/*     */   }
/*     */ 
/*     */   
/*     */   default void accept(String property, JsonElement value) {
/* 296 */     add(property, value);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   default JsonObjectBuilder add(String property, @Nullable JsonElement value) {
/* 302 */     return add(property, value, false);
/*     */   }
/*     */   
/*     */   default JsonObjectBuilder add(String property, @Nullable GsonSerializable serializable) {
/* 306 */     return (serializable == null) ? add(property, (JsonElement)JsonBuilder.nullValue()) : add(property, serializable.serialize());
/*     */   }
/*     */   
/*     */   default JsonObjectBuilder add(String property, @Nullable String value) {
/* 310 */     return add(property, JsonBuilder.primitive(value));
/*     */   }
/*     */   
/*     */   default JsonObjectBuilder add(String property, @Nullable Number value) {
/* 314 */     return add(property, JsonBuilder.primitive(value));
/*     */   }
/*     */   
/*     */   default JsonObjectBuilder add(String property, @Nullable Boolean value) {
/* 318 */     return add(property, JsonBuilder.primitive(value));
/*     */   }
/*     */   
/*     */   default JsonObjectBuilder add(String property, @Nullable Character value) {
/* 322 */     return add(property, JsonBuilder.primitive(value));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   default JsonObjectBuilder addIfAbsent(String property, @Nullable JsonElement value) {
/* 328 */     return addIfAbsent(property, value, false);
/*     */   }
/*     */   
/*     */   default JsonObjectBuilder addIfAbsent(String property, @Nullable GsonSerializable serializable) {
/* 332 */     return (serializable == null) ? addIfAbsent(property, (JsonElement)JsonBuilder.nullValue()) : addIfAbsent(property, serializable.serialize());
/*     */   }
/*     */   
/*     */   default JsonObjectBuilder addIfAbsent(String property, @Nullable String value) {
/* 336 */     return addIfAbsent(property, JsonBuilder.primitive(value));
/*     */   }
/*     */   
/*     */   default JsonObjectBuilder addIfAbsent(String property, @Nullable Number value) {
/* 340 */     return addIfAbsent(property, JsonBuilder.primitive(value));
/*     */   }
/*     */   
/*     */   default JsonObjectBuilder addIfAbsent(String property, @Nullable Boolean value) {
/* 344 */     return addIfAbsent(property, JsonBuilder.primitive(value));
/*     */   }
/*     */   
/*     */   default JsonObjectBuilder addIfAbsent(String property, @Nullable Character value) {
/* 348 */     return addIfAbsent(property, JsonBuilder.primitive(value));
/*     */   }
/*     */   
/*     */   default <T extends JsonElement> JsonObjectBuilder addAll(Iterable<Map.Entry<String, T>> iterable, boolean deepCopy) {
/* 352 */     Objects.requireNonNull(iterable, "iterable");
/* 353 */     for (Map.Entry<String, T> e : iterable) {
/* 354 */       if (e == null || e.getKey() == null) {
/*     */         continue;
/*     */       }
/* 357 */       add(e.getKey(), (JsonElement)e.getValue(), deepCopy);
/*     */     } 
/* 359 */     return this;
/*     */   }
/*     */   
/*     */   default <T extends JsonElement> JsonObjectBuilder addAll(Iterable<Map.Entry<String, T>> iterable) {
/* 363 */     return addAll(iterable, false);
/*     */   }
/*     */   
/*     */   default <T extends JsonElement> JsonObjectBuilder addAll(Stream<Map.Entry<String, T>> stream, boolean deepCopy) {
/* 367 */     Objects.requireNonNull(stream, "stream");
/* 368 */     stream.forEach(e -> {
/*     */           if (e == null || e.getKey() == null) {
/*     */             return;
/*     */           }
/*     */           add((String)e.getKey(), (JsonElement)e.getValue(), paramBoolean);
/*     */         });
/* 374 */     return this;
/*     */   }
/*     */   
/*     */   default <T extends JsonElement> JsonObjectBuilder addAll(Stream<Map.Entry<String, T>> stream) {
/* 378 */     return addAll(stream, false);
/*     */   }
/*     */   
/*     */   default JsonObjectBuilder addAll(JsonObject object, boolean deepCopy) {
/* 382 */     Objects.requireNonNull(object, "object");
/* 383 */     return addAll(object.entrySet(), deepCopy);
/*     */   }
/*     */   
/*     */   default JsonObjectBuilder addAll(JsonObject object) {
/* 387 */     return addAll(object, false);
/*     */   }
/*     */   
/*     */   default <T extends GsonSerializable> JsonObjectBuilder addAllSerializables(Iterable<Map.Entry<String, T>> iterable) {
/* 391 */     Objects.requireNonNull(iterable, "iterable");
/* 392 */     for (Map.Entry<String, T> e : iterable) {
/* 393 */       if (e == null || e.getKey() == null) {
/*     */         continue;
/*     */       }
/* 396 */       add(e.getKey(), (GsonSerializable)e.getValue());
/*     */     } 
/* 398 */     return this;
/*     */   }
/*     */   
/*     */   default JsonObjectBuilder addAllStrings(Iterable<Map.Entry<String, String>> iterable) {
/* 402 */     Objects.requireNonNull(iterable, "iterable");
/* 403 */     for (Map.Entry<String, String> e : iterable) {
/* 404 */       if (e == null || e.getKey() == null) {
/*     */         continue;
/*     */       }
/* 407 */       add(e.getKey(), e.getValue());
/*     */     } 
/* 409 */     return this;
/*     */   }
/*     */   
/*     */   default <T extends Number> JsonObjectBuilder addAllNumbers(Iterable<Map.Entry<String, T>> iterable) {
/* 413 */     Objects.requireNonNull(iterable, "iterable");
/* 414 */     for (Map.Entry<String, T> e : iterable) {
/* 415 */       if (e == null || e.getKey() == null) {
/*     */         continue;
/*     */       }
/* 418 */       add(e.getKey(), (Number)e.getValue());
/*     */     } 
/* 420 */     return this;
/*     */   }
/*     */   
/*     */   default JsonObjectBuilder addAllBooleans(Iterable<Map.Entry<String, Boolean>> iterable) {
/* 424 */     Objects.requireNonNull(iterable, "iterable");
/* 425 */     for (Map.Entry<String, Boolean> e : iterable) {
/* 426 */       if (e == null || e.getKey() == null) {
/*     */         continue;
/*     */       }
/* 429 */       add(e.getKey(), e.getValue());
/*     */     } 
/* 431 */     return this;
/*     */   }
/*     */   
/*     */   default JsonObjectBuilder addAllCharacters(Iterable<Map.Entry<String, Character>> iterable) {
/* 435 */     Objects.requireNonNull(iterable, "iterable");
/* 436 */     for (Map.Entry<String, Character> e : iterable) {
/* 437 */       if (e == null || e.getKey() == null) {
/*     */         continue;
/*     */       }
/* 440 */       add(e.getKey(), e.getValue());
/*     */     } 
/* 442 */     return this;
/*     */   }
/*     */   
/*     */   default <T extends JsonElement> JsonObjectBuilder addAllIfAbsent(Iterable<Map.Entry<String, T>> iterable, boolean deepCopy) {
/* 446 */     Objects.requireNonNull(iterable, "iterable");
/* 447 */     for (Map.Entry<String, T> e : iterable) {
/* 448 */       if (e == null || e.getKey() == null) {
/*     */         continue;
/*     */       }
/* 451 */       addIfAbsent(e.getKey(), (JsonElement)e.getValue(), deepCopy);
/*     */     } 
/* 453 */     return this;
/*     */   }
/*     */   
/*     */   default <T extends JsonElement> JsonObjectBuilder addAllIfAbsent(Iterable<Map.Entry<String, T>> iterable) {
/* 457 */     return addAllIfAbsent(iterable, false);
/*     */   }
/*     */   
/*     */   default <T extends JsonElement> JsonObjectBuilder addAllIfAbsent(Stream<Map.Entry<String, T>> stream, boolean deepCopy) {
/* 461 */     Objects.requireNonNull(stream, "stream");
/* 462 */     stream.forEach(e -> {
/*     */           if (e == null || e.getKey() == null) {
/*     */             return;
/*     */           }
/*     */           addIfAbsent((String)e.getKey(), (JsonElement)e.getValue(), paramBoolean);
/*     */         });
/* 468 */     return this;
/*     */   }
/*     */   
/*     */   default <T extends JsonElement> JsonObjectBuilder addAllIfAbsent(Stream<Map.Entry<String, T>> stream) {
/* 472 */     return addAllIfAbsent(stream, false);
/*     */   }
/*     */   
/*     */   default JsonObjectBuilder addAllIfAbsent(JsonObject object, boolean deepCopy) {
/* 476 */     Objects.requireNonNull(object, "object");
/* 477 */     return addAllIfAbsent(object.entrySet(), deepCopy);
/*     */   }
/*     */   
/*     */   default JsonObjectBuilder addAllIfAbsent(JsonObject object) {
/* 481 */     return addAllIfAbsent(object, false);
/*     */   }
/*     */   
/*     */   default <T extends GsonSerializable> JsonObjectBuilder addAllSerializablesIfAbsent(Iterable<Map.Entry<String, T>> iterable) {
/* 485 */     Objects.requireNonNull(iterable, "iterable");
/* 486 */     for (Map.Entry<String, T> e : iterable) {
/* 487 */       if (e == null || e.getKey() == null) {
/*     */         continue;
/*     */       }
/* 490 */       addIfAbsent(e.getKey(), (GsonSerializable)e.getValue());
/*     */     } 
/* 492 */     return this;
/*     */   }
/*     */   
/*     */   default JsonObjectBuilder addAllStringsIfAbsent(Iterable<Map.Entry<String, String>> iterable) {
/* 496 */     Objects.requireNonNull(iterable, "iterable");
/* 497 */     for (Map.Entry<String, String> e : iterable) {
/* 498 */       if (e == null || e.getKey() == null) {
/*     */         continue;
/*     */       }
/* 501 */       addIfAbsent(e.getKey(), e.getValue());
/*     */     } 
/* 503 */     return this;
/*     */   }
/*     */   
/*     */   default <T extends Number> JsonObjectBuilder addAllNumbersIfAbsent(Iterable<Map.Entry<String, T>> iterable) {
/* 507 */     Objects.requireNonNull(iterable, "iterable");
/* 508 */     for (Map.Entry<String, T> e : iterable) {
/* 509 */       if (e == null || e.getKey() == null) {
/*     */         continue;
/*     */       }
/* 512 */       addIfAbsent(e.getKey(), (Number)e.getValue());
/*     */     } 
/* 514 */     return this;
/*     */   }
/*     */   
/*     */   default JsonObjectBuilder addAllBooleansIfAbsent(Iterable<Map.Entry<String, Boolean>> iterable) {
/* 518 */     Objects.requireNonNull(iterable, "iterable");
/* 519 */     for (Map.Entry<String, Boolean> e : iterable) {
/* 520 */       if (e == null || e.getKey() == null) {
/*     */         continue;
/*     */       }
/* 523 */       addIfAbsent(e.getKey(), e.getValue());
/*     */     } 
/* 525 */     return this;
/*     */   }
/*     */   
/*     */   default JsonObjectBuilder addAllCharactersIfAbsent(Iterable<Map.Entry<String, Character>> iterable) {
/* 529 */     Objects.requireNonNull(iterable, "iterable");
/* 530 */     for (Map.Entry<String, Character> e : iterable) {
/* 531 */       if (e == null || e.getKey() == null) {
/*     */         continue;
/*     */       }
/* 534 */       addIfAbsent(e.getKey(), e.getValue());
/*     */     } 
/* 536 */     return this;
/*     */   }
/*     */   
/*     */   JsonObjectBuilder add(String paramString, @Nullable JsonElement paramJsonElement, boolean paramBoolean);
/*     */   
/*     */   JsonObjectBuilder addIfAbsent(String paramString, @Nullable JsonElement paramJsonElement, boolean paramBoolean);
/*     */   
/*     */   JsonObject build();
/*     */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumin\\utils\gson\JsonBuilder$JsonObjectBuilder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */