import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot } from '@angular/router';
import { Observable } from 'rxjs';
import { MessageService } from 'primeng/api';

@Injectable({
  providedIn: 'root'
})
export class SigninGuard implements CanActivate {

  constructor(private msgSrv: MessageService) {}

  canActivate(route: ActivatedRouteSnapshot,
              state: RouterStateSnapshot) : boolean | Observable<boolean> | Promise<boolean> {
      return new Observable((observer) => {
        if (sessionStorage.getItem('Authorization')) {
            observer.next(true);
            observer.complete();
            return;
        }
        this.msgSrv.add({severity:'success', summary:'Error Message', detail:'Please signin first!'});
        observer.next(false);
        observer.complete();
      });
  }
}
