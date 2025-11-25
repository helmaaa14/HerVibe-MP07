# 🌸 HerVibe - Mood & Cycle Tracker

Aplikasi mood dan cycle tracker untuk wanita yang dibangun dengan **Jetpack Compose** dan **Material Design 3**.

## 📱 Screenshots

(Tambahkan screenshot di sini)

## ✨ Features

- ✅ **Material Design 3** implementation
- ✅ **Custom Color Palette** (Primrose Garden, Pinktone, Lime Lollipop)
- ✅ **Dark/Light Mode** support
- ✅ **Mood Tracker** dengan 6 jenis mood + intensity
- ✅ **User Authentication** dengan DataStore
- ✅ **Room Database** untuk persistent storage
- ✅ **Modern UI Components** (Cards, Buttons, TextFields)
- ✅ **Bottom Navigation** untuk easy navigation

## 🎨 Design System

### Color Palette
- **Primary:** Primrose Garden (#D3A3A6)
- **Secondary:** Pinktone (#E0B0C1)
- **Tertiary:** Lime Lollipop (#C9E4A4)
- **Background:** Yucca White (#F5F5DC)

### Typography
- Display, Headline, Title, Body, Label hierarchy
- Menggunakan Material Design 3 type scale

### Components
- PrimaryButton dengan elevation
- AppCard dengan clickable variant
- AppTextField dengan password toggle

## 🛠️ Tech Stack

- **Language:** Kotlin
- **UI Framework:** Jetpack Compose
- **Architecture:** MVVM
- **Database:** Room
- **Data Storage:** DataStore Preferences
- **Navigation:** Navigation Compose
- **Design:** Material Design 3

## 📦 Dependencies
```gradle
// Compose BOM
androidx.compose:compose-bom:2024.02.00

// Material Design 3
androidx.compose.material3:material3

// Navigation
androidx.navigation:navigation-compose:2.7.7

// Room Database
androidx.room:room-runtime:2.6.1
androidx.room:room-ktx:2.6.1

// DataStore
androidx.datastore:datastore-preferences:1.0.0
```

## 🚀 Getting Started

### Prerequisites
- Android Studio Hedgehog (2023.1.1) or newer
- JDK 17
- Android SDK API 24+

### Installation

1. Clone repository:
```bash
git clone https://github.com/[username-anda]/HerVibe-MP07.git
```

2. Open project di Android Studio

3. Sync Gradle:
4. Run app:
## 📚 Project Structure
HerVibe/
├── app/
│   ├── src/main/
│   │   ├── java/com/example/hervibe/
│   │   │   ├── data/
│   │   │   │   ├── local/       (Database entities, DAOs)
│   │   │   │   ├── preferences/ (DataStore)
│   │   │   │   └── repository/  (Repository pattern)
│   │   │   ├── ui/
│   │   │   │   ├── theme/       (Color, Type, Shape, Theme)
│   │   │   │   ├── components/  (Reusable components)
│   │   │   │   ├── screens/     (Login, Home, Profile, etc)
│   │   │   │   └── navigation/  (NavGraph)
│   │   │   └── MainActivity.kt
│   │   └── res/
│   └── build.gradle.kts
└── build.gradle.kts
## 👩‍💻 Author

**Helma Afifah**  
NIM: 230104040215
Kelas: TI 23 A

**Praktikum Mobile Programming #7**  
Topik: Material Design 3, Style, Theme, & Modern UI Principles  
Dosen: Muhayat, M.IT

## 📄 License

Dibuat untuk keperluan akademis - Praktikum Mobile Programming 2025

## 🙏 Acknowledgments

- Material Design 3 Guidelines by Google
- Jetpack Compose Documentation
- Android Developer Community