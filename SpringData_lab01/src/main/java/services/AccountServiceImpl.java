package services;

import com.example.demo.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class AccountServiceImpl implements AccountService{

   private final AccountRepository accountRepository;

   @Autowired
    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }



    @Override
    public void withDrawMoney(BigDecimal money, Long id) {

    }

    @Override
    public void transferMoney(BigDecimal amount, Long id) {

    }
}
