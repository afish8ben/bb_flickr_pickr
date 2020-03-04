package com.bbarrett.flikrpickr.data.storage.table

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * @author barrett on 3/3/20
 */

@Entity(tableName = "favorites")
class Favorite(
    @PrimaryKey(autoGenerate = true)
    val id: Int,

    @ColumnInfo(name = "title")
    val title: String,

    @ColumnInfo(name = "imageUrl")
    val imageUrl: String
)
