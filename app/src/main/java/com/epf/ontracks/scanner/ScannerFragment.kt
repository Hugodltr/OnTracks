package com.epf.ontracks.scanner

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.epf.ontracks.MainActivity
import com.google.zxing.Result
import me.dm7.barcodescanner.zxing.ZXingScannerView


class ScannerFragment : Fragment(), ZXingScannerView.ResultHandler {
    private var mScannerView: ZXingScannerView? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        (activity as MainActivity).supportActionBar?.title = "Scanner un QR code"

        mScannerView = ZXingScannerView(activity)

        return mScannerView
    }

    override fun onResume() {
        super.onResume()
        mScannerView!!.setResultHandler(this)
        mScannerView!!.startCamera()
    }

    override fun handleResult(rawResult: Result?) {
        Toast.makeText(activity, "Contents = ${rawResult!!.text}, Format = ${rawResult.barcodeFormat}", Toast.LENGTH_SHORT).show()

        Handler().postDelayed({
            mScannerView!!.resumeCameraPreview(this@ScannerFragment)
        }, 2000)
    }

    override fun onPause() {
        super.onPause()
        mScannerView!!.stopCamera()
    }
}