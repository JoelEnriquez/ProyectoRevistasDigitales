import { Observable } from 'rxjs';
import { Injectable } from '@angular/core';
import { Editor } from '../Objects/Editor';
import { Rutas } from '../Objects/Rutas';
import { PersonaFoto } from '../Objects/PersonaFoto';
import { Persona } from '../Objects/Persona';
import { Categoria } from '../Objects/Categoria';
import { Etiqueta } from '../Objects/Etiqueta';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class EditarPerfilService {

  constructor(private _httpClient: HttpClient) { }

  obtenerInfoEditor(_userNameEditor: string):Observable<Editor>{
    return this._httpClient.get<Editor>(Rutas.API_URL+"EditarPerfilControl?userName="+_userNameEditor+"&tipo=editor");
  }

  obtenerInfoUsuario(_userNameUsuario: string):Observable<Editor>{
    return this._httpClient.get<Editor>(Rutas.API_URL+"EditarPerfilControl?userName="+_userNameUsuario+"&tipo=editor");
  }

  actualizarInformacionPersona(_persona: Persona, _imagen: File | null, _listadoCategorias?: Categoria[], _listadoEtiquetas?: Etiqueta[]){
    const formData: FormData = new FormData();

    formData.append('persona', JSON.stringify(_persona)); //Convert info of person  in JSON format
    if (_imagen != null) { formData.append('image', _imagen) };
    if (_persona.tipo != undefined) { formData.append('tipo_update', _persona.tipo) }; //Type of Register, it can be EDITOR or USUARIO
    if(_listadoCategorias!=undefined){ formData.append('list_categories',JSON.stringify(_listadoCategorias))}; //If the person is USUARIO, submit list of categories
    if(_listadoEtiquetas!=undefined){ formData.append('list_etiquetas',JSON.stringify(_listadoEtiquetas))}; //If the person is USUARIO, submit list of etiquetas
    return this._httpClient.post<Persona>(Rutas.API_URL + "EditarPerfilControl", formData);
  }
}
