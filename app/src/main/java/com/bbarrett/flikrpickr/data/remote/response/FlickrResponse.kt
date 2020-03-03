package com.bbarrett.flikrpickr.data.remote.response

import com.bbarrett.flikrpickr.data.remote.model.FlickrData

/**
 * @author barrett on 3/2/20
 */

data class FlickrResponse (
    var photos: FlickrData?,
    var stat: String
)
