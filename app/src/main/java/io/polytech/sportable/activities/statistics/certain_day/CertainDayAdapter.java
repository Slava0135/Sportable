package io.polytech.sportable.activities.statistics.certain_day;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import io.polytech.sportable.R;
import io.polytech.sportable.activities.statistics.ResultFormatter;
import io.polytech.sportable.models.practice.PracticeType;
import io.polytech.sportable.persistence.PracticeResult;

public class CertainDayAdapter extends RecyclerView.Adapter<CertainDayAdapter.ViewHolder> {

    private List<PracticeResult> data = new ArrayList<>();

    static class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
        }

    }

    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        return new ViewHolder(
                LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.certain_day_row, parent, false));
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {
        ResultFormatter formatter = new ResultFormatter();
        final PracticeResult currentItem = data.get(position);
        TextView certainDayTypeTxt = holder.itemView.findViewById(R.id.certain_day_kind_of_workout);
        TextView certainDayCaloriesTxt = holder.
                itemView.
                findViewById(R.id.certain_day_all_calories);
        TextView certainDayDistanceTxt = holder.itemView.findViewById(R.id.certain_day_distance);
        TextView certainDayTimeTxt = holder.itemView.findViewById(R.id.certain_day_time);
        certainDayTypeTxt.setText(formatter.formatType(currentItem.practiceType));
        certainDayCaloriesTxt.setText(formatter.formatCalories(currentItem.calories));
        certainDayDistanceTxt.setText(formatter.formatDistance(currentItem.distance));
        certainDayTimeTxt.setText(formatter.formatTime(currentItem.time));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void setData(List<PracticeResult> data) {
        this.data = data;
        if (this.data.isEmpty())
            this.data.add(new PracticeResult(0, 0.f, 0.f, 0, PracticeType.Nothing));
        notifyDataSetChanged();
    }
}
