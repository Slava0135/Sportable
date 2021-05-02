package io.polytech.sportable.activities.statistics.certain_day;

import android.annotation.SuppressLint;
import android.view.View;
import android.widget.TextView;

import io.polytech.sportable.R;
import io.polytech.sportable.models.practice.PracticeType;
import io.polytech.sportable.persistence.PracticeResult;

import static java.lang.String.format;

public class CertainDayAdapter {

    private View view;

    private PracticeResult data;

    private PracticeType type;
    private float calories;
    private float distance;
    private int time;
    private long date;

    private TextView certainDayTypeTxt, certainDayCaloriesTxt, certainDayDistanceTxt;
    private TextView certainDayTimeTxt, certainDayDateTxt;

    public CertainDayAdapter(View view) {
        this.view = view;
    }

    @SuppressLint({"SetTextI18n", "DefaultLocale"}) // Исправить
    public void setData(PracticeResult data) {
        if (data == null) {
            data = new PracticeResult(0, 0.f, 0.f, 0, PracticeType.Nothing);
        }
        this.data = data;
        type = data.practiceType;
        calories = data.calories;
        distance = data.distance;
        time = data.time;
        date = data.date;

        certainDayDateTxt = view.findViewById(R.id.certain_day_day);
        certainDayTypeTxt = view.findViewById(R.id.certain_day_kind_of_workout);
        certainDayCaloriesTxt = view.findViewById(R.id.certain_day_all_calories);
        certainDayDistanceTxt = view.findViewById(R.id.certain_day_distance);
        certainDayTimeTxt = view.findViewById(R.id.certain_day_time);

        if (date == 0) certainDayTimeTxt.setText("Нет тренировки");
        else certainDayTimeTxt.setText(String.format(
                "%d.%d.%d",
                date / 10000,
                (date / 100) % 100,
                date % 100
        ));
        switch (type) {
            case Run:
            {
                certainDayTypeTxt.setText("Бег");
                break;
            }
            case Walk:
            {
                certainDayTypeTxt.setText("Ходьба");
                break;
            }
            case Bicycle:
            {
                certainDayTypeTxt.setText("Велосипед");
                break;
            }
            case Skies:
            {
                certainDayTypeTxt.setText("Лыжи");
                break;
            }
            case Nothing:
            {
                certainDayTypeTxt.setText("Нет");
                break;
            }
        }
        certainDayCaloriesTxt.setText(calories + " кал.");
        certainDayTimeTxt.setText(format(
                "%s",
                String.valueOf(distance).split("\\.")[0]
        ) + " км." + format(
                "%s",
                String.valueOf(distance).split("\\.")[1]
        ) + " м.");
        certainDayTimeTxt.setText(String.format(
                "%s:%s:%s",
                time / 3600000,
                time / 60000,
                time / 1000
        ));
    }
}
