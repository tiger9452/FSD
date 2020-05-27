import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { FormBuilder, FormGroup, Validators} from '@angular/forms';
import {NGXLogger} from 'ngx-logger';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.scss']
})
export class ProfileComponent implements OnInit {

  @Input() title: string;
  @Output() forward = new EventEmitter<string>();
  myProfileFormGroup: FormGroup;
  
  constructor(private logger: NGXLogger,
              private fb: FormBuilder,) {
    const userJson = sessionStorage.getItem('userInfo');

    const user: any = JSON.parse(userJson ? userJson : '{"name": "", "email": "", "mobile": ""}');
    logger.log(user);
    this.myProfileFormGroup = this.fb.group(
      {
        name: [user.name, [Validators.required]],
        password: ['', [Validators.required]],
        passwordAgain: ['', [Validators.required]],
        newPassword: ['', [Validators.required]],
        email: [user.email, [Validators.required]],
        mobile: [user.mobile],
      });
  }

  forwardTarget() {
    this.logger.log(this.title);
    if (this.title.indexOf("Register") > -1) {
      this.forward.emit('/login');
    }
    if (this.title.indexOf("Update") > -1) {
      this.forward.emit('/compare');
    }
  }

  ngOnInit(): void {
  }

}

