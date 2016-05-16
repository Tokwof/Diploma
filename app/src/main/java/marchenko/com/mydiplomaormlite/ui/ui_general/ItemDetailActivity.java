package marchenko.com.mydiplomaormlite.ui.ui_general;

import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.ActionBar;
import android.view.MenuItem;

import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.Calendar;

import marchenko.com.mydiplomaormlite.R;
import marchenko.com.mydiplomaormlite.mydb.DatabaseManager;
import marchenko.com.mydiplomaormlite.transfer_objects.transfer_objects_general.GroupOfResearcher;
import marchenko.com.mydiplomaormlite.transfer_objects.transfer_objects_general.Interval;
import marchenko.com.mydiplomaormlite.ui.ui_avtomat_meteor_syst.FillMeteorsDataActivity;

/**
 * An activity representing a single Item detail screen. This
 * activity is only used narrow width devices. On tablet-size devices,
 * item details are presented side-by-side with a list of items
 * in a {@link GroupsListActivity}.
 */
public class ItemDetailActivity extends AppCompatActivity {
    private static final String DAY_ID = "dayId";
    private static final String EXPEDITION_ID = "expeditionId";
    private int expeditionId, dayId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.detail_toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own detail action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        // Show the Up button in the action bar.
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        expeditionId = getIntent().getIntExtra(EXPEDITION_ID, -1);
        dayId = getIntent().getIntExtra(DAY_ID, -1);

        // savedInstanceState is non-null when there is fragment state
        // saved from previous configurations of this activity
        // (e.g. when rotating the screen from portrait to landscape).
        // In this case, the fragment will automatically be re-added
        // to its container so we don't need to manually add it.
        // For more information, see the Fragments API guide at:
        //
        // http://developer.android.com/guide/components/fragments.html
        //
        if (savedInstanceState == null) {
            // Create the detail fragment and add it to the activity
            // using a fragment transaction.
            Bundle arguments = new Bundle();
            arguments.putString(ItemDetailFragment.ARG_ITEM_ID,
                    getIntent().getStringExtra(ItemDetailFragment.ARG_ITEM_ID));

            arguments.putInt(ItemDetailFragment.DAY_ID,dayId);
            arguments.putInt(ItemDetailFragment.EXPEDITION_ID,expeditionId);

            ItemDetailFragment fragment = new ItemDetailFragment();
            fragment.setArguments(arguments);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.item_detail_container, fragment)
                    .commit();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            // This ID represents the Home or Up button. In the case of this
            // activity, the Up button is shown. For
            // more details, see the Navigation pattern on Android Design:
            //
            // http://developer.android.com/design/patterns/navigation.html#up-vs-back
            //
            navigateUpTo(new Intent(this, GroupsListActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    ///////////////////////////////
    public void clickBeginIntervalTime(View v){
        DialogFragment newFragment = new TimePickerFragment();
        newFragment.show(getFragmentManager(),"TimePicker");
    }

    ///////////////////////////////
    public void clickAddIntevals(View v){
    }

    public void clickGoToIntevalsActivity(View v){
        //Interval currentInterval = (Interval) listView.getAdapter().getItem(i);
        CurrentIntervalActivity.callMe(ItemDetailActivity.this, 1/*interval.getId()*/);
    }


    public void clickStartActivityFillMeteorData(View v){
        GroupOfResearcher groupOfResearcher = new GroupOfResearcher();
        groupOfResearcher.setName("Зеленые");
        Dao<GroupOfResearcher, Integer> groupsDao = DatabaseManager.getInstance().getHelper().getGroupOfResearcherDao();
        try {
            groupsDao.create(groupOfResearcher);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Interval interval = new Interval();
        interval.setNumber(1);
        interval.setClouds(0.5);
        interval.setGroup(groupOfResearcher);
        java.util.Date date = Calendar.getInstance().getTime();
        interval.setTimeBegin(date);
        Intent myIntent = new Intent(ItemDetailActivity.this, FillMeteorsDataActivity.class);
        myIntent.putExtra(DAY_ID, dayId);
        myIntent.putExtra(EXPEDITION_ID, expeditionId);
        ItemDetailActivity.this.startActivity(myIntent);
    }
}
