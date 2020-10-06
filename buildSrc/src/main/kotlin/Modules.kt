object Modules {
    val app = ":app"

    object Features {
        override fun toString() = ":features"

        object User {
            override fun toString() = "$Features:user"

            object SignIn {
                override fun toString() = "$User:signin"
                val domain = "$SignIn:domain"
                val data = "$SignIn:data"
                val presentation = "$SignIn:presentation"
                val injector = "$SignIn:injector"
            }
        }
    }

    object Libraries {
        override fun toString() = ":libraries"

        object Core {
            override fun toString() = "$Libraries:core"
            val data = "$Core:data"
        }

        object Network {
            override fun toString() = "$Libraries:network"
            val wrappers = "$Network:wrappers"
        }

        val fakeNetwork = "$Libraries:fakenetwork"
        val retroFlow = "$Libraries:retroflow"
    }
}