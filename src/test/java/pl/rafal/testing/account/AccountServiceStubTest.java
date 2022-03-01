package pl.rafal.testing.account;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;

class AccountServiceStubTest {
    @Test
    void getAllActiveAccounts() {
        //given
        AccountRespositoryStub accountRespositoryStub = new AccountRespositoryStub(); //to jest repozytorium
        AccountService accountService = new AccountService(accountRespositoryStub); // tu trzeba podać klase która implementuje repozytorium

        //when
        List<Account> accountList = accountService.getAllActiveAccounts();

        //then
        assertThat(accountList, hasSize(2));
    }
}