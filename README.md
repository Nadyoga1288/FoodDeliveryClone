# 🍔 WoltFoodClone - 

[![Kotlin](https://img.shields.io/badge/Kotlin-1.9.23-blue.svg)](https://kotlinlang.org)
[![Jetpack Compose](https://img.shields.io/badge/Jetpack%20Compose-2024.04.00-green.svg)](https://developer.android.com/jetpack/compose)
[![Clean Architecture](https://img.shields.io/badge/Architecture-Clean%20%2B%20MVVM-orange.svg)](https://blog.cleancoder.com/uncle-bob/2012/08/13/the-clean-architecture.html)
[![Hilt](https://img.shields.io/badge/DI-Hilt-yellow.svg)](https://dagger.dev/hilt/)

A high-quality food delivery Android application built to demonstrate **Strong Junior / Mid-level** skills, specifically tailored for the **Finnish tech market** and companies like **Wolt**.

## 🇫🇮 Why this project is ready for the Finnish Market?

In Finland, engineering culture values **quality, maintainability, and accessibility**. This project reflects these values through:
- **Clean Architecture**: Strict separation of concerns (Domain, Data, Presentation).
- **Test-Driven Mindset**: Unit tests for business logic and UI tests for critical paths.
- **Modern Stack**: 100% Kotlin, Jetpack Compose, Hilt, Coroutines, and Flow.
- **Accessibility**: Material 3 components with proper content descriptions and semantic properties.
- **Performance**: Optimized image loading with Coil and efficient state management.

## 🚀 Key Features

- **Restaurant Discovery**: Browse and search for restaurants with real-time filtering.
- **Detailed View**: View menu items, ratings, and delivery information.
- **Offline-First**: Local caching using Room Database for a seamless user experience.
- **AI-Powered Insights**: Integrated `RecruiterAiAssistant` to provide technical insights for hiring managers.
- **Modern UI**: Beautiful, responsive design using Material 3 and smooth animations.

## 🏗️ Architecture & Tech Stack

### Architecture
- **Domain Layer**: Pure Kotlin, contains business logic (Models, Repositories, UseCases).
- **Data Layer**: Implementation of repositories, API (Retrofit), and Local DB (Room).
- **Presentation Layer**: Jetpack Compose, ViewModels with StateFlow, and Navigation.

### Tech Stack
- **Language**: Kotlin
- **UI**: Jetpack Compose (Material 3)
- **DI**: Hilt (Dagger)
- **Networking**: Retrofit 2 + OkHttp
- **Database**: Room
- **Image Loading**: Coil
- **Asynchronous**: Coroutines + Flow
- **Testing**: JUnit 4, MockK, Truth, Turbine, Compose Test Rule

## 🤖 AI Tools for Recruiters

This project includes a unique `RecruiterAiAssistant` utility. It's designed to help recruiters and technical leads quickly assess the candidate's technical depth.

```kotlin
// Example usage in the project
val insights = RecruiterAiAssistant().getProjectInsights()
println(insights["Architecture"]) // "Clean Architecture with Domain, Data, and Presentation layers."
```

## 🛠️ Getting Started

1. Clone the repository:
   ```bash
   git clone https://github.com/Nadyoga1288/FoodDeliveryClone.git
   ```
2. Open the project in **Android Studio Hedgehog** or newer.
3. Sync Gradle and run the `app` module.

## 🧪 Testing

Run unit tests:
```bash
./gradlew test
```

Run UI tests:
```bash
./gradlew connectedAndroidTest
```

## 👨‍💻 About the Developer

**Serhii Nadozhnyi**
- **LinkedIn**: [linkedin.com/in/serhii-nadozhnyi-3108aa348](https://linkedin.com/in/serhii-nadozhnyi-3108aa348)
- **GitHub**: [@Nadyoga1288](https://github.com/Nadyoga1288)
- **Location**: Ready for opportunities in Finland 🇫🇮

---
*Made with ❤️ for the Wolt Engineering Team.*
