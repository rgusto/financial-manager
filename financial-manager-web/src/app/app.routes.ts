import { Routes } from '@angular/router';
import { UsersComponent } from './features/users/users.component';
import { AccountsComponent } from './features/accounts/accounts.component';

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
