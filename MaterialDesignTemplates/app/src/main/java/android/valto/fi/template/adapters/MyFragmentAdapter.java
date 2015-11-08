package android.valto.fi.template.adapters;

import android.valto.fi.template.fragments.PageOne;
import android.valto.fi.template.fragments.PageThree;
import android.valto.fi.template.fragments.PageTwo;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;


public class MyFragmentAdapter extends FragmentPagerAdapter {
    public MyFragmentAdapter(FragmentManager fm) {
        super(fm);
    }


    @Override
    public Fragment getItem(int position) {
        // We create instances of fragments and pass to viewpager for display
        Fragment fragment;
        switch (position) {
            case 0:
                fragment = new PageOne();
                return fragment;

            case 1:
                fragment = new PageThree();
                return fragment;
            case 2:
                fragment = new PageTwo();
                return fragment;
            default:
                fragment = new PageTwo();
                return fragment;
        }
    }

    @Override
    public int getCount() {
        //The number of fragments we got
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        //Sample titles for our tabs
        if (position == 0) {
            return "Planets";
        } else if (position == 1) {
            return "Galaxies";
        } else if (position == 2) {
            return "Images";
        }
        return "";
    }
}
