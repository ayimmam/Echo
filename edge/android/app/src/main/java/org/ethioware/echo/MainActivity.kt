package org.ethioware.echo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import org.ethioware.echo.data.DemoRepository
import org.ethioware.echo.data.SharedPrefsStore

class MainActivity : ComponentActivity() {

    private val viewModel: EchoViewModel by lazy {
        val app = applicationContext
        ViewModelProvider(
            this,
            object : ViewModelProvider.Factory {
                @Suppress("UNCHECKED_CAST")
                override fun <T : ViewModel> create(modelClass: Class<T>): T =
                    EchoViewModel(DemoRepository.fromAssets(app), SharedPrefsStore(app)) as T
            },
        )[EchoViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent { EchoApp(viewModel) }
    }
}
