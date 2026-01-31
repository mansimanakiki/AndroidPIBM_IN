package com.ramanbyte.pibm_in.data.local

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ramanbyte.pibm_in.data.model.NavigationItem
import kotlinx.coroutines.flow.Flow

@Dao
interface NavigationDao {
    
    @Query("SELECT * FROM navigation_items ORDER BY `order` ASC")
    fun getAllNavigationItems(): Flow<List<NavigationItem>>
    
    @Query("SELECT * FROM navigation_items ORDER BY `order` ASC")
    fun getNavigationItemsPaging(): PagingSource<Int, NavigationItem>
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(items: List<NavigationItem>)
    
    @Query("DELETE FROM navigation_items")
    suspend fun clearAll()
}
