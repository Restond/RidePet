/*     */ package lumine.xikage.mythicmobs.util;
/*     */ 
/*     */ import java.util.List;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PageableList<T>
/*     */ {
/*     */   public static final int DEFAULT_PAGE_SIZE = 10;
/*     */   private static final int PAGE_WINDOW = 10;
/*     */   private List<T> list;
/*  14 */   private int pageSize = 10;
/*     */   
/*     */   private int page;
/*     */   
/*     */   private int startingIndex;
/*     */   private int endingIndex;
/*     */   private int maxPages;
/*     */   
/*     */   public PageableList(List<T> list) {
/*  23 */     this.list = list;
/*  24 */     this.page = 1;
/*  25 */     this.maxPages = 1;
/*     */     
/*  27 */     calculatePages();
/*     */   }
/*     */   
/*     */   private io.lumine.xikage.mythicmobs.util.PageableList<T> calculatePages() {
/*  31 */     if (this.pageSize > 0)
/*     */     {
/*  33 */       if (this.list.size() % this.pageSize == 0) {
/*  34 */         this.maxPages = this.list.size() / this.pageSize;
/*     */       } else {
/*  36 */         this.maxPages = this.list.size() / this.pageSize + 1;
/*     */       } 
/*     */     }
/*  39 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List<T> getList() {
/*  48 */     return this.list;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List<T> getListForPage() {
/*  57 */     return this.list.subList(this.startingIndex, this.endingIndex);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getPageSize() {
/*  66 */     return this.pageSize;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public io.lumine.xikage.mythicmobs.util.PageableList<T> setPageSize(int pageSize) {
/*  75 */     this.pageSize = pageSize;
/*  76 */     calculatePages();
/*  77 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getPage() {
/*  86 */     return this.page;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public io.lumine.xikage.mythicmobs.util.PageableList<T> setPage(int p) {
/*  95 */     if (p >= this.maxPages) {
/*  96 */       this.page = this.maxPages;
/*  97 */     } else if (p <= 1) {
/*  98 */       this.page = 1;
/*     */     } else {
/* 100 */       this.page = p;
/*     */     } 
/*     */ 
/*     */     
/* 104 */     this.startingIndex = this.pageSize * (this.page - 1);
/* 105 */     if (this.startingIndex < 0) {
/* 106 */       this.startingIndex = 0;
/*     */     }
/* 108 */     this.endingIndex = this.startingIndex + this.pageSize;
/* 109 */     if (this.endingIndex > this.list.size()) {
/* 110 */       this.endingIndex = this.list.size();
/*     */     }
/* 112 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getMaxPages() {
/* 121 */     return this.maxPages;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getPreviousPage() {
/* 130 */     if (this.page > 1) {
/* 131 */       return this.page - 1;
/*     */     }
/* 133 */     return 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getNextPage() {
/* 143 */     if (this.page < this.maxPages) {
/* 144 */       return this.page + 1;
/*     */     }
/* 146 */     return 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getMinPageRange() {
/* 156 */     if (getPage() > 10) {
/* 157 */       return getPage() - 10;
/*     */     }
/* 159 */     return 1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getMaxPageRange() {
/* 169 */     if (getPage() < getMaxPages() - 10) {
/* 170 */       return getPage() + 10;
/*     */     }
/* 172 */     return getMaxPages();
/*     */   }
/*     */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmob\\util\PageableList.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */