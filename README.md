
# ⚡ VORTEX UTILITY

<p align="center">

<strong>ADVANCED • MODERN • LIGHTWEIGHT</strong>

<br>

<img src="assets/vortex-utility-banner.png.png" width="100%" alt="VortexUtility Banner">

<strong>ONE PLUGIN. MORE POSSIBILITIES.</strong>

<br><br>

<img src="https://img.shields.io/badge/Platform-Paper%20%7C%20Purpur-00D9FF?style=for-the-badge" />
<img src="https://img.shields.io/badge/Java-25-orange?style=for-the-badge&logo=openjdk" />
<img src="https://img.shields.io/badge/Minecraft-26.3-55CC55?style=for-the-badge&logo=minecraft" />
<img src="https://img.shields.io/badge/Developer-Vortex%20Teams-8B5CF6?style=for-the-badge" />

<br><br>

<br>

<strong>Developed with ❤️ by VORTEX TEAMS</strong>

<em>Building Better Minecraft Experiences.</em>

</p>

---


◆ ABOUT VORTEX UTILITY

VortexUtility is a modern Minecraft utility plugin developed by Vortex Teams.

Designed to bring essential server utilities into one powerful, lightweight, and customizable system.

From advanced Random Teleport to Custom Ender Chest, VortexUtility provides useful features for a better Minecraft server experience.

Your Server. Your System. Your Experience.

---

⚡ CORE FEATURES

🌍 ADVANCED RANDOM TELEPORT

An advanced Random Teleport system with intelligent location searching.

FEATURES

1. Multi-world support.
2. Overworld, Nether & The End.
3. Interactive RTP GUI.
4. Customizable teleport distance.
5. Title-based teleport countdown.
6. Anti-movement cancellation.
7. Safe location detection.
8. Hazardous block protection.
9. Anti-duplicate RTP history.
10. Custom world coordinate boundaries.
11. Minimum distance from other players.
12. Individual player cooldown.
13. Asynchronous chunk loading.
14. No forced generation of new chunks.

📦 CUSTOM ENDER CHEST

A customizable storage system with permission-based capacity.

FEATURES

1. Dedicated Custom Ender Chest GUI.
2. Six storage capacity tiers.
3. Storage capacity up to 54 slots.
4. UUID-based player storage.
5. Persistent item data.
6. Permission-based capacity upgrades.
7. Preserves stored items when capacity changes.

⚙️ CONFIGURATION SYSTEM

A flexible configuration system for server administrators.

FEATURES

1. Separate configuration files.
2. Custom messages and notifications.
3. Configurable RTP worlds and boundaries.
4. Adjustable teleport cooldown.
5. Customizable RTP search attempts.
6. Concurrent RTP search limits.
7. RTP history configuration.
8. GUI customization.
9. Configuration reload without server restart.

---

⌨️ COMMANDS

PLAYER COMMANDS

1. "/rtp"

Open the Random Teleport GUI.

2. "/ec"

Open the Custom Ender Chest.

3. "/enderchest"

Alternative command to open the Custom Ender Chest.

ADMIN COMMANDS

1. "/vortexutility reload"

Reload plugin configurations.

2. "/vu reload"

Reload configuration alias.

---

🔐 PERMISSIONS

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

1. "config.yml"

General plugin settings and GUI configuration.

2. "messages.yml"

Plugin messages, prefixes, and notifications.

3. "rtp.yml"

Random Teleport settings, world boundaries, cooldown, and performance options.

4. "ec.yml"

Custom Ender Chest settings and capacity configuration.

5. "data/enderchests/"

Stores individual player Ender Chest data using UUID.

RTP WORLD SETTINGS

Each world can be configured independently.

1. Enable or disable RTP per world.
2. Set world center coordinates.
3. Define minimum and maximum X/Z boundaries.
4. Configure minimum and maximum teleport distance.
5. Set maximum search attempts.
6. Configure minimum distance from other players.

RTP PERFORMANCE

1. Maximum concurrent RTP searches.
2. Maximum location search attempts.
3. Asynchronous chunk loading.
4. RTP history management.
5. New chunk generation disabled internally.

«IMPORTANT

Pregenerate your RTP areas using Chunky before enabling RTP.

Make sure the configured coordinate boundaries match the actual pregenerated area in each world.»

---

🚀 INSTALLATION

STEP 01 — DOWNLOAD

Download the latest "VortexUtility.jar" from GitHub Releases.

STEP 02 — UPLOAD

Place the JAR file inside your server's "plugins/" directory.

STEP 03 — RESTART

Restart your Paper or Purpur server.

STEP 04 — CONFIGURE

Edit the generated configuration files inside "plugins/VortexUtility/".

STEP 05 — ENJOY

Use "/rtp" and "/ec" to access VortexUtility features.

---

🧩 REQUIREMENTS

1. Minecraft

Version 26.3.

2. Server Software

Paper or Purpur.

3. Java

Java 25.

4. Chunky

Recommended for RTP area pregeneration. Chunky is not a required runtime dependency.

---

🏗️ BUILDING

CLONE REPOSITORY

git clone https://github.com/kingdomvortex3-star/VortexUtility.git
cd VortexUtility

BUILD WITH GRADLE

gradle build

BUILD OUTPUT

build/libs/VortexUtility-*.jar

---

💠 DEVELOPER

<p align="center">⚡ VORTEX TEAMS

MINECRAFT PLUGIN DEVELOPMENT

Developing modern utilities, custom plugins, and powerful solutions for Minecraft servers.

INNOVATION • PERFORMANCE • QUALITY

</p>---

<p align="center">VORTEX UTILITY

ONE PLUGIN. MORE POSSIBILITIES.

Developed with ❤️ by VORTEX TEAMS

Your Server. Your System. Your Experience.

</p>
