import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Revista } from '../Objects/Revista';
import { Observable } from 'rxjs';
import { Rutas } from '../Objects/Rutas';

@Injectable({
  providedIn: 'root'
})
export class CreateRevistaService {

  constructor(private httpClient: HttpClient) { }

  crearRevista(revista: Revista): Observable<void> {
    return this.httpClient.post<void>(Rutas.API_URL + "RegisterRevistaControl", revista);
  }

}
