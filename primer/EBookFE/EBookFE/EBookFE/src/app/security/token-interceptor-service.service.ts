import {Injectable, Injector} from '@angular/core';
import {HttpEvent, HttpHandler, HttpInterceptor, HttpRequest} from '@angular/common/http';
import {Observable} from 'rxjs';
import {UserService} from '../services/users/user.service';

@Injectable({
  providedIn: 'root'
})
export class TokenInterceptorServiceService  implements HttpInterceptor {

  constructor(private inj: Injector) { }

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    const accountService: UserService = this.inj.get(UserService);
    req = req.clone({
      setHeaders: {
        Authorization : `${accountService.getToken()}`
      }
    });
    return next.handle(req);
  }
}
