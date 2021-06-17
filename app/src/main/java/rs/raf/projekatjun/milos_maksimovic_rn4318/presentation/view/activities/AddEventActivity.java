package rs.raf.projekatjun.milos_maksimovic_rn4318.presentation.view.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;

import rs.raf.projekatjun.milos_maksimovic_rn4318.R;
import rs.raf.projekatjun.milos_maksimovic_rn4318.data.models.api.EasternStandardTimeModel;
import rs.raf.projekatjun.milos_maksimovic_rn4318.presentation.view.fragments.DatePickerFragment;
import rs.raf.projekatjun.milos_maksimovic_rn4318.presentation.view.fragments.TimePickerFragment;
import rs.raf.projekatjun.milos_maksimovic_rn4318.presentation.viewmodel.WorldClockViewModel;

public class AddEventActivity extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener, DatePickerDialog.OnDateSetListener {

    public static final String REGION = "Europe";

    private EditText etName;
    private EditText etDesc;
    private Button btnCheckTime;
    private AutoCompleteTextView autoCompleteTextView;
    private Button btnSetDate;
    private Button btnSetTime;
    private Spinner spinnerPriority;
    private EditText etUrl;
    private Button btnSaveEvent;

    private WorldClockViewModel myViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);

        myViewModel = ViewModelProviders.of(this)
                .get(WorldClockViewModel.class);

        init();
    }

    private void init() {
        initView();
        initListeners();
        initObserver();
    }

    private void initObserver() {
        myViewModel.getEasternStandardTime().observe(
                this, new Observer<EasternStandardTimeModel>() {
                    @Override
                    public void onChanged(EasternStandardTimeModel easternStandardTimeModel) {
                        String time = easternStandardTimeModel.getDatetime();
                        String[] timeArr = time.split("T");

                        String[] minH = timeArr[1].split(":");

                        btnCheckTime.setText(timeArr[0] + "\n" + "Time: " + minH[0] + ":" + minH[1]);
                    }
                });
    }

    private void initView() {
        setCitiesValue();
        setSpinnerValue();
        initEt();
        initBtn();
    }

    private void setSpinnerValue() {
        spinnerPriority = findViewById(R.id.spinnerPriority);

        ArrayAdapter<CharSequence> spinnerAdapter = ArrayAdapter.createFromResource(this, R.array.priority_array, android.R.layout.simple_spinner_item);
        spinnerPriority.setAdapter(spinnerAdapter);

        setSpinnerListener();

    }

    private void setSpinnerListener() {

        spinnerPriority.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {

            }

        });
    }

    private void initListeners() {
        btnCheckTime.setOnClickListener(v -> {
            myViewModel.invokeCityService(REGION, autoCompleteTextView.getText().toString());
        });

        btnSetTime.setOnClickListener(v -> {
            DialogFragment dialog = new TimePickerFragment();
            FragmentManager fragmentManager = getSupportFragmentManager();
            dialog.show(fragmentManager, "TimePicker app");
        });

        btnSetDate.setOnClickListener(v -> {
            DialogFragment dialog = new DatePickerFragment();
            FragmentManager fragmentManager = getSupportFragmentManager();
            dialog.show(fragmentManager, "DatePicker app");
        });
    }

    private void initBtn() {
        btnCheckTime = findViewById(R.id.btnCheckTimeLoc);
        btnSaveEvent = findViewById(R.id.btnSaveEvent);
        btnSetTime = findViewById(R.id.btnSetTime);
        btnSetDate = findViewById(R.id.btnSetDate);
    }

    private void initEt() {
        etName = findViewById(R.id.etEventName);
        etDesc = findViewById(R.id.etDescription);
        etUrl = findViewById(R.id.etUrl);
    }

    private void setCitiesValue() {
        autoCompleteTextView = findViewById(R.id.autoCompleteTextView);

        String[] cities = getResources().getStringArray(R.array.cities_array);

        ArrayAdapter<String> arrayAdapter =
                new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, cities);
        autoCompleteTextView.setAdapter(arrayAdapter);
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        String min = "";
        if (minute < 10) {
            min = "0" + minute;
        } else if (minute == 0) {
            min = "00";
        } else {
            min = "" + minute;
        }
        btnSetTime.setText("Time: " + hourOfDay + ":" + min);
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        int monthToDisplay = month + 1;
        btnSetDate.setText("Date: " + dayOfMonth + "." + monthToDisplay + "." + year + ".");

    }
}