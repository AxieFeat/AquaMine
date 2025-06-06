package net.aquamine.processor.catalogue

import com.google.devtools.ksp.symbol.KSClassDeclaration
import com.google.devtools.ksp.symbol.KSPropertyDeclaration
import net.aquamine.annotations.Catalogue
import net.aquamine.annotations.CataloguedBy
import net.aquamine.processor.getClassArgumentByName
import net.aquamine.processor.getKSAnnotation

object CataloguedTypeValidator : CatalogueValidator {

    override fun validateClass(type: KSClassDeclaration) {
        val catalogue = type.getKSAnnotation(CataloguedBy::class).getClassArgumentByName("catalogue").getKSAnnotation(
            Catalogue::class)
        check(catalogue.getClassArgumentByName("type").qualifiedName?.asString() == type.qualifiedName!!.asString()) {
            "Catalogue mismatch for ${type.simpleName.asString()}!"
        }
    }

    override fun validateProperty(property: KSPropertyDeclaration, type: KSClassDeclaration) {
        // Do nothing for catalogued types
    }
}
