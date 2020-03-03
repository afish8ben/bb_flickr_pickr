package com.bbarrett.flikrpickr.data.remote.api

import com.bbarrett.flikrpickr.data.remote.ApiResponse
import com.bbarrett.flikrpickr.data.remote.response.FlickrResponse
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * @author barrett on 3/2/20
 */

interface FlickrApi {

    /*
     * @description: Flickr's API is interesting, you could make your basic CRUD methods for interacting
     * with the API, but for this exercise I am going to hardcode the method for search within the
     * function itself.
     */
    @GET("rest/?method=flickr.photos.search")
    suspend fun getFlickrSearch(@Query("text") text: String): FlickrResponse

}
