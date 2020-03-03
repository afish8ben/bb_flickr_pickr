package com.bbarrett.flikrpickr.data.remote.model

import com.google.gson.annotations.SerializedName

/**
 * @author barrett on 3/2/20
 */

class FlickrData (
    var page: Int,
    var pages: String,
    var perpage: Int,
    var total: String,
    @SerializedName("photo") var photoList: List<PhotoData>
)
