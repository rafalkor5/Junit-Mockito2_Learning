package pl.rafal.testing.account;

import java.util.List;

public interface AccountRespository {
    List<Account> getAllAccounts();
    List<String> getByName(String name);
}
