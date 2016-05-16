package marchenko.com.mydiplomaormlite.ui.ui_general;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import marchenko.com.mydiplomaormlite.R;
import marchenko.com.mydiplomaormlite.ormsqlite_dao.OrmSqliteDayDao;
import marchenko.com.mydiplomaormlite.ormsqlite_dao.OrmSqliteExpeditionDao;
import marchenko.com.mydiplomaormlite.ormsqlite_dao.OrmSqlitePersonDao;
import marchenko.com.mydiplomaormlite.transfer_objects.transfer_objects_general.Day;
import marchenko.com.mydiplomaormlite.transfer_objects.transfer_objects_general.Expedition;
import marchenko.com.mydiplomaormlite.transfer_objects.transfer_objects_general.Person;

public class ExpeditionInfoActivity extends AppCompatActivity {
    private final DateFormat df = new SimpleDateFormat(Day.DATE_FORMAT);
    private static final String EXPEDITION_ID = "expeditionId";
    private int expeditionId;

    View footer;
    View header;

    private ListView listView;
    private List<Day> listDays;
    private ArrayAdapter<Day> arrayAdapter;

    private DatePickerDialog pickerDlgFromDate;

    private void setDateTimeField() {
        Calendar newCalendar = Calendar.getInstance();
        pickerDlgFromDate = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(Calendar.MILLISECOND, 0);
                newDate.set(Calendar.SECOND, 0);
                newDate.set(Calendar.MINUTE, 0);
                newDate.set(Calendar.HOUR_OF_DAY, 0);
                newDate.set(year, monthOfYear, dayOfMonth);

                Day newDay = new Day();
                newDay.setExpedition(new OrmSqliteExpeditionDao().findExpedition(expeditionId));
                newDay.setDateTime(newDate.getTime());

                OrmSqliteDayDao dayDao = new OrmSqliteDayDao();
                if (dayDao.createIfNotExistInThatExpedition(newDay)) {
                    listDays.add(newDay);
                }
                arrayAdapter.notifyDataSetChanged();
            }

        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expedition_info);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        expeditionId = getIntent().getIntExtra(EXPEDITION_ID, -1);
        footer = createFooter();
        header = createHeader();

        OrmSqliteExpeditionDao expDao = new OrmSqliteExpeditionDao();
        Expedition expedition = expDao.findExpedition(expeditionId);
        fillText(R.id.expeditionName_textView, expedition.getName());
        fillText(R.id.expeditionMission_textView, expedition.getMission());

        double d = expedition.getLatitude();
        int integ = (int) d;
        int resMin = (int) ((d % 1) * 60);
        int sec = (int) ((d % 1) * 3600 % 60);
        String strLatitude = "; " + integ + "°" + resMin + "\'" + sec + "\".";

        OrmSqliteDayDao dayDao = new OrmSqliteDayDao();
        Day firsDay = dayDao.findFirstDayOfExpedition(expedition);
        if (firsDay != null) {
            fillText(R.id.firstDateAndLatitude_textView, df.format(firsDay.getDateTime()) + strLatitude);
        }

        /////////////// second block
        setDateTimeField();
        listView = (ListView) findViewById(R.id.days_listView);
        listView.setLongClickable(true);
        listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        // по короткому клику на элемент списка показать полную информацию по выбранной экспедеции
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Day currentDay = (Day) listView.getAdapter().getItem(i);
                GroupsListActivity.callMe(ExpeditionInfoActivity.this, expeditionId, currentDay.getId());
            }
        });


        listDays = new OrmSqliteDayDao().findAllDaysOfExpedition(expeditionId);
        arrayAdapter  = new DaysAdapter(this, R.layout.expedition_row, listDays);
        listView.addHeaderView(header);
        listView.addFooterView(footer);
        listView.setAdapter(arrayAdapter);

        // по длинному клику на элемент списка высветить меню в баре
        //listView.setOnItemLongClickListener(new ActionModeHelper(this, listView));

        ///////////// third block

    }

    View createFooter() {
        View v = getLayoutInflater().inflate(R.layout.footer_for_list_days, null);
        return v;
    }

    View createHeader() {
        View v = getLayoutInflater().inflate(R.layout.header_for_list_days, null);
        return v;
    }

    public static void callMe(Context c, Integer clickCounterId) {
        Intent intent = new Intent(c, ExpeditionInfoActivity.class);
        intent.putExtra(EXPEDITION_ID, clickCounterId);
        c.startActivity(intent);
    }

    private class DaysAdapter extends ArrayAdapter<Day> {
        public DaysAdapter(Context context, int textViewResourceId, List<Day> items) {
            super(context, textViewResourceId, items);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View v = convertView;
            if (v == null) {
                LayoutInflater vi = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                v = vi.inflate(R.layout.day_row, null);
            }
            Day day = getItem(position);
            if (day != null) {
                fillText(v, R.id.day_textView, df.format(day.getDateTime()));
            }
            return v;
        }

        private void fillText(View v, int id, String text) {
            TextView textView = (TextView) v.findViewById(id);
            textView.setText(text == null ? "" : text);
        }

    }

    private void fillText(int resId, String text) {
        TextView textView = (TextView) findViewById(resId);
        textView.setText(text);
    }


//    private void click() {
//        int value = countValue.incrementAndGet();
//
//        AsyncTask<Integer, Void, Void> asyncTask = new AsyncTask<Integer, Void, Void>() {
//
//            @Override
//            protected Void doInBackground(Integer... integers) {
//                Integer countId = integers[0];
//                Integer countValue = integers[1];
//
//                try {
//                    Dao<ClickCount, Integer> dao = getHelper().getClickDao();
//                    ClickCount clickCount = dao.queryForId(countId);
//                    if (clickCount.getValue() < countValue) {
//                        clickCount.changeValue(countValue);
//                        dao.update(clickCount);
//                    }
//                } catch (SQLException e) {
//                    throw new RuntimeException(e);
//                }
//
//                return null;
//            }
//
//            @Override
//            protected void onPostExecute(Void aVoid) {
//                updateScreenValue();
//                MediaPlayer mp = MediaPlayer.create(CounterScreen.this, R.raw.click);
//                mp.start();
//            }
//        };
//
//        asyncTask.execute(clickCounterid, value);
//    }

    public void clickAddMember(View view) {
        LinearLayout layoutConteinerForAllMembers = (LinearLayout) findViewById(R.id.layout_members);

        LayoutInflater inflater = LayoutInflater.from(this);
        final LinearLayout layout = (LinearLayout) inflater.inflate(R.layout.member_row, null, false);

        Button buttonDel = (Button) layout.findViewById(R.id.deleteMember_button);
        buttonDel.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                ((LinearLayout) layout.getParent()).removeView(layout);
            }
        });
        layoutConteinerForAllMembers.addView(layout);
    }

    public void clickAddDay(View view) {
        pickerDlgFromDate.show();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_actions_mode, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.menu_edit) {
            return true;
        } else if (id == R.id.menu_delete) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    public void buttonCancelClicked(View view) {
        finish();
    }

    public void buttonSaveClicked(View view) {
        LinearLayout layoutConteinerForAllMembers = (LinearLayout) findViewById(R.id.layout_members);
        int childcountLayoutsMembers = layoutConteinerForAllMembers.getChildCount();
        LinearLayout[] layoutMember = new LinearLayout[childcountLayoutsMembers];
        Person[] person = new Person[childcountLayoutsMembers];
        for (int i=0; i < childcountLayoutsMembers; i++){
            layoutMember[i] = new LinearLayout(this);
            layoutMember[i] = (LinearLayout) layoutConteinerForAllMembers.getChildAt(i);

//            int numViewsInLayout = layoutMember[i].getChildCount();// их будет 4: name, sername, secName, button
//            for (int j=0; j < numViewsInLayout; j++) {}
            View vName = layoutMember[i].getChildAt(0);
            View vSername = layoutMember[i].getChildAt(1);
            View vSecName = layoutMember[i].getChildAt(2);
            person[i] = new Person();
            person[i].setName(getTextFromEditText(vName));
            person[i].setSurname(getTextFromEditText(vSername));
            person[i].setMiddleName(getTextFromEditText(vSecName));
        }
        OrmSqlitePersonDao ormSqlitePersonDao = new OrmSqlitePersonDao();
        ormSqlitePersonDao.insertListPersons(person);
    }

    String getTextFromEditText(View unknownView) {
        String text = "";
        if (unknownView instanceof EditText) {
            text = ((EditText) unknownView).getText().toString();
        }
        return text;
    }

}
