package pl.rafal.testing.account;

import java.util.List;
import java.util.stream.Collectors;

public class AccountService {
    private AccountRespository accountRespository;

    public AccountService(AccountRespository accountRespository){
        this.accountRespository = accountRespository;
    }

    List<Account> getAllActiveAccounts(){
        return accountRespository.getAllAccounts().stream()
                .filter(Account::isActive)
                .collect(Collectors.toList());
    }

    List<String> findByName(String name){
        return accountRespository.getByName(name);
    }
}
