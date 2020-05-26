package com.epf.ontracks.lineslist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider

import com.epf.ontracks.R
import com.epf.ontracks.databinding.LinesListFragmentBinding

class LinesListFragment : Fragment() {

    private lateinit var binding : LinesListFragmentBinding
    private lateinit var viewModel: LinesListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.lines_list_fragment,container,false)
        viewModel = ViewModelProvider(this).get(LinesListViewModel::class.java)

        binding.lifecycleOwner = this
        binding.linesListViewModel = viewModel

        val adapter = LineAdapter()
        binding.linesList.adapter = adapter

        viewModel.metros.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.submitList(it)
            }
        })

        return binding.root
    }

}
