package com.ramanbyte.pibm_in.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ramanbyte.pibm_in.data.model.NavigationItem

@Database(
    entities = [NavigationItem::class],
    version = 1,
    exportSchema = false
)
abstract class PibmDatabase : RoomDatabase() {
    abstract fun navigationDao(): NavigationDao
}
