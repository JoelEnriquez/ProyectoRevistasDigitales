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

  revistasSinGastoDiario(): Observable<Revista[]>{
    return this.httpClient.get<Revista[]>(Rutas.API_URL+"RegisterRevistaControl");
  }

  asignarCostoDiario(nombre: string, costoDiario: number): Observable<void> {
    const formData: FormData = new FormData();
    formData.set('nombreRevista',nombre);
    formData.set('costoDiario',costoDiario.toLocaleString());
    return this.httpClient.put<void>(Rutas.API_URL+"RegisterRevistaControl",formData);
  }

}
