import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Rutas } from '../Objects/Rutas';
import { Usuario } from '../Objects/Usuario';
import { Persona } from '../Objects/Persona';

@Injectable({
  providedIn: 'root'
})
export class FilesService {

  constructor(httpClient: HttpClient) { }

  public getImage(userName: string): string {
    return Rutas.API_URL + "FileControl?userName=" + userName + "&action=image";
  }
}
