import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Rutas } from '../Objects/Rutas';
import { Persona } from '../Objects/Persona';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class RegistrarService {

  constructor(private httpClient: HttpClient) { }

  registrarPersona(persona: Persona, imagen: File | null): Observable<Persona> {
    const formData: FormData = new FormData();
    const dataPersona = JSON.stringify(persona);
    if (imagen != null) { formData.append('image', imagen)};
    formData.append('persona', dataPersona);
    return this.httpClient.post<Persona>(Rutas.API_URL + "RegisterEditControl", formData);
  }

  existenciaNombreUsuario(userName: string): Observable<boolean> {
    const formData: FormData = new FormData();
    formData.append('userName',userName);
    return this.httpClient.post<boolean>(Rutas.API_URL+"RegisterUserControl",formData);
  }
}
