# Nexus Desktop `2024.9-alpha.5`

### Java 21 library designed to simplify the development of Java Desktop apps. Based on [Nexus Utilities](https://github.com/zyneonstudios/nexus-utilities)

---

[![Javadoc](https://img.shields.io/badge/JavaDoc-Online-green)](https://zyneonstudios.github.io/nexus-desktop/apidocs/)

---

### Contents
- Everything from [Nexus Utilities Contents](https://github.com/zyneonstudios/nexus-utilities#contents)
- NexusDesktop: Default initialization class
- Frame
  - Web
    - NexusWebFrame: Chromium frame with optimized settings
    - NexusWebSetup: Setup for JCEF
  - NexusFrame: Base frame

---

### Implementation

You can implement this library via [Maven](#implement-via-maven), [Gradle (Groovy)](#implement-via-gradle-groovy),  [Gradle (Kotlin)](#implement-via-gradle-kotlin), [SBT](#implement-via-sbt) and [local library](https://github.com/zyneonstudios/nexus-desktop/releases/latest/)

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