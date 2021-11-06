import { Observable } from 'rxjs';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Rutas } from '../Objects/Rutas/Rutas';
import { Revista } from '../Objects/Revista/Revista';
import { RevistaReaccion } from '../Objects/ReportsObjets/RevistaMeGusta';

@Injectable({
  providedIn: 'root'
})
export class ReportesService {

  constructor(private _httpClient:HttpClient) { }

  public obtenerReporteMeGusta(_userName:string, _fechaInicial:string, _fechaFinal:string, _filtro:string): Observable<RevistaReaccion[]>{
    return this._httpClient.get<RevistaReaccion[]>(Rutas.API_URL + "ReportesControl?user_name="+_userName+"&fecha_inicio="+_fechaInicial+"&fecha_fin="+_fechaFinal+"&filtro="+_filtro+"&tipo=EDITOR&action=mas_gustadas");
  }
  
}
