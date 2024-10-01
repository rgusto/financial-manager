import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';

import { AccountsRoutingModule } from './accounts-routing.module';
import { MessageDialogComponent } from '../shared/components/message-dialog/message-dialog.component';



@NgModule({
  declarations: [],
  imports: [
    CommonModule,
    AccountsRoutingModule
  ],
  exports: []
})
export class AccountsModule { }
