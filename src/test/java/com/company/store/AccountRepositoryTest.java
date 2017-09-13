package com.company.store;

import com.company.model.Account;
import com.company.model.Transfer;
import com.company.store.AccountRepository.AccountNotFoundException;
import com.company.store.AccountRepository.InsufficientFundsException;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class AccountRepositoryTest {
  private AccountRepository repository = new AccountRepository();

  @Test
  public void getAccount_accountExists() throws Exception {
    assertNotNull(repository.getAccount("1234"));
  }

  @Test(expected = AccountNotFoundException.class)
  public void getAccount_accountDoesNotExist() throws Exception {
    repository.getAccount("1111");
  }

  @Test(expected = AccountNotFoundException.class)
  public void getAccount_NoAccountId() throws Exception {
    repository.getAccount("");
  }

  @Test
  public void transfer_success() throws Exception {
    Transfer transfer = new Transfer("1234", "2345", new BigDecimal("100"));
    repository.transfer(transfer);
    Account senderAccount = repository.getAccount("1234");
    Account recipientAccount = repository.getAccount("2345");
    assertEquals(new BigDecimal("900"), senderAccount.getBalance());
    assertEquals(new BigDecimal("100"), recipientAccount.getBalance());
  }

  @Test(expected = AccountNotFoundException.class)
  public void transfer_senderDoesNotExist() throws Exception {
    Transfer transfer = new Transfer("2222", "2345", new BigDecimal("100"));
    repository.transfer(transfer);
  }

  @Test(expected = AccountNotFoundException.class)
  public void transfer_recipientDoesNotExist() throws Exception {
    Transfer transfer = new Transfer("1234", "3333", new BigDecimal("100"));
    repository.transfer(transfer);
  }

  @Test(expected = InsufficientFundsException.class)
  public void transfer_insufficientFunds() throws Exception {
    Transfer transfer = new Transfer("2345", "2345", new BigDecimal("1001"));
    repository.transfer(transfer);
  }

}
