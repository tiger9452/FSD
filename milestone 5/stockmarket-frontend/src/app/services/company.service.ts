import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import {environment} from '../../environments/environment';
import { Company, Dict } from '../models/company';
import { NGXLogger } from 'ngx-logger';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root',
})
export class CompanyService {
  constructor(private http: HttpClient,
              private logger: NGXLogger) {}

  findCompanies(company: Company) {
    httpOptions.headers.append('Authorization', `${localStorage.getItem('Authorization')}`);
    httpOptions.headers.append('withCredentials', 'true');
    return this.http.post(`${environment.baseUrl}/api/company/list`, JSON.stringify(company), httpOptions).subscribe((data : any) => {
      data.headers.keys().forEach(key => {
        this.logger.log(`${key} => ${data.headers.get(key)}`);
        if (key === 'Authorization') {
          sessionStorage.setItem('Authorization', data.headers.get(key));
        }
      });
    });
  }

  getCompanies() {
    return this.http.get('assets/data/companies.json').toPromise()
      .then(res => <Company>res)
      .then(data => {
        return data;
      });
  }
}
