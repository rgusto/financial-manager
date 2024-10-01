import { Routes } from '@angular/router';
import { UsersComponent } from './users/users/users.component';
import { AccountsComponent } from './accounts/accounts/accounts.component';

export const APP_ROUTES: Routes = [
  {
    path: 'users',
    component: UsersComponent,
  },
  {
    path: 'accounts',
    component: AccountsComponent,
  },
];
