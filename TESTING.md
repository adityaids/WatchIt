# Testing Strategy

This project employs a multi-layered testing approach to ensure stability and correctness.

## Unit Tests
- Located in `app/src/test`.
- Uses **JUnit4** and **Mockito**.
- Focus on utility classes and ViewModels.
- Run with:
  ```bash
  ./gradlew test
  ```

## Integration Tests
- Located in `app/src/androidTest`.
- Use an in-memory Room database to verify DAO interactions.
- Run with an emulator or device:
  ```bash
  ./gradlew connectedAndroidTest
  ```

## UI Tests
- Located in `app/src/androidTest`.
- Implemented with **Espresso** and `ActivityScenario`.
- Cover main flows including list browsing and detail screens.

## Coverage Goals
- Core logic and utilities: >80% statement coverage.
- DAO and repository interactions: exercise all CRUD operations.
- UI flows: validate critical paths such as navigation and favorites.

## Best Practices
- Keep tests independent and deterministic.
- Use `InstantTaskExecutorRule` for LiveData.
- Register `EspressoIdlingResource` for asynchronous operations.
- Prefer descriptive test names following `methodUnderTest_expectedResult`.
