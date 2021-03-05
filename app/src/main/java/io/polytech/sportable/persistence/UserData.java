package io.polytech.sportable.persistence;

public class UserData {

    private static String name;
    private static float weight;
    private static float height;
    private static float year;

    public UserData(String name,float weight, float height, int year) {
        this.name = name;
        this.weight = weight;
        this.height = height;
        this.year = year;
    }

    static UserData loadUserData() {
        return new UserData("user", 0, 0, 0);
    }

    static void saveUserData(UserData data) { }

    //Для изменения имени, веса, роста и года рождения

    public static void setName(String name) {
        UserData.name = name;
    }

    public void setWeight(float weight) {
        UserData.weight = weight;
    }

    public void setHeight(float height) {
        UserData.height = height;
    }

    public static void setYear(float year) {
        UserData.year = year;
    }

    // гетеры
    public static String getName() {
        return UserData.name;
    }

    public static float getWeight() { return UserData.weight; }

    public static float getHeight() { return UserData.height; }

    public static float getYear() { return UserData.year; }
}
