Etalase movie app

[![Build Status](https://api.travis-ci.com/mochadwi/android-etalase-app.svg)](https://travis-ci.com/mochadwi/android-etalase-app) [![codecov](https://codecov.io/gh/mochadwi/android-etalase-app/branch/master/graph/badge.svg)](https://codecov.io/gh/mochadwi/android-etalase-app)

### Feature
- [x] local-storage dengan Room
- [x] menggunakan view-model dan live-data
- [ ] Hit API di background process dengan work-manager
- [x] Mengimplementasikan kotlin-coroutines
- [ ] arsitektur berbasis offline-first
- [x] format commit dengan git-karma
- [ ] Use modular approach [per-feature](https://github.com/mochadwi/android-news-app)
- [x] Add use case / interactor
- [x] Add mapper / transformation [prefer to this template](https://github.com/hipe-id/clean-arch-mvvm-template/tree/develop) I used to build

### Nice to have:
- [x] mengimplementasikan navigation component dari jetpack
- [x] menggunakan ~~dagger~~ koin

### Instruction
- [x] Buat sebuah aplikasi untuk menampilkan list of movie
- [ ] Dan membuat movie baru yang bersumber dari API: https://api.themoviedb.org/3/.
- [ ] Aplikasi harus mengacu pada paradigma offline-first

### Tech Stack
- Git
  - Git karma convention used for [git commit message](https://plugins.jetbrains.com/plugin/9861-git-commit-template)
- CI / CD
  - Using [Travis](https://github.com/mochadwi/android-etalase-app/blob/master/.travis.yml)
- Kotlin
  - with idiom style: Extension
  - Refactor (!!) marks with safety-call (?)
  - Custom set/getter
- Kotlinx Serialization
  - Used to replace GSON
  - Kotlinx is reflection-less, support many data types
  - Easy to use for retrofit. E.g: @QueryMap, using NetworkExt#QueryParam.toQueryMap without building map (key, value) one by one
- Coroutine
  - Couroutine adapter for retrofit to use Deferred return type
  - Combined with room to have a suspend function (this only available on latest room 2.1.0-beta01)
  - Mostly used for Retrofit and Room
  - Channel used for search query (send & consumeEach)
- Data Binding
  - I'm trying to implement atomic design (<include> tag) combined with Data binding powerful features e.g: (custom_error.xml, custom_loading.xml)
  - I also creating a custom Databinding adapter, to customize *View related attributes e.g: setPriceTag, configuring recyclerview, and many more
  - Using ObservableField from Databinding to update and observe value changes without hassle
- Room
  - Configuring sqlite never been this easy with room, no more cursor loader and manual sql statement to have a CRUD
  - FTS4 (Full-text search) used for searching local SQLite data
- Koin
  - Easy injection on unit / ui test
  - injecting viewmodel and sharedViewModel (share same viewmodel along fragment)
- MyPreferenceFactory
  - (with kotlin extension: **operator function, prefs reader**)
  - No more bloated shared preference set/getter e.g: prefs["key"] = "this-value"
- Building Bundle with ease
  - (with kotlin extension: **bundleOf( "key0" bundleTo "val", "key1" bundleTo 100  )**)
- Unit / Instrumentation test
  - `./gradlew test` to check for the test or
  - `./gradlew clean build` to build everything including check for the test
  - Use `sharedTest` directory for commonly used utils for both `test` & `androidTest`
- Better gradle managament (**buildSrc** directory)
  - Kotlin-DSL used
  - easy search & navigation across dependency management
- AndroidX stable Used
  - room, lifecycle, paging to get latest update and backward compatible
  - one of great feature exist Fragment#getViewLifeCycleOwner() so we don't have to manually configure to destroy the viewmodel
- Android Studio 3.5 Beta1
  - Used to have latest feature from google io 19
  - most important was: Data binding compiler error now arrived, refactor on xml much easier and make sense, no more lags
  - Improved Gradle Sync performance by adjusting for deleted build cache
  - Improved performance and UX with the Layout Editor
  - Incremental annotation processing (kapt and databinding, faster build)
  - Using latest IntelliJ IDEA 2019.1 which is more powerful

### Ref
- [Still not used the 100% clean arch](https://proandroiddev.com/a-guided-tour-inside-a-clean-architecture-code-base-48bb5cc9fc97)

### Known Issue
- [ ] List overloading: [typealias](https://kotlinlang.org/docs/reference/type-aliases.html), [java list param 1](https://stackoverflow.com/q/2241514/3763032), [java list param 2](https://coderanch.com/t/384093/java/Overloading-List-Generic-Types-Argument). The code [here]()
- [ ] [Concrete implementation from generic class](https://stackoverflow.com/a/47796513/3763032)
- [x] Loading animation issue: fix with coroutine in IO thread
- [x] Implement click listener RV onCreateViewHolder: implement an onClick(View, Int) callback
- [ ] Remove hardcoded strings
- [ ] Remove unused resources
- [ ] Don't refresh on user back from detail screen
- [ ] Overview text truncated on the CardView
- [x] Polish the UI screen to be more nicer