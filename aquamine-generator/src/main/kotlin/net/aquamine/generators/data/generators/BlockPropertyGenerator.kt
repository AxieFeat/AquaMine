package net.aquamine.generators.data.generators

import com.google.gson.JsonArray
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import net.aquamine.generators.data.Generator
import net.minecraft.world.level.block.state.properties.*

object BlockPropertyGenerator : Generator<Property<*>>() {

    override val names: Map<Property<*>, String> = BlockStateProperties::class.java.getDeclaredFields().filter {
        Property::class.java.isAssignableFrom(it.type)
    }.associate {
        val property = it.get(null) as Property<*>
        property to it.name
    }

    override fun generate(): JsonElement {
        val blockProperties = JsonObject()

        names.forEach { entry ->
            val minecraftProperty = entry.key
            val property = JsonObject()

            property.addProperty("key", minecraftProperty.name)

            val values = JsonArray()

            when(minecraftProperty) {
                is BooleanProperty -> minecraftProperty.possibleValues.forEach(values::add)
                is IntegerProperty -> minecraftProperty.possibleValues.forEach(values::add)
                is EnumProperty<*> -> minecraftProperty.possibleValues.forEach {
                    property.addProperty("enumMojangName", minecraftProperty.getValueClass().getSimpleName())
                    values.add(it.name)
                }
            }

            property.add("values", values)

            blockProperties.add(entry.value, property)
        }

        return blockProperties

    }
}