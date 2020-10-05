object Modules {
    object Path {
        const val libraries = ":libraries"
        const val feature = ":feature"
    }

    const val app = ":app"

    object Libraries {
        const val core = "${Path.libraries}:core"
        const val network = "${Path.libraries}:network"
        const val fakeNetwork = "${Path.libraries}:fakenetwork"
        const val retroFlow = "${Path.libraries}:retroflow"
    }
}