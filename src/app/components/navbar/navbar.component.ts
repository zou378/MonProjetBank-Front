import { Component, OnInit } from '@angular/core';
import { AuthenticationService } from '../../services/authentication.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {

  constructor(private _authservice: AuthenticationService) { }

  ngOnInit() {
  }

  isLoggedIn(): boolean {
    return this._authservice.isLoggedIn();
  }

  getJwtSubject(): string {
    return this._authservice.getJwtSubject();
  }

}
