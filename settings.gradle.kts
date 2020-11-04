include(":app:navifation_actions")
include(
    ":app",
    ":app:injector",
    ":app:navigation_graph"
)
include(":libraries:fakenetwork")
include(":libraries:retroflow")
include(":libraries:flowbinding")
include(":libraries:paging")

include(
    ":libraries:core:presentation"
)

include(
    ":libraries:network",
    ":libraries:network:wrappers",
    ":libraries:network:pagingwrappers"
)

include(
    ":features:user:signin:data",
    ":features:user:signin:domain",
    ":features:user:signin:presentation"
)

include(
    ":features:news:data",
    ":features:news:domain",
    ":features:news:presentation",

    ":features:news:shared:data",
    ":features:news:shared:domain"
)

include(
    ":features:quotes:domain",
    ":features:quotes:data",
    ":features:quotes:presentation"
)

include(
    ":features:user:accounts:presentation",
    ":features:user:accounts:data",
    ":features:user:accounts:domain"
)