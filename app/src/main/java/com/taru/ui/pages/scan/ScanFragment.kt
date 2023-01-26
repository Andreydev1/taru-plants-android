package com.taru.ui.pages.scan

import android.Manifest
import android.Manifest.permission.CAMERA
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.camera.core.CameraSelector
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import com.google.common.util.concurrent.ListenableFuture
import com.permissionx.guolindev.PermissionX
import com.taru.databinding.NavHomeFragmentBinding
import com.taru.databinding.ScanFragmentBinding
import com.taru.ui.base.FragmentBase
import com.taru.ui.pages.nav.home.NavHomeViewModel
import com.taru.ui.pages.nav.home.category.HomeCategoryAdapter
import dagger.hilt.android.AndroidEntryPoint

/**
 * Created by Niraj on 11-01-2023.
 */
@AndroidEntryPoint
class ScanFragment : FragmentBase(true) {

    private val mViewModel: ScanViewModel by viewModels()
    private lateinit var vBinding: ScanFragmentBinding


    private lateinit var cameraProviderFuture : ListenableFuture<ProcessCameraProvider>
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        vBinding =
            ScanFragmentBinding.inflate(inflater, container, false).apply {
                bViewModel = mViewModel
            }
        return vBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        vBinding.lifecycleOwner = this.viewLifecycleOwner
        lifecycleScope.launchWhenCreated {
            getPermission()
        }
    }

    private fun getPermission(){
        PermissionX.init(requireActivity())
            .permissions(Manifest.permission.CAMERA)
            .onExplainRequestReason { scope, deniedList ->
                scope.showRequestReasonDialog(deniedList, "Core fundamental are based on these permissions", "OK", "Cancel")
            }
            .request { allGranted, grantedList, deniedList ->
                if (allGranted) {

                    Toast.makeText(requireContext(), "All permissions are granted", Toast.LENGTH_LONG).show()
                setupCamera()
                } else {
                    Toast.makeText(requireContext(), "These permissions are denied: $deniedList", Toast.LENGTH_LONG).show()
                }
            }
    }

    private fun setupCamera(){
        cameraProviderFuture = ProcessCameraProvider.getInstance(requireContext())
        cameraProviderFuture.addListener(Runnable {
            val cameraProvider = cameraProviderFuture.get()
            bindPreview(cameraProvider)
        }, ContextCompat.getMainExecutor(requireContext()))
    }

    private fun bindPreview(cameraProvider : ProcessCameraProvider) {
        var preview : Preview = Preview.Builder()
            .build()

        var cameraSelector : CameraSelector = CameraSelector.Builder()
            .requireLensFacing(CameraSelector.LENS_FACING_BACK)
            .build()

        preview.setSurfaceProvider(vBinding.previewView.getSurfaceProvider())

        var camera = cameraProvider.bindToLifecycle(this as LifecycleOwner, cameraSelector, preview)

    }
}