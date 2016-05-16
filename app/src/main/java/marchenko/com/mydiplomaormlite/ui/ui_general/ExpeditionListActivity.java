package marchenko.com.mydiplomaormlite.ui.ui_general;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import marchenko.com.mydiplomaormlite.ActionModeHelper;
import marchenko.com.mydiplomaormlite.R;
import marchenko.com.mydiplomaormlite.ormsqlite_dao.OrmSqliteDayDao;
import marchenko.com.mydiplomaormlite.ormsqlite_dao.OrmSqliteExpeditionDao;
import marchenko.com.mydiplomaormlite.transfer_objects.transfer_objects_general.Day;
import marchenko.com.mydiplomaormlite.transfer_objects.transfer_objects_general.Expedition;

/**
 * Активити со списком всех экспедиций.
 */
public class ExpeditionListActivity extends AppCompatActivity {

    private final DateFormat df = new SimpleDateFormat(Day.DATE_FORMAT);
    private ListView listView;
    private List<Expedition> listExpeditions;
    private ArrayAdapter<Expedition> arrayAdapter;

    /**
     * Выполняет действия над выделенным эл-том списка, которые соответствуют нажатой иконке бара.
     * @param itemId id  элемента списка всех экспедиций
     * @param position позиция выделенного юзером элемента списка всех экспедиций
     * @return
     */
    public boolean performAction(int itemId, int position) {
        switch (itemId) {
            case R.id.menu_delete:
                Expedition expeditionForDel = listExpeditions.get(position);
                arrayAdapter.remove(expeditionForDel);
                new OrmSqliteExpeditionDao().deleteExpedition(expeditionForDel);
                return(true);
            case R.id.menu_edit:
                Expedition expedition = (Expedition) listView.getAdapter().getItem(position);
                CreateExpeditionActivity.callMe(ExpeditionListActivity.this, expedition.getId());
                return(true);
        }
        return(false);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expedition_list);
        setToolbarAndFAB();

        listView = (ListView) findViewById(R.id.expeditions_listView);
        listView.setLongClickable(true);
        listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        // по короткому клику на элемент списка показать полную информацию по выбранной экспедеции
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Expedition expedition = (Expedition) listView.getAdapter().getItem(i);
                ExpeditionInfoActivity.callMe(ExpeditionListActivity.this, expedition.getId());
            }
        });
        // по длинному клику на элемент списка высветить меню в баре
        listView.setOnItemLongClickListener(new ActionModeHelper(this, listView));
    }

    @Override
    protected void onResume() {
        super.onResume();
        fillListWithDataFromBD();
    }

    /**
     * Открыть форму создания новой экспедиции по нажатию на FAB
     */
    private void setToolbarAndFAB() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CreateExpeditionActivity.callMe(ExpeditionListActivity.this);
            }
        });
    }

    private void fillListWithDataFromBD() {
        listExpeditions = new OrmSqliteExpeditionDao().selectExpeditionOrderedByName();
        arrayAdapter  = new ExpeditionsAdapter(this, R.layout.expedition_row, listExpeditions);
        listView.setAdapter(arrayAdapter);
    }

    private class ExpeditionsAdapter extends ArrayAdapter<Expedition> {

        public ExpeditionsAdapter(Context context, int textViewResourceId, List<Expedition> items) {
            super(context, textViewResourceId, items);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View v = convertView;
            if (v == null) {
                LayoutInflater vi = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                v = vi.inflate(R.layout.expedition_row, null);
            }
            Expedition expedition = getItem(position);
            if (expedition != null) {
                fillText(v, R.id.expeditionName_textView, expedition.getName());
                fillText(v, R.id.expeditionMission_textView, expedition.getMission());
                Day day = new OrmSqliteDayDao().findFirstDayOfExpedition(expedition);
                if (day == null) {
                    fillText(v, R.id.expeditionData_textView, "");
                } else {
                    fillText(v, R.id.expeditionData_textView, df.format(day.getDateTime()));
                }
            }
            return v;
        }

        private void fillText(View v, int id, String text) {
            TextView textView = (TextView) v.findViewById(id);
            textView.setText(text == null ? "" : text);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.menu_info) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
