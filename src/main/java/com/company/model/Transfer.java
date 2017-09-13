package com.company.model;

import java.math.BigDecimal;

public class Transfer {
  private String fromAccount;
  private String toAccount;
  private BigDecimal amount;

  public Transfer() {
  }

  public Transfer(String fromAccount, String toAccount, BigDecimal amount) {
    this.fromAccount = fromAccount;
    this.toAccount = toAccount;
    this.amount = amount;
  }

  public String getFromAccount() {
    return fromAccount;
  }

  public String getToAccount() {
    return toAccount;
  }

  public BigDecimal getAmount() {
    return amount;
  }

  @Override
  public String toString() {
    return "Transfer{" +
        "fromAccount='" + fromAccount + '\'' +
        ", toAccount='" + toAccount + '\'' +
        ", amount=" + amount +
        '}';
  }
}
