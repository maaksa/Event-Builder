package rs.raf.projekatjun.milos_maksimovic_rn4318.data.datasources.local;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.Date;

import rs.raf.projekatjun.milos_maksimovic_rn4318.data.models.db.Event;

@Database(version = 1,
        entities = {
                Event.class,
        },
        exportSchema = false
)
public abstract class MyRoomDatabase extends RoomDatabase {

    private static MyRoomDatabase singletonInstance;

    public abstract EventDao eventDao();

    public static MyRoomDatabase getDatabase(final Context context) {
        if (singletonInstance == null) {
            synchronized (MyRoomDatabase.class) {
                if (singletonInstance == null) {
                    singletonInstance = Room.databaseBuilder(
                            context.getApplicationContext(),
                            MyRoomDatabase.class,
                            "my_database")
                            .fallbackToDestructiveMigration()
                            //.addCallback(callback)
                            .build();
                }
            }
        }
        return singletonInstance;
    }

    private static Callback callback =
            new Callback() {
                @Override
                public void onOpen(@NonNull SupportSQLiteDatabase db) {
                    super.onOpen(db);
                    new PopulateDatabaseAsync(singletonInstance).execute();
                }
            };

    private static class PopulateDatabaseAsync extends AsyncTask<Void, Void, Void> {

        private final EventDao eventDao;

        PopulateDatabaseAsync(MyRoomDatabase myRoomDatabase) {
            eventDao = myRoomDatabase.eventDao();
        }

        @Override
        protected Void doInBackground(final Void... params) {
//            long person1 =
//                    eventDao.insert(new Event("Maja", new Date()));
//            long person2 =
//                    eventDao.insert(new Event("Marko", new Date()));

            return null;
        }
    }

}