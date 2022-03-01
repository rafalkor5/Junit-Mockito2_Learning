package pl.rafal.testing.cart;

import pl.rafal.testing.order.OrderStatus;

public class CartService {

    private CartHandler cartHandler;

    public CartService(CartHandler cartHandler) {
        this.cartHandler = cartHandler;
    }

    Cart processCart(Cart cart){
        if(cartHandler.canHandCart(cart)){
            cartHandler.sendToPrepare(cart);
            cart.getOrders().forEach(order ->
                    order.changeOrderStatus(OrderStatus.PREPARING));
            return cart;
        }else {
            cart.getOrders().forEach(order ->
                    order.changeOrderStatus(OrderStatus.REJECTED));
            return cart;
        }
    }
}
