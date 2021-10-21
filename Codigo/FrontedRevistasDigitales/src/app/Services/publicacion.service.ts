import { Observable } from 'rxjs';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Publicacion } from '../Objects/Publicacion';
import { Rutas } from '../Objects/Rutas';

@Injectable({
  providedIn: 'root'
})
export class PublicacionService {

  constructor(private httpClient: HttpClient) { }

  insertarPublicacion(_publicacion: Publicacion, _file: File): Observable<void> {
    const formData: FormData = new FormData();
    formData.append('publicacion',JSON.stringify(_publicacion));
    formData.append('file',_file, _file.name);
    return this.httpClient.post<void>(Rutas.API_URL+"ClaseTemporal",formData);
  }
}
