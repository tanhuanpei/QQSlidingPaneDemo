package hp.slidingpane.demo.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.SlidingPaneLayout;
import android.support.v7.app.ActionBarActivity;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.nineoldandroids.view.ViewHelper;

import hp.slidingpane.demo.R;
import hp.slidingpane.demo.fragment.SlidingPaneHomeFragment;
import hp.slidingpane.demo.fragment.SlidingPaneMenuFragment;
import hp.slidingpane.demo.view.MySlidingPaneLayout;

/**
 * Created by huanpei.tan on 2015/2/28.
 */
public class MainActivity extends ActionBarActivity {

    private SlidingPaneMenuFragment slidingPaneMenuFragment;
    private SlidingPaneHomeFragment slidingPaneHomeFragment;
    private MySlidingPaneLayout mSlidingPaneLayout;
    private DisplayMetrics displayMetrics = new DisplayMetrics();
    private int maxMargin = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            initSlidingFragment();
            initSlidingPanelayout();
        }
    }


    private void initSlidingFragment() {
        slidingPaneMenuFragment = SlidingPaneMenuFragment.newInstance(null);
        slidingPaneHomeFragment = SlidingPaneHomeFragment.newInstance(null);
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction ft = manager.beginTransaction();
        if (!slidingPaneMenuFragment.isAdded()) {
            ft.replace(R.id.ah_slidingpane_menu, slidingPaneMenuFragment);
        }
        if (!slidingPaneHomeFragment.isAdded()) {
            ft.replace(R.id.ah_slidingpane_content, slidingPaneHomeFragment);
        }
        ft.commit();
    }


    private void initSlidingPanelayout() {
        maxMargin = displayMetrics.heightPixels / 10;
        mSlidingPaneLayout = (MySlidingPaneLayout) findViewById(R.id.ah_slidingpane_layout);
        mSlidingPaneLayout.setDisplayMetrics(displayMetrics);
        mSlidingPaneLayout.setSliderFadeColor(0);
        mSlidingPaneLayout.closePane();
        mSlidingPaneLayout.setPanelSlideListener(new SlidingPaneLayout.PanelSlideListener() {

            @Override
            public void onPanelSlide(View arg0, float slideOffset) {

                float offScale = 1 - (slideOffset * maxMargin * 2) / (float) displayMetrics.heightPixels;
                ViewHelper.setScaleX(slidingPaneHomeFragment.getCurrentView(), offScale);// 设置水平方向的缩放比
                ViewHelper.setScaleY(slidingPaneHomeFragment.getCurrentView(), offScale);// 设置垂直方向的缩放比
                ViewHelper.setPivotY(slidingPaneHomeFragment.getCurrentView(), displayMetrics.heightPixels / 2);
                ViewHelper.setPivotX(slidingPaneHomeFragment.getCurrentView(), 0);// 设置缩放和选择的点

                float scale = 1 - ((1 - slideOffset) * maxMargin * 2) / (float) displayMetrics.heightPixels;

                ViewHelper.setScaleX(slidingPaneMenuFragment.getCurrentView(), scale);// 设置水平方向的缩放比
                ViewHelper.setScaleY(slidingPaneMenuFragment.getCurrentView(), scale);// 设置垂直方向的缩放比
                ViewHelper.setPivotY(slidingPaneMenuFragment.getCurrentView(), displayMetrics.heightPixels / 2);
                ViewHelper.setPivotX(slidingPaneMenuFragment.getCurrentView(), 0);// 设置缩放和选择的点
                ViewHelper.setAlpha(slidingPaneMenuFragment.getCurrentView(), slideOffset);
            }


            @Override
            public void onPanelOpened(View arg0) {
            }

            @Override
            public void onPanelClosed(View arg0) {
            }

        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
