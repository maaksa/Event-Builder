package rs.raf.projekatjun.milos_maksimovic_rn4318.data.datasources.local;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import rs.raf.projekatjun.milos_maksimovic_rn4318.data.models.db.Event;

@Dao
public interface EventDao {

    @Insert
    public long insert(Event event);

    @Query("SELECT * FROM event_table")
    public LiveData<List<Event>> getAll();

    @Query("DELETE FROM event_table WHERE name = :name")
    public void deleteByName(String name);

    @Delete
    void delete(Event... events);


}
