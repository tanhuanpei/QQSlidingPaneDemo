package hp.slidingpane.demo.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import hp.slidingpane.demo.R;

/**
 * Created by huanpei.tan on 2015/2/28.
 */
public class SlidingPaneMenuFragment extends Fragment {

    private View currentView;

    public static SlidingPaneMenuFragment newInstance(Bundle bundle) {
        SlidingPaneMenuFragment fragment = new SlidingPaneMenuFragment();
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
        currentView = inflater.inflate(R.layout.fragment_slidingpane_menu, container, false);


        return currentView;
    }
}
