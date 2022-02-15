package pl.rafal.testing;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.jupiter.api.Assertions.*;

class OrderStatusTest {
    @ParameterizedTest
    @EnumSource(OrderStatus.class)
    void allOrderStatusSchouldBESchorterThan15Characters(OrderStatus status){
        assertThat(status.toString().length(), lessThan(15));
    }

}