package pl.rafal.testing.meal;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MealRespository {
    private List<Meal> mealsList = new ArrayList<>();


    public void add(Meal meal){
    mealsList.add(meal);
    }

    public List<Meal> getAllMeals(){
        return mealsList;
    }

    public void delete(Meal meal) {
        mealsList.remove(meal);
    }

    public List<Meal> findByName(String mealName, boolean exactMatch) {

     if(exactMatch) {
         return mealsList.stream()
                 .filter(x -> x.getName()
                         .equals(mealName))
                 .collect(Collectors.toList());
     }else{
         return mealsList.stream()
                 .filter(meals -> meals.getName()
                         .startsWith(mealName))
                 .collect(Collectors.toList());
     }
    }

    public List<Meal> findByPrice(int mealPrice, PriceFilter filter) {

        List<Meal> result = new ArrayList<>();

        switch(filter) {
            case EQUALS:
                result = mealsList.stream()
                    .filter(meals -> meals.getPrice() == mealPrice)
                    .collect(Collectors.toList());
            break;
            case LOWER:
                result = mealsList.stream()
                        .filter(meals -> meals.getPrice() <= mealPrice)
                        .collect(Collectors.toList());
                break;
            case HIGHER:
                result = mealsList.stream()
                        .filter(meals -> meals.getPrice() >= mealPrice)
                        .collect(Collectors.toList());
                break;
            default:
                break;
        }
        return result;
    }
}
