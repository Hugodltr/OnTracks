package com.epf.ontracks.overview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.epf.ontracks.MainActivity
import com.epf.ontracks.R
import com.epf.ontracks.database.FavoriteDatabase
import com.epf.ontracks.databinding.OverviewFragmentBinding
import com.epf.ontracks.stationslist.Station

class OverviewFragment : Fragment() {

    private lateinit var binding : OverviewFragmentBinding
    private lateinit var viewModel: OverviewViewModel
    private lateinit var viewModelFactory: OverviewViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        (activity as MainActivity).supportActionBar?.title = "On Tracks"

        val application = requireNotNull(this.activity).application
        val dataSource = FavoriteDatabase.getInstance(application).favoriteDatabaseDao

        binding = DataBindingUtil.inflate(inflater, R.layout.overview_fragment,container,false)
        viewModelFactory = OverviewViewModelFactory(dataSource)
        viewModel = ViewModelProvider(this, viewModelFactory).get(OverviewViewModel::class.java)

        binding.lifecycleOwner = this
        binding.overviewViewModel = viewModel

        val adapter = FavStationAdapter(FavStationListener { station ->
            viewModel.navigateToStation(true, station)
        })

        viewModel.favorites.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.submitList(it)
            }
        })

        binding.favoriteList.adapter = adapter

        viewModel.navigateToStation.observe(viewLifecycleOwner, Observer { navigating ->
            if(navigating && viewModel.station.value != null) {
                this.findNavController().navigate(OverviewFragmentDirections.actionOverviewFragmentToStationFragment(
                    code = viewModel.station.value!!.code,
                    type = viewModel.station.value!!.type,
                    station = Station(viewModel.station.value!!.stationName, viewModel.station.value!!.stationSlug),
                    id = viewModel.station.value!!.stationId
                ))

                viewModel.navigateToStation(false, null)
            }
        })


        viewModel.navigateToLines.observe(viewLifecycleOwner, Observer { navigating ->
            if(navigating) {
                this.findNavController().navigate(OverviewFragmentDirections.actionOverviewFragmentToLinesListFragment())
                viewModel.navigateToLines(false)
            }
        })

        viewModel.navigateToScanner.observe(viewLifecycleOwner, Observer {
            this.findNavController().navigate(OverviewFragmentDirections.actionOverviewFragmentToScannerFragment())
            viewModel.navigateToLines(false)
        })

        return binding.root
    }
}
