package com.wallet.pools.presentation.screen.scanQR

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.budiyev.android.codescanner.AutoFocusMode
import com.budiyev.android.codescanner.CodeScanner
import com.budiyev.android.codescanner.DecodeCallback
import com.budiyev.android.codescanner.ErrorCallback
import com.budiyev.android.codescanner.ScanMode
import com.google.zxing.BinaryBitmap
import com.google.zxing.LuminanceSource
import com.google.zxing.MultiFormatReader
import com.google.zxing.RGBLuminanceSource
import com.google.zxing.Reader
import com.google.zxing.common.HybridBinarizer
import com.wallet.pools.R
import com.wallet.pools.base.BaseFragment
import com.wallet.pools.base.BaseViewModel
import com.wallet.pools.databinding.FragmentLoginBinding
import com.wallet.pools.databinding.FragmentScanQrCodeBinding
import com.wallet.pools.extenstion.showToast
import com.wallet.pools.presentation.screen.login.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.io.FileNotFoundException
import java.io.InputStream


@AndroidEntryPoint
class ScanQRFragment : BaseFragment<FragmentScanQrCodeBinding, BaseViewModel>() {


    override val viewModel: LoginViewModel by viewModels()


    override fun getViewBinding(): FragmentScanQrCodeBinding =
        FragmentScanQrCodeBinding.inflate(layoutInflater)

    override fun onBackFragment() {
        findNavController().navigateUp()
    }
    private lateinit var scannerQrCode : CodeScanner
    private val changeImage =
        registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) {
            if (it.resultCode == Activity.RESULT_OK) {
                val data = it.data
                val imgUri = data?.data
                try {
                    val inputStream: InputStream? = requireContext().contentResolver.openInputStream(imgUri!!)
                    val bitmap: Bitmap? = BitmapFactory.decodeStream(inputStream)
                    val text = "Result Scan From Image : ${scanQRImage(bitmap!!)}"
                    binding.tvResultImage.text = text
                } catch (e: FileNotFoundException) {
                    e.printStackTrace()
                }
            }
        }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initViewStateChange()
    }


    private fun initViewStateChange() {


    }

    private fun initView() {
        binding.apply {

        }
        checkStatusPermission()
        onClickView()


    }

    private fun onClickView() {
       binding.apply {
           tvFlashButton.setOnClickListener {
               scannerQrCode.isFlashEnabled = !scannerQrCode.isFlashEnabled
           }
           binding.imageQr.setOnClickListener {
               val pickImg = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
               changeImage.launch(pickImg)

           }
       }
    }

    private fun scanQRImage(bMap: Bitmap): String? {
        var contents: String? = null
        val intArray = IntArray(bMap.width * bMap.height)

        bMap.getPixels(intArray, 0, bMap.width, 0, 0, bMap.width, bMap.height)
        val source: LuminanceSource = RGBLuminanceSource(bMap.width, bMap.height, intArray)
        val bitmap = BinaryBitmap(HybridBinarizer(source))
        val reader: Reader = MultiFormatReader()
        try {
            val result = reader.decode(bitmap)
            contents = result.text
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return contents
    }
    private fun scanning() {
        scannerQrCode = CodeScanner(requireContext(),binding.scannerQr)
        scannerQrCode.camera = CodeScanner.CAMERA_BACK
        scannerQrCode.formats = CodeScanner.ALL_FORMATS
        scannerQrCode.autoFocusMode = AutoFocusMode.SAFE
        scannerQrCode.scanMode = ScanMode.SINGLE
        scannerQrCode.isAutoFocusEnabled = true
        scannerQrCode.decodeCallback = DecodeCallback {
            requireActivity().runOnUiThread {
                val text = "Result Scan : ${it.text}"
                binding.tvResultScan.text = text
            }
        }

        scannerQrCode.errorCallback = ErrorCallback {
            requireActivity().runOnUiThread {
                requireActivity().showToast("Camera initialization error : ${it.message}")
            }
        }
        binding.scannerQr.setOnClickListener {
            scannerQrCode.startPreview()
        }
    }
    private fun checkStatusPermission() {
        // Check if all permissions are granted
        val notGrantedPermissions =  Manifest.permission.CAMERA.filter {
            ContextCompat.checkSelfPermission(
                requireContext(), it.toString()
            ) != PackageManager.PERMISSION_GRANTED
        }

        if (notGrantedPermissions.isEmpty()) {
            scanning()
        } else {
            requestPermissionRecordLauncher.launch(arrayOf(Manifest.permission.CAMERA))

        }
    }

    private val requestPermissionRecordLauncher = registerForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { permissions ->
        val granted = permissions.entries.all {
            it.value
        }
        if (granted) {
            scanning()
        } else {
            val imagePermissions =  Manifest.permission.CAMERA
            for (permission in imagePermissions) {
                if (ActivityCompat.shouldShowRequestPermissionRationale(
                        requireActivity(),
                        permission.toString()
                    )
                ) {
                    requireActivity().showToast("Permission camera denied.")
                    return@registerForActivityResult

                }
            }
        }
    }
    override fun onResume() {
        super.onResume()
        if(::scannerQrCode.isInitialized){
            scannerQrCode.startPreview()
        }
    }

    override fun onPause() {
        if(::scannerQrCode.isInitialized){
            scannerQrCode.releaseResources()
        }
        super.onPause()
    }
}