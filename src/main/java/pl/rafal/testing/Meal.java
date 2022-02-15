package pl.rafal.testing;

import java.util.Objects;

public class Meal {
    private int quantity;
    private int price;
    private String name;

    //constructors
    public Meal(int price){
        this.price = price;
    }

    public Meal(int price, String name) {
        this.price = price;
        this.name = name;
    }

    public Meal(int quantity, int price, String name) {
        this.quantity = quantity;
        this.price = price;
        this.name = name;
    }

    //methods

    public int getQuantity() {
        return quantity;
    }

    int getPrice(){
        return price;
    }

    int getDiscountedPrice(int discount){

        if(discount > this.price){
            throw new IllegalArgumentException("This price cannot be higher then Price");
        }

        return this.price -discount;
    }

    @Override
    public String toString() {
        return "Meal{" +
                "price=" + price +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Meal meal = (Meal) o;

        if (price != meal.price) return false;
        return Objects.equals(name, meal.name);
    }

    @Override
    public int hashCode() {
        int result = price;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
}
