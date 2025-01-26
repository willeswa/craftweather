# WeatherCraft

WeatherCraft is a weather application built with Jetpack Compose, providing real-time weather updates and forecasts for cities around the world. The app is built with a clean MVVM architecture, making it modular, scalable, and easy to maintain.

## Libraries Used

This project utilizes the following libraries and tools:

### Dependency Injection
- **Hilt**: Used for dependency injection to simplify code and reduce boilerplate. Hilt provides easy management of application-level dependencies and works seamlessly with ViewModels.

### Networking
- **Retrofit**: A type-safe HTTP client for making API calls. It handles serialization and deserialization of network responses efficiently.
- **OkHttp**: Used alongside Retrofit as the underlying HTTP client for making network requests. It includes features like request interceptors, caching, and logging.

### Serialization
- **Kotlin Serialization**: A powerful library for converting JSON responses into Kotlin objects. This project uses Kotlin Serialization to handle API responses with minimal configuration.

### UI Framework
- **Jetpack Compose**: A modern UI toolkit for building declarative UIs in Android. Compose provides powerful tools for creating reactive UIs that respond to state changes efficiently.
- **Material 3**: Implements Material Design 3 guidelines to ensure a modern, sleek, and accessible user experience.

### Local Storage
- **Room**: A robust database solution for local data storage. Room is used as the single source of truth for offline-first functionality.
- **Datastore**: A modern, lightweight solution for handling key-value data storage, used for session management and storing user preferences.

## Project Architecture

This project adopts the **Clean MVVM (Model-View-ViewModel)** architecture, designed for scalability, testability, and maintainability. Here's an overview of how the architecture is structured:

### Core Principles
- **Unidirectional Data Flow**: The app ensures a one-way flow of data from the data layer to the UI layer. This simplifies state management and makes the app behavior predictable.
- **Room as Source of Truth**: The app is offline-first, with Room serving as the primary data source. All data is fetched from Room, and network updates are used to refresh the database.

### Layers
1. **UI Layer**:
    - Composed of Jetpack Compose components.
    - Reactively updates based on state provided by ViewModels.
    - Uses Material 3 for styling.

2. **ViewModel Layer**:
    - Acts as the bridge between the UI and the repository.
    - Exposes data as Kotlin Flows or StateFlows for reactive updates.
    - Manages UI state and handles business logic.

3. **Data Layer**:
    - **Repository**: Acts as the abstraction layer between the UI and the data sources (local and remote).
    - **Local Data Source**: Powered by Room, it stores and manages cached data for offline support.
    - **Remote Data Source**: Fetches data from APIs using Retrofit and Kotlin Serialization.

### Data Flow
1. UI sends an action or intent (e.g., fetch weather for a city).
2. ViewModel processes the action and interacts with the repository.
3. Repository decides whether to fetch data from the local database (Room) or the remote API (Retrofit).
4. Mappers convert data between the domain model and the UI model, ensuring a separation of concerns.
5. Updated data flows back to the UI via Kotlin Flows, triggering a state update in the UI.

## Setting Up API Keys

To run the project locally, follow these steps:

1. **Create or Open `local.properties`**:
    - Navigate to the root of your project directory.
    - Open the `local.properties` file. If it doesn’t exist, create one.

2. **Add API Keys**:
   Add the following entries to the file:

   ```properties
   weatherApiKey=YOUR_API_KEY
   baseUrl=https://api.openweathermap.org/data/2.5/