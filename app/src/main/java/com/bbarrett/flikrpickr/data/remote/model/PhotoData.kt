package com.bbarrett.flikrpickr.data.remote.model

/**
 * @author barrett on 3/2/20
 */

data class PhotoData (
    var id: String,
    var owner: String,
    var secret: String,
    var server: String,
    var farm: Long,
    var title: String,
    var ispublic: Int,
    var isfriend: Int,
    var isfamily: Int) {

    fun getImageUrl(): String {
        return "https://farm${farm}.staticflickr.com/${server}/${id}_${secret}.jpg"
    }

}
