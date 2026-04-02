/*    */ package lumine.xikage.mythicmobs.util.reflections.scanners;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.util.reflections.ReflectionsException;
/*    */ import io.lumine.xikage.mythicmobs.util.reflections.scanners.MemberUsageScanner;
/*    */ import javassist.CannotCompileException;
/*    */ import javassist.NotFoundException;
/*    */ import javassist.expr.ConstructorCall;
/*    */ import javassist.expr.ExprEditor;
/*    */ import javassist.expr.FieldAccess;
/*    */ import javassist.expr.MethodCall;
/*    */ import javassist.expr.NewExpr;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ class null
/*    */   extends ExprEditor
/*    */ {
/*    */   public void edit(NewExpr e) throws CannotCompileException {
/*    */     try {
/* 40 */       MemberUsageScanner.access$000(MemberUsageScanner.this, e.getConstructor().getDeclaringClass().getName() + ".<init>(" + MemberUsageScanner.this
/* 41 */           .parameterNames(e.getConstructor().getMethodInfo()) + ")", e.getLineNumber(), key);
/* 42 */     } catch (NotFoundException e1) {
/* 43 */       throw new ReflectionsException("Could not find new instance usage in " + key, e1);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void edit(MethodCall m) throws CannotCompileException {
/*    */     try {
/* 50 */       MemberUsageScanner.access$000(MemberUsageScanner.this, m.getMethod().getDeclaringClass().getName() + "." + m.getMethodName() + "(" + MemberUsageScanner.this
/* 51 */           .parameterNames(m.getMethod().getMethodInfo()) + ")", m.getLineNumber(), key);
/* 52 */     } catch (NotFoundException e) {
/* 53 */       throw new ReflectionsException("Could not find member " + m.getClassName() + " in " + key, e);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void edit(ConstructorCall c) throws CannotCompileException {
/*    */     try {
/* 60 */       MemberUsageScanner.access$000(MemberUsageScanner.this, c.getConstructor().getDeclaringClass().getName() + ".<init>(" + MemberUsageScanner.this
/* 61 */           .parameterNames(c.getConstructor().getMethodInfo()) + ")", c.getLineNumber(), key);
/* 62 */     } catch (NotFoundException e) {
/* 63 */       throw new ReflectionsException("Could not find member " + c.getClassName() + " in " + key, e);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void edit(FieldAccess f) throws CannotCompileException {
/*    */     try {
/* 70 */       MemberUsageScanner.access$000(MemberUsageScanner.this, f.getField().getDeclaringClass().getName() + "." + f.getFieldName(), f.getLineNumber(), key);
/* 71 */     } catch (NotFoundException e) {
/* 72 */       throw new ReflectionsException("Could not find member " + f.getFieldName() + " in " + key, e);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmob\\util\reflections\scanners\MemberUsageScanner$1.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */