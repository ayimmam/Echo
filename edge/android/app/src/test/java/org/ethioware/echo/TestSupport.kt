package org.ethioware.echo

import java.io.File
import org.ethioware.echo.data.DemoBundle
import org.ethioware.echo.data.DemoRepository
import org.ethioware.echo.data.MockSource

/** Loads the same shared mock files the APK packages (tools/demo/mock-data/android-assets/mock). */
object TestSupport {
    private val dir: File = listOf(
        File("../../../tools/demo/mock-data/android-assets/mock"), // gradle runs tests in edge/android/app
        File("tools/demo/mock-data/android-assets/mock"),
    ).first { it.isDirectory }

    fun bundle(): DemoBundle = DemoRepository.load(MockSource { name -> File(dir, name).readText() })
}
