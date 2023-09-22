package com.tesinitsyn.thirdprectiseapi34.ui.view

import android.R.attr.height
import android.R.attr.width
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.media.MediaScannerConnection
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asAndroidBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.core.graphics.drawable.toDrawable
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import com.bumptech.glide.Glide
import com.bumptech.glide.request.FutureTarget
import com.bumptech.glide.request.target.BitmapImageViewTarget
import com.tesinitsyn.thirdprectiseapi34.databinding.FragmentListBinding
import com.tesinitsyn.thirdprectiseapi34.ui.viewModel.ItemViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.File
import java.io.FileOutputStream
import java.net.URL


class ListFragment : Fragment() {

    private lateinit var binding: FragmentListBinding
    private lateinit var mItemVM: ItemViewModel;


    @OptIn(DelicateCoroutinesApi::class)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentListBinding.inflate(inflater, container, false)
        var result: String?

        binding.addBtn.setOnClickListener {
            val showPopUp = PopUpFragment()
            showPopUp.show((activity as AppCompatActivity).supportFragmentManager, "showPopUp")
        }

        setFragmentResultListener("requestKey") { requestKey, bundle ->
            result = bundle.getString("bundleKey").toString()
            Log.d("url", result!!)
            GlobalScope.launch(Dispatchers.IO) {
                val url = URL(result).openStream()
                val image = BitmapFactory.decodeStream(url)

                withContext(Dispatchers.Main) {
                    binding.imageView.setImageBitmap(image)
                }
            }

            GlobalScope.launch(Dispatchers.IO){
                val url = URL(result).openStream()
                val outputDir =
                    Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)
                val outputFile = File(outputDir, "1.jpg")
                val outputStream = FileOutputStream(outputFile)
                val buffer = ByteArray(4 * 1024)
                var bytesRead: Int
                while (url.read(buffer).also { bytesRead = it } != -1) {
                    outputStream.write(buffer, 0, bytesRead)
                }
                url.close()
                outputStream.close()
                withContext(Dispatchers.Main){
                    MediaScannerConnection.scanFile(
                        requireContext(),
                        arrayOf(outputFile.toString()),
                        null,
                        null
                    )
                }
            }
        }

        return binding.root
    }

}