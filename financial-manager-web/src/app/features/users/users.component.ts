import { Component, LOCALE_ID, OnInit } from '@angular/core';
import { ProgressSpinnerModule } from 'primeng/progressspinner';
import { TableModule } from 'primeng/table';
import { Observable, catchError, of } from 'rxjs';

import { CommonModule } from '@angular/common';
import { Footer } from 'primeng/api';
import { ButtonModule } from 'primeng/button';
import { DialogService, DynamicDialogRef } from 'primeng/dynamicdialog';
import { TooltipModule } from 'primeng/tooltip';
import { User } from '../../model/user';
import { UsersService } from '../../services/users.service';
import { MessageDialogComponent } from '../../shared/components/message-dialog/message-dialog.component';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'app-users',
  standalone: true,
  imports: [
    CommonModule,
    TableModule,
    ProgressSpinnerModule,
    ButtonModule,
    TooltipModule,
  ],
  templateUrl: './users.component.html',
  styleUrls: ['../../../global.scss', './users.component.scss'],
  providers: [DialogService, { provide: LOCALE_ID, useValue: 'pt-BR' }],
})
export class UsersComponent implements OnInit {
  users$: Observable<User[]> | undefined;
  selectedUsers: User[] = [];
  displayedColumns = ['firstName', 'lastName', 'email'];
  dynamicDialogRef: DynamicDialogRef | undefined;

  constructor(
    private usersService: UsersService,
    public dialogService: DialogService
  ) {
    this.findAll();
  }

  ngOnInit(): void {}

  ngOnDestroy() {
    if (this.dynamicDialogRef) {
      this.dynamicDialogRef.close();
    }
  }

  findAll(): void {
    this.users$ = this.usersService.findAll().pipe(
      catchError((error) => {
        this.onError(error);
        return of([]);
      })
    );
  }

  onError(error: any) {
    if (error instanceof HttpErrorResponse) {
      this.showDialog(error.error ? error.error.message : error.message);
    }
    this.showDialog(error);
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

}
