package pl.rafal.testing;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AccountTest {
    @Test
    void newAccountShouldNotBeActive(){
        //given
        Account newAccount = new Account();
        //then
        assertFalse(newAccount.isActive());
    }
    @Test
    void newAccountShouldBeActiveAfterActivation(){
        //given
        Account newAccount = new Account();
        assertFalse(newAccount.isActive());
        //when
        newAccount.activate();
        //then
        assertTrue(newAccount.isActive());
    }
}
