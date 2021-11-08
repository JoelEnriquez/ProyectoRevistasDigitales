import { Observable } from 'rxjs';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Rutas } from '../Objects/Rutas/Rutas';
import { Revista } from '../Objects/Revista/Revista';
import { RevistaReaccion } from '../Objects/ReportsObjets/RevistaMeGusta';
import { GananciaEditor } from '../Objects/ReportsObjets/GananciaEditor';
import { Comentario } from '../Objects/Revista/Comentario';
import { Suscripcion } from '../Objects/Revista/Suscripcion';
import { RevistaSuscripcion } from '../Objects/ReportsObjets/RevistaSuscripcion';
import { RevistaComentario } from '../Objects/ReportsObjets/RevistaComentario';

@Injectable({
  providedIn: 'root'
})
export class ReportesService {

  constructor(private _httpClient:HttpClient) { }

  public obtenerReporteMeGusta(_userName:string, _fechaInicial:string, _fechaFinal:string, _filtro:string): Observable<RevistaReaccion[]>{
    return this._httpClient.get<RevistaReaccion[]>(Rutas.API_URL + "ReportesControl?user_name="+_userName+"&fecha_inicio="+_fechaInicial+"&fecha_fin="+_fechaFinal+"&filtro="+_filtro+"&tipo=EDITOR&action=mas_gustadas");
  }

  public obtenerReporteGananciasEditor(_userName:string, _fechaInicial:string, _fechaFinal:string, _filtro:string): Observable<GananciaEditor[]>{
    return this._httpClient.get<GananciaEditor[]>(Rutas.API_URL + "ReportesControl?user_name="+_userName+"&fecha_inicio="+_fechaInicial+"&fecha_fin="+_fechaFinal+"&filtro="+_filtro+"&tipo=EDITOR&action=ganancias_editor");
  }

  public obtenerReporteComentariosRevista(_userName:string, _fechaInicial:string, _fechaFinal:string, _filtro:string): Observable<Comentario[]>{
    return this._httpClient.get<Comentario[]>(Rutas.API_URL + "ReportesControl?user_name="+_userName+"&fecha_inicio="+_fechaInicial+"&fecha_fin="+_fechaFinal+"&filtro="+_filtro+"&tipo=EDITOR&action=comentarios");
  }

  public obtenerReporteSuscripciones(_userName:string, _fechaInicial:string, _fechaFinal:string, _filtro:string): Observable<Suscripcion[]>{
    return this._httpClient.get<Suscripcion[]>(Rutas.API_URL + "ReportesControl?user_name="+_userName+"&fecha_inicio="+_fechaInicial+"&fecha_fin="+_fechaFinal+"&filtro="+_filtro+"&tipo=EDITOR&action=suscripciones_revista");
  }

  public obtenerReporteRevistasPopulares( _fechaInicial:string, _fechaFinal:string): Observable<RevistaSuscripcion[]>{
    return this._httpClient.get<RevistaSuscripcion[]>(Rutas.API_URL + "ReportesControl?fecha_inicio="+_fechaInicial+"&fecha_fin="+_fechaFinal+"&tipo=ADMIN&action=revistas_populares");
  }

  public obtenerReporteRevistasComentadas( _fechaInicial:string, _fechaFinal:string): Observable<RevistaComentario[]>{
    return this._httpClient.get<RevistaComentario[]>(Rutas.API_URL + "ReportesControl?&fecha_inicio="+_fechaInicial+"&fecha_fin="+_fechaFinal+"&tipo=ADMIN&action=revistas_comentadas");
  }
  
}
