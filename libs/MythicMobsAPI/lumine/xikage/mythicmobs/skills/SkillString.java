/*     */ package lumine.xikage.mythicmobs.skills;
/*     */ 
/*     */ import io.lumine.xikage.mythicmobs.MythicMobs;
/*     */ import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
/*     */ import io.lumine.xikage.mythicmobs.adapters.bukkit.BukkitAdapter;
/*     */ import io.lumine.xikage.mythicmobs.mobs.ActiveMob;
/*     */ import io.lumine.xikage.mythicmobs.skills.SkillCaster;
/*     */ import io.lumine.xikage.mythicmobs.util.Patterns;
/*     */ import java.util.regex.Matcher;
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.ChatColor;
/*     */ import org.bukkit.entity.Entity;
/*     */ import org.bukkit.entity.LivingEntity;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.entity.Wolf;
/*     */ import org.bukkit.scoreboard.Objective;
/*     */ 
/*     */ public class SkillString
/*     */ {
/*     */   static int rand;
/*     */   static Matcher Rmatcher;
/*     */   
/*     */   public static String parseMobVariables(String s, SkillCaster caster, AbstractEntity target, AbstractEntity trigger) {
/*  24 */     if (s == null) return null;
/*     */     
/*  26 */     Long time = Long.valueOf(System.nanoTime());
/*     */     
/*  28 */     if (s.contains("<mob")) {
/*     */       
/*  30 */       s = s.replace("<mob.hp>", String.valueOf((int)caster.getEntity().getHealth()));
/*  31 */       s = s.replace("<mob.php>", String.valueOf((int)(caster.getEntity().getHealth() / caster.getEntity().getMaxHealth())));
/*  32 */       s = s.replace("<mob.mhp>", String.valueOf(caster.getEntity().getMaxHealth()));
/*  33 */       s = s.replace("<mob.thp>", String.valueOf(caster.getEntity().getHealth()));
/*  34 */       s = s.replace("<mob.uuid>", String.valueOf(caster.getEntity().getUniqueId().toString()));
/*     */       
/*  36 */       if (caster instanceof ActiveMob) {
/*  37 */         ActiveMob am = (ActiveMob)caster;
/*  38 */         if (am.getType().getDisplayName() != null) {
/*  39 */           s = s.replace("<mob.name>", am.getDisplayName());
/*     */         } else {
/*  41 */           s = s.replace("<mob.name>", "Unknown");
/*     */         } 
/*  43 */         s = s.replace("<mob.level>", String.valueOf(am.getLevel()));
/*  44 */         s = s.replace("<mob.stance>", am.getStance());
/*     */         
/*  46 */         if (am.getType().getMythicEntity() instanceof io.lumine.xikage.mythicmobs.adapters.bukkit.entities.BukkitWolf) {
/*     */           
/*  48 */           Wolf w = (Wolf)BukkitAdapter.adapt(am.getEntity());
/*     */           
/*  50 */           if (w.getOwner() != null) {
/*  51 */             s = s.replace("<mob.owner.name>", w.getOwner().getName().toString());
/*  52 */             s = s.replace("<mob.owner.uuid>", w.getOwner().getUniqueId().toString());
/*     */           } 
/*     */         } 
/*     */ 
/*     */         
/*  57 */         if (am.hasThreatTable()) {
/*  58 */           if (am.getThreatTable().inCombat()) {
/*  59 */             s = s.replace("<mob.tt.top>", am.getThreatTable().getTopThreatHolder().getName());
/*     */           } else {
/*  61 */             s = s.replace("<mob.tt.top>", "Unknown");
/*     */           } 
/*     */         }
/*  64 */       } else if (caster.getEntity().isPlayer()) {
/*  65 */         s = s.replace("<mob.name>", caster.getEntity().asPlayer().getName());
/*     */       } 
/*     */ 
/*     */       
/*  69 */       s = s.replace("<mob.l.w>", caster.getEntity().getWorld().getName().toString());
/*     */       
/*  71 */       if (s.contains("<mob.l.x")) {
/*  72 */         if (s.contains("<mob.l.x%")) {
/*  73 */           Rmatcher = Patterns.MSMobX.matcher(s);
/*  74 */           Rmatcher.find();
/*  75 */           rand = (MythicMobs.r.nextInt(2) == 1) ? MythicMobs.r.nextInt(1 + Integer.parseInt(Rmatcher.group(1))) : (0 - MythicMobs.r.nextInt(Integer.parseInt(Rmatcher.group(1))));
/*  76 */           s = s.replace("<mob.l.x%" + Rmatcher.group(1) + ">", Integer.toString(caster.getLocation().getBlockX() + rand));
/*     */         } else {
/*  78 */           s = s.replace("<mob.l.x>", Integer.toString(caster.getLocation().getBlockX()));
/*     */         } 
/*     */       }
/*  81 */       if (s.contains("<mob.l.y")) {
/*  82 */         if (s.contains("<mob.l.y%")) {
/*  83 */           Rmatcher = Patterns.MSMobY.matcher(s);
/*  84 */           Rmatcher.find();
/*  85 */           rand = (MythicMobs.r.nextInt(2) == 1) ? MythicMobs.r.nextInt(1 + Integer.parseInt(Rmatcher.group(1))) : (0 - MythicMobs.r.nextInt(Integer.parseInt(Rmatcher.group(1))));
/*  86 */           s = s.replace("<mob.l.y%" + Rmatcher.group(1) + ">", Integer.toString(caster.getLocation().getBlockY() + rand));
/*     */         } else {
/*  88 */           s = s.replace("<mob.l.y>", Integer.toString(caster.getLocation().getBlockY()));
/*     */         } 
/*     */       }
/*  91 */       if (s.contains("<mob.l.z")) {
/*  92 */         if (s.contains("<mob.l.z%")) {
/*  93 */           Rmatcher = Patterns.MSMobZ.matcher(s);
/*  94 */           Rmatcher.find();
/*  95 */           rand = (MythicMobs.r.nextInt(2) == 1) ? MythicMobs.r.nextInt(1 + Integer.parseInt(Rmatcher.group(1))) : (0 - MythicMobs.r.nextInt(Integer.parseInt(Rmatcher.group(1))));
/*  96 */           s = s.replace("<mob.l.z%" + Rmatcher.group(1) + ">", Integer.toString(caster.getLocation().getBlockZ() + rand));
/*     */         } else {
/*  98 */           s = s.replace("<mob.l.z>", Integer.toString(caster.getLocation().getBlockZ()));
/*     */         } 
/*     */       }
/*     */       
/* 102 */       if (s.contains("<mob.score.")) {
/* 103 */         Rmatcher = Patterns.MobScore.matcher(s);
/*     */         
/* 105 */         while (Rmatcher.find()) {
/* 106 */           String objective = Rmatcher.group(1);
/*     */           
/* 108 */           Objective obj = Bukkit.getScoreboardManager().getMainScoreboard().getObjective(objective);
/*     */           
/* 110 */           int score = 0;
/* 111 */           if (obj != null) {
/* 112 */             score = obj.getScore(caster.getEntity().getUniqueId().toString()).getScore();
/*     */           }
/* 114 */           s = s.replace("<mob.score." + objective + ">", "" + score);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 119 */     if (s.contains("<caster")) {
/*     */       
/* 121 */       s = s.replace("<caster.hp>", String.valueOf((int)caster.getEntity().getHealth()));
/* 122 */       s = s.replace("<caster.php>", String.valueOf((int)(caster.getEntity().getHealth() / caster.getEntity().getMaxHealth())));
/* 123 */       s = s.replace("<caster.mhp>", String.valueOf(caster.getEntity().getMaxHealth()));
/* 124 */       s = s.replace("<caster.thp>", String.valueOf(caster.getEntity().getHealth()));
/* 125 */       s = s.replace("<caster.uuid>", String.valueOf(caster.getEntity().getUniqueId().toString()));
/*     */       
/* 127 */       if (caster instanceof ActiveMob) {
/* 128 */         ActiveMob am = (ActiveMob)caster;
/* 129 */         if (am.getType().getDisplayName() != null) {
/* 130 */           s = s.replace("<caster.name>", am.getDisplayName());
/*     */         } else {
/* 132 */           s = s.replace("<caster.name>", "Unknown");
/*     */         } 
/* 134 */         s = s.replace("<caster.level>", String.valueOf(am.getLevel()));
/* 135 */         s = s.replace("<caster.stance>", am.getStance());
/*     */         
/* 137 */         if (am.getType().getMythicEntity() instanceof io.lumine.xikage.mythicmobs.adapters.bukkit.entities.BukkitWolf) {
/*     */           
/* 139 */           Wolf w = (Wolf)BukkitAdapter.adapt(am.getEntity());
/*     */           
/* 141 */           if (w.getOwner() != null) {
/* 142 */             s = s.replace("<caster.owner.name>", w.getOwner().getName().toString());
/* 143 */             s = s.replace("<caster.owner.uuid>", w.getOwner().getUniqueId().toString());
/*     */           } 
/*     */         } 
/*     */ 
/*     */         
/* 148 */         if (am.hasThreatTable()) {
/* 149 */           if (am.getThreatTable().inCombat()) {
/* 150 */             s = s.replace("<caster.tt.top>", am.getThreatTable().getTopThreatHolder().getName());
/*     */           } else {
/* 152 */             s = s.replace("<caster.tt.top>", "Unknown");
/*     */           } 
/*     */         }
/* 155 */       } else if (caster.getEntity().isPlayer()) {
/* 156 */         s = s.replace("<caster.name>", caster.getEntity().asPlayer().getName());
/*     */       } 
/*     */ 
/*     */       
/* 160 */       s = s.replace("<caster.l.w>", caster.getEntity().getWorld().getName().toString());
/*     */       
/* 162 */       if (s.contains("<caster.l.x")) {
/* 163 */         if (s.contains("<caster.l.x%")) {
/* 164 */           Rmatcher = Patterns.MSMobX.matcher(s);
/* 165 */           Rmatcher.find();
/* 166 */           rand = (MythicMobs.r.nextInt(2) == 1) ? MythicMobs.r.nextInt(1 + Integer.parseInt(Rmatcher.group(1))) : (0 - MythicMobs.r.nextInt(Integer.parseInt(Rmatcher.group(1))));
/* 167 */           s = s.replace("<caster.l.x%" + Rmatcher.group(1) + ">", Integer.toString(caster.getLocation().getBlockX() + rand));
/*     */         } else {
/* 169 */           s = s.replace("<caster.l.x>", Integer.toString(caster.getLocation().getBlockX()));
/*     */         } 
/*     */       }
/* 172 */       if (s.contains("<caster.l.y")) {
/* 173 */         if (s.contains("<caster.l.y%")) {
/* 174 */           Rmatcher = Patterns.MSMobY.matcher(s);
/* 175 */           Rmatcher.find();
/* 176 */           rand = (MythicMobs.r.nextInt(2) == 1) ? MythicMobs.r.nextInt(1 + Integer.parseInt(Rmatcher.group(1))) : (0 - MythicMobs.r.nextInt(Integer.parseInt(Rmatcher.group(1))));
/* 177 */           s = s.replace("<caster.l.y%" + Rmatcher.group(1) + ">", Integer.toString(caster.getLocation().getBlockY() + rand));
/*     */         } else {
/* 179 */           s = s.replace("<caster.l.y>", Integer.toString(caster.getLocation().getBlockY()));
/*     */         } 
/*     */       }
/* 182 */       if (s.contains("<caster.l.z")) {
/* 183 */         if (s.contains("<caster.l.z%")) {
/* 184 */           Rmatcher = Patterns.MSMobZ.matcher(s);
/* 185 */           Rmatcher.find();
/* 186 */           rand = (MythicMobs.r.nextInt(2) == 1) ? MythicMobs.r.nextInt(1 + Integer.parseInt(Rmatcher.group(1))) : (0 - MythicMobs.r.nextInt(Integer.parseInt(Rmatcher.group(1))));
/* 187 */           s = s.replace("<caster.l.z%" + Rmatcher.group(1) + ">", Integer.toString(caster.getLocation().getBlockZ() + rand));
/*     */         } else {
/* 189 */           s = s.replace("<caster.l.z>", Integer.toString(caster.getLocation().getBlockZ()));
/*     */         } 
/*     */       }
/*     */       
/* 193 */       if (s.contains("<caster.score.")) {
/* 194 */         Rmatcher = Patterns.MobScore.matcher(s);
/*     */         
/* 196 */         while (Rmatcher.find()) {
/* 197 */           String objective = Rmatcher.group(1);
/*     */           
/* 199 */           Objective obj = Bukkit.getScoreboardManager().getMainScoreboard().getObjective(objective);
/*     */           
/* 201 */           int score = 0;
/* 202 */           if (obj != null) {
/* 203 */             score = obj.getScore(caster.getEntity().getUniqueId().toString()).getScore();
/*     */           }
/* 205 */           s = s.replace("<caster.score." + objective + ">", "" + score);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 210 */     if (s.contains("<target") && target != null) {
/* 211 */       if (target != null && target.isPlayer()) {
/* 212 */         s = s.replace("<target.name>", target.asPlayer().getName());
/* 213 */       } else if (target != null && target.getName() != null) {
/* 214 */         s = s.replace("<target.name>", target.getName());
/*     */       } 
/* 216 */       s = s.replace("<target.hp>", String.valueOf((int)target.getHealth()));
/* 217 */       s = s.replace("<target.uuid>", String.valueOf(target.getUniqueId().toString()));
/*     */ 
/*     */       
/* 220 */       if (caster instanceof ActiveMob && ((ActiveMob)caster).hasThreatTable()) {
/* 221 */         s = s.replace("<target.threat>", String.valueOf(((ActiveMob)caster).getThreatTable().getThreat(target)));
/*     */       }
/*     */ 
/*     */       
/* 225 */       s = s.replace("<target.l.w>", target.getWorld().getName().toString());
/*     */       
/* 227 */       if (s.contains("<target.l.x")) {
/* 228 */         if (s.contains("<target.l.x%")) {
/* 229 */           Rmatcher = Patterns.MSTargetX.matcher(s);
/* 230 */           Rmatcher.find();
/* 231 */           rand = (MythicMobs.r.nextInt(2) == 1) ? MythicMobs.r.nextInt(1 + Integer.parseInt(Rmatcher.group(1))) : (0 - MythicMobs.r.nextInt(Integer.parseInt(Rmatcher.group(1))));
/* 232 */           s = s.replace("<target.l.x%" + Rmatcher.group(1) + ">", Integer.toString(target.getLocation().getBlockX() + rand));
/*     */         } else {
/* 234 */           s = s.replace("<target.l.x>", Integer.toString(target.getLocation().getBlockX()));
/*     */         } 
/*     */       }
/* 237 */       if (s.contains("<target.l.y")) {
/* 238 */         if (s.contains("<target.l.y%")) {
/* 239 */           Rmatcher = Patterns.MSTargetY.matcher(s);
/* 240 */           Rmatcher.find();
/* 241 */           rand = (MythicMobs.r.nextInt(2) == 1) ? MythicMobs.r.nextInt(1 + Integer.parseInt(Rmatcher.group(1))) : (0 - MythicMobs.r.nextInt(Integer.parseInt(Rmatcher.group(1))));
/* 242 */           s = s.replace("<target.l.y%" + Rmatcher.group(1) + ">", Integer.toString(target.getLocation().getBlockY() + rand));
/*     */         } else {
/* 244 */           s = s.replace("<target.l.y>", Integer.toString(target.getLocation().getBlockY()));
/*     */         } 
/*     */       }
/* 247 */       if (s.contains("<target.l.z")) {
/* 248 */         if (s.contains("<target.l.z%")) {
/* 249 */           Rmatcher = Patterns.MSTargetZ.matcher(s);
/* 250 */           Rmatcher.find();
/* 251 */           rand = (MythicMobs.r.nextInt(2) == 1) ? MythicMobs.r.nextInt(1 + Integer.parseInt(Rmatcher.group(1))) : (0 - MythicMobs.r.nextInt(Integer.parseInt(Rmatcher.group(1))));
/* 252 */           s = s.replace("<target.l.z%" + Rmatcher.group(1) + ">", Integer.toString(target.getLocation().getBlockZ() + rand));
/*     */         } else {
/* 254 */           s = s.replace("<target.l.z>", Integer.toString(target.getLocation().getBlockZ()));
/*     */         } 
/*     */       }
/*     */       
/* 258 */       if (s.contains("<target.score.")) {
/* 259 */         Rmatcher = Patterns.TargetScore.matcher(s);
/*     */         
/* 261 */         while (Rmatcher.find()) {
/* 262 */           String objective = Rmatcher.group(1);
/*     */           
/* 264 */           Objective obj = Bukkit.getScoreboardManager().getMainScoreboard().getObjective(objective);
/*     */           
/* 266 */           int score = 0;
/* 267 */           if (obj != null) {
/* 268 */             if (target.isPlayer()) {
/* 269 */               score = obj.getScore(target.asPlayer().getName()).getScore();
/*     */             } else {
/* 271 */               score = obj.getScore(target.getUniqueId().toString()).getScore();
/*     */             } 
/*     */           }
/* 274 */           s = s.replace("<target.score." + objective + ">", "" + score);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 279 */     if (s.contains("<trigger")) {
/* 280 */       if (trigger != null) {
/* 281 */         if (trigger.isPlayer()) {
/* 282 */           s = s.replace("<trigger.name>", trigger.asPlayer().getName());
/* 283 */         } else if (trigger.getName() != null) {
/* 284 */           s = s.replace("<trigger.name>", trigger.getName());
/*     */         } else {
/* 286 */           s = s.replace("<trigger.name>", "Unknown");
/*     */         } 
/* 288 */         s = s.replace("<trigger.hp>", String.valueOf((int)trigger.getHealth()));
/* 289 */         s = s.replace("<trigger.uuid>", String.valueOf(trigger.getUniqueId().toString()));
/*     */ 
/*     */         
/* 292 */         if (caster instanceof ActiveMob && ((ActiveMob)caster).hasThreatTable()) {
/* 293 */           s = s.replace("<trigger.threat>", String.valueOf(((ActiveMob)caster).getThreatTable().getThreat(trigger)));
/*     */         }
/*     */ 
/*     */         
/* 297 */         s = s.replace("<trigger.l.w>", trigger.getWorld().getName().toString());
/*     */         
/* 299 */         if (s.contains("<trigger.l.x")) {
/* 300 */           if (s.contains("<trigger.l.x%")) {
/* 301 */             Rmatcher = Patterns.MSTriggerX.matcher(s);
/* 302 */             Rmatcher.find();
/* 303 */             rand = (MythicMobs.r.nextInt(2) == 1) ? MythicMobs.r.nextInt(1 + Integer.parseInt(Rmatcher.group(1))) : (0 - MythicMobs.r.nextInt(Integer.parseInt(Rmatcher.group(1))));
/* 304 */             s = s.replace("<trigger.l.x%" + Rmatcher.group(1) + ">", Integer.toString(trigger.getLocation().getBlockX() + rand));
/*     */           } else {
/* 306 */             s = s.replace("<trigger.l.x>", Integer.toString(trigger.getLocation().getBlockX()));
/*     */           } 
/*     */         }
/* 309 */         if (s.contains("<trigger.l.y")) {
/* 310 */           if (s.contains("<trigger.l.y%")) {
/* 311 */             Rmatcher = Patterns.MSTriggerY.matcher(s);
/* 312 */             Rmatcher.find();
/* 313 */             rand = (MythicMobs.r.nextInt(2) == 1) ? MythicMobs.r.nextInt(1 + Integer.parseInt(Rmatcher.group(1))) : (0 - MythicMobs.r.nextInt(Integer.parseInt(Rmatcher.group(1))));
/* 314 */             s = s.replace("<trigger.l.y%" + Rmatcher.group(1) + ">", Integer.toString(trigger.getLocation().getBlockY() + rand));
/*     */           } else {
/* 316 */             s = s.replace("<trigger.l.y>", Integer.toString(trigger.getLocation().getBlockY()));
/*     */           } 
/*     */         }
/* 319 */         if (s.contains("<trigger.l.z")) {
/* 320 */           if (s.contains("<trigger.l.z%")) {
/* 321 */             Rmatcher = Patterns.MSTriggerZ.matcher(s);
/* 322 */             Rmatcher.find();
/* 323 */             rand = (MythicMobs.r.nextInt(2) == 1) ? MythicMobs.r.nextInt(1 + Integer.parseInt(Rmatcher.group(1))) : (0 - MythicMobs.r.nextInt(Integer.parseInt(Rmatcher.group(1))));
/* 324 */             s = s.replace("<trigger.l.z%" + Rmatcher.group(1) + ">", Integer.toString(trigger.getLocation().getBlockZ() + rand));
/*     */           } else {
/* 326 */             s = s.replace("<trigger.l.z>", Integer.toString(trigger.getLocation().getBlockZ()));
/*     */           } 
/*     */         }
/*     */         
/* 330 */         if (s.contains("<trigger.score.")) {
/* 331 */           Rmatcher = Patterns.TriggerScore.matcher(s);
/*     */           
/* 333 */           while (Rmatcher.find()) {
/* 334 */             String objective = Rmatcher.group(1);
/*     */             
/* 336 */             Objective obj = Bukkit.getScoreboardManager().getMainScoreboard().getObjective(objective);
/*     */             
/* 338 */             int score = 0;
/* 339 */             if (obj != null) {
/* 340 */               if (trigger.isPlayer()) {
/* 341 */                 score = obj.getScore(trigger.asPlayer().getName()).getScore();
/*     */               } else {
/* 343 */                 score = obj.getScore(trigger.getUniqueId().toString()).getScore();
/*     */               } 
/*     */             }
/* 346 */             s = s.replace("<trigger.score." + objective + ">", "" + score);
/*     */           } 
/*     */         } 
/*     */       } else {
/* 350 */         s = s.replace("<trigger.name>", "Unknown");
/*     */       } 
/*     */     }
/*     */     
/* 354 */     s = parseMessageSpecialChars(s);
/*     */     
/* 356 */     if (s.contains("<random")) {
/* 357 */       Matcher pMatcher = Patterns.VariableRanges.matcher(s);
/* 358 */       while (pMatcher.find()) {
/* 359 */         int min = Integer.parseInt(pMatcher.group(1));
/* 360 */         int max = Integer.parseInt(pMatcher.group(2));
/* 361 */         int num = MythicMobs.r.nextInt(max - min + 1) + min;
/* 362 */         s = s.replace(pMatcher.group(0), "" + num);
/*     */       } 
/*     */     } 
/*     */     
/* 366 */     if (s.contains("<global.score.")) {
/* 367 */       Rmatcher = Patterns.GlobalScore.matcher(s);
/*     */       
/* 369 */       while (Rmatcher.find()) {
/* 370 */         String objective = Rmatcher.group(1);
/*     */         
/* 372 */         Objective obj = Bukkit.getScoreboardManager().getMainScoreboard().getObjective(objective);
/*     */         
/* 374 */         int score = 0;
/* 375 */         if (obj != null) {
/* 376 */           score = obj.getScore("__GLOBAL__").getScore();
/*     */         }
/* 378 */         s = s.replace("<global.score." + objective + ">", "" + score);
/*     */       } 
/*     */     } 
/*     */     
/* 382 */     if (s.contains("<score.")) {
/* 383 */       Rmatcher = Patterns.GenericScore.matcher(s);
/*     */       
/* 385 */       while (Rmatcher.find()) {
/* 386 */         String objective = Rmatcher.group(1);
/* 387 */         String entry = Rmatcher.group(2);
/*     */         
/* 389 */         Objective obj = Bukkit.getScoreboardManager().getMainScoreboard().getObjective(objective);
/*     */         
/* 391 */         int score = 0;
/* 392 */         if (obj != null) {
/* 393 */           score = obj.getScore(entry).getScore();
/*     */         }
/* 395 */         s = s.replace("<score." + objective + "." + entry + ">", "" + score);
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 401 */     return s;
/*     */   }
/*     */   
/*     */   public static String parseMessageSpecialChars(String s) {
/* 405 */     if (s == null) return null;
/*     */     
/* 407 */     s = s.replace("<&co>", ":");
/* 408 */     s = s.replace("<&sq>", "'");
/* 409 */     s = s.replace("<&da>", "-");
/* 410 */     s = s.replace("<&bs>", "\\");
/* 411 */     s = s.replace("<&fs>", "/");
/* 412 */     s = s.replace("<&sp>", " ");
/* 413 */     s = s.replace("<&cm>", ",");
/* 414 */     s = s.replace("<&sc>", ";");
/* 415 */     s = s.replace("<&eq>", "=");
/*     */     
/* 417 */     s = s.replace("<&dq>", "\"");
/* 418 */     s = s.replace("<&rb>", "]");
/* 419 */     s = s.replace("<&lb>", "[");
/* 420 */     s = s.replace("<&rc>", "}");
/* 421 */     s = s.replace("<&lc>", "{");
/* 422 */     s = s.replace("<&nl>", "\n");
/* 423 */     s = s.replace("<&nm>", "#");
/* 424 */     s = s.replace("<&skull>", "☠");
/* 425 */     s = s.replace("<&heart>", "❤");
/*     */     
/* 427 */     s = ChatColor.translateAlternateColorCodes('&', s);
/* 428 */     return s;
/*     */   }
/*     */   
/*     */   public static String unparseMessageSpecialChars(String s) {
/* 432 */     if (s == null) return null;
/*     */     
/* 434 */     s = s.replace("-", "<&da>");
/* 435 */     s = s.replace("\\", "<&bs>");
/* 436 */     s = s.replace("/", "<&fs>");
/* 437 */     s = s.replace(" ", "<&sp>");
/* 438 */     s = s.replace(",", "<&cm>");
/* 439 */     s = s.replace(";", "<&sc>");
/* 440 */     s = s.replace("=", "<&eq>");
/* 441 */     s = s.replace("{", "<&lc>");
/* 442 */     s = s.replace("}", "<&rc>");
/* 443 */     s = s.replace("[", "<&lb>");
/* 444 */     s = s.replace("]", "<&rb>");
/* 445 */     s = s.replace("'", "<&sq>");
/*     */     
/* 447 */     return s;
/*     */   }
/*     */   
/*     */   public static String convertLegacyVariables(String s) {
/* 451 */     if (s == null) return null;
/*     */     
/* 453 */     s = s.replace("<mob", "<caster");
/* 454 */     s = s.replace("<dropper", "<caster");
/* 455 */     s = s.replace("<player", "<caster");
/*     */     
/* 457 */     s = s.replace("<killer", "<trigger");
/*     */     
/* 459 */     s = s.replace("$mobhp", "<mob.hp>");
/* 460 */     s = s.replace("$bosshp", "<mob.hp>");
/*     */     
/* 462 */     s = s.replace("$mobtruehp", "<mob.thp>");
/* 463 */     s = s.replace("$bosstruehp", "<mob.thp>");
/*     */     
/* 465 */     s = s.replace("$mobpercenthp", "<mob.php>");
/* 466 */     s = s.replace("$bosspercenthp", "<mob.php>");
/*     */     
/* 468 */     s = s.replace("$mobmaxhp", "<mob.mhp>");
/* 469 */     s = s.replace("$bossmaxhp", "<mob.mhp>");
/*     */     
/* 471 */     s = s.replace("$mobuuid", "<mob.uuid>");
/*     */     
/* 473 */     s = s.replace("$moblevel", "<mob.lvl>");
/* 474 */     s = s.replace("$level", "<mob.lvl>");
/*     */     
/* 476 */     s = s.replace("$money", "<drops.money>");
/* 477 */     s = s.replace("$xp", "<drops.xp>");
/*     */ 
/*     */     
/* 480 */     if (s.contains("$boss_x")) {
/* 481 */       if (s.contains("$boss_x%")) {
/* 482 */         Matcher Rmatcher = Patterns.LegacyBossX.matcher(s);
/* 483 */         Rmatcher.find();
/* 484 */         s = s.replace("$boss_x%" + Rmatcher.group(1), "<mob.l.x%" + Rmatcher.group(1) + ">");
/*     */       } else {
/* 486 */         s = s.replace("$boss_x", "<mob.l.x>");
/*     */       } 
/*     */     }
/* 489 */     if (s.contains("$boss_y")) {
/* 490 */       if (s.contains("$boss_y%")) {
/* 491 */         Matcher Rmatcher = Patterns.LegacyBossY.matcher(s);
/* 492 */         Rmatcher.find();
/* 493 */         s = s.replace("$boss_y%" + Rmatcher.group(1), "<mob.l.y%" + Rmatcher.group(1) + ">");
/*     */       } else {
/* 495 */         s = s.replace("$boss_y", "<mob.l.y>");
/*     */       } 
/*     */     }
/* 498 */     if (s.contains("$boss_z")) {
/* 499 */       if (s.contains("$boss_z%")) {
/* 500 */         Matcher Rmatcher = Patterns.LegacyBossZ.matcher(s);
/* 501 */         Rmatcher.find();
/* 502 */         s = s.replace("$boss_z%" + Rmatcher.group(1), "<mob.l.z%" + Rmatcher.group(1) + ">");
/*     */       } else {
/* 504 */         s = s.replace("$boss_z", "<mob.l.z>");
/*     */       } 
/*     */     }
/* 507 */     if (s.contains("$player_x")) {
/* 508 */       if (s.contains("$player_x%")) {
/* 509 */         Matcher Rmatcher = Patterns.LegacyPlayerX.matcher(s);
/* 510 */         Rmatcher.find();
/* 511 */         s = s.replace("$player_x%" + Rmatcher.group(1), "<trigger.l.x%" + Rmatcher.group(1) + ">");
/*     */       } else {
/* 513 */         s = s.replace("$player_x", "<trigger.l.x>");
/*     */       } 
/*     */     }
/* 516 */     if (s.contains("$player_y")) {
/* 517 */       if (s.contains("$player_y%")) {
/* 518 */         Matcher Rmatcher = Patterns.LegacyPlayerY.matcher(s);
/* 519 */         Rmatcher.find();
/* 520 */         s = s.replace("$player_y%" + Rmatcher.group(1), "<trigger.l.y%" + Rmatcher.group(1) + ">");
/*     */       } else {
/* 522 */         s = s.replace("$player_y", "<trigger.l.y>");
/*     */       } 
/*     */     }
/* 525 */     if (s.contains("$player_z")) {
/* 526 */       if (s.contains("$player_z%")) {
/* 527 */         Matcher Rmatcher = Patterns.LegacyPlayerZ.matcher(s);
/* 528 */         Rmatcher.find();
/* 529 */         s = s.replace("$player_z%" + Rmatcher.group(1), "<trigger.l.z%" + Rmatcher.group(1) + ">");
/*     */       } else {
/* 531 */         s = s.replace("$player_z", "<trigger.l.z>");
/*     */       } 
/*     */     }
/*     */     
/* 535 */     s = s.replace("$player", "<target.name>");
/* 536 */     s = s.replace("$target", "<target.name>");
/* 537 */     s = s.replace("$mobname", "<mob.name>");
/* 538 */     s = s.replace("$boss", "<mob.name>");
/*     */     
/* 540 */     return s;
/*     */   }
/*     */   
/*     */   public static String parseMobString(String s, LivingEntity mob, LivingEntity target) {
/* 544 */     if (s == null) return s; 
/* 545 */     ActiveMob am = MythicMobs.inst().getMobManager().getMythicMobInstance((Entity)mob);
/*     */     
/* 547 */     MythicMobs.debug(2, "Parsing Mob String " + s);
/* 548 */     s = parseMessageSpecialChars(s);
/*     */     
/* 550 */     if (s.contains("$mobname")) {
/* 551 */       s = s.replace("$mobname", am.getDisplayName());
/*     */     }
/*     */ 
/*     */     
/* 555 */     int rand = 0;
/*     */     
/* 557 */     if (s.contains("$player_x") && 
/* 558 */       target != null) {
/* 559 */       if (s.contains("$player_x%")) {
/* 560 */         Matcher Rmatcher = Patterns.LegacyPlayerX.matcher(s);
/* 561 */         Rmatcher.find();
/* 562 */         rand = MythicMobs.r.nextInt(2);
/* 563 */         rand = (rand == 1) ? MythicMobs.r.nextInt(1 + Integer.parseInt(Rmatcher.group(1))) : (0 - MythicMobs.r.nextInt(Integer.parseInt(Rmatcher.group(1))));
/*     */         
/* 565 */         s = s.replace("$player_x%" + Rmatcher.group(1), Integer.toString(target.getLocation().getBlockX() + rand));
/*     */       } else {
/* 567 */         s = s.replace("$player_x", Integer.toString(target.getLocation().getBlockX()));
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/* 572 */     if (s.contains("$player_yc") && 
/* 573 */       target != null) {
/* 574 */       if (s.contains("$player_yc%")) {
/* 575 */         Matcher Rmatcher = Patterns.LegacyPlayerYC.matcher(s);
/* 576 */         Rmatcher.find();
/* 577 */         rand = MythicMobs.r.nextInt(2);
/* 578 */         rand = (rand == 1) ? MythicMobs.r.nextInt(1 + Integer.parseInt(Rmatcher.group(1))) : (0 - MythicMobs.r.nextInt(Integer.parseInt(Rmatcher.group(1))));
/*     */         
/* 580 */         s = s.replace("$player_yc%" + Rmatcher.group(1), Integer.toString(target.getLocation().getBlockY() + 1 + rand));
/*     */       } else {
/* 582 */         s = s.replace("$player_yc", Integer.toString(target.getLocation().getBlockY() + 1));
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/* 587 */     if (s.contains("$player_y") && 
/* 588 */       target != null) {
/* 589 */       if (s.contains("$player_y%")) {
/* 590 */         Matcher Rmatcher = Patterns.LegacyPlayerY.matcher(s);
/* 591 */         Rmatcher.find();
/* 592 */         rand = MythicMobs.r.nextInt(2);
/* 593 */         rand = (rand == 1) ? MythicMobs.r.nextInt(1 + Integer.parseInt(Rmatcher.group(1))) : (0 - MythicMobs.r.nextInt(Integer.parseInt(Rmatcher.group(1))));
/*     */         
/* 595 */         s = s.replace("$player_y%" + Rmatcher.group(1), Integer.toString(target.getLocation().getBlockY() + rand));
/*     */       } else {
/* 597 */         s = s.replace("$player_y", Integer.toString(target.getLocation().getBlockY()));
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/* 602 */     if (s.contains("$player_z") && 
/* 603 */       target != null) {
/* 604 */       if (s.contains("$player_z%")) {
/* 605 */         Matcher Rmatcher = Patterns.LegacyPlayerZ.matcher(s);
/* 606 */         Rmatcher.find();
/* 607 */         rand = MythicMobs.r.nextInt(2);
/* 608 */         rand = (rand == 1) ? MythicMobs.r.nextInt(1 + Integer.parseInt(Rmatcher.group(1))) : (0 - MythicMobs.r.nextInt(Integer.parseInt(Rmatcher.group(1))));
/*     */         
/* 610 */         s = s.replace("$player_z%" + Rmatcher.group(1), Integer.toString(target.getLocation().getBlockZ() + rand));
/*     */       } else {
/* 612 */         s = s.replace("$player_z", Integer.toString(target.getLocation().getBlockZ()));
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/* 617 */     if (s.contains("$boss_x")) {
/* 618 */       if (s.contains("$boss_x%")) {
/* 619 */         Matcher Rmatcher = Patterns.LegacyBossX.matcher(s);
/* 620 */         Rmatcher.find();
/* 621 */         rand = MythicMobs.r.nextInt(2);
/* 622 */         rand = (rand == 1) ? MythicMobs.r.nextInt(1 + Integer.parseInt(Rmatcher.group(1))) : (0 - MythicMobs.r.nextInt(Integer.parseInt(Rmatcher.group(1))));
/*     */         
/* 624 */         s = s.replace("$boss_x%" + Rmatcher.group(1), Integer.toString(mob.getLocation().getBlockX() + rand));
/*     */       } else {
/* 626 */         s = s.replace("$boss_x", Integer.toString(mob.getLocation().getBlockX()));
/*     */       } 
/*     */     }
/*     */     
/* 630 */     if (s.contains("$boss_y")) {
/* 631 */       if (s.contains("$boss_y%")) {
/* 632 */         Matcher Rmatcher = Patterns.LegacyBossY.matcher(s);
/* 633 */         Rmatcher.find();
/* 634 */         rand = MythicMobs.r.nextInt(2);
/* 635 */         rand = (rand == 1) ? MythicMobs.r.nextInt(1 + Integer.parseInt(Rmatcher.group(1))) : (0 - MythicMobs.r.nextInt(Integer.parseInt(Rmatcher.group(1))));
/*     */         
/* 637 */         s = s.replace("$boss_y%" + Rmatcher.group(1), Integer.toString(mob.getLocation().getBlockY() + rand));
/*     */       } else {
/* 639 */         s = s.replace("$boss_y", Integer.toString(mob.getLocation().getBlockY()));
/*     */       } 
/*     */     }
/*     */     
/* 643 */     if (s.contains("$boss_z")) {
/* 644 */       if (s.contains("$boss_z%")) {
/* 645 */         Matcher Rmatcher = Patterns.LegacyBossZ.matcher(s);
/* 646 */         Rmatcher.find();
/* 647 */         rand = MythicMobs.r.nextInt(2);
/* 648 */         rand = (rand == 1) ? MythicMobs.r.nextInt(1 + Integer.parseInt(Rmatcher.group(1))) : (0 - MythicMobs.r.nextInt(Integer.parseInt(Rmatcher.group(1))));
/*     */         
/* 650 */         s = s.replace("$boss_z%" + Rmatcher.group(1), Integer.toString(mob.getLocation().getBlockZ() + rand));
/*     */       } else {
/* 652 */         s = s.replace("$boss_z", Integer.toString(mob.getLocation().getBlockZ()));
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/* 657 */     if (s.contains("$mobhp")) {
/* 658 */       s = s.replace("$mobhp", String.valueOf((int)mob.getHealth()));
/*     */     }
/* 660 */     if (s.contains("$bosshp")) {
/* 661 */       s = s.replace("$bosshp", String.valueOf((int)mob.getHealth()));
/*     */     }
/* 663 */     if (s.contains("$mobtruehp")) {
/* 664 */       s = s.replace("$mobtruehp", String.valueOf(mob.getHealth()));
/*     */     }
/* 666 */     if (s.contains("$mobmaxhp")) {
/* 667 */       s = s.replace("$mobmaxhp", String.valueOf((int)mob.getMaxHealth()));
/*     */     }
/* 669 */     if (s.contains("$mobtruemaxhp")) {
/* 670 */       s = s.replace("$mobtruemaxhp", "" + mob.getMaxHealth());
/*     */     }
/* 672 */     if (s.contains("$mobpercenthp")) {
/* 673 */       s = s.replace("$mobpercenthp", String.valueOf((int)(mob.getHealth() / mob.getMaxHealth())));
/*     */     }
/* 675 */     if (s.contains("$moblevel")) {
/* 676 */       s = s.replace("$moblevel", String.valueOf(am.getLevel()));
/*     */     }
/* 678 */     if (s.contains("$level")) {
/* 679 */       s = s.replace("$level", String.valueOf(am.getLevel()));
/*     */     }
/*     */     
/* 682 */     if (s.contains("$mobuuid")) {
/* 683 */       s = s.replace("$mobuuid", mob.getUniqueId().toString());
/*     */     }
/* 685 */     if (s.contains("$boss") && 
/* 686 */       am.getType() != null) {
/* 687 */       s = s.replace("$boss", parseMobString(am.getDisplayName(), mob, target));
/*     */     }
/*     */ 
/*     */     
/* 691 */     if (s.contains("$world")) {
/* 692 */       s = s.replace("$world", mob.getWorld().getName());
/*     */     }
/*     */     
/* 695 */     if (s.contains("$threattarget")) {
/* 696 */       if (am.getThreatTable().getTopThreatHolder() != null) {
/* 697 */         if (am.getThreatTable().getTopThreatHolder() instanceof Player) {
/* 698 */           s = s.replace("$threattargetname", ((Player)am.getThreatTable().getTopThreatHolder()).getName());
/*     */         }
/* 700 */         else if (am.getThreatTable().getTopThreatHolder().getName() != null) {
/* 701 */           s = s.replace("$threattargetname", am.getThreatTable().getTopThreatHolder().getName());
/*     */         } else {
/* 703 */           s = s.replace("$threattargetname", "Unknown");
/*     */         } 
/*     */       } else {
/*     */         
/* 707 */         s = s.replace("$threattargetname", "Unknown");
/*     */       } 
/*     */     }
/* 710 */     if (s.contains("$threattargetthreat")) {
/* 711 */       s = s.replace("$threattargetthreat", String.valueOf((int)am.getThreatTable().getTopTargetThreat()));
/*     */     }
/* 713 */     if (s.contains("$targetthreat")) {
/* 714 */       s = s.replace("$targetthreat", String.valueOf((int)am.getThreatTable().getThreat(BukkitAdapter.adapt((Entity)target))));
/*     */     }
/*     */     
/* 717 */     if (s.contains("$target") || s.contains("$player")) {
/* 718 */       if (target != null) {
/* 719 */         if (target instanceof Player) {
/* 720 */           s = s.replace("$player", ((Player)target).getName());
/* 721 */           s = s.replace("$target", ((Player)target).getName());
/*     */         }
/* 723 */         else if (target.getCustomName() != null) {
/* 724 */           s = s.replace("$player", target.getCustomName());
/* 725 */           s = s.replace("$target", target.getCustomName());
/*     */         } else {
/* 727 */           s = s.replace("$player", "Unknown");
/* 728 */           s = s.replace("$target", "Unknown");
/*     */         } 
/*     */       } else {
/*     */         
/* 732 */         s = s.replace("$player", "Unknown");
/* 733 */         s = s.replace("$target", "Unknown");
/*     */       } 
/*     */     }
/*     */     
/* 737 */     if (s.contains("$trigger")) {
/* 738 */       s = s.replace("$trigger", am.getLastAggroCause().getName());
/*     */     }
/*     */     
/* 741 */     s = ChatColor.translateAlternateColorCodes('&', s);
/*     */     
/* 743 */     MythicMobs.debug(2, "Returning parsed message: " + s);
/* 744 */     return s;
/*     */   }
/*     */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\skills\SkillString.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */