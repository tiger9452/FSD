import { Component, OnInit } from '@angular/core';
import {NGXLogger} from 'ngx-logger';

@Component({
  selector: 'app-company-edit',
  template: `
  <app-company [withAction]="canEdit"></app-company>`,
  styles: [],
})
export class CompanyEditComponent implements OnInit {

  canEdit: boolean = true;
  constructor(private logger: NGXLogger) {}

  ngOnInit() {
  }

}