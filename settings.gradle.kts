include(
    ":app",
    ":app:injector",
    ":app:navigation"
)
include(":libraries:fakenetwork")
include(":libraries:retroflow")
include(":libraries:flowbinding")
include(":libraries:paging")

include(
    ":libraries:core:presentation",
    ":libraries:core:data",
    ":libraries:core:domain"
)

include(
    ":libraries:network",
    ":libraries:network:wrappers",
    ":libraries:network:pagingwrappers"
)

include(
    ":features:user:signin:injector",
    ":features:user:signin:data",
    ":features:user:signin:domain",
    ":features:user:signin:presentation"
)

include(
    ":features:news:injector",
    ":features:news:data",
    ":features:news:domain",
    ":features:news:presentation",

    ":features:news:shared:injector",
    ":features:news:shared:data",
    ":features:news:shared:domain",
    ":features:news:shared:presentation"
)

include(
    ":features:quotes:injector",
    ":features:quotes:domain",
    ":features:quotes:data",
    ":features:quotes:presentation"
)

include(
    ":features:user:accounts:injector",
    ":features:user:accounts:presentation",
    ":features:user:accounts:data",
    ":features:user:accounts:domain"
)