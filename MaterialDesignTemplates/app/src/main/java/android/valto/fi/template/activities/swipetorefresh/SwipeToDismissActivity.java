package android.valto.fi.template.activities.swipetorefresh;

import android.valto.fi.template.R;
import android.valto.fi.template.adapters.SwipeToDismissAdapter;
import android.valto.fi.template.utils.SwipeToDismissCallback;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.Menu;
import android.view.MenuItem;

public class SwipeToDismissActivity extends AppCompatActivity implements SwipeToDismissAdapter.OnStartDragListener {
    private Toolbar toolbar;
    private RecyclerView recyclerView;
    private SwipeToDismissAdapter refreshListAdapter;
    private ItemTouchHelper itemTouchHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swipe_to_dismiss);

        toolbar = (Toolbar) findViewById(R.id.app_bar);

        //safe check for presence of toolbar, we wont want to bump into Mr. bug
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Swipe to Dismiss");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        recyclerView = (RecyclerView) findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        /*
        Other layout managers of recyclerView include GridLayoutManger and StaggeredLayoutManger
         */
        refreshListAdapter = new SwipeToDismissAdapter(this, getApplicationContext());
        recyclerView.setAdapter(refreshListAdapter);

        ItemTouchHelper.Callback callback = new SwipeToDismissCallback(refreshListAdapter);
        itemTouchHelper = new ItemTouchHelper(callback);
        itemTouchHelper.attachToRecyclerView(recyclerView);
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
    public void onStartDrag(RecyclerView.ViewHolder viewHolder) {
        itemTouchHelper.startDrag(viewHolder);
    }
}
