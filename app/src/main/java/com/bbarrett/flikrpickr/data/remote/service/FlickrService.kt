package com.bbarrett.flikrpickr.data.remote.service

import com.bbarrett.flikrpickr.data.ApiFactory

/**
 * @author barrett on 3/2/20
 */

class FlickrService {

    val client = ApiFactory.flickrApi

    suspend fun getFlickrSearch(text: String) = client.getFlickrSearch(text)

}