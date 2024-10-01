import { Component, OnInit } from '@angular/core';
import { TableModule } from 'primeng/table';
import { Observable, catchError, of } from 'rxjs';
import { ProgressSpinnerModule } from 'primeng/progressspinner';

import { User } from '../model/user';
import { UsersService } from '../services/users.service';
import { CommonModule } from '@angular/common';
import { error } from 'console';


@Component({
  selector: 'app-users',
  standalone: true,
  imports: [
    CommonModule,
    TableModule,
    ProgressSpinnerModule
  ],
  templateUrl: './users.component.html',
  styleUrls: ['../../../global.scss','./users.component.scss'],
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
