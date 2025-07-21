package net.aquamine.generators

import com.squareup.kotlinpoet.ANY
import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.FileSpec
import com.squareup.kotlinpoet.FunSpec
import com.squareup.kotlinpoet.KModifier
import com.squareup.kotlinpoet.ParameterizedTypeName.Companion.parameterizedBy
import com.squareup.kotlinpoet.STAR
import com.squareup.kotlinpoet.STRING
import com.squareup.kotlinpoet.TypeName
import com.squareup.kotlinpoet.TypeSpec
import com.squareup.kotlinpoet.TypeVariableName
import com.squareup.kotlinpoet.jvm.jvmStatic
import net.aquamine.generators.privateStaticFunction
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.world.level.block.entity.BaseContainerBlockEntity
import net.minecraft.world.level.block.entity.BlockEntityType
import java.lang.reflect.ParameterizedType
import java.nio.file.Path
import kotlin.io.path.exists
import kotlin.io.path.writeText


class BlockEntityTypeGenerator(path: Path) : StandardGenerator(path) {

    fun run() {
        run<BlockEntityType<*>, BlockEntityType<*>>(
            BuiltInRegistries.BLOCK_ENTITY_TYPE,
            "block.entity.BlockEntityTypes",
            "block.entity.BlockEntityType",
            "BLOCK_ENTITY_TYPE"
        )
    }

    override fun <S, T> run(
        catalogueType: Class<S>,
        type: Class<T>,
        name: ClassName,
        returnType: ClassName,
        registryName: String,
        keyGetter: KeyGetter<T>,
    ) {
        val file = FileSpec.catalogueType(name)
        val outputClass = TypeSpec.catalogueType(name, returnType).registryOfMethod(
            returnType.parameterizedBy(TypeVariableName("T")),
            registryName,
            TypeVariableName("T"),
            ClassName("$PACKAGE.block.entity", "BlockEntity")
        )
        collectFields(catalogueType, type).forEach {
            val genericClazz = ((BlockEntityType::class.java.getField(it.name).genericType as ParameterizedType)
                .actualTypeArguments[0] as Class<*>)

            val isContainer = BaseContainerBlockEntity::class.java.isAssignableFrom(genericClazz)
            val finalPackage = if (isContainer) "$PACKAGE.block.entity.container" else "$PACKAGE.block.entity"

            outputClass.catalogueField(
                it,
                returnType.parameterizedBy(
                    ClassName(
                        finalPackage,
                        genericClazz.simpleName
                            .replace("BlockEntity", "")
                    )
                ),
                keyGetter
            )
        }
        val out = StringBuilder()
        file.addType(outputClass.build()).build().writeTo(out)
        val outputFile =
            output.resolve(name.packageName.replace('.', '/')).tryCreateDirectories().resolve("${name.simpleName}.kt")
        if (outputFile.exists()) return
        outputFile.tryCreateFile().writeText(out.toString().performReplacements(returnType.simpleName, name.simpleName))
    }

    fun TypeSpec.Builder.registryOfMethod(
        returnType: TypeName,
        registryName: String,
        genericType: TypeName,
        genericBound: TypeName? = null
    ): TypeSpec.Builder {
        return privateStaticFunction("of", registryReference.parameterizedBy(returnType)) {
            val typeVariable = if (genericBound != null) {
                TypeVariableName("T", genericBound)
            } else {
                TypeVariableName("T")
            }
            addTypeVariable(typeVariable)

            addParameter("name", STRING)
            addCode("return·RegistryReference.of(Registries.$registryName,·Key.key(name))")
        }
    }

    inline fun TypeSpec.Builder.privateStaticFunction(
        name: String,
        returnType: TypeName,
        builder: FunSpec.Builder.() -> Unit = {}
    ): TypeSpec.Builder =
        addFunction(FunSpec.builder(name)
            .addModifiers(KModifier.PRIVATE)
            .returns(returnType)
            .jvmStatic()
            .apply(builder)
            .build())

}