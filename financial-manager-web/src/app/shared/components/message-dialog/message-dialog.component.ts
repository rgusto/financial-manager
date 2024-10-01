import { Component, Optional } from '@angular/core';
import { DialogService, DynamicDialogConfig, DynamicDialogRef } from 'primeng/dynamicdialog';

@Component({
  selector: 'app-message-dialog',
  standalone: true,
  imports: [],
  templateUrl: './message-dialog.component.html',
  styleUrl: './message-dialog.component.scss'

})
export class MessageDialogComponent {
  message : string = '';

  constructor(
    public ref: DynamicDialogRef,
    @Optional() private config: DynamicDialogConfig
  ) {
    this.message = config.data.message;
    // console.log('Data: ' + JSON.stringify(config.data.message));
  }
}
