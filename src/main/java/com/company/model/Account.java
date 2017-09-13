package com.company.model;

import java.math.BigDecimal;

public class Account {
  private String id;
  private String customer;
  private BigDecimal balance;

  public Account(String id, String customer, BigDecimal balance) {
    this.id = id;
    this.customer = customer;
    this.balance = balance;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getCustomer() {
    return customer;
  }

  public void setCustomer(String customer) {
    this.customer = customer;
  }

  public BigDecimal getBalance() {
    return balance;
  }

  public void setBalance(BigDecimal balance) {
    this.balance = balance;
  }

  @Override
  public String toString() {
    return "Account{" +
        "id='" + id + '\'' +
        ", customer='" + customer + '\'' +
        ", balance=" + balance +
        '}';
  }
}
