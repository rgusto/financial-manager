import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

import { User } from '../model/user';

@Injectable({
  providedIn: 'root',
})
export class UsersService {
  private readonly API = '/api/v1/users';

  constructor(protected httpClient: HttpClient) {}

  findAll() {
    return this.httpClient.get<User[]>(this.API, {observe: "body"});
  }
}
