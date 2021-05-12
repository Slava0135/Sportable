package io.polytech.sportable.activities.statistics;

import android.annotation.SuppressLint;

import org.jetbrains.annotations.NotNull;

import io.polytech.sportable.models.practice.PracticeType;

import static java.lang.String.format;

public class ResultFormatter {

    @NotNull
    public String formatTime(int time) {
        String strHours = String.valueOf(time / 3600);
        String strMinutes = String.valueOf((time % 3600) / 60);
        String strSeconds = String.valueOf((time % 3600) % 60);
        if (strHours.length() == 1) {
            strHours = "0" + strHours;
        }
        if (strMinutes.length() == 1) {
            strMinutes = "0" + strMinutes;
        }
        if (strSeconds.length() == 1) {
            strSeconds = "0" + strSeconds;
        }
        return String.format("%s:%s:%s", strHours, strMinutes, strSeconds);
    }

    @SuppressLint("DefaultLocale")
    public String formatCalories(float calories) {
        return String.format("%.1f", calories)+ " ккал.";
    }

    public String formatType(@NotNull PracticeType type) {
        String res = "";
        switch (type) {
            case Run:
            {
                res = "Бег";
                break;
            }
            case Walk:
            {
                res = "Ходьба";
                break;
            }
            case Bicycle:
            {
                res = "Велосипед";
                break;
            }
            case Skies:
            {
                res = "Лыжи";
                break;
            }
            case Nothing:
            {
                res = "Нет";
                break;
            }
        }
        return res;
    }

    @NotNull
    public String formatDistance(float distance) {
        int roundDistance = Math.round(distance);
        int kilometers = roundDistance / 1000;
        int meters = roundDistance % 1000;
        return String.format("%s", kilometers) + " км. " + format("%.3s", meters) + " м.";
    }
}
