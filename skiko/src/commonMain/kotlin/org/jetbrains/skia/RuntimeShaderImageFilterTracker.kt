package org.jetbrains.skia

import kotlin.concurrent.Volatile

/**
 * Tracks runtime-shader image filters so the Windows Direct3D renderer can switch to a backend
 * that doesn't stall on their first draw.
 */
internal object RuntimeShaderImageFilterTracker {
    // A fresh identity token avoids losing an update if filters are created concurrently.
    @Volatile
    private var latestCreationToken: Any = Any()

    val creationToken: Any
        get() = latestCreationToken

    fun onRuntimeShaderImageFilterCreated() {
        latestCreationToken = Any()
    }
}
