package com.capiro.composables.qr_scanner

import android.Manifest
import android.content.pm.PackageManager
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.QrCodeScanner
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.core.content.ContextCompat
import com.capiro.composables.athomic_composables.buttons.ButtonIconCapiro
import com.capiro.composables.theme.BlackCapiro
import com.capiro.composables.theme.WhiteCapiro
import com.google.mlkit.vision.barcode.BarcodeScannerOptions
import com.google.mlkit.vision.barcode.BarcodeScanning
import com.google.mlkit.vision.barcode.common.Barcode
import com.google.mlkit.vision.common.InputImage
import java.util.concurrent.Executors

private const val DEFAULT_SCANNING_TEXT = "Escaneando…"
private const val DEFAULT_PERMISSION_DENIED_TEXT = "Se necesita permiso de cámara para escanear"

/**
 * A self-contained QR / barcode scanner dialog.
 *
 * Opens the back camera and reports the first code it reads through [onQrCodeScanned]. Camera
 * permission is requested automatically the first time the dialog opens; if the user denies it, a
 * toast is shown and [onDismiss] is invoked. The camera is released automatically when the dialog
 * leaves the composition.
 *
 * State is hoisted (Capiro UI convention): the dialog renders nothing while [isOpen] is `false`.
 * Remember to close it from [onQrCodeScanned] (e.g. set your `isOpen` flag back to `false`).
 *
 * Requires the `android.permission.CAMERA` permission, which this library already declares in its
 * manifest (it is merged into the consuming app automatically).
 *
 * ```
 * var scannerOpen by remember { mutableStateOf(false) }
 *
 * ButtonCapiro(text = "Escanear", onClick = { scannerOpen = true })
 *
 * QrScannerDialogCapiro(
 *     isOpen = scannerOpen,
 *     onQrCodeScanned = { code ->
 *         scannerOpen = false
 *         // use the scanned code
 *     },
 *     onDismiss = { scannerOpen = false },
 * )
 * ```
 *
 * @param isOpen Whether the scanner dialog is shown.
 * @param onQrCodeScanned Invoked with the raw value of the first code detected.
 * @param onDismiss Invoked when the dialog is dismissed (tap outside, or permission denied).
 * @param modifier Modifier applied to the camera preview container.
 * @param onlyQrCodes When `true` only QR codes are detected; when `false` any supported barcode is.
 * @param scanningText Hint text overlaid at the bottom of the preview.
 * @param permissionDeniedText Toast message shown when camera permission is denied.
 */
@Composable
fun QrScannerDialogCapiro(
    isOpen: Boolean,
    onQrCodeScanned: (String) -> Unit,
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier,
    onlyQrCodes: Boolean = true,
    scanningText: String = DEFAULT_SCANNING_TEXT,
    permissionDeniedText: String = DEFAULT_PERMISSION_DENIED_TEXT,
) {
    val context = LocalContext.current

    var hasCameraPermission by remember {
        mutableStateOf(
            ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA) ==
                PackageManager.PERMISSION_GRANTED,
        )
    }

    val permissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission(),
    ) { granted ->
        hasCameraPermission = granted
        if (!granted) {
            Toast.makeText(context, permissionDeniedText, Toast.LENGTH_SHORT).show()
            onDismiss()
        }
    }

    // Request permission the first time the dialog is asked to open without it.
    LaunchedEffect(isOpen) {
        if (isOpen && !hasCameraPermission) {
            permissionLauncher.launch(Manifest.permission.CAMERA)
        }
    }

    if (isOpen && hasCameraPermission) {
        CameraPreviewDialog(
            modifier = modifier,
            scanningText = scanningText,
            onlyQrCodes = onlyQrCodes,
            onDismiss = onDismiss,
            onScanned = onQrCodeScanned,
        )
    }
}

/**
 * A ready-to-use scan button that manages the scanner dialog for you — the easiest way to add QR
 * scanning to a screen. Drop it in and handle the result:
 *
 * ```
 * QrScannerButtonCapiro(onQrCodeScanned = { code -> /* use the code */ })
 * ```
 *
 * @param onQrCodeScanned Invoked with the raw value of the scanned code (the dialog closes first).
 * @param modifier Modifier applied to the button.
 * @param label Optional label displayed under the scan icon.
 * @param onlyQrCodes When `true` only QR codes are detected; when `false` any supported barcode is.
 * @param scanningText Hint text overlaid at the bottom of the preview.
 * @param permissionDeniedText Toast message shown when camera permission is denied.
 */
@Composable
fun QrScannerButtonCapiro(
    onQrCodeScanned: (String) -> Unit,
    modifier: Modifier = Modifier,
    label: String? = null,
    onlyQrCodes: Boolean = true,
    scanningText: String = DEFAULT_SCANNING_TEXT,
    permissionDeniedText: String = DEFAULT_PERMISSION_DENIED_TEXT,
) {
    var isOpen by remember { mutableStateOf(false) }

    ButtonIconCapiro(
        modifier = modifier,
        image = Icons.Filled.QrCodeScanner,
        onClick = { isOpen = true },
        label = label,
    )

    QrScannerDialogCapiro(
        isOpen = isOpen,
        onQrCodeScanned = {
            isOpen = false
            onQrCodeScanned(it)
        },
        onDismiss = { isOpen = false },
        onlyQrCodes = onlyQrCodes,
        scanningText = scanningText,
        permissionDeniedText = permissionDeniedText,
    )
}

@Composable
private fun CameraPreviewDialog(
    modifier: Modifier,
    scanningText: String,
    onlyQrCodes: Boolean,
    onDismiss: () -> Unit,
    onScanned: (String) -> Unit,
) {
    val lifecycleOwner = LocalLifecycleOwner.current
    var scanned by remember { mutableStateOf(false) }
    val cameraExecutor = remember { Executors.newSingleThreadExecutor() }
    val cameraProviderHolder = remember { mutableStateOf<ProcessCameraProvider?>(null) }

    // Release the camera and executor when the dialog leaves the composition.
    DisposableEffect(Unit) {
        onDispose {
            cameraProviderHolder.value?.unbindAll()
            cameraExecutor.shutdown()
        }
    }

    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(usePlatformDefaultWidth = false),
    ) {
        Box(
            modifier = modifier
                .fillMaxWidth(0.9f)
                .height(450.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(BlackCapiro),
            contentAlignment = Alignment.Center,
        ) {
            AndroidView(
                modifier = Modifier
                    .fillMaxSize()
                    .clip(RoundedCornerShape(16.dp)),
                factory = { ctx ->
                    val previewView = PreviewView(ctx)
                    val cameraProviderFuture = ProcessCameraProvider.getInstance(ctx)

                    cameraProviderFuture.addListener({
                        val cameraProvider = cameraProviderFuture.get()
                        cameraProviderHolder.value = cameraProvider

                        val preview = Preview.Builder().build().also {
                            it.surfaceProvider = previewView.surfaceProvider
                        }

                        val options = BarcodeScannerOptions.Builder()
                            .setBarcodeFormats(
                                if (onlyQrCodes) Barcode.FORMAT_QR_CODE else Barcode.FORMAT_ALL_FORMATS,
                            )
                            .build()
                        val barcodeScanner = BarcodeScanning.getClient(options)

                        val imageAnalysis = ImageAnalysis.Builder()
                            .setBackpressureStrategy(ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST)
                            .build()

                        imageAnalysis.setAnalyzer(cameraExecutor) { imageProxy ->
                            val mediaImage = imageProxy.image
                            if (mediaImage != null && !scanned) {
                                val inputImage = InputImage.fromMediaImage(
                                    mediaImage,
                                    imageProxy.imageInfo.rotationDegrees,
                                )
                                barcodeScanner.process(inputImage)
                                    .addOnSuccessListener { barcodes ->
                                        val code = barcodes.firstOrNull()
                                        if (code != null && !scanned) {
                                            scanned = true
                                            onScanned(code.rawValue ?: "")
                                        }
                                    }
                                    .addOnCompleteListener { imageProxy.close() }
                            } else {
                                imageProxy.close()
                            }
                        }

                        cameraProvider.unbindAll()
                        cameraProvider.bindToLifecycle(
                            lifecycleOwner,
                            CameraSelector.DEFAULT_BACK_CAMERA,
                            preview,
                            imageAnalysis,
                        )
                    }, ContextCompat.getMainExecutor(ctx))

                    previewView
                },
            )

            Text(
                text = scanningText,
                color = WhiteCapiro,
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(24.dp),
            )
        }
    }
}
