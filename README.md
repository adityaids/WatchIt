# WatchIt

WatchIt is an Android application that showcases a catalog of popular movies and TV series. Users can browse lists, view detailed information, and mark titles as favorites for quick access.

## Tech Stack

- **Kotlin**
- **Android Jetpack**: ViewModel, LiveData, Room, Paging
- **Glide** for image loading
- **Espresso** for UI testing
- **JUnit & Mockito** for unit testing

## Project Structure

```
WatchIt/
├── app/                # Android application module
│   ├── src/main        # App source code
│   ├── src/test        # Unit tests
│   └── src/androidTest # Instrumentation & UI tests
├── build.gradle        # Root Gradle config
└── settings.gradle
```

## Setup Instructions

1. Ensure you have the Android SDK installed (API 30).
2. Clone the repository:
   ```bash
   git clone <repo-url>
   cd WatchIt
   ```
3. Build the project:
   ```bash
   ./gradlew assembleDebug
   ```
4. Run unit tests:
   ```bash
   ./gradlew test
   ```
5. (Optional) Run instrumentation tests on an emulator or device:
   ```bash
   ./gradlew connectedAndroidTest
   ```

## Running the App

Open the project in Android Studio and run the **app** configuration on an emulator or physical device.

## Usage Example

- Launch the app to view a list of popular titles.
- Tap a title to see detailed information.
- Use the favorite button to add items to your favorites list.

## Known Issues

- Instrumentation tests require an emulator or device. Ensure one is connected before running `connectedAndroidTest`.
- Some assets are mocked for testing and may not represent real network responses.

## Contributing

1. Fork the repository.
2. Create a new feature branch.
3. Commit your changes with clear messages.
4. Open a Pull Request describing your changes.

## License

This project is distributed under the MIT License.
