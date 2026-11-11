
# ⚡ VORTEX UTILITY

### ADVANCED MINECRAFT UTILITY SYSTEM

**ONE PLUGIN. MORE POSSIBILITIES.**

![Platform](https://img.shields.io/badge/Platform-Paper%20%7C%20Purpur-00D9FF?style=for-the-badge)
![Java](https://img.shields.io/badge/Java-25-orange?style=for-the-badge&logo=openjdk)
![Minecraft](https://img.shields.io/badge/Minecraft-26.3-55CC55?style=for-the-badge&logo=minecraft)
![Developer](https://img.shields.io/badge/Developer-Vortex%20Teams-8B5CF6?style=for-the-badge)

<p align="center">
  <img src="assets/vortex-utility-banner.png.png" width="100%" alt="VortexUtility Banner">
</p>

<p align="center">

**Developed with ❤️ by VORTEX TEAMS**

*Building Better Minecraft Experiences.*

</p>

---


◆ ABOUT THE PROJECT

VortexUtility is an advanced Minecraft utility plugin developed by Vortex Teams.

Built to combine essential server utilities into one powerful, lightweight, and customizable system.

Featuring an advanced Random Teleport system and a permission-based Custom Ender Chest, VortexUtility delivers a modern experience for Minecraft servers.

«Your Server. Your System. Your Experience.»

---

✦ FEATURES & CAPABILITIES

🌍 RANDOM TELEPORT SYSTEM

Explore your Minecraft world with an advanced RTP system.

Feature| Description
Multi-World| Overworld, Nether & The End
Modern GUI| Interactive world and distance selection
Countdown| Title-based teleport countdown
Anti-Movement| Cancels teleportation when the player moves
Safe Location| Checks safe ground and available space
RTP History| Avoids recently used locations
World Boundaries| Custom coordinate limits per world
Player Protection| Minimum distance from other players
Cooldown| Individual player cooldown
Async Loading| Asynchronous chunk loading
Chunk Safety| Does not request new chunk generation

📦 CUSTOM ENDER CHEST

A customizable storage system with permission-based capacity.

- Dedicated Ender Chest GUI.
- Six configurable capacity tiers.
- Storage capacity up to 54 slots.
- UUID-based player data.
- Persistent item storage.
- Supports permission-based capacity upgrades.
- Preserves stored items when capacity changes.

⚙️ CONFIGURATION SYSTEM

- Separate configuration files.
- Custom messages and notifications.
- Configurable RTP worlds and boundaries.
- Adjustable teleport cooldown.
- Configurable countdown duration.
- Search attempt limits.
- Concurrent RTP search limits.
- Configuration reload without server restart.
---

⌨️ COMMANDS

PLAYER COMMANDS

Command| Description
"/rtp"| Open Random Teleport GUI
"/ec"| Open Custom Ender Chest
"/enderchest"| Open Custom Ender Chest

ADMIN COMMANDS

Command| Description
"/vortexutility reload"| Reload plugin configuration
"/vu reload"| Reload configuration alias

---

🔐 PERMISSIONS

GENERAL PERMISSIONS

Permission| Description
"vortexutility.rtp"| Access Random Teleport
"vortexutility.ec"| Access Custom Ender Chest
"vortexutility.admin"| Plugin administration

ENDER CHEST CAPACITY

Permission| Storage Capacity
"vortexutility.ec.1"| 9 Slots
"vortexutility.ec.2"| 18 Slots
"vortexutility.ec.3"| 27 Slots
"vortexutility.ec.4"| 36 Slots
"vortexutility.ec.5"| 45 Slots
"vortexutility.ec.6"| 54 Slots

---

📂 CONFIGURATION

VortexUtility automatically generates its configuration files after the first server startup.

DIRECTORY STRUCTURE

plugins/
└── VortexUtility/
    ├── config.yml
    ├── messages.yml
    ├── rtp.yml
    ├── ec.yml
    └── data/
        └── enderchests/
            └── <UUID>.yml

CONFIGURATION FILES

File| Purpose
"config.yml"| General settings and GUI
"messages.yml"| Messages and notifications
"rtp.yml"| Random Teleport settings
"ec.yml"| Ender Chest settings
"data/"| Player storage data

RTP WORLD CONFIGURATION

Each world can be configured independently.

- World activation.
- Center coordinates.
- Minimum and maximum X/Z boundaries.
- Minimum and maximum teleport distance.
- Maximum search attempts.
- Minimum distance from other players.

PERFORMANCE SETTINGS

- Maximum concurrent RTP searches.
- Maximum location search attempts.
- Asynchronous chunk loading.
- RTP history management.
- New chunk generation disabled internally.

«IMPORTANT: Pregenerate your RTP areas using Chunky. Make sure the configured coordinate boundaries match the pregenerated area in each world.»

---

📂 CONFIGURATION

VortexUtility automatically creates its configuration files.

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

File| Purpose
"config.yml"| General settings and GUI
"messages.yml"| Messages and notifications
"rtp.yml"| Random Teleport configuration
"ec.yml"| Ender Chest configuration
"data/"| Player storage data

RTP WORLD CONFIGURATION

Each world can be configured independently.

- World activation.
- Center coordinates.
- Minimum and maximum X/Z boundaries.
- Minimum and maximum teleport distance.
- Maximum search attempts.
- Minimum distance from other players.

PERFORMANCE SETTINGS

- Maximum concurrent RTP searches.
- Maximum location search attempts.
- Asynchronous chunk loading.
- RTP history management.
- New chunk generation disabled internally.

«IMPORTANT: Pregenerate your RTP areas using Chunky. Make sure the configured coordinate boundaries match the pregenerated area in each world.»

---

🚀 INSTALLATION GUIDE

STEP 01 — DOWNLOAD

Download the latest "VortexUtility.jar" from the repository Releases.

STEP 02 — UPLOAD

Place the JAR file into your Minecraft server's "plugins/" directory.

STEP 03 — RESTART

Restart your Paper or Purpur server.

STEP 04 — CONFIGURE

Edit the generated configuration files inside "plugins/VortexUtility/".

STEP 05 — ENJOY

Start using VortexUtility with "/rtp" and "/ec".

---

🧩 COMPATIBILITY

Component| Version
Minecraft| 26.3
Server Software| Paper / Purpur
Java| 25
Chunky| Recommended for RTP pregeneration

---

💠 DEVELOPMENT

Information| Details
Project| VortexUtility
Developer| Vortex Teams
Category| Minecraft Utility Plugin
Main Systems| RTP & Custom Ender Chest
Platform| Paper / Purpur
Language| Java

---

🏆 VORTEX TEAMS

<p align="center">⚡ VORTEX TEAMS

MINECRAFT PLUGIN DEVELOPMENT

Developing modern systems, custom plugins, and powerful solutions for Minecraft servers.

INNOVATION · PERFORMANCE · QUALITY

</p>---

<p align="center">VORTEX UTILITY

ONE PLUGIN. MORE POSSIBILITIES.

Made with ❤️ by VORTEX TEAMS

Your Server. Your System. Your Experience.

</p>
