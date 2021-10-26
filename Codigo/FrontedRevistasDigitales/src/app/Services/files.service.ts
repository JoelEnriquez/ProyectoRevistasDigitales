import { Observable } from 'rxjs';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Rutas } from '../Objects/Rutas/Rutas';

@Injectable({
  providedIn: 'root'
})
export class FilesService {

  constructor(private httpClient: HttpClient) { }

  public getImage(userName: string): string {
    return Rutas.API_URL + "FileControl?userName=" + userName + "&action=image";
  }

  public obtenerReporte(_userName:string, _fechaInicial:string, _fechaFinal:string, _filtro:string, _action:string, _tipo:string){
    return Rutas.API_URL + "JasperControl?user_name="+_userName+"&fecha_inicio="+_fechaInicial+"&fecha_fin="+_fechaFinal+"&filtro="+_filtro+"&tipo="+_tipo+"&action="+_action;
  }

  public descargarReporte(_userName:string, _fechaInicial:string, _fechaFinal:string, _filtro:string, _action:string, _tipo:string){
    return Rutas.API_URL + "JasperControl?user_name="+_userName+"&fecha_inicio="+_fechaInicial+"&fecha_fin="+_fechaFinal+"&filtro="+_filtro+"&tipo="+_tipo+"&action="+_action;
  }

  // public obtenerReporte(_userName:string, _fechaInicial:string, _fechaFinal:string, _filtro:string, _action:string, _tipo:string):Observable<string> {
  //   return this.httpClient.get<string>(Rutas.API_URL + "JasperControl?user_name="+_userName+"&fecha_inicio="+_fechaInicial+"&fecha_fin="+_fechaFinal+"&filtro="+_filtro+"&tipo="+_tipo+"&action="+_action);
  // }
}
