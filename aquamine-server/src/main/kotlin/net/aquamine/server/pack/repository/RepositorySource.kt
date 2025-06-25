package net.aquamine.server.pack.repository

import java.util.function.Consumer

interface RepositorySource {

    fun loadPacks(action: Consumer<Pack>)
}
