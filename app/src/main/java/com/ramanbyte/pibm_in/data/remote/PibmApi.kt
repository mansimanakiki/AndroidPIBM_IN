package com.ramanbyte.pibm_in.data.remote

import com.ramanbyte.pibm_in.data.model.NavigationItem
import retrofit2.http.GET

interface PibmApi {
    
    @GET("navigation-items")
    suspend fun getNavigationItems(): List<NavigationItem>
    
    companion object {
        const val BASE_URL = "https://api.example.com/" // Update with actual API URL
    }
}
