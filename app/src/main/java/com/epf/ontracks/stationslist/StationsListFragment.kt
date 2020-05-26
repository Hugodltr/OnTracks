package com.epf.ontracks.stationslist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.epf.ontracks.R
import com.epf.ontracks.databinding.StationsListFragmentBinding

class StationsListFragment : Fragment() {

    private lateinit var binding: StationsListFragmentBinding
    private lateinit var viewModel: StationsListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.stations_list_fragment,container,false)
        viewModel = ViewModelProvider(this).get(StationsListViewModel::class.java)

        binding.lifecycleOwner = this

        binding.stationsListViewModel = viewModel

        return binding.root
    }
}
