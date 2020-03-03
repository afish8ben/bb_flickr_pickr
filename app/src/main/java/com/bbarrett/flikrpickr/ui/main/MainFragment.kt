package com.bbarrett.flikrpickr.ui.main

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bbarrett.flikrpickr.R
import com.bbarrett.flikrpickr.data.remote.model.PhotoData
import com.bbarrett.flikrpickr.ui.adapters.ImageRecyclerAdapter
import kotlinx.android.synthetic.main.main_fragment.*

class MainFragment : Fragment(), ImageRecyclerAdapter.ImageViewClickListener {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: FlickrSearchViewModel
    private lateinit var mImageViewAdapter: ImageRecyclerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(FlickrSearchViewModel::class.java)
        initializeViewModelListeners()

        viewModel.searchFlickr("Dogs")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mImageViewAdapter = ImageRecyclerAdapter(context, R.layout.adapter_view_flickr_list, this)
        mainRecyclerView.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        mainRecyclerView.adapter = mImageViewAdapter
    }

    override fun onImageClicked(imageData: PhotoData?) {
        // TODO("not implemented")
    }

    private fun initializeViewModelListeners() {
        viewModel.flickLiveData.observe(viewLifecycleOwner, Observer { flickrResponse ->

            if (!flickrResponse.photoList.isNullOrEmpty())
                mImageViewAdapter.setData(flickrResponse.photoList)
        })
    }

}
