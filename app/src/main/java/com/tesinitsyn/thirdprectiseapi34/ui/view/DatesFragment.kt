package com.tesinitsyn.thirdprectiseapi34.ui.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.tesinitsyn.thirdprectiseapi34.data.item.Item
import com.tesinitsyn.thirdprectiseapi34.databinding.FragmentDatesBinding
import com.tesinitsyn.thirdprectiseapi34.ui.adapters.ListAdapter
import java.io.File

class DatesFragment : Fragment() {
    private lateinit var binding: FragmentDatesBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDatesBinding.inflate(inflater, container, false)

        val adapter = ListAdapter()


        var tempList: MutableList<Item> = ArrayList()

        val file = File(
            requireContext().getExternalFilesDir("Pictures/CameraX-Image/"),
            "Date.txt"
        )

        binding.rewriteBtn.setOnClickListener {
            file.writeText("")
            file.forEachLine { tempList.add(Item(0, it)) }
            adapter.setData(tempList)
            binding.recyclerView.adapter = adapter
        }
        file.forEachLine { tempList.add(Item(0, it)) }




        Log.d("array", tempList.toString())
        adapter.setData(tempList)
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        return binding.root
    }
}