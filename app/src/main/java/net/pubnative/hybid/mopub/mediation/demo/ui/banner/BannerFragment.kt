package net.pubnative.hybid.mopub.mediation.demo.ui.banner

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.mopub.mobileads.MoPubErrorCode
import com.mopub.mobileads.MoPubView
import net.pubnative.hybid.mopub.mediation.demo.R


class BannerFragment : Fragment(), MoPubView.BannerAdListener {
    val TAG = BannerFragment::class.java.simpleName

    private lateinit var moPubBanner: MoPubView
    private lateinit var loadButton: Button

    private val adUnitId: String = "38864d35877d4684af11319530993074"

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.fragment_banner, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        moPubBanner = view.findViewById(R.id.mopub_banner)
        loadButton = view.findViewById(R.id.button_load)

        loadButton.setOnClickListener {
            loadBanner()
        }

        moPubBanner.bannerAdListener = this
        moPubBanner.setAdUnitId(adUnitId)
    }

    override fun onDestroy() {
        moPubBanner.destroy()
        super.onDestroy()
    }


    private fun loadBanner() {
        moPubBanner.loadAd(MoPubView.MoPubAdSize.HEIGHT_50)
    }

    override fun onBannerLoaded(banner: MoPubView) {
        Log.d(TAG, "onBannerLoaded")
    }

    override fun onBannerFailed(banner: MoPubView?, errorCode: MoPubErrorCode?) {
        Log.e(TAG, "onBannerFailed:$errorCode")
    }

    override fun onBannerClicked(banner: MoPubView?) {
        Log.d(TAG, "onBannerClicked")
    }

    override fun onBannerExpanded(banner: MoPubView?) {
        Log.d(TAG, "onBannerExpanded")
    }

    override fun onBannerCollapsed(banner: MoPubView?) {
        Log.d(TAG, "onBannerCollapsed")
    }
}