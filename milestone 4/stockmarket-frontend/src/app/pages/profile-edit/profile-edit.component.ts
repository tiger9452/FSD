import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import {NGXLogger} from 'ngx-logger';

@Component({
  selector: 'app-profile-edit',
  template: `
    <app-profile [title]="myProfileTitle" (forward)="forwardTo($event)"></app-profile>
  `,
  styles: []
})
export class ProfileEditComponent implements OnInit {

  myProfileTitle: string = "Update My Profile";
  constructor(private logger: NGXLogger, 
              private router: Router) { }

  ngOnInit(): void {
  }

  forwardTo(path: string): void {
    this.logger.log("path: " + path);
    this.router.navigate([path]);
  }

}
