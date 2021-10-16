import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Rutas } from '../Objects/Rutas';
import { Persona } from '../Objects/Persona';
import { Observable } from 'rxjs';
import { Categoria } from '../Objects/Categoria';
import { Usuario } from '../Objects/Usuario';

@Injectable({
  providedIn: 'root'
})
export class RegistrarService {

  constructor(private httpClient: HttpClient) { }

  registrarPersona(persona: Persona, imagen: File | null, listadoCategorias?: Categoria[]): Observable<Persona> {
    const formData: FormData = new FormData();

    formData.append('persona', JSON.stringify(persona)); //Convert info of person  in JSON format
    if (imagen != null) { formData.append('image', imagen) };
    if (persona.tipo != undefined) { formData.append('tipo_registro', persona.tipo) }; //Type of Register, it can be EDITOR or USUARIO
    if(listadoCategorias!=undefined){ formData.append('list_categories',JSON.stringify(listadoCategorias))}; //If the person is USUARIO, submit list of categories
    return this.httpClient.post<Persona>(Rutas.API_URL + "RegisterControl", formData);
  }

  existenciaNombreUsuario(usuario: Usuario, action: string): Observable<boolean> {
    return this.httpClient.get<boolean>(Rutas.API_URL + "RegisterControl?action="+action+"&persona="+encodeURIComponent(JSON.stringify(usuario)));
  }

  listadoCategorias(action: string): Observable<Categoria[]> {
    return this.httpClient.get<Categoria[]>(Rutas.API_URL + "RegisterControl?action=" + action);
  }
}
