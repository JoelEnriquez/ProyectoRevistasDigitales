import { Injectable } from '@angular/core';

function getLocalStorage() {
  return localStorage;
}

@Injectable({
  providedIn: 'root'
})
export class RefLocalStorageService {

  constructor() { }

  getLocalStorage() {
    return getLocalStorage();
  }
}
