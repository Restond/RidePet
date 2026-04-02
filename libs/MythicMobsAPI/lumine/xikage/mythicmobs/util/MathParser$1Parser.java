/*     */ package lumine.xikage.mythicmobs.util;
/*     */ 
/*     */ import io.lumine.xikage.mythicmobs.util.MathParser;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ class Parser
/*     */ {
/*  44 */   int pos = -1; int c;
/*     */   
/*     */   void eatChar() {
/*  47 */     this.c = (++this.pos < str.length()) ? str.charAt(this.pos) : -1;
/*     */   }
/*     */   
/*     */   void eatSpace() {
/*  51 */     for (; Character.isWhitespace(this.c); eatChar());
/*     */   }
/*     */   
/*     */   double parse() {
/*  55 */     eatChar();
/*  56 */     double v = parseExpression();
/*  57 */     if (this.c != -1) throw new RuntimeException("Unexpected: " + (char)this.c); 
/*  58 */     return v;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   double parseExpression() {
/*  68 */     double v = parseTerm();
/*     */     while (true) {
/*  70 */       eatSpace();
/*  71 */       if (this.c == 43) {
/*  72 */         eatChar();
/*  73 */         v += parseTerm(); continue;
/*  74 */       }  if (this.c == 45) {
/*  75 */         eatChar();
/*  76 */         v -= parseTerm(); continue;
/*     */       }  break;
/*  78 */     }  return v;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   double parseTerm() {
/*  84 */     double v = parseFactor();
/*     */     while (true) {
/*  86 */       eatSpace();
/*  87 */       if (this.c == 47) {
/*  88 */         eatChar();
/*  89 */         v /= parseFactor(); continue;
/*  90 */       }  if (this.c == 42 || this.c == 40) {
/*  91 */         if (this.c == 42) eatChar(); 
/*  92 */         v *= parseFactor(); continue;
/*     */       }  break;
/*  94 */     }  return v;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   double parseFactor() {
/*     */     double v;
/* 101 */     boolean negate = false;
/* 102 */     eatSpace();
/* 103 */     if (this.c == 43 || this.c == 45) {
/* 104 */       negate = (this.c == 45);
/* 105 */       eatChar();
/* 106 */       eatSpace();
/*     */     } 
/* 108 */     if (this.c == 40) {
/* 109 */       eatChar();
/* 110 */       v = parseExpression();
/* 111 */       if (this.c == 41) eatChar(); 
/*     */     } else {
/* 113 */       StringBuilder sb = new StringBuilder();
/* 114 */       while ((this.c >= 48 && this.c <= 57) || this.c == 46) {
/* 115 */         sb.append((char)this.c);
/* 116 */         eatChar();
/*     */       } 
/* 118 */       if (sb.length() == 0) throw new RuntimeException("Unexpected: " + (char)this.c); 
/* 119 */       v = Double.parseDouble(sb.toString());
/*     */     } 
/* 121 */     eatSpace();
/* 122 */     if (this.c == 94) {
/* 123 */       eatChar();
/* 124 */       v = Math.pow(v, parseFactor());
/*     */     } 
/* 126 */     if (negate) v = -v; 
/* 127 */     return v;
/*     */   }
/*     */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmob\\util\MathParser$1Parser.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */