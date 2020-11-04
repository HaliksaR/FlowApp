object Modules {
    object App {
        override fun toString() = ":app"
        val injector = "$App:injector"
        val navigation = "$App:navigation_graph"
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
            }

            object Accounts {
                override fun toString() = "$User:accounts"
                val domain = "$Accounts:domain"
                val data = "$Accounts:data"
                val presentation = "$Accounts:presentation"
            }
        }

        object News {
            override fun toString() = "$Features:news"
            val domain = "$News:domain"
            val data = "$News:data"
            val presentation = "$News:presentation"

            object Shared {
                override fun toString() = "$News:shared"
                val domain = "$Shared:domain"
                val data = "$Shared:data"
            }
        }

        object Quotes {
            override fun toString() = "$Features:quotes"
            val domain = "$Quotes:domain"
            val data = "$Quotes:data"
            val presentation = "$Quotes:presentation"
        }
    }

    object Libraries {
        override fun toString() = ":libraries"

        object Core {
            override fun toString() = "$Libraries:core"
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