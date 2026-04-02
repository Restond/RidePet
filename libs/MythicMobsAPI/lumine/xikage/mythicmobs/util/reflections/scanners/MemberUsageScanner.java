/*     */ package lumine.xikage.mythicmobs.util.reflections.scanners;
/*     */ import io.lumine.xikage.mythicmobs.util.reflections.ReflectionsException;
/*     */ import io.lumine.xikage.mythicmobs.util.reflections.scanners.AbstractScanner;
/*     */ import io.lumine.xikage.mythicmobs.util.reflections.util.ClasspathHelper;
/*     */ import javassist.CannotCompileException;
/*     */ import javassist.ClassPath;
/*     */ import javassist.ClassPool;
/*     */ import javassist.CtBehavior;
/*     */ import javassist.CtClass;
/*     */ import javassist.CtConstructor;
/*     */ import javassist.CtMethod;
/*     */ import javassist.LoaderClassPath;
/*     */ import javassist.bytecode.MethodInfo;
/*     */ import javassist.expr.ExprEditor;
/*     */ 
/*     */ public class MemberUsageScanner extends AbstractScanner {
/*     */   public void scan(Object cls) {
/*     */     try {
/*  19 */       CtClass ctClass = getClassPool().get(getMetadataAdapter().getClassName(cls));
/*  20 */       for (CtConstructor ctConstructor : ctClass.getDeclaredConstructors()) {
/*  21 */         scanMember((CtBehavior)ctConstructor);
/*     */       }
/*  23 */       for (CtMethod ctMethod : ctClass.getDeclaredMethods()) {
/*  24 */         scanMember((CtBehavior)ctMethod);
/*     */       }
/*  26 */       ctClass.detach();
/*  27 */     } catch (Exception e) {
/*  28 */       throw new ReflectionsException("Could not scan method usage for " + getMetadataAdapter().getClassName(cls), e);
/*     */     } 
/*     */   }
/*     */   
/*     */   private ClassPool classPool;
/*     */   
/*     */   void scanMember(CtBehavior member) throws CannotCompileException {
/*  35 */     String key = member.getDeclaringClass().getName() + "." + member.getMethodInfo().getName() + "(" + parameterNames(member.getMethodInfo()) + ")";
/*  36 */     member.instrument((ExprEditor)new Object(this, key));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void put(String key, int lineNumber, String value) {
/*  79 */     if (acceptResult(key)) {
/*  80 */       getStore().put(key, value + " #" + lineNumber);
/*     */     }
/*     */   }
/*     */   
/*     */   String parameterNames(MethodInfo info) {
/*  85 */     return Joiner.on(", ").join(getMetadataAdapter().getParameterNames(info));
/*     */   }
/*     */   
/*     */   private ClassPool getClassPool() {
/*  89 */     if (this.classPool == null) {
/*  90 */       synchronized (this) {
/*  91 */         this.classPool = new ClassPool();
/*  92 */         ClassLoader[] classLoaders = getConfiguration().getClassLoaders();
/*  93 */         if (classLoaders == null) {
/*  94 */           classLoaders = ClasspathHelper.classLoaders(new ClassLoader[0]);
/*     */         }
/*  96 */         for (ClassLoader classLoader : classLoaders) {
/*  97 */           this.classPool.appendClassPath((ClassPath)new LoaderClassPath(classLoader));
/*     */         }
/*     */       } 
/*     */     }
/* 101 */     return this.classPool;
/*     */   }
/*     */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmob\\util\reflections\scanners\MemberUsageScanner.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */