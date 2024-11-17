import { Component, OnInit } from '@angular/core';
import { ProgressSpinnerModule } from 'primeng/progressspinner';
import { TableModule } from 'primeng/table';
import { Observable, catchError, of } from 'rxjs';

import { CommonModule } from '@angular/common';
import { HttpErrorResponse } from '@angular/common/http';
import { MessageService } from 'primeng/api';
import { ButtonModule } from 'primeng/button';
import { TooltipModule } from 'primeng/tooltip';
import { User } from '../../model/user';
import { UsersService } from '../../services/users.service';
import { MessageType } from '../../shared/enums/message-type';

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
  styleUrls: ['../../../global.scss', './users.component.scss']
})
export class UsersComponent implements OnInit {
  users$: Observable<User[]> | undefined;
  selectedUsers: User[] = [];
  displayedColumns = ['firstName', 'lastName', 'email'];

  constructor(
    protected usersService: UsersService,
    protected messageService: MessageService
  ) {
    this.findAll();
  }

  ngOnInit(): void {}

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
      detail: message
    });
  }
}
