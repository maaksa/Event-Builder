package rs.raf.projekatjun.milos_maksimovic_rn4318.presentation.view.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import rs.raf.projekatjun.milos_maksimovic_rn4318.R;

public class MainActivity extends AppCompatActivity {

    private Button btnAddEvent;
    private Button btnShowEvents;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }

    private void init() {
        initView();
        initListeners();
    }

    private void initView() {
        btnAddEvent = findViewById(R.id.btnAddEventMainActivity);
        btnShowEvents = findViewById(R.id.btnShowEventsMainActivity);
    }

    private void initListeners() {
        btnAddEvent.setOnClickListener(v -> {
            Intent intent = new Intent(this, AddEventActivity.class);
            startActivity(intent);
        });
    }
}