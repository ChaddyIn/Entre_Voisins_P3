
package com.openclassrooms.entrevoisins.neighbour_list;

import android.app.Activity;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;

import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.DummyNeighbourGenerator;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;
import com.openclassrooms.entrevoisins.ui.neighbour_list.DetailNeighbourActivity;
import com.openclassrooms.entrevoisins.ui.neighbour_list.ListNeighbourActivity;
import com.openclassrooms.entrevoisins.ui.neighbour_list.NeighbourFragment;
import com.openclassrooms.entrevoisins.utils.DeleteViewAction;

import org.hamcrest.Matchers;
import org.hamcrest.core.StringContains;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.assertThat;
import static android.support.test.espresso.matcher.ViewMatchers.hasMinimumChildCount;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static com.openclassrooms.entrevoisins.utils.RecyclerViewItemCountAssertion.withItemCount;
import static org.hamcrest.core.IsNot.not;
import static org.hamcrest.core.IsNull.notNullValue;



/**
 * Test class for list of neighbours
 */
@RunWith(AndroidJUnit4.class)
public class NeighboursListTest {

    // This is fixed
    private static int ITEMS_COUNT = 12;

    private ListNeighbourActivity mActivity;

    @Rule
    public ActivityTestRule<ListNeighbourActivity> mActivityRule =
            new ActivityTestRule(ListNeighbourActivity.class);

    @Before
    public void setUp() {
        mActivity = mActivityRule.getActivity();
        assertThat(mActivity, notNullValue());
    }

    /**
     * We ensure that our recyclerview is displaying at least on item
     */
    @Test
    public void myNeighboursList_shouldNotBeEmpty() {
        // First scroll to the position that needs to be matched and click on it.
        onView(Matchers.allOf(ViewMatchers.withId(R.id.list_neighbours), ViewMatchers.isDisplayed()))
                .check(matches(hasMinimumChildCount(1)));
    }

    /**
     * When we delete an item, the item is no more shown
     */
    @Test
    public void myNeighboursList_deleteAction_shouldRemoveItem() {
        // Given : We remove the element at position 2

        // When perform a click on a delete icon
        onView(Matchers.allOf(ViewMatchers.withId(R.id.list_neighbours), ViewMatchers.isDisplayed()))
                .perform(RecyclerViewActions.actionOnItemAtPosition(1, new DeleteViewAction()));
        // Then : the number of element is 11
        onView(Matchers.allOf(ViewMatchers.withId(R.id.list_neighbours), ViewMatchers.isDisplayed()))
                .check(withItemCount(ITEMS_COUNT-1));
    }

    @Test

    public void launchDetailActivity(){

        onView(Matchers.allOf(ViewMatchers.withId(R.id.list_neighbours), ViewMatchers.isDisplayed()))
                .perform(RecyclerViewActions.actionOnItemAtPosition(1,click()));
        onView(ViewMatchers.withId(R.id.imageDetailNeighbourView))
                .check(matches(isDisplayed()));

    }

    @Test

    public void textViewIsFilled(){

        onView(Matchers.allOf(ViewMatchers.withId(R.id.list_neighbours), ViewMatchers.isDisplayed()))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        onView(ViewMatchers.withId(R.id.detailPrenom))
                .check(matches(withText(" Caroline")));
    }

    @Test
    public void favoriteTab_OnlyDisplayFavoriteNeigbours(){

        onView(Matchers.allOf(ViewMatchers.withId(R.id.list_neighbours), ViewMatchers.isDisplayed()))
                .perform(RecyclerViewActions.actionOnItemAtPosition(1,click()));
        onView(ViewMatchers.withId(R.id.imageDetailNeighbourView))
                .check(matches(isDisplayed()));
        onView(ViewMatchers.withId(R.id.ButtonFav))
                .perform(click());
        onView(ViewMatchers.withId(R.id.backButton))
                .perform(click());
        onView(Matchers.allOf(ViewMatchers.withId(R.id.list_neighbours), ViewMatchers.isDisplayed()));
        onView(ViewMatchers.withId(R.id.tabs))
                .check(matches(isDisplayed()));
              onView(ViewMatchers.withText("FAVORITES"))
            .check(matches(isDisplayed()))
              .perform(click());
        onView(Matchers.allOf(ViewMatchers.withId(R.id.list_neighbours), ViewMatchers.isDisplayed()))
                .check(withItemCount(1));

    }




}