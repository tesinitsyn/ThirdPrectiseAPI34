package com.tesinitsyn.thirdprectiseapi34.ui.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.setFragmentResult
import androidx.lifecycle.ViewModelProvider
import com.tesinitsyn.thirdprectiseapi34.data.item.Item
import com.tesinitsyn.thirdprectiseapi34.databinding.FragmentPopUpBinding
import com.tesinitsyn.thirdprectiseapi34.ui.viewModel.ItemViewModel

class PopUpFragment : DialogFragment() {

    private lateinit var binding: FragmentPopUpBinding
    private lateinit var mItemVM: ItemViewModel;


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPopUpBinding.inflate(inflater, container, false)
        binding.addButtonInPopUp.setOnClickListener {
            val tempName = binding.inputItem.text.toString()
            if(tempName.isEmpty()){
                Toast.makeText(requireContext(), "Write smth man", Toast.LENGTH_LONG).show()
            }else{
                Log.d("idk", tempName)
                setFragmentResult("requestKey", bundleOf("bundleKey" to tempName))
            }
            dismiss()
        }
        return binding.root
    }
}