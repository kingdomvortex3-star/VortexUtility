
<div align="center">

# ⚡ VORTEX UTILITY

### ADVANCED • MODERN • LIGHTWEIGHT

**ONE PLUGIN. MORE POSSIBILITIES.**

<img src="assets/vortex-utility-banner.png" width="100%" alt="VortexUtility Banner">

![Paper](https://img.shields.io/badge/Platform-Paper%20%7C%20Purpur-00D9FF?style=for-the-badge)
![Java](https://img.shields.io/badge/Java-25-orange?style=for-the-badge&logo=openjdk)
![Minecraft](https://img.shields.io/badge/Minecraft-26.3-55CC55?style=for-the-badge&logo=minecraft)
![Developer](https://img.shields.io/badge/Developer-Vortex%20Teams-8B5CF6?style=for-the-badge)

<br>

<img src="assets/vortex-utility-banner.png" width="100%" alt="VortexUtility Banner">

<br>

**Developed with ❤️ by VORTEX TEAMS**

*Building Better Minecraft Experiences.*

</div>

---


</p>---

🌌 ABOUT VORTEX UTILITY

VortexUtility is an advanced Minecraft utility plugin developed by Vortex Teams.

Designed to combine essential server utilities into one modern, lightweight, and customizable system.

From intelligent Random Teleport to Custom Ender Chest, VortexUtility brings powerful features and a clean gameplay experience to your Minecraft server.

Your Server. Your System. Your Experience.

---

⚡ CORE FEATURES

🌍 ADVANCED RANDOM TELEPORT

Explore the world with an advanced Random Teleport system.

- Multi-world support.
- Overworld, Nether & The End.
- Modern interactive RTP GUI.
- Customizable teleport distance.
- Countdown with Title display.
- Anti-movement cancellation.
- Safe location detection.
- Anti-duplicate RTP history.
- Per-world coordinate boundaries.
- Minimum distance from other players.
- Individual player cooldown.
- Asynchronous chunk loading.
- No forced generation of new chunks.

📦 CUSTOM ENDER CHEST

A flexible storage system with permission-based capacity.

- Custom Ender Chest GUI.
- 6 storage capacity tiers.
- Up to 54 inventory slots.
- UUID-based player storage.
- Persistent item data.
- Permission-based capacity.
- Saved player inventory data.

⚙️ SMART CONFIGURATION

Take control of your plugin settings.

- Separate configuration files.
- Custom messages and notifications.
- Configurable GUI appearance.
- Per-world RTP settings.
- Adjustable cooldown.
- Configurable search attempts.
- Concurrent RTP search limits.
- Reload configuration without restarting the server.

---

🎨 GUI SHOWCASE

WORLD SELECTION

Choose your destination dimension.

- OVERWORLD
- THE NETHER
- THE END

DISTANCE SELECTION

Choose your preferred teleport distance.

- 500 Blocks
- 1,000 Blocks
- 2,500 Blocks
- 5,000 Blocks

Available distances are also restricted by each world's configuration.

RTP COUNTDOWN

Stay still while the countdown is running.

Moving during the countdown will automatically cancel the teleportation.

CUSTOM ENDER CHEST

A dedicated storage interface with permission-based inventory capacity.

---

🛠️ COMMANDS

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
"vortexutility.ec"| Access Ender Chest
"vortexutility.admin"| Plugin administration

ENDER CHEST TIERS

Permission| Capacity
"vortexutility.ec.1"| 9 Slots
"vortexutility.ec.2"| 18 Slots
"vortexutility.ec.3"| 27 Slots
"vortexutility.ec.4"| 36 Slots
"vortexutility.ec.5"| 45 Slots
"vortexutility.ec.6"| 54 Slots

---

📂 CONFIGURATION STRUCTURE

VortexUtility/
│
├── config.yml
├── messages.yml
├── rtp.yml
├── ec.yml
│
└── data/
    └── enderchests/
        └── <UUID>.yml

CONFIGURATION FILES

File| Description
"config.yml"| Main plugin configuration
"messages.yml"| Messages and notifications
"rtp.yml"| Random Teleport settings
"ec.yml"| Ender Chest settings
"data/"| Player storage data

---

⚙️ RTP CONFIGURATION

VortexUtility provides flexible settings for controlling the RTP system.

GENERAL SETTINGS

Setting| Description
"cooldown-seconds"| RTP cooldown duration
"countdown-seconds"| Teleport countdown
"max-concurrent-searches"| Maximum simultaneous RTP searches

HISTORY SETTINGS

Setting| Description
"history.enabled"| Enable RTP history
"history.max-locations"| Maximum saved locations
"history.minimum-distance"| Minimum distance from previous locations

PERFORMANCE SETTINGS

Setting| Description
"performance.max-attempts"| Maximum location search attempts
"performance.async-chunk-loading"| Enable asynchronous chunk loading
"performance.generate-new-chunks"| New chunk generation is disabled internally

WORLD SETTINGS

Each world can have its own configuration:

- Enable or disable RTP.
- Center coordinates.
- Minimum and maximum X/Z boundaries.
- Minimum and maximum teleport distance.
- Search attempts.
- Minimum distance from other players.

IMPORTANT: Use Chunky to pregenerate your RTP area before enabling RTP. Make sure the configured coordinate boundaries match the pregenerated area.

---

📥 INSTALLATION

Follow these steps to install VortexUtility.

STEP 01 — DOWNLOAD

Download the latest "VortexUtility.jar" from the Releases section.

STEP 02 — UPLOAD

Place the JAR file into your server's "plugins/" folder.

STEP 03 — RESTART

Restart your Minecraft server.

STEP 04 — CONFIGURE

Open "plugins/VortexUtility/" and customize the configuration files.

STEP 05 — FINISH

Use the available commands and enjoy VortexUtility!

---

🧩 REQUIREMENTS

- Minecraft Server: Paper / Purpur.
- Java: 25 for the current project build.
- Chunky: Recommended for RTP area pregeneration.

---

🚀 PERFORMANCE & OPTIMIZATION

VortexUtility is designed with performance and usability in mind.

- Controlled RTP search attempts.
- Limited concurrent RTP searches.
- Asynchronous chunk loading.
- No forced new chunk generation.
- Configurable pregenerated boundaries.
- Limited RTP history per player.
- Separate world configurations.

Our goal is to provide useful features without unnecessary server overhead.

---

💎 WHY VORTEX UTILITY?

Feature| Experience
Advanced RTP| Intelligent random teleport
Modern GUI| Clean and interactive menus
Safe Location| Safer teleport destinations
Multi-World| Multiple dimension support
Custom EC| Flexible player storage
Smart Configuration| Easy server management
Performance Focus| Controlled resource usage

---

🏆 DEVELOPED BY

<p align="center">⚡ VORTEX TEAMS

MINECRAFT PLUGIN DEVELOPMENT

We build systems, utilities, and solutions for modern Minecraft servers.

INNOVATION • PERFORMANCE • QUALITY

</p>---

<p align="center">VORTEX UTILITY

ONE PLUGIN. MORE POSSIBILITIES.

Developed with ❤️ by VORTEX TEAMS

Your Server. Your System. Your Experience.

</p>
