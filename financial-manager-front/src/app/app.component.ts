import { Component } from '@angular/core';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MatSidenavModule } from '@angular/material/sidenav';
import { MatToolbarModule } from '@angular/material/toolbar';
import { RouterOutlet } from '@angular/router';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [
    RouterOutlet,
    MatToolbarModule,
    MatIconModule,
    MatButtonModule,
    MatSidenavModule,
  ],
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss',
})
export class AppComponent {
  title = 'financial-manager';
  sidebar = document.getElementById('mySidebar')!;
  main = document.getElementById('main')!;

  /* Set the width of the sidebar to 250px and the left margin of the page content to 250px */
  openNav(): void {
    if (this.sidebar !== null && this.main !== null) {
      this.sidebar.style.width = '250px';
      this.main.style.marginLeft = '250px';
    }
  }

  /* Set the width of the sidebar to 0 and the left margin of the page content to 0 */
  closeNav(): void {
    if (this.sidebar !== null && this.main !== null) {
      this.sidebar.style.width = '0';
      this.main.style.marginLeft = '0';
    }
  }
}
