package pl.rafal.testing.cart;

public interface CartHandler {

    boolean canHandCart(Cart cart);
    void sendToPrepare(Cart cart);
}
