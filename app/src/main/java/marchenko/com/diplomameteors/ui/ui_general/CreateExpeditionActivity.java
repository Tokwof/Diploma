package marchenko.com.diplomameteors.ui.ui_general;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import marchenko.com.diplomameteors.R;
import marchenko.com.diplomameteors.ormsqlite_dao.OrmSqliteDayDao;
import marchenko.com.diplomameteors.ormsqlite_dao.OrmSqliteExpeditionDao;
import marchenko.com.diplomameteors.transfer_objects.transfer_objects_general.Day;
import marchenko.com.diplomameteors.transfer_objects.transfer_objects_general.Expedition;

/**
 * Форма создания новой экспедиции.
 */
public class CreateExpeditionActivity extends AppCompatActivity implements View.OnClickListener {
    private String TAG = "MY_TAG";
    private static final String EXPEDITION_ID = "expeditionId";
    private final DateFormat df = new SimpleDateFormat(Day.DATE_FORMAT);
    private final SimpleDateFormat dateFormatter = new SimpleDateFormat(Day.DATE_FORMAT);

    private Expedition expeditBuf;

    private EditText etExpeditionName;
    private EditText etExpeditionMission;
    private EditText etFromDate;
    private EditText etLatitudeDigree;
    private EditText etLatitudeMin;
    private EditText etLatitudeSec;
    private Button buttonSaveExpedition;
    private Button buttonCancelExpedition;
    private DatePickerDialog pickerDlgFromDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_expedition);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        expeditBuf = new Expedition();
        findViewsById();
        setDateTimeField();
        addListenersToViews();
        reInit(savedInstanceState);
    }

    // вызывается для создания экспедиции
    public static void callMe(Context c) {
        c.startActivity(new Intent(c, CreateExpeditionActivity.class));
    }

    // вызывается для редактирования существующей экспедиции
    public static void callMe(Context c, Integer expeditionId) {
        Intent intent = new Intent(c, CreateExpeditionActivity.class);
        intent.putExtra(EXPEDITION_ID, expeditionId);
        c.startActivity(intent);
    }

    private void findViewsById() {
        etFromDate = (EditText) findViewById(R.id.fromDate_editText);
        etFromDate.setInputType(InputType.TYPE_NULL);
        etFromDate.requestFocus();

        etExpeditionName = (EditText) findViewById(R.id.expeditionName_editText);
        etExpeditionMission = (EditText) findViewById(R.id.expeditionMission_editText);

        etLatitudeDigree = (EditText) findViewById(R.id.latitude_digree_editText);
        etLatitudeMin = (EditText) findViewById(R.id.latitude_min_editText);
        etLatitudeSec = (EditText) findViewById(R.id.latitude_sec_editText);

        buttonCancelExpedition = (Button) findViewById(R.id.cancel_button);
        buttonSaveExpedition = (Button) findViewById(R.id.save_button);
    }

    private void setDateTimeField() {
        Calendar newCalendar = Calendar.getInstance();
        pickerDlgFromDate = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                newDate.set(Calendar.MILLISECOND, 0);
                newDate.set(Calendar.SECOND, 0);
                newDate.set(Calendar.MINUTE, 0);
                newDate.set(Calendar.HOUR_OF_DAY, 0);
                etFromDate.setText(dateFormatter.format(newDate.getTime()));
            }

        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

    }

    @Override
    public void onClick(View view) {
        if (view == etFromDate) {
            pickerDlgFromDate.show();
        } else if (view == buttonCancelExpedition) {
            startActivity(new Intent(this, ExpeditionListActivity.class));
            finish();
        } else if (view == buttonSaveExpedition) {
            final Expedition newVersionExpedition = saveToObj();
            final OrmSqliteExpeditionDao expeditionDao = new OrmSqliteExpeditionDao();
            OrmSqliteDayDao dayDao = new OrmSqliteDayDao();
            boolean alreadyCreated = false;
            if (newVersionExpedition.getId() != 0) {
                Expedition dbExp = expeditionDao.findExpedition(newVersionExpedition.getId());
                Day dayOld = dayDao.findFirstDayOfExpedition(dbExp);
                int oldDayId = dayOld.getId();
                dbExp.setFirstDay(df.format(dayOld.getDateTime()));
                // если запись в БД такая уже есть и мы редактируем существующее, а не добавляем новую экспедицию
                if (dbExp != null) {
                    expeditionDao.updateExpedition(newVersionExpedition, oldDayId);
                    alreadyCreated = true;
                }
            }
            Toast toast = Toast.makeText(getApplicationContext(),
                    getResources().getString(R.string.change_saving),
                    Toast.LENGTH_LONG);
            toast.show();

            if (alreadyCreated) {
                Thread thread = new Thread(){
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(3000); // As I am using LENGTH_LONG in Toast
                            finish();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                };
                thread.start();

            } else {
                Thread thread = new Thread(){
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(3500); // As I am using LENGTH_LONG in Toast
                            expeditionDao.createExpedition(newVersionExpedition);
                            ExpeditionInfoActivity.callMe(CreateExpeditionActivity.this, newVersionExpedition.getId());
                            finish();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                };
                thread.start();

            }

        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Expedition expedition = saveToObj();
        outState.putSerializable("EXPEDITION", expedition);
    }

    private void reInit(Bundle savedInstanceState) {
        setSaveButtonVisibility();
        if (savedInstanceState != null && savedInstanceState.get(EXPEDITION_ID) != null) {
            loadFromObj((Expedition) savedInstanceState.get(EXPEDITION_ID));
        } else {
            int expeditionId = getExpeditionId();
            if (expeditionId > -1) {
                Expedition expedition = new OrmSqliteExpeditionDao().findExpedition(expeditionId);
                if (expedition != null) {
                    Day day = new OrmSqliteDayDao().findFirstDayOfExpedition(expedition);
                    expedition.setFirstDay(df.format(day.getDateTime()));
                    loadFromObj(expedition);
                }
            }
        }
    }

    private class CustomWatcher implements TextWatcher {

        public void afterTextChanged(Editable s) {
            setSaveButtonVisibility();
        }

        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        public void onTextChanged(CharSequence s, int start, int before, int count) {
        }
    }

    private void addListenersToViews() {
        etFromDate.setOnClickListener(this);
        buttonSaveExpedition.setOnClickListener(this);
        buttonCancelExpedition.setOnClickListener(this);
        etFromDate.addTextChangedListener(new CustomWatcher());
        etExpeditionName.addTextChangedListener(new CustomWatcher());
        etExpeditionMission.addTextChangedListener(new CustomWatcher());

        etLatitudeDigree.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
                setSaveButtonVisibility();
                if (s.toString().isEmpty()) {
                    expeditBuf.setLatitude(0);
                    return;
                }
                int resultDegree = Integer.parseInt(s.toString());
                int dataSec;
                int dataMin;
                try {
                    dataMin = Integer.parseInt(etLatitudeMin.getText().toString());
                    dataSec = Integer.parseInt(etLatitudeSec.getText().toString());
                } catch (NumberFormatException e) {
                    dataMin = 0;
                    dataSec = 0;
                }

                double wholeRes = resultDegree + (double) dataMin / 60.0 + (double) dataSec / 3600.0;
                boolean validData = expeditBuf.setLatitude(wholeRes);
                etLatitudeDigree.setTextColor(acceptedTextColor(validData));
                etLatitudeMin.setTextColor(acceptedTextColor(validData));
                etLatitudeSec.setTextColor(acceptedTextColor(validData));
                Log.d(TAG, "wholeRes2_1 = " + wholeRes);
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
        });

        etLatitudeMin.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
                setSaveButtonVisibility();
                if (s.toString().isEmpty()) {
                    expeditBuf.setLatitudeObservations_fi_minutes(0);
                    return;
                }
                int resultMin = Integer.parseInt(s.toString());
                int dataDig;
                int dataSec;
                try {
                    dataDig = Integer.parseInt(etLatitudeDigree.getText().toString());
                    dataSec = Integer.parseInt(etLatitudeSec.getText().toString());
                } catch (NumberFormatException e) {
                    dataDig = 0;
                    dataSec = 0;
                }
                boolean validData = expeditBuf.setLatitudeObservations_fi_minutes(resultMin);
                etLatitudeMin.setTextColor(acceptedTextColor(validData));
                double wholeRes = dataDig + (double) resultMin / 60.0 + (double) dataSec / 3600.0;
                boolean validData2 = expeditBuf.setLatitude(wholeRes);
                Log.d(TAG, "wholeRes2 = " + wholeRes);
                etLatitudeDigree.setTextColor(acceptedTextColor(validData2));
                etLatitudeSec.setTextColor(acceptedTextColor(validData2));
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
        });

        etLatitudeSec.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
                setSaveButtonVisibility();
                if (s.toString().isEmpty()) {
                    expeditBuf.setLatitudeObservations_fi_seconds(0);
                    return;
                }
                int resultSec = Integer.parseInt(s.toString());
                int dataDig;
                int dataMin;
                try {
                    dataDig = Integer.parseInt(etLatitudeDigree.getText().toString());
                    dataMin = Integer.parseInt(etLatitudeMin.getText().toString());
                } catch (NumberFormatException e) {
                    dataDig = 0;
                    dataMin = 0;
                }
                boolean validData = expeditBuf.setLatitudeObservations_fi_seconds(resultSec);
                etLatitudeSec.setTextColor(acceptedTextColor(validData));
                // if(validData) {
                double wholeRes = dataDig + (double) resultSec / 3600.0 + dataMin;
                boolean validData2 = expeditBuf.setLatitude(wholeRes);
                Log.d(TAG, "wholeRes2 = " + wholeRes);
                etLatitudeDigree.setTextColor(acceptedTextColor(validData2));
                etLatitudeMin.setTextColor(acceptedTextColor(validData2));

            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
        });
    }

    private void setSaveButtonVisibility() {
        boolean isAllowed = isAllFieldsFilled();
        buttonSaveExpedition.setEnabled(isAllowed);
    }

    // если данные не попадают в заданные границы значений - красный цвет, иначе - зеленый
    private int acceptedTextColor(boolean validData) {
        return validData ? Color.GREEN : Color.RED;
    }

    private Expedition saveToObj() {
        Expedition c = new Expedition();

        int expeditionId = getExpeditionId();
        if (expeditionId > -1) {
            c.setId(expeditionId);
        }

        c.setMission(etExpeditionMission.getText().toString());
        c.setName(etExpeditionName.getText().toString());
        c.setFirstDay(etFromDate.getText().toString());
        c.setLatitude(readLatitudeFromAllViews());
        return c;
    }

    private void loadFromObj(Expedition c) {
        etExpeditionName.setText(c.getName());
        etExpeditionMission.setText(c.getMission());
        etFromDate.setText(c.getFirstDay());

        double d = c.getLatitude();
        int integ = (int) d;
        int resMin = (int) ((d % 1) * 60);
        int sec = (int) ((d % 1) * 3600 % 60);

        etLatitudeDigree.setText(String.valueOf(integ));
        etLatitudeMin.setText(String.valueOf(resMin));
        etLatitudeSec.setText(String.valueOf(sec));
    }

    private double readLatitudeFromAllViews() {
        double degree;
        double min;
        double sec;
        try {
            degree = Double.valueOf(etLatitudeDigree.getText().toString());
        } catch (NumberFormatException e) {
            degree = 0;
        }
        try {
            min = Double.valueOf(etLatitudeMin.getText().toString()) / 60.0;
        } catch (NumberFormatException e) {
            min = 0;
        }
        try {
            sec = Double.valueOf(etLatitudeSec.getText().toString()) / 3600.0;
        } catch (NumberFormatException e) {
            sec = 0;
        }
        return degree + min + sec;
    }

    private int getExpeditionId() {
        return getIntent().getIntExtra(EXPEDITION_ID, -1);
    }

    private boolean isAllFieldsFilled() {
        if (etFromDate.getText().toString().isEmpty() ||
                etExpeditionName.getText().toString().isEmpty() ||
                etExpeditionMission.getText().toString().isEmpty() ||
                etLatitudeDigree.getText().toString().isEmpty() ||
                etLatitudeMin.getText().toString().isEmpty() ||
                etLatitudeSec.getText().toString().isEmpty()) {
            return false;
        }
        return true;
    }

}
