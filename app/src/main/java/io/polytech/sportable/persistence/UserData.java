package io.polytech.sportable.persistence;

public class UserData {

    private String name;
    private float weight;
    private float height;
    private float year;

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

    public void setName(String name) {
        this.name = name;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public void setYear(float year) {
        this.year = year;
    }
}
