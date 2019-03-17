package activity

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isCompletelyDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import com.arun.twittertest.R
import com.arun.twittertest.activity.MainActivity
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import util.BackgroundIdleResource
import util.RecyclerViewAssertion

@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    private val backgroundIdleResource = BackgroundIdleResource()

    @Rule
    @JvmField
    val rule = ActivityTestRule(MainActivity::class.java)

    @Before
    fun setUp() {
        IdlingRegistry.getInstance().register(backgroundIdleResource)
        rule.activity.backgroundIdleResourceListener = backgroundIdleResource
        rule.activity.setIdle()
    }

    @Test
    fun testListViewDisplayed() {
        onView(withId(R.id.twitterRecyclerView)).check(matches(isCompletelyDisplayed()))
    }

    @Test
    fun testListCount() {
        onView(withId(R.id.twitterRecyclerView)).check(RecyclerViewAssertion(10))
    }

}