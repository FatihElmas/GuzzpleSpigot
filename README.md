<img src="https://i.ibb.co/R252N2n/Logo-New.png" align="right"></img>
GuzzpleSpigot is an clean, reflection based, clean coded addon system.
## Summary

GuzzpleSpigot is an clean, reflection based, clean coded addon system.
_Please see the __documentation__ for more information about __GuzzpleSpigot__._

1. [Introduction](#introduction)
4. [Download](#download)
5. [Documentation](#documentation)
7. [Extensions And Plugins](#third-party-recommendations)
8. [Contributing](#contributing-to-jda)
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

## Example Maven Configuration
```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>co.guzzple</groupId>
    <artifactId>testaddon</artifactId>
    <version>1.0-SNAPSHOT</version>
    <packaging>jar</packaging>

    <name>TestAddon</name>

    <properties>
        <java.version>1.8</java.version>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
    </properties>

    <build>
        <plugins>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-compiler-plugin</artifactId>
                <version>3.8.1</version>
                <configuration>
                    <source>${java.version}</source>
                    <target>${java.version}</target>
                </configuration>
            </plugin>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-shade-plugin</artifactId>
                <version>3.2.4</version>
                <executions>
                    <execution>
                        <phase>package</phase>
                        <goals>
                            <goal>shade</goal>
                        </goals>
                        <configuration>
                            <createDependencyReducedPom>false</createDependencyReducedPom>
                        </configuration>
                    </execution>
                </executions>
            </plugin>
        </plugins>
        <resources>
            <resource>
                <directory>src/main/resources</directory>
                <filtering>true</filtering>
            </resource>
        </resources>
    </build>

    <repositories>
        <repository>
            <id>spigotmc-repo</id>
            <url>https://hub.spigotmc.org/nexus/content/repositories/snapshots/</url>
        </repository>
        <repository>
            <id>sonatype</id>
            <url>https://oss.sonatype.org/content/groups/public/</url>
        </repository>
      
      <!-- GUZZPLE SPIGOT REPOSITORY-->
      <repository>
        <id>guzzple-repo</id>
        <url>https://guzzple.co/spigot/repo/</url>
      </repository>
    </repositories>

    <dependencies>
        <dependency>
            <groupId>org.spigotmc</groupId>
            <artifactId>spigot-api</artifactId>
            <version>${VERSION}</version>
            <scope>provided</scope>
        </dependency>
        <dependency>
            <groupId>co.guzzple</groupId>
            <artifactId>guzzple</artifactId>
            <version>${VERSION}</version>
            <scope>provided</scope>
        </dependency>
    </dependencies>
</project>
```

