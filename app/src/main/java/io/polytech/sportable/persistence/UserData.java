package io.polytech.sportable.persistence;

public class UserData {

    private final float weight, height, year;

    public UserData(float weight, float height, int year) {
        this.weight = weight;
        this.height = height;
        this.year = year;
    }

    static UserData loadUserData() {
        return new UserData(0, 0, 0);
    }

    static void saveUserData(UserData data) { }

}
