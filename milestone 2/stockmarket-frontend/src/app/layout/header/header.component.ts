import { Component, DoCheck, AfterContentChecked } from '@angular/core';
import { ToastModule } from 'primeng/toast';
import { Router } from '@angular/router';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss'],
})
export class HeaderComponent implements AfterContentChecked {
  constructor(private router: Router) {}

  isSignin: boolean;
  isAdmin: boolean;
  compareActive = "inactive";
  searchActive = "inactive";
  profileEditActive = "inactive"
  importActive = "inactive";
  companyEditActive = "inactive";

  ngAfterContentChecked(): void {
    // this.route.url.subscribe((url) => console.log(url[0].path));
    if (sessionStorage.getItem('token')) {
      this.isSignin = true;
      if (this.router.url.substr(1) === 'compare') {
        this.compareActive = "active";
        this.searchActive = "inactive";
        this.profileEditActive = "inactive";
      }
      if (this.router.url.substr(1) === 'search') {
        this.compareActive = "inactive";
        this.searchActive = "active";
        this.profileEditActive = "inactive";
      }
      if (this.router.url.substr(1) === 'profile-edit') {
        this.compareActive = "inactive";
        this.searchActive = "inactive";
        this.profileEditActive = "active";
      }
      if (this.router.url.substr(1) === 'import') {
        this.importActive = "active";
        this.companyEditActive = "inactive";
      }
      if (this.router.url.substr(1) === 'company-edit') {
        this.importActive = "inactive";
        this.companyEditActive = "active";
      }
    } else {
      this.isSignin = false;
    }
    if (sessionStorage.getItem('isAdmin') === '1') {
      this.isAdmin = true;
    } else {
      this.isAdmin = false;
    }
  }

  signOut() {
    sessionStorage.clear();
    this.router.navigate(['/login']);
  }
}
