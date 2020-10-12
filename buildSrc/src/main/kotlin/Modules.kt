object Modules {
    object App {
        override fun toString() = ":app"
        val injector = "$App:injector"
        val navigation = "$App:navigation"
    }

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

        object News {
            override fun toString() = "$Features:news"
            val domain = "$News:domain"
            val data = "$News:data"
            val presentation = "$News:presentation"
            val injector = "$News:injector"

            object Shared {
                override fun toString() = "$News:shared"
                val domain = "$Shared:domain"
                val data = "$Shared:data"
                val presentation = "$Shared:presentation"
                val injector = "$Shared:injector"
            }
        }

        object Quotes {
            override fun toString() = "$Features:quotes"
            val domain = "$Quotes:domain"
            val data = "$Quotes:data"
            val presentation = "$Quotes:presentation"
            val injector = "$Quotes:injector"
        }
    }

    object Libraries {
        override fun toString() = ":libraries"

        object Core {
            override fun toString() = "$Libraries:core"
            val data = "$Core:data"
            val domain = "$Core:domain"
            val presentation = "$Core:presentation"
        }

        object Network {
            override fun toString() = "$Libraries:network"
            val wrappers = "$Network:wrappers"
            val pagingwrappers = "$Network:pagingwrappers"
        }

        val fakeNetwork = "$Libraries:fakenetwork"
        val retroFlow = "$Libraries:retroflow"
        val fllowbinding = "$Libraries:flowbinding"
        val paging = "$Libraries:paging"
    }
}