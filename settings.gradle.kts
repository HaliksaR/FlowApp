include(
    ":app",
    ":app:injector",
    ":app:navigation"
)
include(":libraries:fakenetwork")
include(":libraries:retroflow")

include(
    ":libraries:core",
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
    ":features:news:data",
    ":features:news:domain"
)
