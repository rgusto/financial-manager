import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { delay, tap } from 'rxjs';

import { User } from '../model/user';

@Injectable({
  providedIn: 'root',
})
export class UsersService {
  private readonly API = './assets/users.json';

  constructor(private httpClient: HttpClient) {}

  findAll() {
    return this.httpClient.get<User[]>(this.API).pipe(
      delay(60000),
      tap((users: any) => console.log(users))
    );
  }
}
