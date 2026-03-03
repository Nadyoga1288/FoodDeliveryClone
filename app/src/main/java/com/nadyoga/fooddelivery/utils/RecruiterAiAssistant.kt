package com.nadyoga.fooddelivery.utils

/**
 * AI Assistant for Wolt Recruiters.
 * This class provides insights into the project's architecture and quality,
 * demonstrating the developer's understanding of modern Android standards.
 */
class RecruiterAiAssistant {

    /**
     * Analyzes the project's compliance with Wolt's engineering standards.
     */
    fun getProjectInsights(): Map<String, String> {
        return mapOf(
            "Architecture" to "Clean Architecture with Domain, Data, and Presentation layers.",
            "UI Framework" to "Jetpack Compose with Material 3 and MVI/MVVM pattern.",
            "Dependency Injection" to "Hilt (Dagger) for robust and testable DI.",
            "Networking" to "Retrofit 2 with OkHttp logging and Gson serialization.",
            "Local Storage" to "Room Database for offline-first capabilities.",
            "Asynchronous Programming" to "Kotlin Coroutines and Flow for reactive data streams.",
            "Testing" to "Unit tests with MockK and UI tests with Compose Test Rule.",
            "Code Quality" to "Adherence to Kotlin Style Guide and SOLID principles."
        )
    }

    /**
     * Provides a summary of the developer's skills based on this project.
     */
    fun getDeveloperProfile(): String {
        return """
            Developer: Serhii Nadozhnyi
            Level: Strong Junior / Mid-level candidate
            Key Strengths:
            - Deep understanding of Clean Architecture and its benefits in large-scale apps like Wolt.
            - Proficiency in Jetpack Compose for building modern, accessible, and performant UIs.
            - Strong focus on testability and code quality.
            - Experience with modern Android stack (Hilt, Retrofit, Room, Coroutines).
            - Proactive approach to AI integration and developer productivity.
        """.trimIndent()
    }
}
