package pl.rafal.testing.meal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.not;


public class MealRespositoryTest {
    MealRespository mealRespository;
    @BeforeEach
    void intializeMealRespository(){
        mealRespository = new MealRespository();
    }
    @Test
    void schouldBeAbleToAddMealToRespository(){
        //given
        Meal meal = new Meal(10,"Pizza");
        //when
        mealRespository.add(meal);

        //then
        assertThat(mealRespository.getAllMeals().get(0), is(meal));
    }

    @Test
    void schouldBeAbleToRemoveMealFromRespository(){
        //given
        Meal meal = new Meal(10,"Pizza");
        mealRespository.add(meal);
        //when
        mealRespository.delete(meal);
        //
        assertThat(mealRespository.getAllMeals(),not(contains(meal)) );

    }

    @Test
    void shouldBeAbleToFindByExactName(){
        //given
        Meal meal = new Meal(10,"Pizza");
        Meal meal2 = new Meal(165,"Pi");
        mealRespository.add(meal);
        mealRespository.add(meal2);
        //when
        List<Meal> results = mealRespository.findByName("Pizza", true);
        //then
        assertThat(results.size(), is(1));
    }

    @Test
    void shouldBeReturnMealFilterByThePrice(){
        //given
        Meal meal = new Meal(10,"Pizza");
        Meal meal2 = new Meal(15,"KEBAP");
        mealRespository.add(meal);
        mealRespository.add(meal2);
        //when
        List<Meal> results = mealRespository.findByPrice(10, PriceFilter.EQUALS);
        //then
        assertThat(results.size(), is(1));
    }

    @Test
    void shouldBeReturnMealFilterByLowerThenPrice(){
        //given
        Meal meal = new Meal(10,"Pizza");
        Meal meal2 = new Meal(15,"KEBAP");
        mealRespository.add(meal);
        mealRespository.add(meal2);
        //when
        List<Meal> results = mealRespository.findByPrice(20, PriceFilter.LOWER);
        //then
        assertThat(results.size(), is(2));
    }

    @Test
    void shouldBeReturnMealFilterByHigherThenPrice(){
        //given
        Meal meal = new Meal(10,"Pizza");
        Meal meal2 = new Meal(15,"KEBAP");
        mealRespository.add(meal);
        mealRespository.add(meal2);
        //when
        List<Meal> results = mealRespository.findByPrice(5, PriceFilter.HIGHER);
        //then
        assertThat(results.size(), is(2));
    }

    @Test
    void shouldBeAbleToFindByStartingLetters(){
        //given
        Meal meal = new Meal(10,"Pizza");
        Meal meal2 = new Meal(165,"Pi");
        mealRespository.add(meal);
        mealRespository.add(meal2);
        //when
        List<Meal> results = mealRespository.findByName("Pi",false);
        //then
        assertThat(results.size(), is(2));
    }


}
