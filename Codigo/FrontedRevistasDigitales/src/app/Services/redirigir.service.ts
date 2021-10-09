import { Injectable } from '@angular/core';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class RedirigirService {

  constructor(private router: Router) { }

  redirigir(url: String) {
    this.router.navigate([url]);
  }
}
