package pl.rafal.testing.order;


import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import pl.rafal.testing.meal.Meal;

import java.io.FileNotFoundException;
import java.io.IOException;

class orderBackupTest {
    private static pl.rafal.testing.order.orderBackup orderBackup;

    @BeforeAll
    static void setup() throws FileNotFoundException {
        orderBackup = new orderBackup();
        orderBackup.createFile();
    }
    @Test
    void backupOrderWithOneMeal() throws IOException {
        //given
        Meal meal = new Meal(15, "czekolada");
        Order order = new Order();
        order.addMealToOrder(meal);
        //when
        orderBackup.backupOrder(order);
        //then
        System.out.println("Order " + order + " backed up");

    }
    @AfterAll
    static void tearDown() throws IOException {
        orderBackup.closeFile();
    }
}
