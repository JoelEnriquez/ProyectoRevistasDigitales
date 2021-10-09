import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http'
import { Persona } from '../Objects/Persona';
import { Observable } from 'rxjs';
import { Rutas } from '../Objects/Rutas';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  constructor(private httpClient: HttpClient) { }

  validarPersona(persona: Persona): Observable<Persona>{
    return this.httpClient.post<Persona>(Rutas.API_URL + "LoginControl", persona);
  }
  
}
