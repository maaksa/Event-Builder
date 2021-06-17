package rs.raf.projekatjun.milos_maksimovic_rn4318.presentation.view.recycler.adapter;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import rs.raf.projekatjun.milos_maksimovic_rn4318.R;
import rs.raf.projekatjun.milos_maksimovic_rn4318.data.models.db.Event;

public class EventRecyclerViewAdapter extends RecyclerView.Adapter<EventRecyclerViewAdapter.ViewHolder> {

    List<Event> items;

    public EventRecyclerViewAdapter(List<Event> items) {
        this.items = items;
    }

    //kreiramo objekat tipa VH klase
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.event_list_item, parent, false);
        ViewHolder result = new ViewHolder(v);
        return result;
    }

    //kako ce izgledati item kakve ce vrednosti imati
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) { //pozicija da
        Event item = items.get(position);

        View view = holder.view;
        //view.getBackground().setAlpha(150);

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

    //bitno da vratimo velicinu niza koji smo prosledili kao podatke sa kojima zelimo da popuni RV
    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {//ako zelimo da imamo neke akcije
        View view;

        public ViewHolder(View view) {
            super(view);
            this.view = view;
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
//            TextView description = view.findViewById(R.id.languageDescription);
//            description.setText(description.getText().toString() + "clicked; ");
        }
    }

}
