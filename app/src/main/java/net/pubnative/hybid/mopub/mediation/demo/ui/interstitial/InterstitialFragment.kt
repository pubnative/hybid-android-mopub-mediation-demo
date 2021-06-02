package net.pubnative.hybid.mopub.mediation.demo.ui.interstitial

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.mopub.mobileads.MoPubErrorCode
import com.mopub.mobileads.MoPubInterstitial
import net.pubnative.hybid.mopub.mediation.demo.R

class InterstitialFragment : Fragment(), MoPubInterstitial.InterstitialAdListener {
    val TAG = InterstitialFragment::class.java.simpleName

    private lateinit var loadButton: Button
    private lateinit var showButton: Button

    private var interstitial: MoPubInterstitial? = null
    private val adUnitId: String = "2bb6db4d69fa407a87b0c96c55d7c2b4"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? =
        inflater.inflate(R.layout.fragment_interstitial, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loadButton = view.findViewById(R.id.button_load)
        showButton = view.findViewById(R.id.button_show)

        loadButton.setOnClickListener {
            loadInterstitial()
        }

        showButton.setOnClickListener {
            interstitial?.let {
                if (it.isReady) {
                    it.show()
                }
            }
        }
    }

    override fun onDestroy() {
        interstitial?.destroy()
        super.onDestroy()
    }

    private fun loadInterstitial() {
        interstitial = MoPubInterstitial(requireActivity(), adUnitId)
        interstitial?.interstitialAdListener = this
        interstitial?.load()
    }

    override fun onInterstitialLoaded(interstitial: MoPubInterstitial?) {
        Log.d(TAG, "onInterstitialLoaded")
        showButton.isEnabled = true
    }

    override fun onInterstitialFailed(
        interstitial: MoPubInterstitial?,
        errorCode: MoPubErrorCode?
    ) {
        Log.e(TAG, "onInterstitialFailed:$errorCode")
    }

    override fun onInterstitialShown(interstitial: MoPubInterstitial?) {
        Log.d(TAG, "onInterstitialShown")
    }

    override fun onInterstitialClicked(interstitial: MoPubInterstitial?) {
        Log.d(TAG, "onInterstitialClicked")
    }

    override fun onInterstitialDismissed(interstitial: MoPubInterstitial?) {
        Log.d(TAG, "onInterstitialDismissed")
    }
}