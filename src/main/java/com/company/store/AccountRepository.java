package com.company.store;

import com.company.model.Account;
import com.company.model.Transfer;

import java.math.BigDecimal;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class AccountRepository {

  private Map<String, Account> accounts = new ConcurrentHashMap<>();

  {
    // Initialise some accounts
    accounts.put("1234", new Account("1234", "Bob", new BigDecimal("1000")));
    accounts.put("2345", new Account("2345", "Alice", new BigDecimal("0")));
    accounts.put("3456", new Account("3456", "Fred", new BigDecimal("250")));
    accounts.put("4567", new Account("4567", "Sally", new BigDecimal("300")));
  }

  public Account getAccount(String accountId) {
    Account account = accounts.get(accountId);
    if (account == null) {
      throw new AccountNotFoundException(String.format("Account %s cannot be found", accountId));
    }
    return account;
  }

  public void transfer(Transfer transfer) {
    Account fromAccount = getAccount(transfer.getFromAccount());
    Account toAccount = getAccount(transfer.getToAccount());
    synchronized (this) {
      BigDecimal remainingBalance = fromAccount.getBalance().subtract(transfer.getAmount());
      if (remainingBalance.signum() == -1) {
        throw new InsufficientFundsException(
            String.format("There is not enough money in this account. An additional %s is required.",
                remainingBalance.abs()));
      }
      fromAccount.setBalance(remainingBalance);
      toAccount.setBalance(toAccount.getBalance().add(transfer.getAmount()));
    }
  }

  public static class AccountNotFoundException extends RuntimeException {
    public AccountNotFoundException(String message) {
      super(message);
    }
  }

  public static class InsufficientFundsException extends RuntimeException {
    public InsufficientFundsException(String message) {
      super(message);
    }
  }

}
