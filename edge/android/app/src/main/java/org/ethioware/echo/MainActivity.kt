package org.ethioware.echo

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.ViewModelProvider
import org.ethioware.echo.pilot.PilotApp
import org.ethioware.echo.pilot.PilotViewModel

class MainActivity : ComponentActivity() {
    private val model by lazy { ViewModelProvider(this)[PilotViewModel::class.java] }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        if (intent.getBooleanExtra("reflection", false)) model.tab = 1
        setContent { PilotApp(model) { startActivity(Intent(this, SyntheticDemoActivity::class.java)) } }
    }
    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        if (intent.getBooleanExtra("reflection", false)) { model.tab = 1; model.refresh() }
    }
}
