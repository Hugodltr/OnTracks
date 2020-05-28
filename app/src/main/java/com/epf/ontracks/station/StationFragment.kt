package com.epf.ontracks.station

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.epf.ontracks.R
import com.epf.ontracks.database.FavoriteDatabase
import com.epf.ontracks.databinding.StationFragmentBinding

class StationFragment : Fragment() {

    private lateinit var binding: StationFragmentBinding
    private lateinit var viewModel: StationViewModel
    private lateinit var viewModelFactory: StationViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val args = StationFragmentArgs.fromBundle(requireArguments())
        val application = requireNotNull(this.activity).application
        val dataSource = FavoriteDatabase.getInstance(application).favoriteDatabaseDao

        binding = DataBindingUtil.inflate(inflater, R.layout.station_fragment,container,false)
        viewModelFactory = StationViewModelFactory(args.code, args.station, args.type, dataSource, args.id)
        viewModel = ViewModelProvider(this, viewModelFactory).get(StationViewModel::class.java)

        binding.lifecycleOwner = this
        binding.stationViewModel = viewModel
        binding.stationName.text = viewModel.station.name

        val adapter = ScheduleAdapter()
        binding.schedulesList.adapter = adapter

        viewModel.schedules.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.submitList(it)
            }
        })

        return binding.root
    }
}
