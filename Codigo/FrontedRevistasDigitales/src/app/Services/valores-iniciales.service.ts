import { Observable } from 'rxjs';
import { ValoresGlobales } from './../Objects/ValoresGlobales';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Rutas } from '../Objects/Rutas/Rutas';

@Injectable({
  providedIn: 'root'
})
export class ValoresInicialesService {

  constructor(private httpClient: HttpClient) { }

  verificarValoresGlobales():Observable<number>{
    return this.httpClient.get<number>(Rutas.API_URL+"ComprobarControl");
  }

  ingresarValores(valoresGlobales: ValoresGlobales): Observable<void>{
    return this.httpClient.post<void>(Rutas.API_URL+"ComprobarControl",valoresGlobales);
  }
}
