package pl.rafal.testing.cart;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import pl.rafal.testing.order.Order;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
@MockitoSettings(strictness = Strictness.STRICT_STUBS)
@ExtendWith(MockitoExtension.class)
class CartServiceTest {

    @InjectMocks
    private CartService cartService;//Przekazuje mock do cart service
    @Mock
    private CartHandler cartHandler;// Tworzy mock interfejsu Cart Handler
    @Captor
    private ArgumentCaptor<Cart> argumentCaptor;

    @Test
    void processCartShouldSendToPrepare(){
        //given
        Order order = new Order();
        Cart cart = new Cart();
        cart.addOrderToCart(order);

//        CartHandler cartHandler = mock(CartHandler.class); // Tworzy mock interfejsu Cart Handler
//        CartService cartService = new CartService(cartHandler); //Przekazuje mock do cart service

        given(cartHandler.canHandCart(any())).willReturn(true);  // Gdy zostanie wykonana metoda canHandCart zwraca true
        //when
        Cart resultCart = cartService.processCart(cart); // wykonuje metody klasy cartservice na mocku

        //then
        verify(cartHandler).sendToPrepare(cart); // sprawdza czy na mocku została wywołana metoda z metody process cart
        assertThat(resultCart.getOrders(), hasSize(1));
    }

    @Test
    void processCartShouldSendToPrepareWithLambdas(){
        //given
        Order order = new Order();
        Cart cart = new Cart();
        cart.addOrderToCart(order);

        CartHandler cartHandler = mock(CartHandler.class); // Tworzy mock interfejsu Cart Handler
        CartService cartService = new CartService(cartHandler); //Przekazuje mock do cart service

        given(cartHandler.canHandCart(argThat(x -> x.getOrders().size() > 0))).willReturn(true);
        // Gdy zostanie wykonana metoda canHandCart( posiadająca conajmniej liste z zamówieniami większą od 0) zwraca true
        //when
        Cart resultCart = cartService.processCart(cart); // wykonuje metody klasy cartservice na mocku

        //then
        then(cartHandler).should().sendToPrepare(cart);// sprawdza czy na mocku została wywołana metoda z metody process cart
        assertThat(resultCart.getOrders(), hasSize(1));
    }

    @Test
    void processCartShouldThrowException(){
        //given
        Order order = new Order();
        Cart cart = new Cart();
        cart.addOrderToCart(order);

        CartHandler cartHandler = mock(CartHandler.class); // Tworzy mock interfejsu Cart Handler
        CartService cartService = new CartService(cartHandler); //Przekazuje mock do cart service

        given(cartHandler.canHandCart(cart)).willThrow(IllegalStateException.class);

        //when
        //then
        assertThrows(IllegalStateException.class,() -> cartService.processCart(cart));
        //wywołuje metode processcart za pomocą lambdy która wywoła metode canHandCart która wyżuci nasz podany wyjątek
    }

    @Test
    void processCartShouldSendToPrepareWithArgumentCaptor(){
        //given
        Order order = new Order();
        Cart cart = new Cart();
        cart.addOrderToCart(order);

//        CartHandler cartHandler = mock(CartHandler.class); // Tworzy mock interfejsu Cart Handler
//        CartService cartService = new CartService(cartHandler); //Przekazuje mock do cart service

//        ArgumentCaptor<Cart> argumentCaptor = ArgumentCaptor.forClass(Cart.class);
        // wywołanie argumentcaptor łapie obiekt który podajemy do metody za pomocą given

        given(cartHandler.canHandCart(cart)).willReturn(true);

        //when
        Cart resultCart = cartService.processCart(cart); // wykonuje metody klasy cartservice na mocku

        //then
        then(cartHandler).should().sendToPrepare(argumentCaptor.capture());
        // sprawdza czy na mocku została wywołana metoda z metody process cart oraz przechwytuje obiekt do argumentCapture
        assertThat(argumentCaptor.getValue().getOrders().size(), equalTo(1));
        //assercja sprawdza czy obiekt ktróy został przechwycony posiada liste wielkości 1
    }

}