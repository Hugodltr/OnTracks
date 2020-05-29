package com.epf.ontracks.lineslist

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
import com.epf.ontracks.databinding.LinesListFragmentBinding

class LinesListFragment : Fragment() {

    private lateinit var binding : LinesListFragmentBinding
    private lateinit var viewModel: LinesListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        (activity as MainActivity).supportActionBar?.title = "Lignes"

        binding = DataBindingUtil.inflate(inflater, R.layout.lines_list_fragment,container,false)
        viewModel = ViewModelProvider(this).get(LinesListViewModel::class.java)

        binding.lifecycleOwner = this
        binding.linesListViewModel = viewModel

        val adapter = LineAdapter(LineListener { line, lineType ->
            viewModel.navigateToStations(true, line, lineType)
        })

        binding.linesList.adapter = adapter

        viewModel.metros.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.submitList(it)
            }
        })

        viewModel.navigateToStations.observe(viewLifecycleOwner, Observer { navigating ->
            if(navigating && viewModel.line.value != null && viewModel.lineType.value != null) {
                this.findNavController().navigate(LinesListFragmentDirections.actionLinesListFragmentToStationsListFragment(viewModel.line.value!!, viewModel.lineType.value!!))
                viewModel.navigateToStations(false, null, null)
            }
        })

        return binding.root
    }

}
