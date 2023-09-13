package com.tesinitsyn.thirdprectiseapi34.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import com.tesinitsyn.thirdprectiseapi34.data.Item
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
        mItemVM = ViewModelProvider(this)[ItemViewModel::class.java]
        binding.addButtonInPopUp.setOnClickListener {
            val tempName = binding.inputItem.text.toString()
            if(tempName.isEmpty()){
                Toast.makeText(requireContext(), "Write smth man", Toast.LENGTH_LONG).show()
            }else{
                val item = Item(0, tempName)
                mItemVM.addItem(item)
            }
            dismiss()
        }
        return binding.root
    }
}