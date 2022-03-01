package pl.rafal.testing.order;

import pl.rafal.testing.meal.Meal;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private List<Meal> meals = new ArrayList<>();
    private OrderStatus orderStatus;

    public void addMealToOrder(Meal meal){
        this.meals.add(meal);
    }

    public void removeMealFromOrder(Meal meal){
        this.meals.remove(meal);
    }
    public List<Meal> getMeals() {
        return meals;
    }
    public void cancel(){
        this.meals.clear();
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }
    public void changeOrderStatus(OrderStatus orderStatus){
        this.orderStatus = orderStatus;
    }

    @Override
    public String toString() {
        return "Order{" +
                "meals=" + meals +
                '}';
    }
}
