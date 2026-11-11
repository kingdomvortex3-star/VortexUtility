VortexUtility

<div align="center"> __     __         _            _   _ _ _ _ _
 \ \   / /__  _ __| |_ _____  _| | | | (_) |_
  \ \ / / _ \| '__| __/ _ \ \/ / | | | | | __|
   \ V / (_) | |  | ||  __/>  <| |_| | | | |_
    \_/ \___/|_|   \__\___/_/\_\\___/|_|_|\__|

Advanced Random Teleport + Custom Ender Chest utility plugin for Paper/Purpur 26.3

""Platform" (https://img.shields.io/badge/Platform-Paper%20%7C%20Purpur-00D9FF?style=for-the-badge)" (#requirements)
""Minecraft" (https://img.shields.io/badge/Minecraft-26.3-55CC55?style=for-the-badge&logo=minecraft)" (#requirements)
""Java" (https://img.shields.io/badge/Java-25-orange?style=for-the-badge&logo=openjdk)" (#requirements)
""Developer" (https://img.shields.io/badge/Developer-Vortex%20Teams-8B5CF6?style=for-the-badge)" (#developer)

<img src="assets/vortex-utility-banner.png.png" width="100%" alt="VortexUtility Banner">Developed with ❤️ by VORTEX TEAMS

Building Better Minecraft Experiences.

</div>VortexUtility combines an advanced Random Teleport system and a permission-based Custom Ender Chest into one configurable Minecraft plugin. Designed for modern Paper and Purpur servers with flexible world settings, player storage, and controlled RTP searches.

---

Features

Category| Description
Random Teleport| GUI-based random teleportation
Multi-World| Overworld, Nether, and The End
Distance Selection| Configurable teleport distance
Countdown| Title-based teleport countdown
Anti-Movement| Cancels teleportation when the player moves
Safe Location| Ground, space, and hazard checks
RTP History| Avoids recently used locations
World Boundaries| Custom RTP coordinate limits
Cooldown| Individual player cooldown
Async Chunk Loading| Asynchronous loading of existing chunks
Custom Ender Chest| Permission-based storage GUI
Player Storage| UUID-based persistent storage
Configuration| Separate configuration files
Reload System| Reload configurations without restarting

---

Random Teleport System

Advanced RTP with configurable worlds, distance limits, and location searching.

Supported Worlds

- "world" — Overworld
- "world_nether" — The Nether
- "world_the_end" — The End

World availability depends on the server world and its configuration.

Location Search

- Configurable minimum and maximum teleport distance.
- Custom X/Z coordinate boundaries.
- Safe ground and player-space validation.
- Hazardous block detection.
- Minimum distance from other players.
- RTP location history per player and world.
- Limited location search attempts.
- Maximum concurrent RTP searches.

Chunk Loading

RTP uses existing pregenerated chunks and does not request the generation of new chunks.

Recommended: Use Chunky to pregenerate the RTP area before enabling a world.

«Configured RTP boundaries must match the actual pregenerated area. Chunky does not automatically provide boundary configuration to VortexUtility.»

---

Custom Ender Chest

Permission-based storage with six capacity tiers.

Storage Features

- Dedicated Custom Ender Chest GUI.
- Six storage capacity tiers.
- UUID-based player storage.
- Persistent item data.
- Permission-based capacity upgrades.
- Preserves stored items when capacity changes.
- Maximum storage capacity of 54 slots.

---

Commands

All commands are available through the following command list.

Player Commands

1. "/rtp"

Open the Random Teleport GUI.

2. "/ec"

Open the Custom Ender Chest.

3. "/enderchest"

Alternative command to open the Custom Ender Chest.

Admin Commands

1. "/vortexutility reload"

Reload plugin configurations without restarting the server.

2. "/vu reload"

Alias for the plugin reload command.

---

<details>
<summary>🔐 Permissions</summary>General Permissions

1. "vortexutility.rtp"

Allows players to access the Random Teleport system.

2. "vortexutility.ec"

Allows players to access the Custom Ender Chest.

3. "vortexutility.admin"

Allows administrators to manage the plugin.

Ender Chest Capacity

1. "vortexutility.ec.1"

Storage Capacity: 9 Slots

2. "vortexutility.ec.2"

Storage Capacity: 18 Slots

3. "vortexutility.ec.3"

Storage Capacity: 27 Slots

4. "vortexutility.ec.4"

Storage Capacity: 36 Slots

5. "vortexutility.ec.5"

Storage Capacity: 45 Slots

6. "vortexutility.ec.6"

Storage Capacity: 54 Slots

</details>---

<details>
<summary>⚙️ RTP Configuration</summary>RTP configuration is stored in "rtp.yml".

General Settings

1. "cooldown-seconds"

Controls the RTP cooldown duration.

2. "max-concurrent-searches"

Controls the maximum number of simultaneous RTP searches.

History Settings

1. "history.enabled"

Enables or disables RTP history.

2. "history.max-locations"

Sets the maximum number of saved locations.

3. "history.minimum-distance"

Sets the minimum distance from previously used locations.

Performance Settings

1. "performance.max-attempts"

Sets the maximum number of location search attempts.

2. "performance.async-chunk-loading"

Enables asynchronous chunk loading.

3. "performance.generate-new-chunks"

New chunk generation remains disabled internally.

World Settings

Each world has independent settings:

- "enabled"
- "center-x"
- "center-z"
- "min-x"
- "max-x"
- "min-z"
- "max-z"
- "min-distance"
- "max-distance"
- "attempts"
- "min-player-distance"

</details>---

<details>
<summary>📦 Requirements</summary>1. Paper / Purpur

Minecraft server software version 26.3.

2. Java

Java 25 for the current project build.

3. Chunky

Recommended for RTP area pregeneration.

Chunky is recommended, not a required runtime dependency.

</details>---

<details>
<summary>🏗️ Building</summary>Clone Repository

git clone https://github.com/kingdomvortex3-star/VortexUtility.git
cd VortexUtility

Build with Gradle

gradle build

Build Output

build/libs/VortexUtility-*.jar

Installation

1. Place the generated JAR into the server's "plugins/" directory.

2. Start or restart the Paper/Purpur server.

3. Edit the generated configuration files.

4. Run "/vortexutility reload" after making configuration changes.

</details>---

Configuration

Configuration files are generated on the first server startup.

Directory Structure

plugins/
└── VortexUtility/
    │
    ├── config.yml
    ├── messages.yml
    ├── rtp.yml
    ├── ec.yml
    │
    └── data/
        └── enderchests/
            └── <UUID>.yml

Configuration Files

1. "config.yml"

General plugin settings and GUI configuration.

2. "messages.yml"

Plugin messages and notifications.

3. "rtp.yml"

Random Teleport settings and world configuration.

4. "ec.yml"

Custom Ender Chest settings.

5. "data/enderchests/"

Player Ender Chest storage files based on UUID.

RTP World Configuration

Each world can be configured independently.

- Enable or disable RTP per world.
- Set world center coordinates.
- Define minimum and maximum X/Z boundaries.
- Configure minimum and maximum teleport distance.
- Set maximum location search attempts.
- Configure minimum distance from other players.

Performance Settings

- Maximum concurrent RTP searches.
- Maximum location search attempts.
- Asynchronous chunk loading.
- RTP history management.
- No forced new chunk generation.

«IMPORTANT: Pregenerate RTP areas using Chunky before enabling RTP. Ensure that the configured coordinate boundaries match the pregenerated area in each world.»

---

Developer

<div align="center">⚡ VORTEX TEAMS

MINECRAFT PLUGIN DEVELOPMENT

Developing modern utilities, custom systems, and powerful solutions for Minecraft servers.

INNOVATION • PERFORMANCE • QUALITY

</div>---

<div align="center">VortexUtility — One Plugin. More Possibilities.

Developed with ❤️ by VORTEX TEAMS

Your Server. Your System. Your Experience.

</div>
