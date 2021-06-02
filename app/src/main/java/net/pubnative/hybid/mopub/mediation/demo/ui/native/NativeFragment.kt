package net.pubnative.hybid.mopub.mediation.demo.ui.native

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import com.mopub.nativeads.*
import net.pubnative.hybid.mopub.mediation.demo.R
import java.util.*


class NativeFragment : Fragment(), MoPubNative.MoPubNativeNetworkListener, com.mopub.nativeads.NativeAd.MoPubNativeEventListener {
    val TAG = NativeFragment::class.java.simpleName

    private lateinit var loadButton: Button
    private lateinit var adContainer: ViewGroup

    private var nativeAd: MoPubNative? = null

    private val adUnitId: String = "2dfcd13fb5dc4e9d9bc6ab3e382ffeb7"

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.fragment_native, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loadButton = view.findViewById(R.id.button_load)

        adContainer = view.findViewById(R.id.ad_container)

        loadButton.setOnClickListener {
            loadNativeAd()
        }

        nativeAd = MoPubNative(requireActivity(), adUnitId, this)

        val viewBinder = ViewBinder.Builder(R.layout.layout_native_ad)
            .mainImageId(R.id.ad_banner)
            .iconImageId(R.id.ad_icon)
            .titleId(R.id.ad_title)
            .textId(R.id.ad_description)
            .privacyInformationIconImageId(R.id.ad_choices)
            .callToActionId(R.id.ad_call_to_action)
            .build()
        val nativeAdRenderer = MoPubStaticNativeAdRenderer(viewBinder)
        nativeAd?.registerAdRenderer(nativeAdRenderer)
    }

    override fun onDestroy() {
        nativeAd?.destroy()
        super.onDestroy()
    }

    private fun loadNativeAd() {
        adContainer.removeAllViews()
        val desiredAssets = EnumSet.of(
            RequestParameters.NativeAdAsset.TITLE,
            RequestParameters.NativeAdAsset.TEXT,
            RequestParameters.NativeAdAsset.CALL_TO_ACTION_TEXT,
            RequestParameters.NativeAdAsset.MAIN_IMAGE,
            RequestParameters.NativeAdAsset.ICON_IMAGE,
            RequestParameters.NativeAdAsset.STAR_RATING)
        val requestParameters = RequestParameters.Builder().desiredAssets(desiredAssets).build()
        nativeAd?.makeRequest(requestParameters)
    }

    fun renderAd(ad: NativeAd?) {
        val adapterHelper = AdapterHelper(requireActivity(), 0, 3)
        val view = adapterHelper.getAdView(null, adContainer, ad, ViewBinder.Builder(0).build())
        ad?.setMoPubNativeEventListener(this)
        adContainer.addView(view)
    }

    override fun onNativeLoad(nativeAd: NativeAd?) {
        Log.d(TAG, "onNativeLoad")
        renderAd(nativeAd)
    }

    override fun onNativeFail(errorCode: NativeErrorCode?) {
        Log.e(TAG, "onNativeFail:$errorCode")

    }

    override fun onImpression(view: View?) {
        Log.d(TAG, "onImpression")
    }

    override fun onClick(view: View?) {
        Log.d(TAG, "onClick")
    }
}