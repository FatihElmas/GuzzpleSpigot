<img src="https://i.ibb.co/R252N2n/Logo-New.png" align="right"></img>
GuzzpleSpigot is an clean, reflection based, clean coded addon system.
## Summary

GuzzpleSpigot is an clean, reflection based, clean coded addon system.
_Please see the __documentation__ for more information about __GuzzpleSpigot__._

1. [Introduction](#introduction)
4. [Download](#download)
5. [Documentation](#documentation)
7. [Extensions And Plugins](#third-party-recommendations)
8. [Contributing](#contributing)
9. [Dependencies](#dependencies)
10. [Other Libraries](#related-projects)
## Introduction
Follow these steps carefully to create a plugin

#### Maven
```xml
<!-- GUZZPLE SPIGOT REPOSITORY-->
<repository>
  <id>guzzple-repo</id>
  <url>https://guzzple.co/spigot/repo/</url>
</repository>

<!-- GUZZPLE SPIGOT DEPENDENCY-->
<dependency>
  <groupId>co.guzzple</groupId>
  <artifactId>guzzple</artifactId>
  <version>${VERSION}</version>
  <scope>provided</scope>
</dependency>
```
#### Gradle
```gradle
repositories {
    maven {
        url "https://guzzple.co/spigot/repo/"
    }
}

dependencies {
  compile "co.guzzple:guzzple:${VERSION}"
}

```

#### Example Addon
```java
/**
* ----------
* MAIN CLASS
* ----------
**/

package co.guzzple.testaddon;

import co.guzzple.guzzple.AddonBase;
import co.guzzple.guzzple.api.chat.MinecraftText;

public final class Main extends AddonBase { // Implement AddonBase

    @Override
    public void onEnable() {
        System.out.println(new MinecraftText("&aTest Addon enabled!").color().getString());
    }

    @Override
    public void onDisable() {
        System.out.println(new MinecraftText("&cTest Addon disabled!").color().getString());
    }
}
```

```yml
# -----------------------------
# ADDON YML for Configurations.
# -----------------------------
name: TestAddon // necessary field*
version: 1.0 // necessary field*
main: co.guzzple.testaddon.Main // necessary field*
```

## Documentation
**Addon Docs** can be found on the [Guzzple Docs](wiki.guzzple.co/guzzplespigot) or directly [here](https://github.com/guzzpleco/GuzzpleSpigot/README.MD#documentation)
A simple Wiki can also be found in this repository's Wiki section.

**Creating Addon Base**
```java
package co.guzzple.testaddon;

import co.guzzple.guzzple.AddonBase;

public final class Main extends AddonBase { // Implementing AddonBase

    @Override
    public void onEnable() {
        // on Addon Enable Event
    }

    @Override
    public void onDisable() {
        // on Addon Disable Event
    }
}
```
## API Documentation
**API Docs** can be found on the [Guzzple Docs](wiki.guzzple.co/guzzplespigot) or directly [here](https://github.com/guzzpleco/GuzzpleSpigot/README.MD#documentation)
A **simple** Wiki can also be found in this repository's Wiki section.

**Minecraft Text**
```java
    public void myCoolMethod() {
        // COLORING EXAMPLES
        new MinecraftText("&cYour String with Colored/Uncolored").color().getString(); // Coloring and returning String
        new MinecraftText("&cYour", "&dArray", "&bwith", "&aColored/Uncolored").color().getList(); // Coloring and returning list.
    }
```

_Please visit the [wiki](wiki.guzzple.co/guzzplespigot) for more documentation._

## Contributing
<a href="https://github.com/FatihElmas"><img src="https://i.ibb.co/YLT3p2X/logo.png" alt="logo" border="0" width="64"></a>

