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
    ":libraries:core",
    ":libraries:core:presentation",
    ":libraries:core:data",
    ":libraries:core:domain"
)

include(
    ":libraries:network",
    ":libraries:network:wrappers"
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
    ":features:news:presentation"
)
