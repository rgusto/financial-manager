import { CommonModule, formatCurrency } from '@angular/common';
import { Account } from '../../model/account';

import { Component, OnInit } from '@angular/core';

import { ProgressSpinnerModule } from 'primeng/progressspinner';
import { TableModule } from 'primeng/table';
import { Observable, catchError, of } from 'rxjs';

import { HttpErrorResponse } from '@angular/common/http';
import { MessageService } from 'primeng/api';
import { ButtonModule } from 'primeng/button';
import { TooltipModule } from 'primeng/tooltip';
import { AccountsService } from '../../services/accounts.service';
import { MessageType } from '../../shared/enums/message-type';

@Component({
  selector: 'app-accounts',
  standalone: true,
  imports: [
    CommonModule,
    TableModule,
    ProgressSpinnerModule,
    ButtonModule,
    TooltipModule,
  ],
  templateUrl: './accounts.component.html',
  styleUrls: ['../../../global.scss', './accounts.component.scss'],
})
export class AccountsComponent implements OnInit {
  accounts$: Observable<Account[]> | undefined;
  displayedColumns = ['name', 'type', 'balance'];

  constructor(
    protected accountsService: AccountsService,
    protected messageService: MessageService
  ) {
    this.findAll();
  }

  ngOnInit(): void {}

  findAll(): void {
    this.accounts$ = this.accountsService.findAll().pipe(
      catchError((error) => {
        this.onError('Erro ao carregar contas: ' + error.error.message);
        return of([]);
      })
    );
  }

  onError(error: any) {
    if (error instanceof HttpErrorResponse) {
      this.showDialog(
        error.error ? error.error.message : error.message,
        MessageType.ERROR
      );
    } else {
      this.showDialog(error, MessageType.ERROR);
    }
  }

  showDialog(message: string, type: MessageType = MessageType.INFO): void {
    this.messageService.add({
      severity: type,
      detail: message,
    });
  }

  getFormattedCurrencyValue(value: number): string {
    return formatCurrency(value, 'pt-BR', 'R$', 'BRL', '1.2-2');
  }
}
