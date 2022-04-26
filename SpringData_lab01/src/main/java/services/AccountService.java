package services;

import java.math.BigDecimal;

public interface AccountService {
    void withDrawMoney(BigDecimal money, Long id);
    void transferMoney(BigDecimal amount, Long id);
}
