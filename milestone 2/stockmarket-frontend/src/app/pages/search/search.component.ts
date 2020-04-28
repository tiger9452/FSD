import { Component, OnInit } from '@angular/core';
import {NGXLogger} from 'ngx-logger';

@Component({
  selector: 'app-search',
  template: `
  <app-company></app-company>`,
  styles: [],
})
export class SearchComponent implements OnInit {

  constructor(private logger: NGXLogger) {}

  ngOnInit() {
  }

}
