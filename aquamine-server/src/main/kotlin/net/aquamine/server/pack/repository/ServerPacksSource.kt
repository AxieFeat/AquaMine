package net.aquamine.server.pack.repository

import net.kyori.adventure.key.Key
import net.kyori.adventure.text.Component
import net.aquamine.server.pack.BuiltInMetadata
import net.aquamine.server.pack.PackResources
import net.aquamine.server.pack.PackType
import net.aquamine.server.pack.VanillaPackResources
import net.aquamine.server.pack.VanillaPackResourcesBuilder
import net.aquamine.server.pack.metadata.PackMetadataSection
import java.nio.file.Path

class ServerPacksSource : BuiltInPackSource(PackType.SERVER_DATA, createVanillaPackSource(), PACKS_DIRECTORY) {

    override fun getPackTitle(pack: String): Component = Component.text(pack)

    override fun createVanillaPack(resources: PackResources): Pack? =
        Pack.readMetaAndCreate(VANILLA_ID, VANILLA_NAME, false, { resources }, PackType.SERVER_DATA, Pack.Position.BOTTOM, PackSource.BUILT_IN)

    override fun createBuiltinPack(id: String, resources: Pack.ResourcesSupplier, title: Component): Pack? =
        Pack.readMetaAndCreate(id, title, false, resources, PackType.SERVER_DATA, Pack.Position.TOP, PackSource.FEATURE)

    companion object {

        private val VERSION_METADATA_SECTION = PackMetadataSection(
            Component.translatable("dataPack.vanilla.description"),
            PackType.SERVER_DATA.version()
        )
        private val BUILT_IN_METADATA = BuiltInMetadata.of(PackMetadataSection.Serializer, VERSION_METADATA_SECTION)
        private val VANILLA_NAME = Component.translatable("dataPack.vanilla.name")
        private val PACKS_DIRECTORY = Key.key(Key.MINECRAFT_NAMESPACE, "datapacks")

        @JvmStatic
        private fun createVanillaPackSource(): VanillaPackResources {
            return VanillaPackResourcesBuilder()
                .metadata(BUILT_IN_METADATA)
                .exposeNamespaces(Key.MINECRAFT_NAMESPACE)
                .pushJarResources()
                .build()
        }

        @JvmStatic
        fun createPackRepository(path: Path): PackRepository {
            return PackRepository(ServerPacksSource(), FolderRepositorySource(path, PackType.SERVER_DATA, PackSource.WORLD))
        }
    }
}
