package android.valto.fi.template.views;

import android.valto.fi.template.R;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

import com.github.mrengineer13.snackbar.SnackBar;

public class UIElements extends AppCompatActivity {
    private Toolbar mToolbar;
    private Button mSnackBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uielements);

        registerViews();
        addListenersToViews();
        setSupportActionBar(mToolbar);


        //safe check for presence of toolbar
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    private void registerViews() {
        mSnackBar = (Button) findViewById(R.id.snackbar);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
    }

    private void addListenersToViews() {

        //All Snackbar customization is done under this
        //https://github.com/MrEngineer13/SnackBar
        mSnackBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new SnackBar.Builder(UIElements.this)
                        .withActionMessage("Done")
                        .withTextColorId(R.color.snackbar_text_color)
                        .withBackgroundColorId(R.color.snackbar_background)
                        .withMessage("This is a simple snackbar")
                        .withDuration(new Short("4000"))
                        .show();
                Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.grow_shrink_btn);
                mSnackBar.startAnimation(animation);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_uielements, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
