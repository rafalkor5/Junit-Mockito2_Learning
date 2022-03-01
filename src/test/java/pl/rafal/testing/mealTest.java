package pl.rafal.testing;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import pl.rafal.testing.extensions.IAExepctionIgnoreExtension;
import pl.rafal.testing.order.Order;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;

//@ExtendWith(BeforeAfterExtension.class)
class mealTest {

    private pl.rafal.testing.order.Order order;
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

    @ParameterizedTest
    @ValueSource(ints = {5,10,15})
    void mealPricesSchouldBeLowerThan20(int price){
        assertThat(price,lessThan(20));
    }

    @ParameterizedTest
    @MethodSource("createMealsWithNameAndPrice")
    void burgerSchouldHaveCorrectNameAndPrice(String name,int price){
        assertThat(name,containsString("burger"));
        assertThat(price,greaterThanOrEqualTo(10));
    }

    private static Stream<Arguments> createMealsWithNameAndPrice(){
        return Stream.of(
                Arguments.of("Hamburger",10),
                Arguments.of("Cheesburger", 12)
        );
    }
    @ExtendWith(IAExepctionIgnoreExtension.class)
    @ParameterizedTest
    @ValueSource(ints = {5,10,15})
    void mealPricesSchouldBeLowerThan10(int price){

        if(price>5){
            throw new IllegalArgumentException();
        }

        assertThat(price,lessThan(20));
    }

//    @TestFactory
//    Collection<DynamicTest> dynamicTestCollection(){
//        return Arrays.asList(
//                dynamicTest("Dynamic test 1", ()-> assertThat(5,lessThan(6))),
//                dynamicTest("Dynamic test 2", ()-> assertEquals(4,2*2))
//        );
//    }

    @TestFactory
    Collection<DynamicTest> calculateMealPrices(){
        //dodajemy posiłki
        order.addMealToOrder(new Meal(10,5,"BUlka"));
        order.addMealToOrder(new Meal(15,2,"zupa"));
        order.addMealToOrder(new Meal(20,1,"chlep"));
        Collection<DynamicTest> dynamicTests = new ArrayList<>();
        //petla wielkosci zamówienia
        for (int i = 0; i <order.getMeals().size(); i++) {
            int price = order.getMeals().get(i).getPrice();
            int quantity = order.getMeals().get(i).getQuantity();
            //executable z junit.jupiter.api.function
            Executable executable = () -> assertThat(calculatePrice(price,quantity), lessThan(100));

            String name = "Test name:" + i;
            //Test dynamiczny składa sie z nazwy i działania
            DynamicTest dynamicTest = DynamicTest.dynamicTest(name,executable);
            dynamicTests.add(dynamicTest);
        }
        return dynamicTests;
    }

    private int calculatePrice(int price, int quantity){
        return price*quantity;
    }

    @Test
    void testMealSumPrice(){
        //given
        Meal meal = mock(Meal.class);

        given(meal.getPrice()).willReturn(15);
        given(meal.getQuantity()).willReturn(3);
        given(meal.sumPrice()).willCallRealMethod();
        // uzycie prawdziwej metody na mocku. Bez tego zwracane będzie w przypadku typu int 0
        //when
        int result = meal.sumPrice();

        //then
        assertThat(result,equalTo(45));
    }


    @Test
    void testMealSumPriceWithSpy(){
        //given
        Meal meal = spy(Meal.class);

        given(meal.getPrice()).willReturn(15);
        given(meal.getQuantity()).willReturn(3);

        // uzycie prawdziwej metody na mocku. Bez tego zwracane będzie w przypadku typu int 0
        //when
        int result = meal.sumPrice();

        //then

        assertThat(result,equalTo(45));
    }
}