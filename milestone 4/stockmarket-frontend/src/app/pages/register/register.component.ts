import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import {NGXLogger} from 'ngx-logger';

@Component({
  selector: 'app-register',
  template: `
    <app-profile [title]="registerTitle" (forward)="forwardTo($event)"></app-profile>
  `,
  styles: []
})
export class RegisterComponent implements OnInit {

  registerTitle: string = "Register an new Account";
  constructor(private logger: NGXLogger, 
    private router: Router) { }

  ngOnInit(): void {
  }

  forwardTo(path: string): void {
    this.logger.log("path: " + path);
    this.router.navigate([path]);
  }

}
