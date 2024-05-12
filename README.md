# The Movie DB App

This is a mobile application developed using C++, Kotlin, Java, and Gradle. The application uses the Movie DB API to fetch and display movie data.

## Features

- Fetch and display movies from the Movie DB API
- High-quality image loading
- User Interface designed using Jetpack Compose
- API calls made using Retrofit
- Follows MVVM architecture with ViewModel
- Uses Usecase pattern for clean architecture
- Asynchronous code handled with Coroutines
- API key securely stored in the native C++ layer using CMake
- Unit tests written using JUnit and Mockito

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes.

### Prerequisites

- Android Studio Iguana | 2023.2.1 or later
- JDK 8 or later
- Android SDK
- NDK

### Installing

1. Clone the repository
2. Open the project in Android Studio
3. Build the project and run on an emulator or device

## Built With

- [Kotlin](https://kotlinlang.org/) - The main programming language
- [Java](https://www.java.com/) - Used in some parts of the application
- [C++](https://www.cplusplus.com/) - Used for secure API key storage
- [Gradle](https://gradle.org/) - Dependency Management
- [Jetpack Compose](https://developer.android.com/jetpack/compose) - Used for UI design
- [Retrofit](https://square.github.io/retrofit/) - Used for API calls
- [Coroutines](https://kotlinlang.org/docs/coroutines-guide.html) - Used for asynchronous code
- [JUnit](https://junit.org/junit5/) and [Mockito](https://site.mockito.org/) - Used for testing

## Authors

- Rahul Appmaster

## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details

## Acknowledgments

- Thanks to The Movie DB for their API
