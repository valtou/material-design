package android.valto.fi.template.activities;

import android.valto.fi.template.R;
import android.valto.fi.template.adapters.MyFragmentAdapter;
import android.valto.fi.template.fragments.NavigationFragment;
import android.valto.fi.template.tabs.SlidingTabLayout;
import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;
import android.widget.Toast;

import com.getbase.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FloatingActionButton floatingActionButton = (FloatingActionButton) findViewById(R.id.floating_menu);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "FAB", Toast.LENGTH_SHORT).show();
            }
        });

        MyFragmentAdapter myFragmentAdapter = new MyFragmentAdapter(getSupportFragmentManager());
        DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        Toolbar toolbar = (Toolbar) findViewById(R.id.app_bar);
        SlidingTabLayout slidingTabLayout = (SlidingTabLayout) findViewById(R.id.tabs);

        //there is a bug on when return from FadeToolbar class.
        //resetting the background color for toolbar and tabs do help as for now...
        toolbar.setBackgroundColor(getResources().getColor(R.color.primary));
        slidingTabLayout.setBackgroundColor(getResources().getColor(R.color.primary));

        ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        viewPager.setAdapter(myFragmentAdapter);
        slidingTabLayout.setDistributeEvenly(true);
        slidingTabLayout.setViewPager(viewPager);
        setSupportActionBar(toolbar);
        NavigationFragment navigationFragment = (NavigationFragment) getSupportFragmentManager().findFragmentById(R.id.nav_fragment);
        navigationFragment.setUp(R.id.drawer_layout, drawerLayout, toolbar);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        MenuItem searchItem = menu.findItem(R.id.search);
        SearchManager searchManager = (SearchManager) MainActivity.this.getSystemService(Context.SEARCH_SERVICE);

        SearchView searchView = null;
        if (searchItem != null) {
            searchView = (SearchView) searchItem.getActionView();
        }
        if (searchView != null) {
            searchView.setSearchableInfo(searchManager.getSearchableInfo(MainActivity.this.getComponentName()));
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            Toast.makeText(getApplicationContext(), "Settings", Toast.LENGTH_LONG).show();
        }
        if (id == R.id.search) {
            Toast.makeText(getApplicationContext(), "Search", Toast.LENGTH_LONG).show();
        }
        if (id == R.id.share) {
            Toast.makeText(getApplicationContext(), "Share", Toast.LENGTH_LONG).show();
        }
        return super.onOptionsItemSelected(item);
    }
}
