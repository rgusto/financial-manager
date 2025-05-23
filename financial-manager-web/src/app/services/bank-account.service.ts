import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment';
import { BankAccount } from '../model/bank-account.model';
import { Page } from '../model/page.model';

@Injectable({
  providedIn: 'root'
})
export class BankAccountService {
  private readonly apiUrl = `${environment.apiUrl}/bank-accounts`;

  constructor(private readonly http: HttpClient) { }

  findAll(page: number = 0, size: number = 10, sort: string = 'name,asc'): Observable<Page<BankAccount>> {
    const params = new HttpParams()
      .set('page', page.toString())
      .set('size', size.toString())
      .set('sort', sort);

    return this.http.get<Page<BankAccount>>(this.apiUrl, { params });
  }

  findById(id: string): Observable<BankAccount> {
    return this.http.get<BankAccount>(`${this.apiUrl}/${id}`);
  }

  create(bankAccount: BankAccount): Observable<BankAccount> {
    return this.http.post<BankAccount>(this.apiUrl, bankAccount);
  }

  update(id: string, bankAccount: BankAccount): Observable<BankAccount> {
    return this.http.put<BankAccount>(`${this.apiUrl}/${id}`, bankAccount);
  }

  delete(id: string): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
}
