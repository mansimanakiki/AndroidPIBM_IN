# PIBM Android Application - Setup Guide

## Complete Project Structure

### 1. Firebase Setup

#### Step 1: Create Firebase Project
1. Go to [Firebase Console](https://console.firebase.google.com/)
2. Create a new project named "PIBM"
3. Add an Android app with package name: `com.ramanbyte.pibm_in`

#### Step 2: Download google-services.json
1. In Firebase Console, go to Project Settings
2. Download `google-services.json`
3. Place it in the `app/` directory

#### Step 3: Configure Remote Config
In Firebase Console → Remote Config, add these parameters:

**banners** (JSON):
```json
[
  {
    "id": "1",
    "imageUrl": "https://example.com/banner1.jpg",
    "title": "Welcome to PIBM",
    "subtitle": "Premier Business Education"
  },
  {
    "id": "2",
    "imageUrl": "https://example.com/banner2.jpg",
    "title": "Admissions Open 2024",
    "subtitle": "Apply Now"
  }
]
```

**pibm_info** (JSON):
```json
{
  "title": "Pune Institute of Business Management",
  "description": "PIBM is one of India's premier business schools, offering world-class management education with industry integration and 100% placement assistance.",
  "highlights": [
    "AICTE Approved",
    "Industry-Integrated Curriculum",
    "100% Placement Assistance",
    "State-of-the-art Infrastructure",
    "International Collaborations"
  ]
}
```

**navigation_items** (JSON):
```json
[
  {
    "id": 1,
    "title": "Admissions",
    "icon": "school",
    "url": "https://pibm.in/admissions",
    "order": 1
  },
  {
    "id": 2,
    "title": "Courses",
    "icon": "book",
    "url": "https://pibm.in/courses",
    "order": 2
  },
  {
    "id": 3,
    "title": "Placements",
    "icon": "work",
    "url": "https://pibm.in/placements",
    "order": 3
  },
  {
    "id": 4,
    "title": "Faculty",
    "icon": "people",
    "url": "https://pibm.in/faculty",
    "order": 4
  },
  {
    "id": 5,
    "title": "Campus",
    "icon": "location_city",
    "url": "https://pibm.in/campus",
    "order": 5
  },
  {
    "id": 6,
    "title": "Contact Us",
    "icon": "contact_mail",
    "url": "https://pibm.in/contact",
    "order": 6
  },
  {
    "id": 7,
    "title": "Gallery",
    "icon": "photo",
    "url": "https://pibm.in/gallery",
    "order": 7
  }
]
```

### 2. Build and Run

```bash
# Open project in Android Studio
# File → Open → Select pibm_app folder

# Sync Gradle files
# Build → Make Project

# Run on device or emulator
# Run → Run 'app'
```

### 3. Testing the Application

1. **Splash Screen**: 
   - On Android 12+: Uses new Splash Screen API
   - On Android 11 and below: Uses legacy theme-based splash

2. **Home Screen Features**:
   - Banner carousel (if configured in Firebase)
   - PIBM information card
   - Navigation grid (6-7 items)
   - Click any navigation item to open URL in browser

3. **Firebase Remote Config**:
   - App fetches latest configuration on launch
   - Default values are used if fetch fails
   - 1-hour cache interval for production

### 4. Customization

#### Update URLs
Edit `RemoteConfigManager.kt` to change default URLs:
```kotlin
NavigationItem(1, "Admissions", "school", "YOUR_URL_HERE", 1)
```

#### Change Colors
Edit `app/src/main/res/values/colors.xml`:
```xml
<color name="primary">#YOUR_COLOR</color>
```

#### Update API Endpoint
Edit `PibmApi.kt`:
```kotlin
const val BASE_URL = "https://your-api.com/"
```

### 5. Architecture Overview

```
app/
├── data/
│   ├── local/          # Room Database
│   ├── model/          # Data models
│   ├── remote/         # Retrofit API & Firebase Remote Config
│   └── repository/     # Repository pattern
├── di/                 # Hilt dependency injection
├── presentation/       # UI layer (Compose)
│   └── home/          # Home screen
└── ui/theme/          # Material Design theme

Key Technologies:
✓ Kotlin - Programming language
✓ Jetpack Compose - Modern UI toolkit
✓ Room - Local database
✓ Paging 3 - Efficient data loading
✓ Retrofit - Network calls
✓ Firebase Remote Config - Dynamic configuration
✓ Hilt/Dagger - Dependency injection
✓ Splash Screen API - Android 12+ splash
✓ Material Design 3 - Modern UI components
```

### 6. Key Features Implemented

✅ **Single Page Home Screen** with:
   - Dynamic banner carousel
   - PIBM information section
   - 6-7 navigation blocks in a grid

✅ **Splash Screen** with:
   - Android 12+ support (Splash Screen API)
   - Android 11 and below support (legacy)

✅ **Firebase Remote Config** for:
   - Dynamic banners
   - Dynamic navigation items
   - Dynamic PIBM information

✅ **Clean Architecture** with:
   - MVVM pattern
   - Repository pattern
   - Dependency injection

✅ **Modern Android Development**:
   - 100% Kotlin
   - Jetpack Compose UI
   - Material Design 3
   - Reactive programming with Flow

### 7. Package Structure

```
com.ramanbyte.pibm_in/
├── PibmApplication.kt           # Application class
├── MainActivity.kt              # Main activity
├── data/
│   ├── local/
│   │   ├── PibmDatabase.kt     # Room database
│   │   └── NavigationDao.kt    # DAO
│   ├── model/
│   │   └── Models.kt           # Data classes
│   ├── remote/
│   │   ├── PibmApi.kt          # Retrofit interface
│   │   └── RemoteConfigManager.kt
│   └── repository/
│       └── PibmRepository.kt
├── di/
│   └── AppModule.kt            # Hilt modules
├── presentation/
│   └── home/
│       ├── HomeScreen.kt       # UI
│       └── HomeViewModel.kt    # ViewModel
└── ui/theme/
    ├── Theme.kt
    └── Type.kt
```

### 8. Testing URLs

Update these URLs in Firebase Remote Config or default values:
- Admissions: https://pibm.in/admissions
- Courses: https://pibm.in/courses
- Placements: https://pibm.in/placements
- Faculty: https://pibm.in/faculty
- Campus: https://pibm.in/campus
- Contact: https://pibm.in/contact

### 9. Icon Mapping

Available icons for navigation items:
- school, book, work, people, location_city
- contact_mail, home, info, phone, photo

### 10. Troubleshooting

**Issue**: google-services.json not found
- **Solution**: Download from Firebase Console and place in `app/` directory

**Issue**: Splash screen not working on Android 12+
- **Solution**: Ensure `androidx.core:core-splashscreen:1.0.1` is included

**Issue**: Navigation items not loading
- **Solution**: Check Firebase Remote Config parameters

**Issue**: Build errors with Hilt
- **Solution**: Ensure `kapt` plugin is applied and dependencies are correct

### 11. Production Checklist

- [ ] Replace all placeholder URLs
- [ ] Add real banner images to Firebase
- [ ] Update app icon (ic_launcher)
- [ ] Configure ProGuard rules for release
- [ ] Test on multiple Android versions
- [ ] Enable Firebase Analytics
- [ ] Set proper Remote Config fetch intervals
- [ ] Add error tracking (Firebase Crashlytics)
- [ ] Test deep links if needed
- [ ] Verify all external links work
- [ ] Test on different screen sizes

---

## Support

For any issues, please check:
1. Firebase configuration is correct
2. `google-services.json` is in the right location
3. All URLs are valid and accessible
4. Internet permission is granted

Happy coding! 🚀
