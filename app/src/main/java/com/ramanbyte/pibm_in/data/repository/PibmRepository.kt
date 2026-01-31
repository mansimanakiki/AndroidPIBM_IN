package com.ramanbyte.pibm_in.data.repository

import com.ramanbyte.pibm_in.data.local.NavigationDao
import com.ramanbyte.pibm_in.data.model.BannerItem
import com.ramanbyte.pibm_in.data.model.NavigationItem
import com.ramanbyte.pibm_in.data.model.PibmInfo
import com.ramanbyte.pibm_in.data.remote.RemoteConfigManager
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PibmRepository @Inject constructor(
    private val navigationDao: NavigationDao,
    private val remoteConfigManager: RemoteConfigManager
) {
    
    val navigationItems: Flow<List<NavigationItem>> = navigationDao.getAllNavigationItems()
    
    suspend fun refreshData() {
        remoteConfigManager.fetchAndActivate()
        val items = remoteConfigManager.getNavigationItems()
        navigationDao.clearAll()
        navigationDao.insertAll(items)
    }
    
    fun getBanners(): List<BannerItem> {
        return remoteConfigManager.getBanners()
    }
    
    fun getPibmInfo(): PibmInfo {
        return remoteConfigManager.getPibmInfo()
    }
}
