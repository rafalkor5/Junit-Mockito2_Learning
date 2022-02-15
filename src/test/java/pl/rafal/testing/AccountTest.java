package pl.rafal.testing;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

//import static org.hamcrest.MatcherAssert.assertThat;
//import static org.hamcrest.Matchers.*;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumingThat;

class AccountTest {
    @Test
    void newAccountShouldNotBeActive(){
        //given
        Account newAccount = new Account();
        //then
        assertFalse(newAccount.isActive());
        //hamcrest
//        assertThat(newAccount.isActive(), equalTo(false));
//        assertThat(newAccount.isActive(), is(false));
        //assertJ
        assertThat(newAccount.isActive()).isFalse();

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
        //hamcrest
//        assertThat(address,nullValue());
        //assertj
        assertThat(address).isNull();
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
        @RepeatedTest(5)
        void newAccountWithNotNullAddresSchouldBeActive(){
        //given
        Address address = new Address("zmieniaczana ", 45);
        //when
        Account account = new Account(address);
        //then
        assumingThat(address != null,
                () -> assertTrue(account.isActive())
                );
        }
}
