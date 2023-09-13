package com.tesinitsyn.thirdprectiseapi34.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.tesinitsyn.thirdprectiseapi34.databinding.FragmentListBinding
import com.tesinitsyn.thirdprectiseapi34.ui.adapters.ListAdapter
import com.tesinitsyn.thirdprectiseapi34.ui.viewModel.ItemViewModel

class ListFragment : Fragment() {

    private lateinit var binding: FragmentListBinding
    private lateinit var mItemVM: ItemViewModel;


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentListBinding.inflate(inflater, container, false)
        mItemVM = ViewModelProvider(this)[ItemViewModel::class.java]

        val adapter = ListAdapter()

        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        mItemVM.getAllData.observe(viewLifecycleOwner, Observer { item ->
            adapter.setData(item)
        })
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.addBtn.setOnClickListener {
            val showPopUp = PopUpFragment()
            showPopUp.show((activity as AppCompatActivity).supportFragmentManager, "showPopUp")
        }
    }
}