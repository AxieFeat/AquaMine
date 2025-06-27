package net.aquamine.processor

import com.google.devtools.ksp.processing.Resolver
import com.google.devtools.ksp.processing.SymbolProcessor
import com.google.devtools.ksp.processing.SymbolProcessorEnvironment
import com.google.devtools.ksp.symbol.KSAnnotated
import com.google.devtools.ksp.symbol.KSVisitor
import net.aquamine.annotations.Catalogue
import net.aquamine.annotations.CataloguedBy
import net.aquamine.annotations.ImmutableType
import net.aquamine.annotations.TypeFactory
import net.aquamine.processor.catalogue.CatalogueChecker
import net.aquamine.processor.factory.FactoryChecker
import net.aquamine.processor.immutable.ImmutabilityChecker
import net.aquamine.processor.util.VisitorContext
import kotlin.reflect.KClass

class AquaSymbolProcessor(private val environment: SymbolProcessorEnvironment) : SymbolProcessor {

    override fun process(resolver: Resolver): List<KSAnnotated> {
        val context = VisitorContext(resolver, environment.logger)
        visitAllAnnotatedWith(ImmutableType::class, context, ImmutabilityChecker)
        visitAllAnnotatedWith(TypeFactory::class, context, FactoryChecker)
        visitAllAnnotatedWith(Catalogue::class, context, CatalogueChecker)
        visitAllAnnotatedWith(CataloguedBy::class, context, CatalogueChecker)
        return emptyList()
    }

    private fun visitAllAnnotatedWith(annotation: KClass<*>, context: VisitorContext, visitor: KSVisitor<VisitorContext, Unit>) {
        val symbols = context.resolver.getSymbolsWithAnnotation(annotation.qualifiedName!!)
        var empty = true
        symbols.forEach {
            empty = false
            it.accept(visitor, context)
        }
        if (empty) context.logger.warn("No classes annotated with ${annotation.qualifiedName} found.")
    }
}
