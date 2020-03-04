package com.bbarrett.flikrpickr.data.storage.dao

import androidx.room.*
import com.bbarrett.flikrpickr.data.storage.table.Favorite

/**
 * @author barrett on 3/3/20
 */

@Dao
interface FavoriteDao {

    @Query("Select * from favorites")
    fun getFavorites(): List<Favorite>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFavorites(favorite: Favorite)

    @Delete
    fun deleteFavorite(favorite: Favorite)

}
