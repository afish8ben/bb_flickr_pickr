package com.bbarrett.flikrpickr.ui.vm

import androidx.lifecycle.*
import com.bbarrett.flikrpickr.data.remote.model.FlickrData
import com.bbarrett.flikrpickr.data.remote.service.FlickrService
import kotlinx.coroutines.launch

class FlickrSearchViewModel : ViewModel() {

    private val TAG = FlickrSearchViewModel::class.java.canonicalName

    private val mFlickrService = FlickrService()

    private val _flickrLiveData = MutableLiveData<FlickrData>()
    val flickLiveData: LiveData<FlickrData>
        get() = _flickrLiveData

    fun searchFlickr(input: String) = viewModelScope.launch {

        val response = mFlickrService.getFlickrSearch(input)

        // Do we got it?
        response.photos?.let {
            _flickrLiveData.postValue(it)
        }

    }

//    fun fetchImages() = viewModelScope.launch {
//
//        val response = mImageListService.getPictureList()
//
//        when (response) {
//            is ApiResponse.Success -> {
//                response.data.let {
//                    _imageLiveData.postValue(it.results)
//                }
//            }
//            is ApiResponse.Error -> {
//                Log.d(TAG, "Error thrown in Image Service")
//                _imageLiveData.postValue(emptyList())
//            }
//            is ApiResponse.Loading -> {
//                // TODO - a loading state
//            }
//        }
//    }

}
