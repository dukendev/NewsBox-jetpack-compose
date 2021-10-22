# NewsBox-jetpack-compose
## Jetpack compose app 
This is a simple news app which uses newapi.org api to showcase news in very user friendly manner. It is made complete using 100% kotlin using android new declarative UI framework [Jetpack Compose][compose]. This app follow google recommended archietechture for android app MVVM and has single activity model. 

Check out the Demo below :

[![NewsBox](https://img.shields.io/badge/NewsBox🌈-APK-black.svg?style=for-the-badge&logo=android)](https://github.com/sanjeet131/NewsBox-jetpack-compose/releases/download/v1.0.0-beta/NewsBox-v1_0_0.apk)

### Preview
![ezgif com-gif-maker (1)](https://user-images.githubusercontent.com/87947328/137799316-5f8ff700-a845-47d6-9bff-9bfed3bc1835.gif)

## Tech Stack Used 
--------------------------------
### Jetpack Compose 
#### Compose is Android modern declaritive UI framework using only kotlin, This App is completely made with kotlin using compose and animation APIs are in several parts of app.
#### All the material components like botton navigation bar and search bars are made with composable functions. 

### Retrofit
#### Retrofit is goto solution for making network calls in android. This app demonstrate GET methods. Further network error handling is also done to catch errors of network calls.

### Hilt
#### Hilt is simple solution for dependency injection in android projects. This app use hilt for providing dependencies.

### Coil
#### [Coil][coil] is a very convenient library to load images from url.

### LiveData
#### LiveData is an observable data holder class. Unlike a regular observable, LiveData is lifecycle-aware, meaning it respects the lifecycle of other app components, such as activities, fragments, or services. This awareness ensures LiveData only updates app component observers that are in an active lifecycle state.
#### Using Livedata with jetpack compose

### Coroutines
#### A coroutine is a concurrency design pattern that you can use on Android to simplify code that executes asynchronously.


[compose]: https://developer.android.com/jetpack/compose
[coil]: https://coil-kt.github.io/coil/compose
