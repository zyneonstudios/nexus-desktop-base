# nexus-desktop-base
Java library for desktop apps

---
Integrate via Maven:
```
<repository>
  <id>zyneonstudios-repo-releases</id>
  <name>Zyneon Studios Maven repository</name>
  <url>https://maven.zyneonstudios.com/releases</url>
</repository>
```

```
<dependency>
  <groupId>com.zyneonstudios.nexus</groupId>
  <artifactId>base-desktop</artifactId>
  <version>2024.8-alpha.1</version>
</dependency>
```

---
Integrate via Gradle Kotlin:
```
maven {
    name = "zyneonstudiosRepoReleases"
    url = uri("https://maven.zyneonstudios.com/releases")
}
```

```
implementation("com.zyneonstudios.nexus:base-desktop:2024.8-alpha.1")
```

---
Integrate via Gradle Groovy:
```
maven {
    name "zyneonstudiosRepoReleases"
    url "https://maven.zyneonstudios.com/releases"
}
```

```
implementation "com.zyneonstudios.nexus:base-desktop:2024.8-alpha.1"
```

---
Integrate via SBT:
```
resolvers +=
  "zyneonstudios-repo-releases"
     at "https://maven.zyneonstudios.com/releases"
```

```
"com.zyneonstudios.nexus" %% "base-desktop" %% "2024.8-alpha.1"
```
