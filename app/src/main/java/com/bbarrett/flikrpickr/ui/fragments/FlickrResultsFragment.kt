package com.bbarrett.flikrpickr.ui.fragments

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bbarrett.flikrpickr.R
import com.bbarrett.flikrpickr.data.remote.model.PhotoData
import com.bbarrett.flikrpickr.ui.adapters.ImageRecyclerAdapter
import com.bbarrett.flikrpickr.ui.vm.FlickrSearchViewModel
import kotlinx.android.synthetic.main.main_fragment.*
import timber.log.Timber

class FlickrResultsFragment : Fragment(), ImageRecyclerAdapter.ImageViewClickListener {

    companion object {
        fun newInstance() = FlickrResultsFragment()
    }

    private lateinit var mFlickrViewModel: FlickrSearchViewModel
    private lateinit var mImageViewAdapter: ImageRecyclerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mFlickrViewModel = ViewModelProviders.of(this).get(FlickrSearchViewModel::class.java)
        initializeViewModelListeners()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mImageViewAdapter = ImageRecyclerAdapter(context, R.layout.adapter_view_flickr_list, this)
        mainRecyclerView.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        mainRecyclerView.adapter = mImageViewAdapter
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_main, menu)

        val searchView  = menu.findItem(R.id.action_search).actionView as SearchView
        searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                Timber.d("The little magnifying glass was clicked.  QUERY = ${query ?: ""}")

                if (!query.isNullOrEmpty()) {
                    mFlickrViewModel.searchFlickr(query)
                } else {
                    // TODO Load the Blank Screen
                }

                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                Timber.d("Search Input! ${newText ?: ""}")
                return false
            }
        })

    }

    override fun onImageClicked(imageData: PhotoData?) {
        // TODO("not implemented")
    }

    private fun initializeViewModelListeners() {
        mFlickrViewModel.flickLiveData.observe(viewLifecycleOwner, Observer { flickrResponse ->

            if (!flickrResponse.photoList.isNullOrEmpty())
                mImageViewAdapter.setData(flickrResponse.photoList)
        })
    }

}
