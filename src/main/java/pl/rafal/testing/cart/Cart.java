package pl.rafal.testing.cart;

import pl.rafal.testing.meal.Meal;
import pl.rafal.testing.order.Order;

import java.util.ArrayList;
import java.util.List;

public class Cart {

    private List<Order> orders = new ArrayList<>();

    void addOrderToCart(Order order){
        this.orders.add(order);
    }

    void clearCart(){
        this.orders.clear();
    }

    public List<Order> getOrders() {
        return orders;
    }

    void simulateLargeCart(int size){
        //Cart cart = new Cart();
        for (int i = 0; i < size; i++) {
            Meal meal = new Meal(15, "Pizza");
            Order order = new Order();
            order.addMealToOrder(meal);
            addOrderToCart(order);
        }
        System.out.println("Cart size: " + orders.size());
        clearCart();
    }

}
