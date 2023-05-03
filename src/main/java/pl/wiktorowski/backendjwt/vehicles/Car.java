package pl.wiktorowski.backendjwt.vehicles;

public class Car {

    public String mark;
    public String model;
    public int year;
    public String colour;

    public Car(String mark, String model, int year, String colour) {
        this.mark = mark;
        this.model = model;
        this.year = year;
        this.colour = colour;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }
}
