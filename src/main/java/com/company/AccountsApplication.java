package com.company;

import com.company.api.AccountService;
import com.company.api.AppExceptionMapper;
import com.company.store.AccountRepository;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class AccountsApplication extends Application<AppConfiguration> {

  public static void main(final String[] args) throws Exception {
    new AccountsApplication().run(args);
  }

  @Override
  public String getName() {
    return "revolut-test";
  }

  @Override
  public void initialize(final Bootstrap<AppConfiguration> bootstrap) {
  }

  @Override
  public void run(final AppConfiguration configuration,
                  final Environment environment) {
    final AccountService accounts = new AccountService(new AccountRepository());
    environment.jersey().register(accounts);
    environment.jersey().register(new AppExceptionMapper());
  }

}
