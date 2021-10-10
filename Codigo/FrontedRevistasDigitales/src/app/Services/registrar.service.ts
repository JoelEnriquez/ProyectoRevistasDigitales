import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Rutas } from '../Objects/Rutas';
import { Persona } from '../Objects/Persona';
import { RespuestaRegistro } from '../Objects/ResponseRegister';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class RegistrarService {

  constructor(private httpClient: HttpClient) { }

  registrarPersona(persona: Persona): Observable<Persona>{
    return this.httpClient.post<Persona>(Rutas.API_URL + "RegisterControl", persona);
  }
}
