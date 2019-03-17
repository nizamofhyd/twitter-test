package util

import androidx.test.espresso.IdlingResource
import com.arun.twittertest.util.BackgroundIdleResourceListener

class BackgroundIdleResource : IdlingResource, BackgroundIdleResourceListener {

    private var idle = true
    private var idlingResourceCallback: IdlingResource.ResourceCallback? = null

    override fun getName(): String {
        return BackgroundIdleResource::class.java.name
    }

    override fun isIdleNow(): Boolean {
        return idle
    }

    override fun registerIdleTransitionCallback(callback: IdlingResource.ResourceCallback?) {
        idlingResourceCallback = callback
    }

    override fun start() {
        idle = false
    }

    override fun end() {
        idle = true
        idlingResourceCallback?.onTransitionToIdle()
    }
}