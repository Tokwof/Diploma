package marchenko.com.diplomameteors.ui.ui_general;

import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.widget.Button;
import android.app.DialogFragment;
import android.app.Dialog;
import java.util.Calendar;
import android.widget.TimePicker;

import marchenko.com.diplomameteors.R;

public class TimePickerFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener{

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){
        final Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);

        return new TimePickerDialog(getActivity(),this, hour, minute,
                DateFormat.is24HourFormat(getActivity()));
    }

    public void onTimeSet(TimePicker view, int hourOfDay, int minute){
        Button beginTimeIntervalButton = (Button) getActivity().findViewById(R.id.beginIntervalTime_button);
        beginTimeIntervalButton.setText(String.valueOf(hourOfDay)
                + " - " + String.valueOf(minute));
    }
}