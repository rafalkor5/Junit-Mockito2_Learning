package pl.rafal.testing.account;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AccountServiceMockTest {
    @Test
    void getAllActiveAccounts() {
        //given
        List<Account> accounts = prepareData();
        AccountRespository accountRepository = mock(AccountRespository.class);
        AccountService accountService = new AccountService(accountRepository); // tu trzeba podać klase która implementuje repozytorium
        given(accountRepository.getAllAccounts()).willReturn(accounts);

        //when
        List<Account> accountList = accountService.getAllActiveAccounts();

        //then
        assertThat(accountList, hasSize(2));
    }

    @Test
    void getAccountByName() {
        //given
        List<Account> accounts = prepareData();
        AccountRespository accountRepository = mock(AccountRespository.class);
        AccountService accountService = new AccountService(accountRepository); // tu trzeba podać klase która implementuje repozytorium
        given(accountRepository.getByName("dawid")).willReturn(Collections.singletonList("nowak"));

        //when
        List<String> accountNames = accountService.findByName("dawid");

        //then
        assertThat(accountNames, contains("nowak"));
    }


    private List<Account> prepareData(){
        Address address1= new Address("Lipowa", 15);
        Account account1 = new Account(address1);
        Account account2= new Account();
        Address address2 = new Address("Chujowa", 6);
        Account account3 = new Account(address2);

        return Arrays.asList(account1,account2,account3);
    }
}