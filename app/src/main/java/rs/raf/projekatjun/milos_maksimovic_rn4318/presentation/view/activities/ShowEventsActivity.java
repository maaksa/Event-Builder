package rs.raf.projekatjun.milos_maksimovic_rn4318.presentation.view.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.List;

import rs.raf.projekatjun.milos_maksimovic_rn4318.R;
import rs.raf.projekatjun.milos_maksimovic_rn4318.data.datasources.local.MyRoomDatabase;
import rs.raf.projekatjun.milos_maksimovic_rn4318.data.models.db.Event;
import rs.raf.projekatjun.milos_maksimovic_rn4318.presentation.view.recycler.adapter.EventRecyclerViewAdapter;

public class ShowEventsActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_events);

        init();
    }

    private void init() {
        initView();
        roomDb();
    }

    private void roomDb() {
        final MyRoomDatabase myRoomDatabase = MyRoomDatabase.getDatabase(this);

        myRoomDatabase.eventDao()
                //observujemo getAll metodu koja vraca listu usera u bazi koji su LiveData i zato mozemo da observujemo promene
                .getAll().observe(
                ShowEventsActivity.this, new Observer<List<Event>>() {
                    //persons ce nam biti ono sto nam se vrati iz getAll metode
                    @Override
                    public void onChanged(List<Event> events) {
                        recyclerView.setLayoutManager(new LinearLayoutManager(ShowEventsActivity.this));
                        EventRecyclerViewAdapter mAdapter = new EventRecyclerViewAdapter(events);
                        recyclerView.setAdapter(mAdapter);
                    }
                });
    }

    private void initView() {

        recyclerView = findViewById(R.id.rvEvents);


    }


}