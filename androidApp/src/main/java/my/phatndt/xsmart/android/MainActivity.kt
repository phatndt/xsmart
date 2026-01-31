package my.phatndt.xsmart.android

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.core.view.WindowCompat
import my.xsmart.feature.dashboard.DashboardActivity

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        startActivity(Intent(this, DashboardActivity::class.java))
        finish()
    }
}
