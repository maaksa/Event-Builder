package rs.raf.projekatjun.milos_maksimovic_rn4318.presentation.view.recycler.adapter;

import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.List;

import rs.raf.projekatjun.milos_maksimovic_rn4318.R;
import rs.raf.projekatjun.milos_maksimovic_rn4318.data.datasources.local.MyRoomDatabase;
import rs.raf.projekatjun.milos_maksimovic_rn4318.data.models.db.Event;
import rs.raf.projekatjun.milos_maksimovic_rn4318.presentation.view.activities.ShowEventsActivity;

public class EventRecyclerViewAdapter extends RecyclerView.Adapter<EventRecyclerViewAdapter.ViewHolder> {

    List<Event> items;

    public EventRecyclerViewAdapter(List<Event> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.event_list_item, parent, false);
        ViewHolder result = new ViewHolder(v);
        return result;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) { //pozicija da
        Event item = items.get(position);

        View view = holder.view;

        if (item.getPriority().equals("High")) {
            view.setBackgroundColor(Color.parseColor("#FF6347"));
            view.setAlpha(0.7f);
        } else if (item.getPriority().equals("Medium")) {
            view.setBackgroundColor(Color.parseColor("#32CD32"));
            view.setAlpha(0.7f);
        } else {
            view.setBackgroundColor(Color.parseColor("#F5F5F5"));
            view.setAlpha(0.7f);
        }

        TextView eventName = view.findViewById(R.id.etNameRvItem);
        eventName.setText(eventName.getText() + item.getName());
        TextView eventDescription = view.findViewById(R.id.etDescRvItem);
        eventDescription.setText(eventDescription.getText() + item.getDescription());
        TextView eventTime = view.findViewById(R.id.etTimeZoneRvItem);
        eventTime.setText(item.getTime());
        TextView eventUrl = view.findViewById(R.id.etUrlRvItem);
        eventUrl.setText(eventUrl.getText() + item.getUrl());

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        View view;

        public ViewHolder(View view) {
            super(view);
            this.view = view;
            View delete = view.findViewById(R.id.ivSendRvItem);
            delete.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            TextView eventName = view.findViewById(R.id.etNameRvItem);
            TextView eventDescription = view.findViewById(R.id.etDescRvItem);
            TextView eventTime = view.findViewById(R.id.etTimeZoneRvItem);
            TextView eventUrl = view.findViewById(R.id.etUrlRvItem);

            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_SEND);

            intent.putExtra(Intent.EXTRA_TEXT,
                    eventName.getText().toString()
                            + "\n" + eventDescription.getText().toString()
                            + "\n" + eventTime.getText().toString()
                            + "\n" + eventUrl.getText().toString());

            intent.setType("text/plain");
            PackageManager manager = view.getContext().getPackageManager();
            ComponentName componentName = intent.resolveActivity(manager);
            if (componentName != null) {
                Intent newIntent = Intent.createChooser(
                        intent, "Choose a program");
                view.getContext().startActivity(newIntent);
            }
        }
    }

}
