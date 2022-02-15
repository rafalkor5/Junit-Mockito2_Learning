package pl.rafal.testing;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

class mealTest {

    private Order order;
    @BeforeEach
    void initializeOrder(){
        System.out.println("Hello");
        order = new Order();
    }

    @AfterEach
    void cleanUp(){
        System.out.println("GoodBye");
        order.cancel();
    }
    @Test
    void schouldReturnDiscountedPrice() {
        //given
        Meal meal = new Meal(30);

        //when
        int dicountedPrice = meal.getDiscountedPrice(5);

        //then
        assertEquals(25,dicountedPrice);
    }
    @Test
    void referencesToTheDifrentObjectSchouldBeNotEqual(){
        Meal meal1 = new Meal(10);
        Meal meal2 = new Meal(15);
        assertNotSame(meal1, meal2);


    }

    @Test
    void twoMealsShouldBeEqualsWhenPriceAndNameAreTheSame(){
        //given
        Meal meal1 = new Meal(15,"Bulka");
        Meal meal2 = new Meal(15,"Bulka");
        //then
        assertEquals(meal1,meal2);
    }

    @Test
    void addMealToOrderScholudIncreaseOrderSize(){
        //given
        Meal meal = new Meal(15,"Burger");
        //when
        order.addMealToOrder(meal);
        //then
        assertThat(order.getMeals(), hasSize(1));
        assertThat(order.getMeals(), contains(meal));
        assertThat(order.getMeals(), hasItem(meal));
    }

    @Test
    void removingItemFromOrderSchouldDecreaseOrderSize(){
        //given
        Meal meal = new Meal(15,"Burger");
        //when
        order.addMealToOrder(meal);
        order.removeMealFromOrder(meal);
        //then
        assertThat(order.getMeals(), empty());
    }

    @Test
    void mealsSchouldBeInCorrectOrderAfterAddingTheToOrder(){
        //given
        Meal meal1 = new Meal(15,"Burger");
        Meal meal2 = new Meal(20,"Burger");
        //when
        order.addMealToOrder(meal1);
        order.addMealToOrder(meal2);
        //then
        assertThat(order.getMeals(), containsInAnyOrder(meal1,meal2));
    }

    @Test
    void exceptionScholudBeThrownIfDiscountIsHigherThenThePrice(){
        //given
        Meal meal = new Meal(15,"Burger");
        //when
        //then
        assertThrows(IllegalArgumentException.class,() -> meal.getDiscountedPrice(20));
    }
}