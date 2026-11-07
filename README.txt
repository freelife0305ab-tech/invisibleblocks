Invisible Blocks (Fabric 1.21.11, client-side)

Visible: chests, trapped chests, ender chests, barrels, shulker boxes, mob spawners.
Invisible: every other block, including flowers, grass, ferns and torches.
Toggle in game: /invisibleblocks

BUILD (needs Java 21 and internet, one time):
  Easiest: open this folder in IntelliJ IDEA (Gradle imports itself), then run Gradle task  build.
  Or with Gradle 8.14+ installed:  gradle build
Jar appears in build/libs/invisibleblocks-1.0.0.jar -> put in .minecraft/mods with Fabric Loader + Fabric API.

Before building, check gradle.properties against https://fabricmc.net/develop (1.21.11).
