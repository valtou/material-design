package android.valto.fi.template.fragments;


import android.valto.fi.template.R;
import android.valto.fi.template.activities.CollapseTabsActivity;
import android.valto.fi.template.activities.CollapseToToolbarActivity;
import android.valto.fi.template.activities.DialogBoxesActivity;
import android.valto.fi.template.activities.GoogleCardsActivity;
import android.valto.fi.template.views.HideViews;
import android.valto.fi.template.views.UIElements;
import android.valto.fi.template.activities.swipetorefresh.SwipeToDismissActivity;
import android.valto.fi.template.activities.swipetorefresh.SwipeToRefreshActivity;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 * Note that design support library allows allow easy
 * adding of navigation drawer but I wanted to have
 * an easy to customize way of adding it.
 */
public class NavigationFragment extends android.support.v4.app.Fragment {
    private static final String KEY_USER_HAS_LEARNED_DRAWER = "user_has_learned_drawer";
    private static final String PREF_FILE_NAME = "prefs";
    private ViewGroup viewGroup;
    private LinearLayout linearLayout;
    private LinearLayout fixedLayout;
    private DrawerLayout drawerLayout;
    private View mNavigationFragment;
    private ActionBarDrawerToggle actionBarDrawerToggle;

    public NavigationFragment() {
        // Required empty public constructor
    }

    public static void saveToPreference(Context context, String prefName, String prefValue) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(prefName, prefValue);
        editor.apply();
    }

    private static String readFromPreference(Context context, String prefName, String defaultfValue) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(prefName, defaultfValue);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        boolean hasUserLearnedDrawer = Boolean.valueOf(readFromPreference(getActivity(), KEY_USER_HAS_LEARNED_DRAWER, "false"));
        if (savedInstanceState == null) {
            boolean mFromSavedInstance = true;
        }
    }

    public void setUp(int fragmentID, DrawerLayout drawer, Toolbar toolBar) {
        this.drawerLayout = drawer;
        mNavigationFragment = getActivity().findViewById(fragmentID);

        actionBarDrawerToggle = new ActionBarDrawerToggle(getActivity(), drawer, toolBar, R.string.opened, R.string.closed) {
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                getActivity().invalidateOptionsMenu();
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                getActivity().invalidateOptionsMenu();
            }
        };
        drawer.post(new Runnable() {
            @Override
            public void run() {
                //this adds the icon and do the animation magic
                actionBarDrawerToggle.syncState();
            }
        });

        drawer.setDrawerListener(actionBarDrawerToggle);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        actionBarDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_navigation, container, false);

        linearLayout = (LinearLayout) view.findViewById(R.id.drawer);
        fixedLayout = (LinearLayout) view.findViewById(R.id.fixed_layout);

        //Adding items to navigation drawer
        addNavigationDrawerHeader(R.drawable.defaultprofile, "Valto Uotila", "valto.uotila@metropolia.fi");
        addNavigationDrawerItem(R.drawable.bt_ic_customcluster_g50_24dp, "Google Cards");
        addNavigationDrawerItem(R.drawable.bt_ic_customcluster_g50_24dp, "Hide Views");
        addNavigationDrawerItem(R.drawable.bt_ic_customcluster_g50_24dp, "UI Elements");
        addSectionDivider();
        addNavigationDrawerSubHeader("Subtitle");
        addNavigationDrawerItem(R.drawable.bt_ic_customcluster_g50_24dp, "Swipe To Refresh");
        addNavigationDrawerItem(R.drawable.bt_ic_customcluster_g50_24dp, "Swipe To Dismiss");
        addNavigationDrawerItem(R.drawable.bt_ic_customcluster_g50_24dp, "Dialog Boxes");
        addNavigationDrawerItem(R.drawable.bt_ic_customcluster_g50_24dp, "Collapse to Toolbar");
        addNavigationDrawerItem(R.drawable.bt_ic_customcluster_g50_24dp, "Collapse to Tabs");
        addSectionDivider();
        addNavigationDrawerSubHeader("Subtitle");
        addNavigationDrawerItem(R.drawable.bt_ic_customcluster_g50_24dp, "Personal");
        addNavigationDrawerItem(R.drawable.bt_ic_customcluster_g50_24dp, "Favorites");
        addNavigationDrawerItem(R.drawable.bt_ic_customcluster_g50_24dp, "Examine");
        setUpFixedLayout(R.drawable.bt_ic_settings_grey600_24, "Settings");
        setUpFixedLayout(R.drawable.bt_ic_help_g50_24dp, "Help & Feedback");
        Log.d("Getting children: ", linearLayout.getChildAt(0).toString() + "");

        addListenersToItems();

        return view;
    }

    private void addListenersToItems() {
        linearLayout.getChildAt(1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), GoogleCardsActivity.class));
                closeDrawer();
            }
        });
        linearLayout.getChildAt(2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), HideViews.class));
                closeDrawer();
            }
        });
        linearLayout.getChildAt(3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), UIElements.class));
                closeDrawer();
            }
        });
        linearLayout.getChildAt(6).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), SwipeToRefreshActivity.class));
                closeDrawer();
            }
        });
        linearLayout.getChildAt(7).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), SwipeToDismissActivity.class));
                closeDrawer();
            }
        });
        linearLayout.getChildAt(8).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), DialogBoxesActivity.class));
                closeDrawer();
            }
        });

        linearLayout.getChildAt(9).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), CollapseToToolbarActivity.class));
                closeDrawer();
            }
        });
        linearLayout.getChildAt(10).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), CollapseTabsActivity.class));
                closeDrawer();
            }
        });
        linearLayout.getChildAt(13).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                closeDrawer();
            }
        });
        linearLayout.getChildAt(14).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                closeDrawer();
            }
        });
        linearLayout.getChildAt(15).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                closeDrawer();
            }
        });
        fixedLayout.getChildAt(0).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "Settings", Toast.LENGTH_SHORT).show();
                closeDrawer();
            }
        });
        fixedLayout.getChildAt(1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "Help And Feedback", Toast.LENGTH_SHORT).show();
                closeDrawer();
            }
        });
    }

    private View getNavigationDrawerHeader(Context context) {
        return LayoutInflater.from(context).inflate(R.layout.navigation_drawer_header, viewGroup, false);
    }

    private View getSectionDivider(Context context) {
        return LayoutInflater.from(context).inflate(R.layout.navigation_drawer_divider, viewGroup, false);
    }

    private View getNavigationDrawerItem() {
        return LayoutInflater.from(getActivity()).inflate(R.layout.navigation_drawer_item, viewGroup, false);
    }

    private View getNavigationDrawerSubHeader() {
        return LayoutInflater.from(getActivity()).inflate(R.layout.navigation_drawer_subheader, viewGroup, false);
    }

    private void addSectionDivider() {
        linearLayout.addView(getSectionDivider(getActivity()));
        Log.d("Adding Divider", "Added successfully");

    }

    private void addNavigationDrawerHeader(int imageResource, String name, String email) {
        View view = getNavigationDrawerHeader(getActivity());
        ImageView profile = (ImageView) view.findViewById(R.id.account_image);
        TextView account_name = (TextView) view.findViewById(R.id.account_name);
        TextView account_email = (TextView) view.findViewById(R.id.account_email);

        profile.setImageResource(imageResource);
        account_name.setText(name);
        account_email.setText(email);

        linearLayout.addView(view);
        Log.d("Adding header", "Added successfully");
    }

    private void addNavigationDrawerItem(int imageResource, String itemTitle) {
        View view = getNavigationDrawerItem();
        ImageView icon = (ImageView) view.findViewById(R.id.item_image);
        TextView title = (TextView) view.findViewById(R.id.item_title);

        icon.setImageResource(imageResource);
        title.setText(itemTitle);

        linearLayout.addView(view);
        Log.d("Adding Item", "Added successfully");
    }

    private void addNavigationDrawerSubHeader(String subHeader) {
        View view = getNavigationDrawerSubHeader();
        TextView title = (TextView) view.findViewById(R.id.subheader);

        title.setText(subHeader);

        linearLayout.addView(view);
        Log.d("Adding Sub header", "Added successfully");
    }

    public void openDrawer() {
        if (drawerLayout != null) {
            drawerLayout.openDrawer(mNavigationFragment);
        }
    }

    private void setUpFixedLayout(int imageResource, String itemTitle) {
        View view = getNavigationDrawerItem();
        ImageView icon = (ImageView) view.findViewById(R.id.item_image);
        TextView title = (TextView) view.findViewById(R.id.item_title);

        icon.setImageResource(imageResource);
        title.setText(itemTitle);

        fixedLayout.addView(view);
        Log.d("Adding Item", "Added successfully");
    }

    private void closeDrawer() {
        if (drawerLayout.isDrawerOpen(Gravity.LEFT) && drawerLayout != null) {
            drawerLayout.closeDrawer(Gravity.LEFT);
        }
    }

}
