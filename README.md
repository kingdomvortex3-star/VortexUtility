⚡ VortexUtility

<div align="center">ADVANCED MINECRAFT UTILITY SYSTEM

ONE PLUGIN. MORE POSSIBILITIES.

""Platform" (https://img.shields.io/badge/Platform-Paper%20%7C%20Purpur-00D9FF?style=for-the-badge)" (#requirements)
""Minecraft" (https://img.shields.io/badge/Minecraft-26.3-55CC55?style=for-the-badge&logo=minecraft)" (#requirements)
""Java" (https://img.shields.io/badge/Java-25-orange?style=for-the-badge&logo=openjdk)" (#requirements)
""Developer" (https://img.shields.io/badge/Developer-Vortex%20Teams-8B5CF6?style=for-the-badge)" (#developer)

<img src="assets/vortex-utility-banner.png.png" width="100%" alt="VortexUtility Banner">Developed with ❤️ by VORTEX TEAMS

Building Better Minecraft Experiences.

</div>---

ABOUT THE PROJECT

VortexUtility is an advanced Minecraft utility plugin developed by Vortex Teams.

Built to combine essential server utilities into one powerful, lightweight, and customizable system.

Featuring an advanced Random Teleport system and a permission-based Custom Ender Chest, VortexUtility delivers a modern experience for Minecraft servers.

Your Server. Your System. Your Experience.

---

CORE FEATURES

🌍 ADVANCED RANDOM TELEPORT

Explore your Minecraft world with an advanced Random Teleport system.

FEATURES

- Multi-world support.
- Overworld, Nether & The End.
- Interactive RTP GUI.
- Customizable teleport distance.
- Title-based teleport countdown.
- Anti-movement cancellation.
- Safe location detection.
- RTP location history.
- Avoids recently used locations.
- Custom coordinate boundaries per world.
- Minimum distance from other players.
- Individual player cooldown.
- Asynchronous chunk loading.
- No forced generation of new chunks.

📦 CUSTOM ENDER CHEST

A flexible storage system with permission-based capacity.

FEATURES

- Dedicated Custom Ender Chest GUI.
- Six configurable storage capacity tiers.
- Storage capacity up to 54 slots.
- UUID-based player data storage.
- Persistent item storage.
- Permission-based capacity upgrades.
- Preserves stored items when capacity changes.

⚙️ CONFIGURATION SYSTEM

A flexible configuration system for server administrators.

FEATURES

- Separate configuration files.
- Custom messages and notifications.
- Configurable RTP worlds and boundaries.
- Adjustable teleport cooldown.
- Configurable search attempts.
- Concurrent RTP search limits.
- RTP history management.
- GUI customization.
- Reload configuration without server restart.

---

COMMANDS

PLAYER COMMANDS

1. "/rtp"

Open the Random Teleport GUI.

2. "/ec"

Open the Custom Ender Chest.

3. "/enderchest"

Alternative command to open the Custom Ender Chest.

ADMIN COMMANDS

1. "/vortexutility reload"

Reload all plugin configurations.

2. "/vu reload"

Reload configuration alias.

---

PERMISSIONS

GENERAL PERMISSIONS

1. "vortexutility.rtp"

Allows players to access the Random Teleport system.

2. "vortexutility.ec"

Allows players to access the Custom Ender Chest.

3. "vortexutility.admin"

Allows administrators to manage the plugin.

ENDER CHEST CAPACITY

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

---

CONFIGURATION

VortexUtility automatically generates its configuration files after the first server startup.

DIRECTORY STRUCTURE

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

CONFIGURATION FILES

1. "config.yml"

General plugin settings and GUI configuration.

2. "messages.yml"

Plugin messages and notifications.

3. "rtp.yml"

Random Teleport settings and world configuration.

4. "ec.yml"

Custom Ender Chest settings.

5. "data/enderchests/"

Player storage files based on UUID.

RTP WORLD CONFIGURATION

Each world can be configured independently.

- Enable or disable RTP per world.
- Set world center coordinates.
- Define minimum and maximum X/Z boundaries.
- Configure minimum and maximum teleport distance.
- Set maximum location search attempts.
- Configure minimum distance from other players.

RTP HISTORY

- Enable or disable location history.
- Set maximum saved locations.
- Configure minimum distance from previous locations.
- Separate history for each player and world.

PERFORMANCE SETTINGS

- Maximum concurrent RTP searches.
- Maximum location search attempts.
- Asynchronous chunk loading.
- Controlled location searching.
- No forced new chunk generation.

«IMPORTANT

Pregenerate your RTP areas using Chunky before enabling RTP.

Make sure configured coordinate boundaries match the pregenerated area in each world.»

---

REQUIREMENTS

1. Paper / Purpur

Minecraft server version 26.3.

2. Java

Java 25 runtime and build environment.

3. Chunky

Recommended for RTP area pregeneration.

Chunky is recommended, not a required runtime dependency.

---

BUILDING

CLONE REPOSITORY

git clone https://github.com/kingdomvortex3-star/VortexUtility.git
cd VortexUtility

BUILD WITH GRADLE

gradle build

BUILD OUTPUT

build/libs/VortexUtility-*.jar

INSTALLATION

1. DOWNLOAD

Download the latest "VortexUtility.jar" from GitHub Releases.

2. UPLOAD

Place the JAR file into your server's "plugins/" directory.

3. RESTART

Restart your Paper or Purpur server.

4. CONFIGURE

Edit the generated configuration files inside "plugins/VortexUtility/".

5. ENJOY

Start using VortexUtility on your server.

---

DEVELOPER

<div align="center">⚡ VORTEX TEAMS

MINECRAFT PLUGIN DEVELOPMENT

Developing modern utilities, custom systems, and powerful solutions for Minecraft servers.

INNOVATION • PERFORMANCE • QUALITY

</div>---

<div align="center">VORTEX UTILITY

ONE PLUGIN. MORE POSSIBILITIES.

Developed with ❤️ by VORTEX TEAMS

Your Server. Your System. Your Experience.

</div>
