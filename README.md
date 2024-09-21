# Nexus Desktop `2024.9`

### Java 21 library designed to simplify the development of Java Desktop apps.<br>Based on [Nexus Utilities](https://github.com/zyneonstudios/nexus-utilities)

---

[![Javadoc](https://img.shields.io/badge/JavaDoc-Online-green)](https://zyneonstudios.github.io/nexus-desktop-base/apidocs/)
[![Documentation](https://img.shields.io/badge/Nexus-Docs/Wiki-blue)](https://zyneonstudios.github.io/nexus-desktop-base/apidocs/)


---

### Contents
- Everything from [Nexus Utilities Contents](https://github.com/zyneonstudios/nexus-utilities#contents)
- [NexusDesktop: Default initialization class](https://zyneonstudios.github.io/nexus-desktop-base/apidocs/com/zyneonstudios/nexus/desktop/NexusDesktop)
- [Frame](https://zyneonstudios.github.io/nexus-desktop-base/apidocs/com/zyneonstudios/nexus/desktop/frame/package-summary)
  - [NexusFrame: Base frame](https://zyneonstudios.github.io/nexus-desktop-base/apidocs/com/zyneonstudios/nexus/desktop/frame/NexusFrame)
  - [Web](https://zyneonstudios.github.io/nexus-desktop-base/apidocs/com/zyneonstudios/nexus/desktop/frame/web/package-summary)
    - [NexusWebFrame: Chromium frame with optimized settings](https://zyneonstudios.github.io/nexus-desktop-base/apidocs/com/zyneonstudios/nexus/desktop/frame/web/NexusWebFrame)
    - [NexusWebSetup: Setup for JCEF](https://zyneonstudios.github.io/nexus-desktop-base/apidocs/com/zyneonstudios/nexus/desktop/frame/web/NexusWebSetup)

---

### Implementation

You can implement this library via [Maven](#implement-via-maven), [Gradle (Groovy)](#implement-via-gradle-groovy),  [Gradle (Kotlin)](#implement-via-gradle-kotlin), [SBT](#implement-via-sbt) and as local library

#### Implement via Maven
```
<repositories>
    <!--Other repositories-->
    <repository>
      <id>zyneonstudios-repo-releases</id>
      <name>Zyneon Studios Maven repository</name>
      <url>https://maven.zyneonstudios.com/releases</url>
    </repository>
</repositories>
```
```
<dependencies>
    <!--Other dependencies-->
    <dependency>
        <groupId>com.zyneonstudios.nexus</groupId>
        <artifactId>base-desktop</artifactId>
        <version>LATEST</version>
    </dependency>
</dependencies>
```

#### Implement via Gradle (Groovy)
```
repositories {
    maven {
        name "zyneonstudiosRepoReleases"
        url "https://maven.zyneonstudios.com/releases"
    }
}
```
```
dependencies {
    implementation 'com.zyneonstudios.nexus:base-desktop:+'
}
```

#### Implement via Gradle (Kotlin)
```
repositories {
    maven {
        name = "zyneonstudiosRepoReleases"
        url = uri("https://maven.zyneonstudios.com/releases")
    }
}
```
```
dependencies {
    implementation('com.zyneonstudios.nexus:base-desktop:+')
}
```

#### Implement via SBT
```
resolvers += "zyneonstudios-repo-releases" at "https://maven.zyneonstudios.com/releases"
```
```
libraryDependencies += "com.zyneonstudios.nexus" % "base-desktop" % "LATEST_VERSION"
```