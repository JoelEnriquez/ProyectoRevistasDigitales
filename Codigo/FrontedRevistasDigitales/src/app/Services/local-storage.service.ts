import { Injectable } from '@angular/core';
import { RefLocalStorageService } from './ref-local-storage.service';

@Injectable({
  providedIn: 'root'
})
export class LocalStorageService {
  private _localStorage: Storage;
  constructor(private _localStorageRef: RefLocalStorageService) {
    this._localStorage = this._localStorageRef.getLocalStorage();
  }

  setItem(_data: any, _llave: string) {
    this._localStorage.setItem(_llave, JSON.stringify(_data));
  }

  clear() {
    this._localStorage.clear();
  }

  obtenerData(_llave: string) {
    return this._localStorage.getItem(_llave);
  }

  removerData(_llave: string) {
    this._localStorage.removeItem(_llave);
  }
}

