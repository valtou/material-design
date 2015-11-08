package android.valto.fi.template.activities;

import android.valto.fi.template.R;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.afollestad.materialdialogs.MaterialDialog;
import com.afollestad.materialdialogs.Theme;


public class DialogBoxesActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private Button mSimpleLightDialog;
    private Button mListLightDialog;
    private Button mRadioLightDialog;
    private Button mMultiLightChoice;
    private Button mSimpleDarkDialog;
    private Button mListDarkDialog;
    private Button mRadioDarkDialog;
    private Button mMultiDarkChoice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog_boxes);

        registerViews();
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Dialog Boxes");
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        addListenersToViews();
    }


    private void registerViews() {
        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        //Light views
        mMultiLightChoice = (Button) findViewById(R.id.light_multi_choice_dialog);
        mSimpleLightDialog = (Button) findViewById(R.id.light_simple_dialog);
        mListLightDialog = (Button) findViewById(R.id.light_list_dialog);
        mRadioLightDialog = (Button) findViewById(R.id.light_radio_dialog);

        //Dark views
        mMultiDarkChoice = (Button) findViewById(R.id.dark_multi_choice_dialog);
        mSimpleDarkDialog = (Button) findViewById(R.id.dark_simple_dialog);
        mListDarkDialog = (Button) findViewById(R.id.dark_list_dialog);
        mRadioDarkDialog = (Button) findViewById(R.id.dark_radio_dialog);
    }

    //Lets just say views needs listeners
    private void addListenersToViews() {
        mMultiLightChoice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new MaterialDialog.Builder(DialogBoxesActivity.this)
                        .title("Select some planet")
                        .theme(Theme.LIGHT)
                        .items(R.array.planets)
                        .itemsCallbackMultiChoice(null, new MaterialDialog.ListCallbackMultiChoice() {
                            @Override
                            public boolean onSelection(MaterialDialog dialog, Integer[] which, CharSequence[] text) {
                                /**
                                 * If you use alwaysCallMultiChoiceCallback(), which is discussed below,
                                 * returning false here won't allow the newly selected check box to actually be selected.
                                 * See the limited multi choice dialog example in the sample project for details.
                                 **/
                                return true;
                            }
                        })
                        .positiveText("Select")
                        .show();
            }
        });

        mRadioLightDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new MaterialDialog.Builder(DialogBoxesActivity.this)
                        .title("Select your planet")
                        .items(R.array.planets)
                        .theme(Theme.LIGHT)
                        .itemsCallbackSingleChoice(-1, new MaterialDialog.ListCallbackSingleChoice() {
                            @Override
                            public boolean onSelection(MaterialDialog dialog, View view, int which, CharSequence text) {
                                /**
                                 * If you use alwaysCallSingleChoiceCallback(), which is discussed below,
                                 * returning false here won't allow the newly selected radio button to actually be selected.
                                 **/
                                return true;
                            }
                        })
                        .positiveText("Select")
                        .show();
            }
        });

        mListLightDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new MaterialDialog.Builder(DialogBoxesActivity.this)
                        .title("Planet List")
                        .theme(Theme.LIGHT)
                        .items(R.array.planets)
                        .itemsCallback(new MaterialDialog.ListCallback() {
                            @Override
                            public void onSelection(MaterialDialog dialog, View view, int which, CharSequence text) {
                            }
                        })
                        .show();
            }
        });

        mSimpleLightDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new MaterialDialog.Builder(DialogBoxesActivity.this)
                        .title("Simple Dialog")
                        .theme(Theme.LIGHT)
                        .content("This is a very simple dialog")
                        .positiveText("Yes")
                        .negativeText("No")
                        .show();
            }
        });

        //dark
        mMultiDarkChoice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new MaterialDialog.Builder(DialogBoxesActivity.this)
                        .title("Select some planets")
                        .theme(Theme.DARK)
                        .items(R.array.planets)
                        .itemsCallbackMultiChoice(null, new MaterialDialog.ListCallbackMultiChoice() {
                            @Override
                            public boolean onSelection(MaterialDialog dialog, Integer[] which, CharSequence[] text) {
                                /**
                                 * If you use alwaysCallMultiChoiceCallback(), which is discussed below,
                                 * returning false here won't allow the newly selected check box to actually be selected.
                                 * See the limited multi choice dialog example in the sample project for details.
                                 **/
                                return true;
                            }
                        })
                        .positiveText("Select")
                        .show();
            }
        });

        mRadioDarkDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new MaterialDialog.Builder(DialogBoxesActivity.this)
                        .title("Select your planet")
                        .theme(Theme.DARK)
                        .items(R.array.planets)
                        .itemsCallbackSingleChoice(-1, new MaterialDialog.ListCallbackSingleChoice() {
                            @Override
                            public boolean onSelection(MaterialDialog dialog, View view, int which, CharSequence text) {
                                /**
                                 * If you use alwaysCallSingleChoiceCallback(), which is discussed below,
                                 * returning false here won't allow the newly selected radio button to actually be selected.
                                 **/
                                return true;
                            }
                        })
                        .positiveText("Select")
                        .show();
            }
        });

        mListDarkDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new MaterialDialog.Builder(DialogBoxesActivity.this)
                        .title("Planet List")
                        .theme(Theme.DARK)
                        .items(R.array.planets)
                        .itemsCallback(new MaterialDialog.ListCallback() {
                            @Override
                            public void onSelection(MaterialDialog dialog, View view, int which, CharSequence text) {
                            }
                        })
                        .show();
            }
        });

        mSimpleDarkDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new MaterialDialog.Builder(DialogBoxesActivity.this)
                        .title("Simple Dialog")
                        .theme(Theme.DARK)
                        .content("This is a very simple dialog")
                        .positiveText("Yes")
                        .negativeText("No")
                        .show();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_dialog_boxes, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
