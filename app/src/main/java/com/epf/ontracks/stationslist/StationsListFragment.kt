package com.epf.ontracks.stationslist

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.epf.ontracks.R
import com.epf.ontracks.databinding.StationsListFragmentBinding

class StationsListFragment : Fragment() {

    private lateinit var binding: StationsListFragmentBinding

    companion object {
        fun newInstance() = StationsListFragment()
    }

    private lateinit var viewModel: StationsListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.stations_list_fragment,container,false)
        viewModel = ViewModelProviders.of(this).get(StationsListViewModel::class.java)

        binding.stationsListViewModel = viewModel

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(StationsListViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
