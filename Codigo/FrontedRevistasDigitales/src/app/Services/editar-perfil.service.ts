import { Observable } from 'rxjs';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Editor } from '../Objects/Editor';
import { Rutas } from '../Objects/Rutas';
import { PersonaFoto } from '../Objects/PersonaFoto';

@Injectable({
  providedIn: 'root'
})
export class EditarPerfilService {

  constructor(private httpClient: HttpClient) { }

  obtenerInfoEditor(userNameEditor: string):Observable<Editor>{
    return this.httpClient.get<Editor>(Rutas.API_URL+"EditarPerfilControl?userName="+userNameEditor+"&tipo=editor");
  }

  obtenerInfoUsuario(userNameUsuario: string):Observable<Editor>{
    return this.httpClient.get<Editor>(Rutas.API_URL+"EditarPerfilControl?userName="+userNameUsuario+"&tipo=editor");
  }
}
