package android.valto.fi.template.activities.swipetorefresh;

import android.valto.fi.template.R;
import android.valto.fi.template.adapters.RefreshListAdapter;
import android.valto.fi.template.utils.DummyData;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class SwipeToRefreshActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {
    private SwipeRefreshLayout swipeRefreshLayout;
    private List<String> list;

    //These are the colors that'll be used by refresh widget
    private int[] refreshColors = {
            R.color.primary,
            R.color.pink_pressed,
            R.color.accent};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swipe_to_refresh);

        list = new ArrayList<>();
        populateList();
        Toolbar toolbar = (Toolbar) findViewById(R.id.app_bar);
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.refreshLayout);

        swipeRefreshLayout.setColorSchemeResources(refreshColors);

        //safe check for presence of toolbar, we wont want to bump into Mr. bug
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Swipe to Refresh");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        RefreshListAdapter refreshListAdapter = new RefreshListAdapter(list, DummyData.images);
        recyclerView.setAdapter(refreshListAdapter);
    }

    private void populateList() {
        for (int i = 1; i < 50; i++) {
            list.add("This is item number: " + i);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_swipe_to_refresh, menu);
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

    @Override
    public void onRefresh() {
        // Start showing the refresh animation
        swipeRefreshLayout.setRefreshing(true);

        // Simulate a long running activity
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                updateList();
            }
        }, 5000);
    }

    private void updateList() {

        for (int i = 1; i < 10; i++) {
            list.add("These are new members: " + i);
        }

        // Signify that we are done refreshing
        swipeRefreshLayout.setRefreshing(false);
    }

}
