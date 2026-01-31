package com.ramanbyte.pibm_in.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "navigation_items")
data class NavigationItem(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String,
    val icon: String,
    val url: String,
    val order: Int
)

data class BannerItem(
    val _id: String?,
    val _imageUrl: String?,
    val _title: String?,
    val _subtitle: String?
) {
    val id: String
        get() = _id ?: ""
    val imageUrl: String
        get() = _imageUrl ?: ""
    val title: String
        get() = _title ?: ""
    val subtitle: String
        get() = _subtitle ?: ""
}

data class PibmInfo(
    val _title: String?,
    val _description: String?,
    val _highlights: List<String>?
) {
    val title: String
        get() = _title.orEmpty()

    val description: String
        get() = _description.orEmpty()

    val highlights: List<String>
        get() = _highlights ?: emptyList()
}

