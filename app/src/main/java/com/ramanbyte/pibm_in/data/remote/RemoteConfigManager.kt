package com.ramanbyte.pibm_in.data.remote

import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.ramanbyte.pibm_in.data.model.BannerItem
import com.ramanbyte.pibm_in.data.model.NavigationItem
import com.ramanbyte.pibm_in.data.model.PibmInfo
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteConfigManager @Inject constructor(
    private val remoteConfig: FirebaseRemoteConfig,
    private val gson: Gson
) {
    
    companion object {
        private const val BANNERS_KEY = "banners"
        private const val PIBM_INFO_KEY = "pibm_info"
        private const val NAVIGATION_ITEMS_KEY = "navigation_items"
        private const val FETCH_INTERVAL = 3600L // 1 hour
    }
    
    init {
        val configSettings = FirebaseRemoteConfigSettings.Builder()
            .setMinimumFetchIntervalInSeconds(FETCH_INTERVAL)
            .build()
        remoteConfig.setConfigSettingsAsync(configSettings)
        
        // Set default values
        remoteConfig.setDefaultsAsync(
            mapOf(
                BANNERS_KEY to "[]",
                PIBM_INFO_KEY to "{}",
                NAVIGATION_ITEMS_KEY to getDefaultNavigationItemsJson()
            )
        )
    }
    
    suspend fun fetchAndActivate(): Boolean {
        return try {
            remoteConfig.fetchAndActivate().await()
        } catch (e: Exception) {
            false
        }
    }
    
    fun getBanners(): List<BannerItem> {
        val json = remoteConfig.getString(BANNERS_KEY)
        return try {
            val type = object : TypeToken<List<BannerItem>>() {}.type
            gson.fromJson(json, type)
        } catch (e: Exception) {
            emptyList()
        }
    }
    
    fun getPibmInfo(): PibmInfo {
        val json = remoteConfig.getString(PIBM_INFO_KEY)
        return try {
            gson.fromJson(json, PibmInfo::class.java)
        } catch (e: Exception) {
            getDefaultPibmInfo()
        }
    }
    
    fun getNavigationItems(): List<NavigationItem> {
        val json = remoteConfig.getString(NAVIGATION_ITEMS_KEY)
        return try {
            val type = object : TypeToken<List<NavigationItem>>() {}.type
            gson.fromJson(json, type)
        } catch (e: Exception) {
            getDefaultNavigationItems()
        }
    }
    
    private fun getDefaultNavigationItems(): List<NavigationItem> {
        return listOf(
            NavigationItem(1, "Admissions", "school", "https://pibm.in/admissions", 1),
            NavigationItem(2, "Courses", "book", "https://pibm.in/courses", 2),
            NavigationItem(3, "Placements", "work", "https://pibm.in/placements", 3),
            NavigationItem(4, "Faculty", "people", "https://pibm.in/faculty", 4),
            NavigationItem(5, "Campus", "location_city", "https://pibm.in/campus", 5),
            NavigationItem(6, "Contact Us", "contact_mail", "https://pibm.in/contact", 6)
        )
    }
    
    private fun getDefaultNavigationItemsJson(): String {
        return gson.toJson(getDefaultNavigationItems())
    }
    
    private fun getDefaultPibmInfo(): PibmInfo {
        return PibmInfo(
            _title = "Pune Institute of Business Management",
            _description = "PIBM is one of India's premier business schools, offering world-class management education.",
            _highlights = listOf(
                "AICTE Approved",
                "Industry-Integrated Curriculum",
                "100% Placement Assistance",
                "State-of-the-art Infrastructure"
            )
        )
    }
}
