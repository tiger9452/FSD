import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import {UserService} from '../../services/user.service';
import {User} from '../../models/user';

interface Alert {
  type: string;
  message: string;
}

const ALERTS: Alert[] = [];

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})

export class LoginComponent implements OnInit {

  alerts: Alert[];
  url: string;

  constructor(private userService: UserService, private router: Router) {
    this.reset();
  }

  ngOnInit(): void {
    this.url = "assets/image/login_banner.jpeg"
    if (sessionStorage.getItem('Authorization')) {
      this.router.navigate(['/compare']);
    }
  }

  onSubmit(value: any) {
    if (this.validInput(value)) {
      this.userService.getSignIn(value).then(
        (data : any) => {
          // console.log(JSON.stringify(data));
          const users: User[] = <User[]>data;
          users.forEach(element => {
            if (element.name === value.name && element.password === value.password) {
              sessionStorage.setItem('userInfo', JSON.stringify(element));
              if (element.adminRole) {
                sessionStorage.setItem('isAdmin', '1');
                this.router.navigate(['/import']);
              } else {
                sessionStorage.setItem('isAdmin', '0');
                this.router.navigate(['/compare']);
              }
              return;
            }
          });
          this.alerts.push({type : 'danger', message: 'username or password error!'});
        }
      );
    }
  }
  
  validInput(value: any): boolean {
    this.reset();
    let result = true
    if (!value.name) {
      this.alerts.push({type : 'danger', message: 'username required!'});
      result = false;
    }

    if (!value.password) {
      this.alerts.push({type : 'danger', message: 'password required!'});
      result =  false;
    }

    if (value.password.length < 6) {
      this.alerts.push({type : 'danger', message: 'password length must be greater than 6!'});
      result =  false;
    }
    return result;
  }

  close(alert: Alert) {
    this.alerts.splice(this.alerts.indexOf(alert), 1);
  }

  reset() {
    this.alerts = Array.from(ALERTS);
  }

  onRegister() {
    this.router.navigate(['/register']);
  }

}
