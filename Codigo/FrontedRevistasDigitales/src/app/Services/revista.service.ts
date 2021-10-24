import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Revista } from '../Objects/Revista/Revista';
import { Observable } from 'rxjs';
import { Rutas } from '../Objects/Rutas/Rutas';
import { Etiqueta } from '../Objects/Revista/Etiqueta';
import { Categoria } from '../Objects/Revista/Categoria';
import { FiltroEnum } from '../Objects/Revista/FiltroEnum';

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

  /**
   * Se actualizan los datos de una revista
   * @param revista 
   * @param listadoEtiquetas 
   * @returns 
   */
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

  /**
   * Obtener el listado de las etiquetas asociadas a una revista
   * @param nombreRevista 
   * @param action 
   * @returns 
   */
  etiquetasRevista(nombreRevista: string, action:string): Observable<Etiqueta[]>{
    return this.httpClient.get<Etiqueta[]>(Rutas.API_URL+"RevistaControl?revista="+nombreRevista+"&action="+action);
  }

  cantidadEstadisticaRevista(nombreRevista: string, estadistica:string): Observable<number>{
    return this.httpClient.get<number>(Rutas.API_URL+"RevistaControl?revista="+nombreRevista+"&action=estadistica&estadistica="+estadistica);
  }

  asignarCostoDiario(nombre: string, costoDiario: number): Observable<void> {
    const formData: FormData = new FormData();
    formData.set('nombreRevista',nombre);
    formData.set('costoDiario',costoDiario.toLocaleString());
    return this.httpClient.put<void>(Rutas.API_URL+"RegisterRevistaControl",formData);
  }

  /**
   * 
   * @param nombreRevista 
   * @param statusNuevo 
   * @param campoModificar LIKES | COMENTS | SUSCRIPCIONES
   * @returns 
   */
  cambiarCampoRevista(nombreRevista: string, statusNuevo:boolean, campoModificar:string){
    const formData: FormData = new FormData();
    formData.set('nombreRevista',nombreRevista);
    formData.set('statusNuevo',statusNuevo.toLocaleString());
    formData.set('campoModificar',campoModificar);
    return this.httpClient.put<void>(Rutas.API_URL+"RevistaControl",formData);
  }

  obtenerListadoCategoriasPreferencia(_userName: string): Observable<Categoria[]>{
    return this.httpClient.get<Categoria[]>(Rutas.API_URL+"ControlInfoRevistas?action=listado_categorias&user_name="+_userName);
  }

  obtenerRevistasPorCategoria(_nombreCategoria:string): Observable<Revista[]>{
    return this.httpClient.get<Revista[]>(Rutas.API_URL+"ControlInfoRevistas?action=revistas_categoria&categoria="+_nombreCategoria);
  }
  
  obtenerRevistasPorFiltro(_nombreBusqueda:string, filtro: FiltroEnum): Observable<Revista[]>{
    return this.httpClient.get<Revista[]>(Rutas.API_URL+"ControlInfoRevistas?action=filtro&filtro="+filtro+"&busqueda="+_nombreBusqueda);
  }

  obtenerRevistaPorNombre(_nombreRevista:string): Observable<Revista[]>{
    return this.httpClient.get<Revista[]>(Rutas.API_URL+"ControlInfoRevistas?action=info_revista&nombre="+_nombreRevista);
  }
}
