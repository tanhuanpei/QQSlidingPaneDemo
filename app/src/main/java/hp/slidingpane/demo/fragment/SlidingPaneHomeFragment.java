package hp.slidingpane.demo.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import hp.slidingpane.demo.R;

/**
 * Created by huanpei.tan on 2015/2/28.
 */
public class SlidingPaneHomeFragment extends Fragment {

    private View currentView;
    private Toolbar mToolbar;

    public static SlidingPaneHomeFragment newInstance(Bundle bundle) {
        SlidingPaneHomeFragment fragment = new SlidingPaneHomeFragment();
        if (bundle != null)
            fragment.setArguments(bundle);
        return fragment;
    }

    public View getCurrentView() {
        return currentView;
    }

    public void setCurrentViewPararms(FrameLayout.LayoutParams layoutParams) {
        currentView.setLayoutParams(layoutParams);
    }

    public FrameLayout.LayoutParams getCurrentViewParams() {
        return (FrameLayout.LayoutParams) currentView.getLayoutParams();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        currentView = inflater.inflate(R.layout.fragment_slidingpane_home, container, false);
        initToolbar(currentView);
        return currentView;
    }

    private void initToolbar(View view) {
        mToolbar = (Toolbar) view.findViewById(R.id.toolbar);
        mToolbar.setBackgroundColor(getResources().getColor(android.R.color.holo_red_light));
        mToolbar.setTitleTextColor(getResources().getColor(android.R.color.white));

        ActionBarActivity actionBarActivity = (ActionBarActivity)getActivity();
        actionBarActivity.setSupportActionBar(mToolbar);
        actionBarActivity.setTitle(getString(R.string.app_name));
//        actionBarActivity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
