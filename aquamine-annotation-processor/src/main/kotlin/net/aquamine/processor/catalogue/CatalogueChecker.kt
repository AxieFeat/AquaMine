package net.aquamine.processor.catalogue

import com.google.devtools.ksp.KspExperimental
import com.google.devtools.ksp.getDeclaredProperties
import com.google.devtools.ksp.isAnnotationPresent
import com.google.devtools.ksp.symbol.KSClassDeclaration
import net.aquamine.annotations.Catalogue
import net.aquamine.annotations.CataloguedBy
import net.aquamine.processor.util.ContextualVisitor
import net.aquamine.processor.util.VisitorContext

object CatalogueChecker : ContextualVisitor() {

    @OptIn(KspExperimental::class)
    override fun visitClassDeclaration(classDeclaration: KSClassDeclaration, data: VisitorContext) {
        val validator = when {
            classDeclaration.isAnnotationPresent(Catalogue::class) -> CatalogueTypeValidator
            classDeclaration.isAnnotationPresent(CataloguedBy::class) -> CataloguedTypeValidator
            else -> return
        }
        validator.validateClass(classDeclaration)
        classDeclaration.getDeclaredProperties().map { validator.validateProperty(it, classDeclaration) }
    }
}
