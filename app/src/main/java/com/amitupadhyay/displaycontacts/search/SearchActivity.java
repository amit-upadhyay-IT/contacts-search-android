package com.amitupadhyay.displaycontacts.search;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import com.amitupadhyay.displaycontacts.R;
import com.amitupadhyay.displaycontacts.search.fragment.BaseExampleFragment;
import com.amitupadhyay.displaycontacts.search.fragment.SlidingSearchResultsExampleFragment;
import com.arlib.floatingsearchview.FloatingSearchView;

import java.util.List;

public class SearchActivity extends AppCompatActivity implements BaseExampleFragment.BaseExampleFragmentCallbacks {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        showFragment(new SlidingSearchResultsExampleFragment());
    }

    @Override
    public void onBackPressed() {
        List fragments = getSupportFragmentManager().getFragments();
        BaseExampleFragment currentFragment = (BaseExampleFragment) fragments.get(fragments.size() - 1);

        if (!currentFragment.onActivityBackPress()) {
            super.onBackPressed();
        }
    }

    private void showFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, fragment).commit();
    }

    @Override
    public void onAttachSearchViewToDrawer(FloatingSearchView searchView) {
        //searchView.attachNavigationDrawerToMenuButton(mLeftDrawerLayout);
    }
}
