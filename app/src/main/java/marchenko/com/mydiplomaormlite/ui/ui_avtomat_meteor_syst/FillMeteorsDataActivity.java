package marchenko.com.mydiplomaormlite.ui.ui_avtomat_meteor_syst;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import marchenko.com.mydiplomaormlite.R;
import marchenko.com.mydiplomaormlite.counters.CounterEquatorialCoordinate;
import marchenko.com.mydiplomaormlite.maps_pictures.DrawingSaving;
import marchenko.com.mydiplomaormlite.ormsqlite_dao.OrmSqliteDayDao;
import marchenko.com.mydiplomaormlite.ormsqlite_dao.OrmSqliteEqСoordDao;
import marchenko.com.mydiplomaormlite.ormsqlite_dao.OrmSqliteExpeditionDao;
import marchenko.com.mydiplomaormlite.ormsqlite_dao.OrmSqliteMeteorDao;
import marchenko.com.mydiplomaormlite.transfer_objects.transfer_objects_avtomat_meteor_syst.HorizontalCoordinates;
import marchenko.com.mydiplomaormlite.transfer_objects.transfer_objects_general.Day;
import marchenko.com.mydiplomaormlite.transfer_objects.transfer_objects_general.EquatorialСoordinate;
import marchenko.com.mydiplomaormlite.transfer_objects.transfer_objects_general.Meteor;

public class FillMeteorsDataActivity extends AppCompatActivity {
    String DUMMY_INTERVAL = "22:50";
    private static final String DAY_ID = "dayId";
    private static final String EXPEDITION_ID = "expeditionId";
    static int expeditionId;
    static int dayId;
    String METEOR_COUNT = "meteorCount";
    private static int meteorCount=1;

    Meteor meteor = new Meteor();

    TextView intervalTime_textView;
    TextView groupName_textView;

    TableLayout tableLayoutConteinerForAllMeteors;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fill_meteors_data);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addMeteorRowToTable();
                meteorCount++;
            }
        });
        tableLayoutConteinerForAllMeteors = (TableLayout) findViewById(R.id.conteinerMeteors_tableLayout);

        expeditionId = getIntent().getIntExtra(EXPEDITION_ID, -1);
        dayId = getIntent().getIntExtra(DAY_ID, -1);
        OrmSqliteDayDao dayDao = new OrmSqliteDayDao();
        DateFormat df = new SimpleDateFormat(Day.DATE_FORMAT);
        String strData = df.format(dayDao.findDay(dayId).getDateTime());

        intervalTime_textView = (TextView) findViewById(R.id.intervalTime_textView);
        intervalTime_textView.setText(strData +" - " + DUMMY_INTERVAL);
    }

    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(METEOR_COUNT, meteorCount);
    }

    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        meteorCount = savedInstanceState.getInt(METEOR_COUNT);
    }

    private void addMeteorRowToTable () {


        LayoutInflater inflater = LayoutInflater.from(FillMeteorsDataActivity.this);
        final LinearLayout layout = (LinearLayout) inflater.inflate(R.layout.meteor_row, null, false);

        final EditText etZenithDistance;
        final EditText etP;
        final EditText etDashedP;
        final EditText etLength;
        etZenithDistance = (EditText) layout.findViewById(R.id.zenith_distance_editText);
        etDashedP = (EditText) layout.findViewById(R.id.dashed_p_editText);
        etP = (EditText) layout.findViewById(R.id.p_editText);
        etLength  = (EditText) layout.findViewById(R.id.length_editText);


        etZenithDistance.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
                boolean isValidData = isValideData("etZenithDistance", s.toString());
                etZenithDistance.setTextColor(acceptedTextColor(isValidData));
            }
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
        });

        etDashedP.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
                boolean isValidData = isValideData("etDashedP", s.toString());
                etDashedP.setTextColor(acceptedTextColor(isValidData));
            }
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
        });

        etP.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
                boolean isValidData = isValideData("etP", s.toString());
                etP.setTextColor(acceptedTextColor(isValidData));
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
        });

        etLength.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
                boolean isValidData = isValideData("etLength", s.toString());
                etLength.setTextColor(acceptedTextColor(isValidData));
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
        });
        TextView id_tv = (TextView) layout.findViewById(R.id.id_textView);

        id_tv.setText(String.valueOf(meteorCount));
        Button buttonDel = (Button) layout.findViewById(R.id.deleteMeteorRow_button);
        buttonDel.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                ((LinearLayout) layout.getParent()).removeView(layout);
            }
        });
        tableLayoutConteinerForAllMeteors.addView(layout);
    }


    // если данные не попадают в заданные границы значений - красный цвет, иначе - зеленый
    private int acceptedTextColor(boolean validData) {
        return validData ? Color.GREEN : Color.RED;
    }

    // приведение нестандартного формата вроде ".", ".365464" или пустого значения к корректному
    private double getCorrectDouble(String strResult) {
        double result = 0;
        try {
            result = Double.parseDouble(strResult);
        } catch (NumberFormatException e) {
            if (strResult.charAt(0) == '.') {
                strResult = "0".concat(strResult);
                result = Double.parseDouble(strResult);
            }
        }
        return result;
    }

    private boolean isValideData(String viewName, String data) {
        if (data.isEmpty()) {
            return false;
        }
        int iResult;
        double dResult;
        switch (viewName) {
            case "etZenithDistance":
                iResult = Integer.parseInt(data);
                return meteor.setZenit(iResult);
            case "etP":
                dResult = getCorrectDouble(data);
                return meteor.setPprime(dResult);
            case "etDashedP":
                dResult = getCorrectDouble(data);
                return meteor.setP(dResult);
            case "etLength":
                dResult = getCorrectDouble(data);
                Log.d("Tag", "etLength hhhhhhh" + dResult +" "+data);
                return meteor.setLength(dResult);
        }
        return false;
    }

    public void testValues(View view) {
        TextView tvHorizCoor = (TextView) findViewById(R.id.horizontal_coord_textView);
        TextView tvEquatCoor = (TextView) findViewById(R.id.equatorial_coord_textView);
        OrmSqliteExpeditionDao expeditionDao = new OrmSqliteExpeditionDao();
        double latitude = expeditionDao.findExpedition(expeditionId).getLatitude();

        OrmSqliteMeteorDao meteorDao = new OrmSqliteMeteorDao();
        OrmSqliteEqСoordDao eqCoordDao = new OrmSqliteEqСoordDao();
        List<Meteor> meteorsList = meteorDao.findAllMeteors();
        StringBuilder sbHorizontCoord = new StringBuilder();
        StringBuilder sbEquatorialCoord = new StringBuilder();

        for (Meteor meteor : meteorsList) {
            HorizontalCoordinates horizontalCoordinates = new HorizontalCoordinates();
            horizontalCoordinates.countAzimuth(meteor.getPprime());
            sbHorizontCoord.append("\nN ").append(meteor.getNumber()).append(", азимут = ").append(horizontalCoordinates.getAzimuth());

            EquatorialСoordinate equatorialСoordinate = new EquatorialСoordinate();
            CounterEquatorialCoordinate counter = new CounterEquatorialCoordinate();
            // склонение
            double delta = counter.countDeclension_delta(latitude, meteor.getZenit(), horizontalCoordinates.getAzimuth());
            double hourAngle = counter.countHourAngleInDegree(latitude, meteor.getZenit(), horizontalCoordinates.getAzimuth(), delta);
            equatorialСoordinate.setDeclension(delta);
            equatorialСoordinate.setHourAngleInDegree(hourAngle);
            equatorialСoordinate.setHourAngleInHour(counter.countHourAngleInHour(hourAngle));
            equatorialСoordinate.setMeteor(meteor);
            equatorialСoordinate.setQuadrantNumber(counter.getNumberOfQuadrant());
            eqCoordDao.insertEqСoord(equatorialСoordinate);
            sbEquatorialCoord.append("\nN ").append(meteor.getNumber()).append(", склонение = ").append(delta);
            sbEquatorialCoord.append(", часовой угол = ").append(equatorialСoordinate.getHourAngleInHour());
        }
        tvHorizCoor.setText(sbHorizontCoord);
        tvEquatCoor.setText(sbEquatorialCoord);

        //Log.d();
//        horizontalCoordinates.countHeight();
    }

    public void clickDrawOnMap(View view) {
        OrmSqliteEqСoordDao eqCoordDao = new OrmSqliteEqСoordDao();
        DrawingSaving drawingSaving = new DrawingSaving(this);
        drawingSaving.drawingLines(eqCoordDao.getAllEqCoord());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_meteors, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.menu_save) {
            saveClicked();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void clickClearTableMeteors(View view) {
        OrmSqliteMeteorDao meteorDao = new OrmSqliteMeteorDao();
        meteorDao.clearTableMeteors();
        callMe(this);
        finish();
    }

    public static void callMe(Context c) {
        Intent intent = new Intent(c, FillMeteorsDataActivity.class);
        meteorCount = 1;
        intent.putExtra(EXPEDITION_ID, expeditionId);
        intent.putExtra(DAY_ID, dayId);
        c.startActivity(intent);
    }

    public void saveClicked() {
        int childcountLayoutsMembers = tableLayoutConteinerForAllMeteors.getChildCount();// в нем ещё и шапка
        TableRow[] rawMeteors = new TableRow[childcountLayoutsMembers];
        Meteor[] meteorsArray = new Meteor[childcountLayoutsMembers-1];
        for (int i=1; i < childcountLayoutsMembers; i++){
            rawMeteors[i-1] = new TableRow(this);
            rawMeteors[i-1] = (TableRow) tableLayoutConteinerForAllMeteors.getChildAt(i);

      //      int numColumns = rawMeteors[i].getChildCount();// их будет 6: N, Z, P, P', lambda, delButton
            View numberMeteor = rawMeteors[i-1].getChildAt(0);
                View vZ = rawMeteors[i-1].getChildAt(1);
                View vP = rawMeteors[i-1].getChildAt(2);
                View vPP = rawMeteors[i-1].getChildAt(3);
            View vLength = rawMeteors[i-1].getChildAt(4);
            meteorsArray[i-1] = new Meteor();
            meteorsArray[i-1].setNumber(getTextFromEditTextInt(numberMeteor));
            meteorsArray[i-1].setZenit(getTextFromEditTextInt(vZ));
            meteorsArray[i-1].setP(getTextFromEditTextDouble(vP));
            meteorsArray[i-1].setPprime(getTextFromEditTextDouble(vPP));
            Log.d("Tag", "getTextFromEditTextDouble(vLength) = " + getTextFromEditTextDouble(vLength));
            meteorsArray[i-1].setLength(getTextFromEditTextDouble(vLength));
            meteorsArray[i-1].setNotes("my notes here");
            meteorsArray[i-1].setSource("my source here");
            ////
        }
        OrmSqliteMeteorDao meteorDao = new OrmSqliteMeteorDao();
        meteorDao.insertListMeteors(meteorsArray);
    }

    double getTextFromEditTextDouble(View unknownView) {
        double value = 0;
        if (unknownView instanceof EditText) {
            value = getCorrectDouble(((EditText) unknownView).getText().toString());
        }
        return value;
    }

    int getTextFromEditTextInt(View unknownView) {
        int value = 0;
        if (unknownView instanceof EditText) {
            value = Integer.parseInt(((EditText) unknownView).getText().toString());
        }
        return value;
    }

}
