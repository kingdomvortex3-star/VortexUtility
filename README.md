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

<img src="assets/vortex-utility-banner.png.png" width="100%" alt="VortexUtility Banner"></div>VortexUtility combines an advanced Random Teleport system and a permission-based Custom Ender Chest into one configurable Minecraft plugin. Designed for modern Paper and Purpur servers with flexible world settings, player storage, and controlled RTP searches.

---

Features

Category| Description
Random Teleport| GUI-based random teleportation with multi-world support
World Support| Overworld, Nether, and The End
Distance Selection| Configurable teleport distance
Countdown| Teleport countdown with movement cancellation
Safe Location| Ground, space, and hazard checks
RTP History| Avoids recently used locations per player and world
World Boundaries| Custom RTP coordinate limits for each world
Cooldown| Individual player RTP cooldown
Async Chunk Loading| Loads existing chunks asynchronously without requesting new chunk generation
Custom Ender Chest| Dedicated storage GUI with permission-based capacity
Player Storage| UUID-based persistent storage
Configuration| Separate settings for RTP, EC, messages, and GUI
Reload System| Reload plugin configuration without server restart

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
- RTP location history.
- Limited location search attempts.
- Maximum concurrent RTP searches.

Chunk Loading

RTP uses existing pregenerated chunks and does not request generation of new chunks.

Recommended: Use Chunky to pregenerate the RTP area before enabling a world.

«The configured RTP boundaries must match the actual pregenerated area. Chunky does not automatically provide the boundary configuration to VortexUtility.»

---

Custom Ender Chest

Permission-based storage with six capacity tiers.

Permission| Capacity
"vortexutility.ec.1"| 9 Slots
"vortexutility.ec.2"| 18 Slots
"vortexutility.ec.3"| 27 Slots
"vortexutility.ec.4"| 36 Slots
"vortexutility.ec.5"| 45 Slots
"vortexutility.ec.6"| 54 Slots

Storage Features

- Dedicated Ender Chest GUI.
- UUID-based player storage.
- Persistent item data.
- Permission-based capacity.
- Stored items are preserved when capacity changes.

---

Commands

Command| Permission| Description
"/rtp"| "vortexutility.rtp"| Open RTP GUI
"/ec"| "vortexutility.ec"| Open Custom Ender Chest
"/enderchest"| "vortexutility.ec"| Open Custom Ender Chest
"/vortexutility reload"| "vortexutility.admin"| Reload plugin configuration
"/vu reload"| "vortexutility.admin"| Reload configuration alias

---

<details>
<summary>🔐 Permissions</summary>Permission| Default| Description
"vortexutility.rtp"| OP| Access Random Teleport
"vortexutility.ec"| OP| Access Custom Ender Chest
"vortexutility.admin"| OP| Plugin administration
"vortexutility.ec.1"| OP| 9-slot capacity
"vortexutility.ec.2"| OP| 18-slot capacity
"vortexutility.ec.3"| OP| 27-slot capacity
"vortexutility.ec.4"| OP| 36-slot capacity
"vortexutility.ec.5"| OP| 45-slot capacity
"vortexutility.ec.6"| OP| 54-slot capacity

</details><details>
<summary>⚙️ RTP Configuration</summary>RTP configuration is stored in "rtp.yml".

General Settings

Setting| Description
"cooldown-seconds"| RTP cooldown
"max-concurrent-searches"| Maximum simultaneous RTP searches

History Settings

Setting| Description
"history.enabled"| Enable RTP history
"history.max-locations"| Maximum saved locations
"history.minimum-distance"| Minimum distance from previous locations

Performance Settings

Setting| Description
"performance.max-attempts"| Maximum location search attempts
"performance.async-chunk-loading"| Enable asynchronous chunk loading
"performance.generate-new-chunks"| New chunk generation remains disabled internally

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

</details><details>
<summary>📦 Requirements</summary>Requirement| Version| Purpose
Paper / Purpur| 26.3| Minecraft server software
Java| 25| Runtime and build environment
Chunky| Recommended| RTP area pregeneration

Chunky is recommended, not a required runtime dependency.

</details><details>
<summary>🏗️ Building</summary>Clone repository:

git clone https://github.com/kingdomvortex3-star/VortexUtility.git
cd VortexUtility

Build with Gradle:

gradle build

Output:

build/libs/VortexUtility-*.jar

Install:

1. Copy the generated JAR into "plugins/".
2. Start or restart your Paper/Purpur server.
3. Edit the generated configuration files.
4. Run "/vortexutility reload" after configuration changes.

</details>---

Configuration

Generated on first run inside "plugins/VortexUtility/":

File| Purpose
"config.yml"| General settings and GUI
"messages.yml"| Plugin messages and notifications
"rtp.yml"| Random Teleport configuration
"ec.yml"| Custom Ender Chest configuration
"data/enderchests/"| Player storage files by UUID

Directory structure:

VortexUtility/
├── config.yml
├── messages.yml
├── rtp.yml
├── ec.yml
└── data/
    └── enderchests/
        └── <UUID>.yml

---

Developer

<div align="center">⚡ VORTEX TEAMS

Minecraft Plugin Development

Creating modern utilities, custom systems, and powerful solutions for Minecraft servers.

INNOVATION • PERFORMANCE • QUALITY

</div>---

<div align="center">VortexUtility — One Plugin. More Possibilities.

Developed with ❤️ by Vortex Teams

</div>
