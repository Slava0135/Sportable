package io.polytech.sportable.persistence;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class PracticeHistory {

    private final Map<Date, PracticeResult> history; //Возможно использовать что-нибудь попроще, чем Date

    public PracticeHistory() {
        this.history = new HashMap<>();
    } //Случай, если до этого никакой истории не было

    public PracticeHistory(Map<Date, PracticeResult> previousHistory) {
        this.history = previousHistory;
    } //Случай, если приложение уже использовалось

    public void add(Date date, PracticeResult res) {
        this.history.put(date, res);
    }

    public PracticeResult getPractiseResult(Date date) {
        return this.history.get(date);
    }
 }
