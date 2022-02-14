package pl.rafal.testing;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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

    @Test
    void NewCreatedAccountSchuldNotHaveDeliveryAddresSet(){
        //given
        Account account = new Account();

        //when
        Address address = account.getDefaultDeliveryAddress();

        //then
        assertNull(address);

        }

        @Test
    void defaultDeliveryAddresShouldBeNotNullAfterSet(){
        //given
        Account account = new Account();
        Address address = new Address("wroclawska", 5);
        account.setDefaultDeliveryAddress(address);

        //when
        Address address1 = account.getDefaultDeliveryAddress();

        //then
         assertNotNull(address1);
        }
}
