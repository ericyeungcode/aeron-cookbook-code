# How to run `aeron-core`

* Tested Java version: Java 25

1. add mainClass and Jvm args


```kts
application {
    mainClass.set(project.findProperty("mainClass")?.toString() ?: "com.example.DefaultMain")

    applicationDefaultJvmArgs = listOf(
        "--add-opens", "java.base/jdk.internal.misc=ALL-UNNAMED",
        "--add-opens", "java.base/sun.nio.ch=ALL-UNNAMED",
        "--add-opens", "java.base/java.nio=ALL-UNNAMED",
        "--add-opens", "java.base/sun.misc=ALL-UNNAMED"
    )    

}

```

2. Run aeron-core

```bash

./gradlew :aeron-core:build
./gradlew :aeron-core:run -PmainClass=com.aeroncookbook.aeron.rpc.server.Server


> Task :aeron-core:run
WARNING: package sun.misc not in java.base
20:39:49.067 [main] Dir /var/folders/******tmp-dir****/T/aeron-xxxx-client
20:39:49.075 [server] Server starting
20:39:54.041 [server] Received connect request with response URI aeron:udp?endpoint=127.0.0.1:2001 stream 1
20:39:54.085 [server] responding on correlation 78f5e2b6-b3ed-4f05-ba8b-f65f11166278 with value STRING TO BE MADE UPPERCASE

BUILD SUCCESSFUL in 7s
4 actionable tasks: 2 executed, 2 up-to-date

```

* in another shell windows

```bash
./gradlew :aeron-core:run -PmainClass=com.aeroncookbook.aeron.rpc.client.Client                            ✔  14s  20:39:40

> Task :aeron-core:run
WARNING: package sun.misc not in java.base
/var/folders/******tmp-dir****/T/aeron-xxxx-client

20:39:53.981 [client] Client starting
20:39:54.028 [client] awaiting outbound server connect
20:39:54.031 [client] awaiting inbound server connect
20:39:54.082 [client] sending: string to be made uppercase with correlation 78f5e2b6-b3ed-4f05-ba8b-f65f11166278
20:39:54.088 [client] Received STRING TO BE MADE UPPERCASE

BUILD SUCCESSFUL in 2s
4 actionable tasks: 2 executed, 2 up-to-date

```