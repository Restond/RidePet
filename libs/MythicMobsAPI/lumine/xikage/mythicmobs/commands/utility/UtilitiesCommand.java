/*     */ package lumine.xikage.mythicmobs.commands.utility;
/*     */ import io.lumine.utils.commands.Command;
/*     */ import io.lumine.xikage.mythicmobs.MythicMobs;
/*     */ import io.lumine.xikage.mythicmobs.commands.utility.GetBlockCoordinatesCommand;
/*     */ import io.lumine.xikage.mythicmobs.commands.utility.GetItemInfoCommand;
/*     */ import io.lumine.xikage.mythicmobs.commands.utility.GetTargetInfoCommand;
/*     */ import io.lumine.xikage.mythicmobs.skills.IDummySkill;
/*     */ import io.lumine.xikage.mythicmobs.skills.IMetaSkill;
/*     */ import io.lumine.xikage.mythicmobs.skills.INoTargetSkill;
/*     */ import io.lumine.xikage.mythicmobs.skills.auras.Aura;
/*     */ import io.lumine.xikage.mythicmobs.skills.conditions.IEntityComparisonCondition;
/*     */ import io.lumine.xikage.mythicmobs.skills.conditions.IEntityCondition;
/*     */ import io.lumine.xikage.mythicmobs.skills.conditions.ILocationComparisonCondition;
/*     */ import io.lumine.xikage.mythicmobs.skills.conditions.ILocationCondition;
/*     */ import io.lumine.xikage.mythicmobs.skills.conditions.ISkillMetaCondition;
/*     */ import io.lumine.xikage.mythicmobs.util.annotations.MythicCondition;
/*     */ import io.lumine.xikage.mythicmobs.util.annotations.MythicField;
/*     */ import io.lumine.xikage.mythicmobs.util.annotations.MythicMechanic;
/*     */ import io.lumine.xikage.mythicmobs.util.reflections.Reflections;
/*     */ import java.io.File;
/*     */ import java.io.FileNotFoundException;
/*     */ import java.io.IOException;
/*     */ import java.io.PrintWriter;
/*     */ import java.lang.reflect.Field;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.Set;
/*     */ import java.util.stream.Collectors;
/*     */ import org.apache.commons.lang.StringUtils;
/*     */ import org.bukkit.command.CommandSender;
/*     */ 
/*     */ public class UtilitiesCommand extends Command<MythicMobs> {
/*     */   public UtilitiesCommand(Command<MythicMobs> parent) {
/*  34 */     super(parent);
/*     */     
/*  36 */     addSubCommands(new Command[] { (Command)new BroadcastCommand(this), (Command)new GetBlockCoordinatesCommand(this), (Command)new GetItemInfoCommand(this), (Command)new GetTargetInfoCommand(this) });
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean onCommand(CommandSender sender, String[] args) {
/*  47 */     if (args.length > 0 && 
/*  48 */       args[0].equalsIgnoreCase("generatedocs")) {
/*     */       
/*  50 */       String docsFolder = ((MythicMobs)getPlugin()).getDataFolder() + System.getProperty("file.separator") + "Docs";
/*     */       
/*  52 */       String mechanicsFolder = docsFolder + System.getProperty("file.separator") + "Mechanics";
/*  53 */       String mechanicsFile = docsFolder + System.getProperty("file.separator") + "mechanics.txt";
/*     */ 
/*     */ 
/*     */       
/*  57 */       File file = new File(docsFolder);
/*  58 */       if (!file.exists()) {
/*  59 */         file.mkdir();
/*     */       }
/*  61 */       file = new File(mechanicsFolder);
/*  62 */       if (!file.exists()) {
/*  63 */         file.mkdir();
/*     */       }
/*  65 */       file = new File(mechanicsFile);
/*  66 */       if (file.exists()) {
/*  67 */         file.delete();
/*     */       }
/*     */       try {
/*  70 */         file.createNewFile();
/*  71 */       } catch (IOException e) {
/*  72 */         e.printStackTrace();
/*     */       } 
/*     */       
/*  75 */       try (PrintWriter out = new PrintWriter(mechanicsFile)) {
/*     */         
/*  77 */         Set<Class<?>> mechanicsClasses = (new Reflections("io.lumine.xikage.mythicmobs.skills.mechanics", new io.lumine.xikage.mythicmobs.util.reflections.scanners.Scanner[0])).getTypesAnnotatedWith(MythicMechanic.class);
/*     */ 
/*     */ 
/*     */         
/*  81 */         for (Class<?> clazz : (Iterable<Class<?>>)mechanicsClasses.stream().sorted((a, b) -> ((MythicMechanic)a.getAnnotation(MythicMechanic.class)).name().compareTo(((MythicMechanic)b.getAnnotation(MythicMechanic.class)).name())).collect(Collectors.toList())) {
/*     */           try {
/*  83 */             String name = StringUtils.capitalize(((MythicMechanic)clazz.<MythicMechanic>getAnnotation(MythicMechanic.class)).name());
/*  84 */             String author = ((MythicMechanic)clazz.<MythicMechanic>getAnnotation(MythicMechanic.class)).author();
/*  85 */             String[] aliases = ((MythicMechanic)clazz.<MythicMechanic>getAnnotation(MythicMechanic.class)).aliases();
/*  86 */             String description = ((MythicMechanic)clazz.<MythicMechanic>getAnnotation(MythicMechanic.class)).description();
/*  87 */             String version = ((MythicMechanic)clazz.<MythicMechanic>getAnnotation(MythicMechanic.class)).version();
/*     */             
/*  89 */             String type = "";
/*     */             
/*  91 */             if (Aura.class.isAssignableFrom(clazz)) {
/*  92 */               type = type + "Aura";
/*     */             } else {
/*  94 */               if (IDummySkill.class.isAssignableFrom(clazz)) {
/*  95 */                 type = "Dummy";
/*     */               }
/*  97 */               if (IMetaSkill.class.isAssignableFrom(clazz)) {
/*  98 */                 if (type.length() > 0) {
/*  99 */                   type = type + ", ";
/*     */                 }
/* 101 */                 type = type + "Meta";
/*     */               } 
/* 103 */               if (INoTargetSkill.class.isAssignableFrom(clazz)) {
/* 104 */                 if (type.length() > 0) {
/* 105 */                   type = type + ", ";
/*     */                 }
/* 107 */                 type = type + "No-Target";
/* 108 */               } else if (ITargetedEntitySkill.class.isAssignableFrom(clazz)) {
/* 109 */                 if (type.length() > 0) {
/* 110 */                   type = type + ", ";
/*     */                 }
/* 112 */                 type = type + "Entity";
/* 113 */               } else if (ITargetedLocationSkill.class.isAssignableFrom(clazz)) {
/* 114 */                 if (type.length() > 0) {
/* 115 */                   type = type + ", ";
/*     */                 }
/* 117 */                 type = type + "Location";
/*     */               } 
/*     */             } 
/*     */ 
/*     */             
/* 122 */             out.println("| [[:skills:mechanics:" + name.toLowerCase() + "|" + name + "]] | " + type + " | " + description + " |");
/*     */             
/* 124 */             String cFile = name.toLowerCase() + ".txt";
/* 125 */             File cfile = new File(mechanicsFolder + System.getProperty("file.separator") + cFile);
/* 126 */             if (cfile.exists()) {
/* 127 */               cfile.delete();
/*     */             }
/* 129 */             cfile.createNewFile();
/*     */             
/* 131 */             try (PrintWriter cout = new PrintWriter(cfile)) {
/*     */               
/* 133 */               List<Field> fields = new ArrayList<>();
/*     */               
/* 135 */               for (Field f : clazz.getDeclaredFields()) {
/* 136 */                 if ((f.getAnnotations()).length > 0) {
/* 137 */                   fields.add(f);
/*     */                 }
/*     */               } 
/*     */               
/* 141 */               cout.println("<WRAP infobox right>");
/* 142 */               cout.println("<WRAP infoboxheader>" + name + "</WRAP>");
/* 143 */               cout.println("<WRAP infoboxtable>");
/* 144 */               cout.println("^Type| " + type + "|");
/*     */               
/* 146 */               if (aliases.length > 0) {
/* 147 */                 cout.println("^Aliases| " + String.join(", ", (CharSequence[])aliases) + "|");
/*     */               }
/*     */               
/* 150 */               cout.println("^Added In| " + version + "|");
/* 151 */               cout.println("^Author| " + author + "|");
/* 152 */               cout.println("</WRAP>");
/* 153 */               cout.println("</WRAP>");
/* 154 */               cout.println("====== Mechanic: " + name + " ======");
/* 155 */               cout.println(" ");
/* 156 */               cout.println(description);
/* 157 */               cout.println(" ");
/* 158 */               cout.println("===== Attributes =====");
/* 159 */               cout.println("^Attribute ^Aliases ^Description ^Default ^");
/*     */               
/* 161 */               if (fields.size() == 0) {
/* 162 */                 cout.println("|  |  |  |");
/*     */               } else {
/* 164 */                 for (Field field : fields) {
/* 165 */                   String aName = ((MythicField)field.<MythicField>getAnnotation(MythicField.class)).name();
/* 166 */                   String[] aAliases = ((MythicField)field.<MythicField>getAnnotation(MythicField.class)).aliases();
/* 167 */                   String aDescription = ((MythicField)field.<MythicField>getAnnotation(MythicField.class)).description();
/* 168 */                   String defValue = ((MythicField)field.<MythicField>getAnnotation(MythicField.class)).defValue();
/* 169 */                   cout.println("| " + aName + " | " + String.join(", ", (CharSequence[])aAliases) + " | " + aDescription + " | " + defValue + " |");
/*     */                 } 
/*     */               } 
/* 172 */               cout.println("\\\\");
/* 173 */               cout.println(" ");
/* 174 */               cout.println("===== Examples =====");
/* 175 */               cout.println(" ");
/* 176 */               cout.println("<code>");
/* 177 */               cout.println("Skills:");
/* 178 */               cout.println("- " + name.toLowerCase() + "{} true");
/* 179 */               cout.println("</code>");
/* 180 */               cout.println(" ");
/* 181 */               cout.println(" ");
/*     */             }
/* 183 */             catch (FileNotFoundException e) {
/* 184 */               e.printStackTrace();
/*     */             }
/*     */           
/* 187 */           } catch (Exception exception) {}
/*     */         }
/*     */       
/*     */       }
/* 191 */       catch (FileNotFoundException e) {
/* 192 */         e.printStackTrace();
/*     */       } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 200 */       String conditionsFolder = docsFolder + System.getProperty("file.separator") + "Conditions";
/* 201 */       String conditionsFile = docsFolder + System.getProperty("file.separator") + "conditions.txt";
/*     */       
/* 203 */       file = new File(docsFolder);
/* 204 */       if (!file.exists()) {
/* 205 */         file.mkdir();
/*     */       }
/* 207 */       file = new File(conditionsFolder);
/* 208 */       if (!file.exists()) {
/* 209 */         file.mkdir();
/*     */       }
/* 211 */       file = new File(conditionsFile);
/* 212 */       if (file.exists()) {
/* 213 */         file.delete();
/*     */       }
/*     */       try {
/* 216 */         file.createNewFile();
/* 217 */       } catch (IOException e) {
/* 218 */         e.printStackTrace();
/*     */       } 
/*     */       
/* 221 */       try (PrintWriter out = new PrintWriter(conditionsFile)) {
/*     */         
/* 223 */         Set<Class<?>> conditionsClasses = (new Reflections("io.lumine.xikage.mythicmobs.skills.conditions.all", new io.lumine.xikage.mythicmobs.util.reflections.scanners.Scanner[0])).getTypesAnnotatedWith(MythicCondition.class);
/*     */ 
/*     */ 
/*     */         
/* 227 */         for (Class<?> clazz : (Iterable<Class<?>>)conditionsClasses.stream().sorted((a, b) -> ((MythicCondition)a.getAnnotation(MythicCondition.class)).name().compareTo(((MythicCondition)b.getAnnotation(MythicCondition.class)).name())).collect(Collectors.toList())) {
/*     */           try {
/* 229 */             String name = StringUtils.capitalize(((MythicCondition)clazz.<MythicCondition>getAnnotation(MythicCondition.class)).name());
/* 230 */             String author = ((MythicCondition)clazz.<MythicCondition>getAnnotation(MythicCondition.class)).author();
/* 231 */             String[] aliases = ((MythicCondition)clazz.<MythicCondition>getAnnotation(MythicCondition.class)).aliases();
/* 232 */             String description = ((MythicCondition)clazz.<MythicCondition>getAnnotation(MythicCondition.class)).description();
/* 233 */             String version = ((MythicCondition)clazz.<MythicCondition>getAnnotation(MythicCondition.class)).version();
/*     */             
/* 235 */             String type = "";
/*     */             
/* 237 */             if (ISkillMetaCondition.class.isAssignableFrom(clazz)) {
/* 238 */               type = type + "Meta";
/*     */             } else {
/* 240 */               if (IEntityCondition.class.isAssignableFrom(clazz)) {
/* 241 */                 type = "Entity";
/*     */               }
/* 243 */               if (ILocationCondition.class.isAssignableFrom(clazz)) {
/* 244 */                 if (type.length() > 0) {
/* 245 */                   type = type + ", ";
/*     */                 }
/* 247 */                 type = type + "Location";
/*     */               } 
/* 249 */               if (IEntityComparisonCondition.class.isAssignableFrom(clazz)) {
/* 250 */                 if (type.length() > 0) {
/* 251 */                   type = type + ", ";
/*     */                 }
/* 253 */                 type = type + "Compare";
/* 254 */               } else if (ILocationComparisonCondition.class.isAssignableFrom(clazz)) {
/* 255 */                 if (type.length() > 0) {
/* 256 */                   type = type + ", ";
/*     */                 }
/* 258 */                 type = type + "Compare";
/*     */               } 
/*     */             } 
/*     */ 
/*     */             
/* 263 */             out.println("| [[:conditions:" + name.toLowerCase() + "|" + name + "]] | " + type + " | " + description + " |");
/*     */             
/* 265 */             String cFile = name.toLowerCase() + ".txt";
/* 266 */             File cfile = new File(conditionsFolder + System.getProperty("file.separator") + cFile);
/* 267 */             if (cfile.exists()) {
/* 268 */               cfile.delete();
/*     */             }
/* 270 */             cfile.createNewFile();
/*     */             
/* 272 */             try (PrintWriter cout = new PrintWriter(cfile)) {
/*     */               
/* 274 */               List<Field> fields = new ArrayList<>();
/*     */               
/* 276 */               for (Field f : clazz.getDeclaredFields()) {
/* 277 */                 if ((f.getAnnotations()).length > 0) {
/* 278 */                   fields.add(f);
/*     */                 }
/*     */               } 
/*     */               
/* 282 */               cout.println("<WRAP infobox right>");
/* 283 */               cout.println("<WRAP infoboxheader>" + name + "</WRAP>");
/* 284 */               cout.println("<WRAP infoboxtable>");
/* 285 */               cout.println("^Type| " + type + "|");
/*     */               
/* 287 */               if (aliases.length > 0) {
/* 288 */                 cout.println("^Aliases| " + String.join(", ", (CharSequence[])aliases) + "|");
/*     */               }
/*     */               
/* 291 */               cout.println("^Added In| " + version + "|");
/* 292 */               cout.println("^Author| " + author + "|");
/* 293 */               cout.println("</WRAP>");
/* 294 */               cout.println("</WRAP>");
/* 295 */               cout.println("====== Condition: " + name + " ======");
/* 296 */               cout.println(" ");
/* 297 */               cout.println(description);
/* 298 */               cout.println(" ");
/* 299 */               cout.println("===== Attributes =====");
/* 300 */               cout.println("^Attribute ^Aliases ^Description ^");
/*     */               
/* 302 */               if (fields.size() == 0) {
/* 303 */                 cout.println("|  |  |  |");
/*     */               } else {
/* 305 */                 for (Field field : fields) {
/* 306 */                   String aName = ((MythicField)field.<MythicField>getAnnotation(MythicField.class)).name();
/* 307 */                   String[] aAliases = ((MythicField)field.<MythicField>getAnnotation(MythicField.class)).aliases();
/* 308 */                   String aDescription = ((MythicField)field.<MythicField>getAnnotation(MythicField.class)).description();
/* 309 */                   cout.println("| " + aName + " | " + String.join(", ", (CharSequence[])aAliases) + " | " + aDescription + " |");
/*     */                 } 
/*     */               } 
/* 312 */               cout.println("\\\\");
/* 313 */               cout.println(" ");
/* 314 */               cout.println("===== Examples =====");
/* 315 */               cout.println(" ");
/* 316 */               cout.println("<code>");
/* 317 */               cout.println("Conditions:");
/* 318 */               cout.println("- " + name.toLowerCase() + "{} true");
/* 319 */               cout.println("</code>");
/* 320 */               cout.println(" ");
/* 321 */               cout.println(" ");
/*     */             }
/* 323 */             catch (FileNotFoundException e) {
/* 324 */               e.printStackTrace();
/*     */             }
/*     */           
/* 327 */           } catch (Exception exception) {}
/*     */         }
/*     */       
/*     */       }
/* 331 */       catch (FileNotFoundException e) {
/* 332 */         e.printStackTrace();
/*     */       } 
/*     */     } 
/*     */     
/* 336 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public List<String> onTabComplete(CommandSender sender, String[] args) {
/* 341 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getPermissionNode() {
/* 346 */     return "mythicmobs.command.utilities";
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isConsoleFriendly() {
/* 351 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getName() {
/* 356 */     return "utilities";
/*     */   }
/*     */ 
/*     */   
/*     */   public String[] getAliases() {
/* 361 */     return new String[] { "utility", "u" };
/*     */   }
/*     */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\command\\utility\UtilitiesCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */