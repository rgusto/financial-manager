import { CommonModule, formatCurrency } from '@angular/common';

import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { BankAccount } from '../../model/bank-account.model';
import { BankAccountService } from '../../services/bank-account.service';

import { ProgressSpinnerModule } from 'primeng/progressspinner';
import { TableModule } from 'primeng/table';

import { HttpErrorResponse } from '@angular/common/http';
import { MessageService } from 'primeng/api';
import { ButtonModule } from 'primeng/button';
import { TooltipModule } from 'primeng/tooltip';
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
    FormsModule,
  ],
  templateUrl: './accounts.component.html',
  styleUrls: ['../../../global.scss', './accounts.component.scss'],
})
export class AccountsComponent implements OnInit {
  accounts: BankAccount[] = [];
  currentPage = 0;
  pageSize = 10;
  totalElements = 0;
  totalPages = 0;
  sort = 'name,asc';
  loading = false;

  constructor(
    private readonly bankAccountService: BankAccountService,
    protected messageService: MessageService
  ) {}

  ngOnInit(): void {
    this.loadAccounts();
  }

  loadAccounts(): void {
    this.loading = true;
    this.bankAccountService.findAll(this.currentPage, this.pageSize, this.sort)
      .subscribe({
        next: (page) => {
          this.accounts = page.content;
          this.totalElements = page.totalElements;
          this.totalPages = page.totalPages;
          this.loading = false;
        },
        error: (error) => {
          this.onError(error);
          this.loading = false;
        }
      });
  }

  onPageChange(page: number): void {
    this.currentPage = page;
    this.loadAccounts();
  }

  onSortChange(sort: string): void {
    this.sort = sort;
    this.currentPage = 0;
    this.loadAccounts();
  }

  deleteAccount(id: string): void {
    if (confirm('Are you sure you want to delete this account?')) {
      this.bankAccountService.delete(id).subscribe({
        next: () => {
          this.loadAccounts();
          this.showDialog('Conta excluída com sucesso', MessageType.SUCCESS);
        },
        error: (error) => this.onError(error)
      });
    }
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
