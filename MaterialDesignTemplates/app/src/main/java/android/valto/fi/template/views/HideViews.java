package android.valto.fi.template.views;

import android.valto.fi.template.utils.OnScrollListener;
import android.valto.fi.template.R;
import android.valto.fi.template.adapters.ListItemsAdapter;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.getbase.floatingactionbutton.FloatingActionButton;
import com.getbase.floatingactionbutton.FloatingActionsMenu;

import java.util.ArrayList;
import java.util.List;

import jp.wasabeef.recyclerview.animators.FadeInDownAnimator;

public class HideViews extends AppCompatActivity {
    private Toolbar toolBar;
    private FloatingActionsMenu floatingActionsMenu;
    private List<String> items;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);

        Toast.makeText(getApplicationContext(), "Scroll up to hide Toolbar and FAB", Toast.LENGTH_LONG).show();

        items = new ArrayList<>();
        populateList();
        setUpFloatingMenu();
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);

        //sadly setOnScrollListener is deprecated.
        recyclerView.setOnScrollListener(new OnScrollListener() {
            @Override
            public void onHide() {
                hideViews();
            }

            @Override
            public void onShow() {
                showViews();
            }
        });
        ListItemsAdapter listItemsAdapter = new ListItemsAdapter(items);
        recyclerView.setAdapter(listItemsAdapter);
        recyclerView.setItemAnimator(new FadeInDownAnimator());
        toolBar = (Toolbar) findViewById(R.id.app_bar);
        toolBar.setBackgroundColor(getResources().getColor(R.color.primary));
        setSupportActionBar(toolBar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    private void populateList() {
        for (int i = 1; i < 100; i++) {
            items.add("Planet " + i);
        }
    }

    private void showViews() {
        toolBar.animate().translationY(0).setInterpolator(new DecelerateInterpolator(2));
        floatingActionsMenu.animate().translationY(0).setInterpolator(new DecelerateInterpolator(2));
    }

    private void hideViews() {
        //Get the hide and margin of the view then animate, simple enough? check it our
        toolBar.animate().translationY(-toolBar.getHeight()).setInterpolator(new AccelerateInterpolator(2));
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) floatingActionsMenu.getLayoutParams();
        int fabMarginBottom = layoutParams.bottomMargin;
        floatingActionsMenu.animate().translationY(floatingActionsMenu.getHeight() + fabMarginBottom).setInterpolator(new AccelerateInterpolator(2));
    }

    private void setUpFloatingMenu() {
        //here is the drill, initialize some FloatingActionButtons then add to floatingActionsMenu.
        floatingActionsMenu = (FloatingActionsMenu) findViewById(R.id.floating_menu);
        final FloatingActionButton floatingActionButton1 = new FloatingActionButton(this);
        floatingActionButton1.setTitle("Search");
        floatingActionButton1.setIcon(R.drawable.ic_menu_search);
        floatingActionButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                floatingActionsMenu.collapse();
                Toast.makeText(getApplicationContext(), "Search", Toast.LENGTH_SHORT).show();
            }
        });
        final FloatingActionButton floatingActionButton2 = new FloatingActionButton(this);
        floatingActionButton2.setTitle("Search");
        floatingActionButton2.setIcon(R.drawable.ic_menu_search);
        floatingActionButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                floatingActionsMenu.collapse();
                Toast.makeText(getApplicationContext(), "Search", Toast.LENGTH_SHORT).show();
            }
        });
        floatingActionsMenu.addButton(floatingActionButton1);
        floatingActionsMenu.addButton(floatingActionButton2);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_sub, menu);
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

        if (id == android.R.id.home) {
            NavUtils.navigateUpFromSameTask(this);
        }

        return super.onOptionsItemSelected(item);
    }
}
