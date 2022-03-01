package pl.rafal.testing.order;

import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import pl.rafal.testing.Meal;
import pl.rafal.testing.extensions.BeforeAfterExtension;
import pl.rafal.testing.order.Order;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(BeforeAfterExtension.class)
class OrderTest {

    @Test
    void testAssertArrayEquals(){
        //given
        int[] ints1 = {1,2,3};
        int[] ints2 = {1,2,3};
        //then
        assertArrayEquals(ints1,ints2);

    }

    @Test
    void mealListSchouldBeEmptyAfterCreationOfOrder(){
        //given
        Order order = new Order();
        //then
        assertThat(order.getMeals(), empty());
        assertThat(order.getMeals().size(), equalTo(0));
        assertThat(order.getMeals(), hasSize(0));
        MatcherAssert.assertThat(order.getMeals(), emptyCollectionOf(Meal.class));


    }
}