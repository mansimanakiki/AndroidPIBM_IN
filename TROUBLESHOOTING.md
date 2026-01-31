# Troubleshooting Guide

## ✅ KSP is Now Used (Kapt Issue Fixed!)

This project now uses **KSP (Kotlin Symbol Processing)** instead of Kapt, which completely avoids the Java compatibility error you encountered.

## What Changed?

- ❌ Removed: `kotlin-kapt` plugin
- ✅ Added: `com.google.devtools.ksp` plugin
- ✅ All annotation processors now use KSP (Room, Hilt)

This means the project will work with any Java version (11, 17, or higher) without issues!

## Quick Start

1. **Extract the zip file**
2. **Open in Android Studio**
   - File → Open → Select `pibm_app` folder
3. **Sync Gradle**
   - Android Studio will automatically download dependencies
4. **Add Firebase Configuration**
   - Download `google-services.json` from Firebase Console
   - Place it in `app/` directory
5. **Build and Run**
   - Click the green play button

## Common Issues & Solutions

### 1. Gradle Sync Failed

**Solution:**
```bash
# In Android Studio
Build → Clean Project
File → Invalidate Caches → Invalidate and Restart
```

### 2. Missing google-services.json

**Error:** `File google-services.json is missing`

**Solution:**
1. Go to [Firebase Console](https://console.firebase.google.com/)
2. Select your project (or create new one)
3. Add Android app with package: `com.ramanbyte.pibm_in`
4. Download `google-services.json`
5. Place in `app/` directory (same level as `build.gradle.kts`)

### 3. Build Takes Too Long

**Solution:**
Enable Gradle parallel builds:
- Add to `gradle.properties`:
```properties
org.gradle.parallel=true
org.gradle.caching=true
```

### 4. Cannot Resolve Dependencies

**Solution:**
1. Check internet connection
2. In Android Studio: `File` → `Settings` → `Build Tools` → `Gradle`
3. Enable: "Download external annotations for dependencies"
4. Click "Sync Now"

### 5. App Crashes on Launch

**Possible causes:**
- Missing `google-services.json` 
- Firebase not initialized properly

**Solution:**
- Check Logcat for error messages
- Ensure Firebase is configured correctly
- App will work with default values even without Firebase

## Clean Build Steps
## Clean Build Steps

If you're having persistent issues:

```bash
# Terminal in project root
./gradlew clean
./gradlew build

# Or in Android Studio
Build → Clean Project
Build → Rebuild Project
```

Delete build directories manually:
```bash
rm -rf .gradle
rm -rf build
rm -rf app/build
rm -rf .idea
```

Then reopen the project in Android Studio.

## Verification Checklist

After successful build, verify:
- ✅ Project syncs without errors
- ✅ Build completes successfully
- ✅ App installs on emulator/device
- ✅ Home screen shows navigation items
- ✅ Clicking navigation items opens URLs

## Project Configuration

**Current Setup:**
- ✅ KSP (not Kapt) - No Java compatibility issues
- ✅ Kotlin 1.9.10
- ✅ Android Gradle Plugin 8.1.4
- ✅ Gradle 8.2
- ✅ Hilt 2.48.1
- ✅ Compose BOM 2023.10.01
- ✅ Room 2.6.1
- ✅ Java 11 target

## Need More Help?

### Check Android Studio Version
- `Help` → `About` 
- Minimum: Android Studio Hedgehog (2023.1.1) or newer

### Check Gradle JDK
- `File` → `Settings` → `Build Tools` → `Gradle`
- Should be: Java 11 or embedded JDK

### Enable Debug Logging
Add to `gradle.properties`:
```properties
org.gradle.logging.level=debug
```

## KSP Benefits (Why We Use It)

✅ **Faster builds** - 2x faster than Kapt
✅ **Better error messages** - More helpful compiler errors
✅ **No Java compatibility issues** - Works with any JDK version
✅ **Future-proof** - Google's recommended approach
✅ **Better IDE support** - Faster indexing and autocomplete

## Still Having Issues?

1. **Ensure you're using the latest Android Studio**
2. **Check you have stable internet** (for dependency downloads)
3. **Try on a different machine** (to rule out local environment issues)
4. **Check Firebase quota** (if using Remote Config heavily)

The project is configured to work out of the box with KSP, so you should not encounter the Kapt error anymore! 🎉
