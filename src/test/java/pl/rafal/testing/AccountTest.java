package pl.rafal.testing;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;

class AccountTest {
    @Test
    void myTest(){
        Account newAccount = new Account();
        assertFalse(newAccount.isActive());
    }
}
