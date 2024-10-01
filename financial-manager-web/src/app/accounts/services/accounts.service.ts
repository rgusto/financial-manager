import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { delay, tap } from 'rxjs';
import { Account } from '../model/account';

@Injectable({
  providedIn: 'root'
})
export class AccountsService {

  private readonly API = 'api/v1/bank-accounts';

  constructor(private httpClient: HttpClient) {}

  findAll() {
    return this.httpClient.get<Account[]>(this.API).pipe(
       // delay(3000),
      tap((accounts: any) => console.log(accounts))
    );
  }
}
