package net.pubnative.hybid.mopub.mediation.demo.ui.rewarded

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.mopub.common.MoPub
import com.mopub.common.MoPubReward
import com.mopub.mobileads.MoPubErrorCode
import com.mopub.mobileads.MoPubRewardedAdListener
import com.mopub.mobileads.MoPubRewardedAds
import net.pubnative.hybid.mopub.mediation.demo.R

class RewardedFragment : Fragment(), MoPubRewardedAdListener {
    val TAG = RewardedFragment::class.java.simpleName

    private lateinit var loadButton: Button
    private lateinit var showButton: Button

    private val adUnitId: String = "4f6a64ece43847ecbc3475219e8dd9b6"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MoPub.onCreate(requireActivity())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? =
        inflater.inflate(R.layout.fragment_rewarded, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loadButton = view.findViewById(R.id.button_load)
        showButton = view.findViewById(R.id.button_show)

        loadButton.setOnClickListener {
            loadRewardedVideo()
        }

        showButton.setOnClickListener {
            MoPubRewardedAds.showRewardedAd(adUnitId)
        }

        MoPubRewardedAds.setRewardedAdListener(this)
    }

    override fun onResume() {
        super.onResume()
        MoPub.onResume(requireActivity())
    }

    override fun onPause() {
        super.onPause()
        MoPub.onPause(requireActivity())
    }

    override fun onStart() {
        super.onStart()
        MoPub.onStart(requireActivity())
    }

    override fun onStop() {
        super.onStop()
        MoPub.onStop(requireActivity())
    }

    override fun onDestroy() {
        super.onDestroy()
        MoPub.onDestroy(requireActivity())
    }

    private fun loadRewardedVideo() {
        MoPubRewardedAds.loadRewardedAd(adUnitId)
    }

    override fun onRewardedAdLoadSuccess(adUnitId: String) {
        if (adUnitId == this.adUnitId) {
            Log.d(TAG, "onRewardedAdLoadSuccess")
            showButton.isEnabled = true
        }
    }

    override fun onRewardedAdLoadFailure(adUnitId: String, errorCode: MoPubErrorCode) {
        Log.e(TAG, "onRewardedAdLoadFailure:$errorCode")
    }

    override fun onRewardedAdStarted(adUnitId: String) {
        Log.d(TAG, "onRewardedAdStarted")
    }

    override fun onRewardedAdShowError(adUnitId: String, errorCode: MoPubErrorCode) {
        Log.e(TAG, "onRewardedAdShowError:$errorCode")
    }

    override fun onRewardedAdCompleted(adUnitIds: Set<String?>, reward: MoPubReward) {
        Log.d(TAG, "onRewardedAdCompleted")
    }

    override fun onRewardedAdClosed(adUnitId: String) {
        Log.d(TAG, "onRewardedAdClosed")
        showButton.isEnabled = false
    }

    override fun onRewardedAdClicked(adUnitId: String) {
        Log.d(TAG, "onRewardedAdClicked")
    }
}