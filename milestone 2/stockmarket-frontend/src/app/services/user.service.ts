import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Observable} from 'rxjs';
import { map, mergeMap } from 'rxjs/operators';
import { environment } from '../../environments/environment';
import { User } from '../models/user';
import { NGXLogger } from 'ngx-logger';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class UserService {

  users: User[];

  constructor(private http: HttpClient,
              private logger: NGXLogger) { }

  // createDb() {
  //   const users = this.getSignIn(null);
  //   return {users};
  // }

  public get currentUserToken(): string {
    return sessionStorage.getItem('token');
  }

  getUsers(): Observable<User[]> {
    return this.http.get<User[]>('assets/data/users.json').pipe(map(res => res));
 }

  getData(search: string) {
    const WIKIPEDIA_URL = "https://zh.wikipedia.org/w/api.php";
    const url = this.searchUrl(search, WIKIPEDIA_URL);
    this.logger.log("access URL： " + url)
    return this.http.jsonp(url, 'callback')
    .subscribe(
      results => {
      this.logger.log("get data： " + results)
    });
  }

  searchUrl(term: string, base: string) {
    let params = new HttpParams()
      .append('action', 'opensearch')
      .append('search', encodeURIComponent(term))
      .append('format', 'json');
    return `${base}?${params.toString()}`;
  }

  postSignIn(user: User) {
    return this.http.post(`${environment.baseUrl}/login`, JSON.stringify(user), httpOptions);
  }

  getSignIn(user: User) {
    return this.http.get('assets/data/users.json').toPromise()
      .then(res => <User>res)
      .then(data => {
        return data;
      });
  }

}