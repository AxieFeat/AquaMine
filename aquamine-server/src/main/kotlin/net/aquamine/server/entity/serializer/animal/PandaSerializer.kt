package net.aquamine.server.entity.serializer.animal

import net.aquamine.api.entity.animal.type.PandaGene
import net.aquamine.server.entity.animal.AquaPanda
import net.aquamine.server.entity.serializer.AgeableSerializer
import net.aquamine.server.entity.serializer.EntitySerializer
import net.aquamine.server.util.nbt.putStringEnum
import xyz.axie.nbt.CompoundTag
import xyz.axie.nbt.StringTag

object PandaSerializer : EntitySerializer<AquaPanda> {

    private const val MAIN_GENE_TAG = "MainGene"
    private const val HIDDEN_GENE_TAG = "HiddenGene"
    private val GENE_NAMES = PandaGene.entries.associateBy { it.name.lowercase() }

    override fun load(entity: AquaPanda, data: CompoundTag) {
        AgeableSerializer.load(entity, data)
        if (data.contains(MAIN_GENE_TAG, StringTag.ID)) entity.knownGene = deserializeGene(data.getString(MAIN_GENE_TAG))
        if (data.contains(HIDDEN_GENE_TAG, StringTag.ID)) entity.hiddenGene = deserializeGene(data.getString(HIDDEN_GENE_TAG))
    }

    override fun save(entity: AquaPanda): CompoundTag.Builder = AgeableSerializer.save(entity).apply {
        putStringEnum(MAIN_GENE_TAG, entity.knownGene)
        putStringEnum(HIDDEN_GENE_TAG, entity.hiddenGene)
    }

    @JvmStatic
    private fun deserializeGene(name: String): PandaGene = GENE_NAMES.getOrDefault(name, PandaGene.NORMAL)
}
