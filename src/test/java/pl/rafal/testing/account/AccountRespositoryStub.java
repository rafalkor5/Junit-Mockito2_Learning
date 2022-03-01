package pl.rafal.testing.account;

import java.util.Arrays;
import java.util.List;

public class AccountRespositoryStub implements AccountRespository{
    @Override
    public List<Account> getAllAccounts() {
        Address address1= new Address("Lipowa", 15);
        Account account1 = new Account(address1);
        Account account2= new Account();
        Address address2 = new Address("Chujowa", 6);
        Account account3 = new Account(address2);

        return Arrays.asList(account1,account2,account3);
    }

    @Override
    public List<String> getByName(String name) {
        return null;
    }
}
