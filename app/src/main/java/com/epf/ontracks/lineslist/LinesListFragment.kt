package com.epf.ontracks.lineslist

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil

import com.epf.ontracks.R
import com.epf.ontracks.databinding.LinesListFragmentBinding

class LinesListFragment : Fragment() {

    private lateinit var binding : LinesListFragmentBinding

    companion object {
        fun newInstance() = LinesListFragment()
    }

    private lateinit var viewModel: LinesListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.lines_list_fragment,container,false)
        viewModel = ViewModelProviders.of(this).get(LinesListViewModel::class.java)

        binding.linesListViewModel = viewModel

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(LinesListViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
