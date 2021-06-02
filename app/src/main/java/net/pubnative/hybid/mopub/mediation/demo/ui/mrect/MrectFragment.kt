package net.pubnative.hybid.mopub.mediation.demo.ui.mrect

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

class MrectFragment : Fragment(), MoPubView.BannerAdListener {
    val TAG = MrectFragment::class.java.simpleName

    private lateinit var moPubMrect: MoPubView
    private lateinit var loadButton: Button

    private val adUnitId: String = "0af490cac3d34256b0010fac17f26759"

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.fragment_mrect, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        moPubMrect = view.findViewById(R.id.mopub_mrect)
        loadButton = view.findViewById(R.id.button_load)

        loadButton.setOnClickListener {
            loadBanner()
        }

        moPubMrect.bannerAdListener = this
        moPubMrect.setAdUnitId(adUnitId)
    }

    override fun onDestroy() {
        moPubMrect.destroy()
        super.onDestroy()
    }

    private fun loadBanner() {
        moPubMrect.loadAd(MoPubView.MoPubAdSize.HEIGHT_250)
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