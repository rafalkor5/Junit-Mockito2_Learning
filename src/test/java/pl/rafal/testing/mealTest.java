package pl.rafal.testing;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class mealTest {

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
}