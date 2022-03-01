package pl.rafal.testing.order;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import pl.rafal.testing.order.OrderStatus;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.lessThan;

class OrderStatusTest {
    @ParameterizedTest
    @EnumSource(OrderStatus.class)
    void allOrderStatusSchouldBESchorterThan15Characters(OrderStatus status){
        assertThat(status.toString().length(), lessThan(15));
    }

}