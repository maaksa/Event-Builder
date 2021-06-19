package rs.raf.projekatjun.milos_maksimovic_rn4318.presentation.view.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;

import java.util.List;

import rs.raf.projekatjun.milos_maksimovic_rn4318.R;
import rs.raf.projekatjun.milos_maksimovic_rn4318.data.datasources.local.MyRoomDatabase;
import rs.raf.projekatjun.milos_maksimovic_rn4318.data.models.db.Event;
import rs.raf.projekatjun.milos_maksimovic_rn4318.presentation.view.recycler.adapter.EventRecyclerViewAdapter;
import rs.raf.projekatjun.milos_maksimovic_rn4318.presentation.view.recycler.diff.EventDiffItemCallback;

public class ShowEventsActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private EventRecyclerViewAdapter eventRecyclerViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_events);

        final MyRoomDatabase myRoomDatabase = MyRoomDatabase.getDatabase(this);


        init(myRoomDatabase);
    }

    private void init(MyRoomDatabase myRoomDatabase) {
        initView();
        roomDb(myRoomDatabase);
        initRecycler(myRoomDatabase);
    }

    private void initRecycler(MyRoomDatabase myRoomDatabase) {
        eventRecyclerViewAdapter = new EventRecyclerViewAdapter(new EventDiffItemCallback(),
                delete -> {
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            myRoomDatabase.eventDao().delete(delete);
                        }
                    }).start();
                    return null;
                },
                send -> {
                    Intent intent = new Intent();
                    intent.setAction(Intent.ACTION_SEND);

                    intent.putExtra(Intent.EXTRA_TEXT,
                            send.getName()
                                    + "\n" + send.getDescription()
                                    + "\n" + send.getTime()
                                    + "\n" + send.getUrl());

                    intent.setType("text/plain");
                    PackageManager manager = getPackageManager();
                    ComponentName componentName = intent.resolveActivity(manager);
                    if (componentName != null) {
                        Intent newIntent = Intent.createChooser(
                                intent, "Choose a program");
                        startActivity(newIntent);
                    }
                    return null;
                });

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(eventRecyclerViewAdapter);
    }

    private void roomDb(MyRoomDatabase myRoomDatabase) {

        myRoomDatabase.eventDao()
                .getAll().observe(
                ShowEventsActivity.this, new Observer<List<Event>>() {
                    @Override
                    public void onChanged(List<Event> events) {
                        eventRecyclerViewAdapter.submitList(events);
                    }
                });
    }

    private void initView() {

        recyclerView = findViewById(R.id.rvEvents);


    }


}