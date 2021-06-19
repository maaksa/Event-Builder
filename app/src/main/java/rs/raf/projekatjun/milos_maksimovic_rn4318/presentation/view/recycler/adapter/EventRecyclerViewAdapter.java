package rs.raf.projekatjun.milos_maksimovic_rn4318.presentation.view.recycler.adapter;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.List;
import java.util.function.Function;

import rs.raf.projekatjun.milos_maksimovic_rn4318.R;
import rs.raf.projekatjun.milos_maksimovic_rn4318.data.datasources.local.MyRoomDatabase;
import rs.raf.projekatjun.milos_maksimovic_rn4318.data.models.db.Event;
import rs.raf.projekatjun.milos_maksimovic_rn4318.presentation.view.activities.ShowEventsActivity;

public class EventRecyclerViewAdapter extends ListAdapter<Event, EventRecyclerViewAdapter.ViewHolder> {

    private final Function<Event, Void> onDeleteClicked;
    private final Function<Event, Void> onSentClicked;


    public EventRecyclerViewAdapter(@NonNull DiffUtil.ItemCallback<Event> diffCallback, Function<Event, Void> onDeleteClicked, Function<Event, Void> onSentClicked) {
        super(diffCallback);
        this.onDeleteClicked = onDeleteClicked;
        this.onSentClicked = onSentClicked;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.event_list_item, parent, false);
        return new ViewHolder(v, parent.getContext(),
                position -> {
                    Event event = getItem(position);
                    onDeleteClicked.apply(event);
                    return null;
                },
                position -> {
                    Event event = getItem(position);
                    onSentClicked.apply(event);
                    return null;
                });
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Event item = getItem(position);
        View view = holder.itemView;

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

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final Context parent;

        private ImageView sent;
        private ImageView delete;

        public ViewHolder(@NonNull View itemView, Context contex, Function<Integer, Void> onDeleteClicker, Function<Integer, Void> onSendClicked) {
            super(itemView);

            this.parent = contex;
            sent = itemView.findViewById(R.id.ivSendRvItem);
            delete = itemView.findViewById(R.id.ivDeleteRvItem);

            delete.setOnClickListener(v -> {
                if (getAdapterPosition() != RecyclerView.NO_POSITION) {
                    onDeleteClicker.apply(getAdapterPosition());
                }
            });

            sent.setOnClickListener(v -> {
                if (getAdapterPosition() != RecyclerView.NO_POSITION) {
                    onSendClicked.apply(getAdapterPosition());
                }
            });
        }

    }

}
