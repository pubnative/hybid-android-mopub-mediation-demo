package net.pubnative.hybid.mopub.mediation.demo

import android.os.Bundle
import android.util.Log
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.mopub.common.MoPub
import com.mopub.common.SdkConfiguration
import net.pubnative.hybid.mopub.mediation.demo.databinding.ActivityMainBinding
import net.pubnative.lite.sdk.HyBid

class MainActivity : AppCompatActivity() {
    private val TAG = MainActivity::class.simpleName
    private lateinit var binding: ActivityMainBinding
    private val hybidAppToken = "dde3c298b47648459f8ada4a982fa92d"
    private val mopubInitAdUnit = "38864d35877d4684af11319530993074"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(setOf(
            R.id.navigation_banner,
            R.id.navigation_mrect,
            R.id.navigation_interstitial,
            R.id.navigation_rewarded,
            R.id.navigation_native
        ))
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        HyBid.initialize(hybidAppToken, application) { cmpSuccess ->
            Log.d(TAG, "HyBid SDK initialised.")
        }
        HyBid.setInterstitialSkipOffset(5)

        val mopubSdkConfiguration = SdkConfiguration.Builder(mopubInitAdUnit).build()
        MoPub.initializeSdk(this, mopubSdkConfiguration) {
            Log.d(TAG, "MoPub SDK initialised.")
        }
    }
}