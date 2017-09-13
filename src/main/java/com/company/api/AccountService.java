package com.company.api;

import com.company.model.Account;
import com.company.model.Transfer;
import com.company.store.AccountRepository;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/account")
@Produces(MediaType.APPLICATION_JSON)
public class AccountService {
  private AccountRepository repository;

  public AccountService(AccountRepository repository) {
    this.repository = repository;
  }

  @GET
  @Path("/{id}/info")
  public Account accountInfo(@PathParam("id") String accountId) {
    return repository.getAccount(accountId);
  }

  @POST
  @Path("/transfer")
  @Consumes(MediaType.APPLICATION_JSON)
  public Response transfer(@PathParam("id") String accountId, Transfer transfer) {
    repository.transfer(transfer);
    return Response.ok().build();
  }
}
