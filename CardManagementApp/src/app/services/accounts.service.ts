import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Account } from '../models/account.model';
const baseUrl = 'http://localhost:8080/api/v1/accounts';

@Injectable({
  providedIn: 'root'
})
export class AccountsService {

  constructor(private http: HttpClient) { }


  // //get all accounts ok
  // getAllAccounts(): Observable<Account[]> {
  //   return this.http.get<Account[]>(baseUrl);
  // }

  //get account by client id
  getAccountByClientId(clientId: number): Observable<Account[]> {
    return this.http.get<Account[]>(`${baseUrl}/client-account/${clientId}`);
  }

  //create account
  create(account: any): Observable<any> {
    return this.http.post(baseUrl, account);
  }

  //update account by accountId ok
  update(accountId: any, data: any): Observable<any> {
    return this.http.put(`${baseUrl}/${accountId}`, data);
  }

  //delete account by accountId ok
  delete(accountId: any): Observable<any> {
    return this.http.delete(`${baseUrl}/${accountId}`);
  }

  //get account by accountId ok
  getAccountByAccountId(accountId: any): Observable<Account> {
    return this.http.get(`${baseUrl}/account/${accountId}`);
  }

}
