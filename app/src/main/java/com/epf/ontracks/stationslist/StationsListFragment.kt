package com.epf.ontracks.stationslist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.epf.ontracks.R
import com.epf.ontracks.databinding.StationsListFragmentBinding

class StationsListFragment : Fragment() {

    private lateinit var binding: StationsListFragmentBinding
    private lateinit var viewModel: StationsListViewModel
    private lateinit var viewModelFactory: StationsListViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.stations_list_fragment,container,false)

        val args = StationsListFragmentArgs.fromBundle(requireArguments())
        viewModelFactory = StationsListViewModelFactory(args.line, args.lineType)
        viewModel = ViewModelProvider(this, viewModelFactory).get(StationsListViewModel::class.java)

        binding.lifecycleOwner = this
        binding.stationsListViewModel = viewModel

        val adapter = StationAdapter(StationListener { station ->
            viewModel.navigateToStation(true, station)
        })

        binding.stationsList.adapter = adapter

        viewModel.stations.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.submitList(it)
            }
        })

        viewModel.navigateToStation.observe(viewLifecycleOwner, Observer { navigating ->
            if(navigating && viewModel.station.value != null) {
                this.findNavController().navigate(StationsListFragmentDirections.actionStationsListFragmentToStationFragment(
                    code = viewModel.line.code,
                    type = viewModel.type,
                    station = viewModel.station.value!!,
                    id = 0L
                ))
                viewModel.navigateToStation(false, null)
            }
        })

        return binding.root
    }
}
