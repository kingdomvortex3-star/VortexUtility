⚡ VortexUtility

Modern • Lightweight • Advanced Minecraft Utility Plugin

VortexUtility adalah plugin utilitas Minecraft yang dirancang untuk meningkatkan pengalaman bermain melalui sistem Random Teleport (RTP), GUI modern, dan Ender Chest dengan kapasitas berdasarkan permission.

---

✨ Fitur Utama

🌍 Advanced Random Teleport

- Mendukung Overworld, Nether, dan The End.
- GUI pemilihan dunia dan jarak.
- Countdown sebelum teleportasi.
- Teleportasi dibatalkan jika pemain bergerak.
- Pemeriksaan lokasi aman.
- Menghindari lokasi RTP yang pernah digunakan.
- Cooldown per pemain.
- Batas jarak dan area RTP yang dapat dikonfigurasi.
- Pencarian lokasi menggunakan chunk yang sudah tersedia.
- Tidak meminta pembuatan chunk baru.

📦 Custom Ender Chest

- GUI Ender Chest khusus.
- Kapasitas berdasarkan permission.
- Mendukung penyimpanan hingga 54 slot.
- Data tersimpan berdasarkan UUID pemain.
- Isi inventory tetap tersimpan meskipun kapasitas permission berubah.

⚙️ Configuration & Performance

- Konfigurasi terpisah untuk setiap sistem.
- Pengaturan pesan dan notifikasi.
- Pengaturan batas RTP per world.
- Batas pencarian RTP bersamaan.
- Dukungan asynchronous chunk loading.
- Reload konfigurasi tanpa restart server.

---

📸 Preview

RTP World Selection

Pilih dimensi yang ingin dijelajahi.

RTP Distance Selection

Pilih jarak teleportasi sesuai kebutuhan.

RTP Countdown

Pemain harus tetap diam selama hitungan mundur.

---

📥 Installation

1. Download file "VortexUtility.jar" dari bagian Releases.
2. Masukkan file ke folder "plugins/".
3. Restart server.
4. Buka folder "plugins/VortexUtility/".
5. Sesuaikan konfigurasi sesuai kebutuhan.
6. Jalankan "/vortexutility reload" setelah mengubah konfigurasi.

---

🗂️ Configuration

plugins/
└── VortexUtility/
    ├── config.yml
    ├── messages.yml
    ├── rtp.yml
    ├── ec.yml
    └── data/
        └── enderchests/
            └── <UUID>.yml

File| Fungsi
"config.yml"| Pengaturan umum dan GUI
"messages.yml"| Pesan dan notifikasi
"rtp.yml"| Pengaturan sistem RTP
"ec.yml"| Pengaturan Ender Chest
"data/"| Penyimpanan data pemain

RTP Configuration

Beberapa pengaturan yang tersedia:

- "cooldown-seconds"
- "max-concurrent-searches"
- "countdown-seconds"
- "history.enabled"
- "history.max-locations"
- "history.minimum-distance"
- "performance.max-attempts"
- "performance.async-chunk-loading"
- "worlds.<world>.enabled"
- "worlds.<world>.min-x"
- "worlds.<world>.max-x"
- "worlds.<world>.min-z"
- "worlds.<world>.max-z"

Penting: Sesuaikan batas koordinat RTP dengan area yang sudah di-pregenerate menggunakan Chunky. RTP tidak dimaksudkan untuk menghasilkan chunk baru.

---

⌨️ Commands

Command| Keterangan
"/rtp"| Membuka GUI Random Teleport
"/ec"| Membuka Custom Ender Chest
"/enderchest"| Alternatif membuka Ender Chest
"/vortexutility reload"| Reload konfigurasi
"/vu reload"| Alias reload

---

🔐 Permissions

General

Permission| Keterangan
"vortexutility.rtp"| Menggunakan RTP
"vortexutility.ec"| Menggunakan Ender Chest
"vortexutility.admin"| Akses administrasi plugin

Ender Chest Tiers

Permission| Kapasitas
"vortexutility.ec.1"| 9 Slots
"vortexutility.ec.2"| 18 Slots
"vortexutility.ec.3"| 27 Slots
"vortexutility.ec.4"| 36 Slots
"vortexutility.ec.5"| 45 Slots
"vortexutility.ec.6"| 54 Slots

---

🛠️ Requirements

- Minecraft Server: Paper / Purpur
- Java: Sesuai versi build plugin
- Chunky: Direkomendasikan untuk pregeneration area RTP

---

💎 Vortex Network

Developed by Vortex Network

Built for modern Minecraft servers.

- Advanced Utility System
- Modern GUI Experience
- Configurable Features
- Performance-Oriented Design

---

VortexUtility — One Plugin, More Possibilities.
