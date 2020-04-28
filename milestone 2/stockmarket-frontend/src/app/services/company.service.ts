import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import {environment} from '../../environments/environment';
import { Company, Dict } from '../models/company';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root',
})
export class CompanyService {
  constructor(private http: HttpClient) {}

  postCompanies(company: Company) {
    return this.http.post(`${environment.baseUrl}/companies`, JSON.stringify(company), httpOptions);
  }

  getCompanies() {
    return this.http.get('assets/data/companies.json').toPromise()
      .then(res => <Company>res)
      .then(data => {
        return data;
      });
  }
}
