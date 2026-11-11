⚡ VORTEX UTILITY

Advanced • Modern • Lightweight Minecraft Utility Plugin

<p align="center">One Plugin. More Possibilities.

Dibangun untuk menghadirkan pengalaman bermain Minecraft yang lebih praktis, modern, dan nyaman.

<br>Developed with ❤️ by VORTEX TEAMS

</p>---

🌌 ABOUT

VortexUtility merupakan plugin utilitas Minecraft yang dikembangkan oleh Vortex Teams untuk mendukung kebutuhan server Survival, SMP, RPG, dan berbagai mode permainan lainnya.

Menghadirkan sistem Random Teleport (RTP) modern, Custom Ender Chest, GUI interaktif, serta konfigurasi yang fleksibel dalam satu plugin.

Dirancang dengan memperhatikan performa, keamanan lokasi, dan kemudahan pengelolaan server.

---

✨ MAIN FEATURES

🌍 Advanced Random Teleport

- Multi-world support: Overworld, Nether, dan The End.
- Modern GUI untuk pemilihan dunia dan jarak.
- Countdown teleport dengan tampilan Title.
- Auto-cancel ketika pemain bergerak.
- Safe location detection.
- Anti-duplicate RTP location.
- Cooldown per pemain.
- Pengaturan area dan jarak setiap world.
- Asynchronous chunk loading.
- Tidak meminta generate chunk baru.

📦 Custom Ender Chest

- Custom Ender Chest GUI.
- Sistem kapasitas berdasarkan permission.
- Mendukung kapasitas hingga 54 slot.
- Penyimpanan data berdasarkan UUID.
- Data item tetap dipertahankan ketika kapasitas berubah.

⚙️ Configuration & Performance

- Konfigurasi terpisah dan mudah dikelola.
- Custom messages dan notifikasi.
- Pengaturan RTP setiap world.
- Pembatasan pencarian RTP bersamaan.
- Reload konfigurasi tanpa restart server.
- Sistem pencarian lokasi dengan batas percobaan.

---

🖥️ GUI PREVIEW

World Selection

Memilih dimensi tujuan teleportasi.

Distance Selection

Menentukan jarak perjalanan sesuai kebutuhan.

RTP Countdown

Pemain harus tetap diam sampai countdown selesai.

---

📥 INSTALLATION

Ikuti langkah berikut untuk memasang VortexUtility.

1. Download

Unduh "VortexUtility.jar" dari GitHub Releases.

2. Upload

Masukkan file plugin ke folder:

"plugins/"

3. Restart

Restart server Minecraft.

4. Configure

Buka folder konfigurasi:

"plugins/VortexUtility/"

5. Finish

Sesuaikan konfigurasi dan jalankan:

"/vortexutility reload"

---

📂 DIRECTORY STRUCTURE

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

File| Description
"config.yml"| General configuration
"messages.yml"| Messages and notifications
"rtp.yml"| Random Teleport settings
"ec.yml"| Ender Chest settings
"data/"| Player data storage

---

⚙️ RTP CONFIGURATION

VortexUtility menyediakan pengaturan RTP yang fleksibel.

Configuration| Description
"cooldown-seconds"| RTP cooldown
"countdown-seconds"| Countdown duration
"max-concurrent-searches"| Maximum simultaneous searches
"history.enabled"| Enable RTP history
"history.max-locations"| Maximum saved locations
"history.minimum-distance"| Minimum distance from previous RTP
"performance.max-attempts"| Maximum location search attempts
"performance.async-chunk-loading"| Asynchronous chunk loading
"worlds.<world>.enabled"| Enable or disable a world

Important: Sesuaikan batas koordinat RTP dengan area yang telah di-pregenerate menggunakan Chunky. Sistem RTP tidak meminta pembuatan chunk baru.

---

⌨️ COMMANDS

Command| Description
"/rtp"| Open RTP GUI
"/ec"| Open Custom Ender Chest
"/enderchest"| Open Custom Ender Chest
"/vortexutility reload"| Reload plugin configuration
"/vu reload"| Reload alias

---

🔐 PERMISSIONS

General Permissions

Permission| Description
"vortexutility.rtp"| Access Random Teleport
"vortexutility.ec"| Access Ender Chest
"vortexutility.admin"| Plugin administration

Ender Chest Permissions

Permission| Capacity
"vortexutility.ec.1"| 9 Slots
"vortexutility.ec.2"| 18 Slots
"vortexutility.ec.3"| 27 Slots
"vortexutility.ec.4"| 36 Slots
"vortexutility.ec.5"| 45 Slots
"vortexutility.ec.6"| 54 Slots

---

🧩 REQUIREMENTS

- Paper / Purpur
- Java version sesuai build plugin
- Chunky (recommended for RTP pregeneration)

---

🚀 DEVELOPMENT

Project: VortexUtility

Developer: Vortex Teams

Category: Minecraft Utility Plugin

Platform: Paper / Purpur

---

💠 VORTEX TEAMS

Kami membangun berbagai solusi dan sistem untuk kebutuhan server Minecraft.

VORTEX TEAMS
Building Better Minecraft Experiences.

---

<p align="center">⚡ VORTEX UTILITY

Developed by VORTEX TEAMS

Powerful Features. Clean Experience. Better Performance.

</p>
