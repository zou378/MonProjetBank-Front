import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { JwtHelperService } from '@auth0/angular-jwt';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {
    constructor(private http: HttpClient) { }

    url_authentication: string = 'http://localhost:8080/auth';
    login(username: string, password: string) {
        return this.http.post<any>(this.url_authentication, { username: username, password: password })
            .pipe(map((res: any) => {
                // login successful if there's a jwt token in the response
                if (res && res.token) {

                  // Debugging angular-jwt library
                  // const helper = new JwtHelperService();
                  // const decodedToken = helper.decodeToken(res.token);
                  // const expirationDate = helper.getTokenExpirationDate(res.token);
                  // const isExpired = helper.isTokenExpired(res.token);
                  // console.log( '****** Jeton decode : ' + JSON.stringify(decodedToken));

                    // store username and jwt token in local storage to keep user logged in between page refreshes
                    localStorage.setItem('currentUser', JSON.stringify({ username, token: res.token }));

                  //   const cnt = localStorage.getItem('currentUser');
                  //   const cnt_token_parsed = helper.decodeToken(JSON.parse(cnt).token);
                  //   //console.log( '****** Jeton decode à partir du lcoal storage: ' + JSON.stringify(cnt_token_parsed) );

                }
            }));
    }

    logout() {
        // remove user from local storage to log user out
        localStorage.removeItem('currentUser');
    }

    isLoggedIn(): boolean {
        return (localStorage.getItem('currentUser')) ? true : false;
    }
    getJwtSubject(): string {
      const tokenCnt = localStorage.getItem('currentUser');
      if (tokenCnt) {
        return new JwtHelperService().decodeToken(JSON.parse(tokenCnt).token).sub;
      }
      return null;
    }

}
