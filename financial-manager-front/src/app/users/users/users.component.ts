import { Component, OnInit } from '@angular/core';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { MatPaginator, MatPaginatorModule } from '@angular/material/paginator';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import { MatTableModule } from '@angular/material/table';
import { Observable, catchError, of } from 'rxjs';

import { User } from '../model/user';
import { UsersService } from '../services/users.service';
import { CommonModule } from '@angular/common';
import { error } from 'console';


@Component({
  selector: 'app-users',
  standalone: true,
  imports: [
    CommonModule,
    MatPaginator,
    MatPaginatorModule,
    MatTableModule,
    MatButtonModule,
    MatCardModule,
    MatProgressSpinnerModule
  ],
  templateUrl: './users.component.html',
  styleUrl: './users.component.scss',
})
export class UsersComponent implements OnInit {
  users$: Observable<User[]>;
  displayedColumns = ['firstName', 'lastName', 'email'];

  constructor(private usersService: UsersService) {
    this.users$ = this.usersService.findAll().pipe(
      catchError(error => {
        console.log(error);
        return of ([])
      })
    );
  }

  ngOnInit(): void {}
}
