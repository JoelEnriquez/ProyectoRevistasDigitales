import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Revista } from '../Objects/Revista/Revista';
import { Observable } from 'rxjs';
import { Rutas } from '../Objects/Rutas/Rutas';
import { Etiqueta } from '../Objects/Revista/Etiqueta';
import { Categoria } from '../Objects/Revista/Categoria';

@Injectable({
  providedIn: 'root'
})
export class RevistaService {

  constructor(private httpClient: HttpClient) { }

  crearRevista(revista: Revista, listadoEtiquetas: Etiqueta[]): Observable<void> {
    const formData: FormData = new FormData();
    formData.set('revista',JSON.stringify(revista));
    formData.set('etiquetas',JSON.stringify(listadoEtiquetas));
    return this.httpClient.post<void>(Rutas.API_URL + "RegisterRevistaControl", formData);
  }

  actualizarRevista(revista: Revista, listadoEtiquetas: Etiqueta[]): Observable<void> {
    const formData: FormData = new FormData();
    formData.set('revista',JSON.stringify(revista));
    formData.set('etiquetas',JSON.stringify(listadoEtiquetas));
    return this.httpClient.post<void>(Rutas.API_URL + "RevistaControl", formData);
  }

  //Revistas que no tengan asignado un costo diario aun
  revistasSinGastoDiario(): Observable<Revista[]>{
    return this.httpClient.get<Revista[]>(Rutas.API_URL+"RegisterRevistaControl");
  }

  /**
   * Obtener el listado de revistas del editor en base a su nombre de usuario
   * @param userNameEditor 
   * @returns 
   */
  revistasPropias(userNameEditor: string, action:string): Observable<Revista[]>{
    return this.httpClient.get<Revista[]>(Rutas.API_URL+"RevistaControl?editor="+userNameEditor+"&action="+action);
  }

  etiquetasRevista(nombreRevista: string, action:string): Observable<Etiqueta[]>{
    return this.httpClient.get<Etiqueta[]>(Rutas.API_URL+"RevistaControl?revista="+nombreRevista+"&action="+action);
  }

  asignarCostoDiario(nombre: string, costoDiario: number): Observable<void> {
    const formData: FormData = new FormData();
    formData.set('nombreRevista',nombre);
    formData.set('costoDiario',costoDiario.toLocaleString());
    return this.httpClient.put<void>(Rutas.API_URL+"RegisterRevistaControl",formData);
  }

  cambiarCampoRevista(nombreRevista: string, statusNuevo:boolean, campoModificar:string){
    const formData: FormData = new FormData();
    formData.set('nombreRevista',nombreRevista);
    formData.set('statusNuevo',statusNuevo.toLocaleString());
    formData.set('campoModificar',campoModificar);
    return this.httpClient.put<void>(Rutas.API_URL+"RevistaControl",formData);
  }

}
