package model;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class Baseproduct extends Menu implements Serializable {

    private double rating;
    private double calories;
    private double protein;
    private double fat;
    private double sodium;
    private float price;

    public Baseproduct(String name,double rating, double calories, double protein, double fat, double sodium, float price) {
        this.name= name;
        this.rating = rating;
        this.calories = calories;
        this.protein = protein;
        this.fat = fat;
        this.sodium = sodium;
        this.price = price;
    }

    public Baseproduct()
    {

    }



    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public double getCalories() {
        return calories;
    }

    public void setCalories(double calories) {
        this.calories = calories;
    }

    public double getProtein() {
        return protein;
    }

    public void setProtein(double protein) {
        this.protein = protein;
    }

    public double getFat() {
        return fat;
    }

    public void setFat(double fat) {
        this.fat = fat;
    }

    public double getSodium() {
        return sodium;
    }

    public void setSodium(double sodium) {
        this.sodium = sodium;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    @Override
    public float priceadd()
    {
        return price;
    }

    @Override
    public String toString() {
        return name +
                " rating=" + rating +
                ", calories=" + calories +
                ", protein=" + protein +
                ", fat=" + fat +
                ", sodium=" + sodium +
                ", price=" + price +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Baseproduct that = (Baseproduct) o;
        return Double.compare(that.rating, rating) == 0 && Double.compare(that.calories, calories) == 0 && Double.compare(that.protein, protein) == 0 && Double.compare(that.fat, fat) == 0 && Double.compare(that.sodium, sodium) == 0 && Float.compare(that.price, price) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(rating, calories, protein, fat, sodium, price);
    }
}
