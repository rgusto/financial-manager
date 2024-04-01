import { CommonModule, formatCurrency } from '@angular/common';

import { Component, LOCALE_ID, OnDestroy, OnInit } from '@angular/core';

import { DialogService, DynamicDialogRef } from 'primeng/dynamicdialog';

import { ProgressSpinnerModule } from 'primeng/progressspinner';
import { TableModule } from 'primeng/table';
import { Observable, catchError, of } from 'rxjs';
import { Account } from './../model/account';

import { Footer } from 'primeng/api';
import { ButtonModule } from 'primeng/button';
import { MessageDialogComponent } from '../../shared/components/message-dialog/message-dialog.component';
import { AccountsService } from '../services/accounts.service';
import { ToolbarModule } from 'primeng/toolbar';

@Component({
  selector: 'app-accounts',
  standalone: true,
  imports: [CommonModule, TableModule, ProgressSpinnerModule, ButtonModule, ToolbarModule],
  templateUrl: './accounts.component.html',
  styleUrls: ['../../../global.scss', './accounts.component.scss'],
  providers: [DialogService, { provide: LOCALE_ID, useValue: 'pt-BR' }],
})
export class AccountsComponent implements OnInit, OnDestroy {
  accounts$: Observable<Account[]>;
  displayedColumns = ['name', 'type', 'balance'];
  dynamicDialogRef: DynamicDialogRef | undefined;

  constructor(
    private accountsService: AccountsService,
    public dialogService: DialogService
  ) {
    this.accounts$ = this.accountsService.findAll().pipe(
      catchError((error) => {
        this.onError('Erro ao carregar contas.');
        return of([]);
      })
    );
  }

  ngOnInit(): void {}

  ngOnDestroy() {
    if (this.dynamicDialogRef) {
      this.dynamicDialogRef.close();
    }
  }

  onError(errorMessage: string) {
    this.showDialog(errorMessage);
  }

  showDialog(message: string): void {
    this.dynamicDialogRef = this.dialogService.open(MessageDialogComponent, {
      header: 'Erro',
      modal: true,
      width: '500px',
      height: '200px',
      data: { type: 'E', message: message },
      templates: {
        footer: Footer,
      },
    });
  }

  getFormattedCurrencyValue(value: number): string {
    return formatCurrency(value, 'pt-BR', 'R$', 'BRL', '1.2-2');
  }
}
