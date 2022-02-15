package pl.rafal.testing;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.Duration;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

class CartTest {
    private Cart cart;
    @BeforeEach
    void setup(){
        cart = new Cart();
    }

    @Test
    void simulateLargeOrder(){
        //given
        //when
        //then
        assertTimeout(Duration.ofMillis(100), () -> cart.simulateLargeCart(20000));
    }

    @Test
    void cartSchouldBeEmpty(){
        //given
        Meal meal = new Meal(15, "chelp");
        Order order = new Order();
        order.addMealToOrder(meal);
        cart.addOrderToCart(order);
        //when
        cart.clearCart();
        //then
        assertThat(cart.getOrders(), empty());

    }
    @Test
    void cartSchouldBeNotEmpty(){
        //given
        Meal meal = new Meal(15, "chelp");
        Order order = new Order();
        order.addMealToOrder(meal);
        //when
        cart.addOrderToCart(order);
        //then
        assertThat(cart.getOrders(), allOf(
                notNullValue(),
                hasSize(1),
                is(not(empty())),
                is(not(emptyCollectionOf(Order.class)))

        ));

        assertAll(
                () -> assertThat(cart.getOrders(), notNullValue())
                // () ->
                // () ->
        );
    }
}