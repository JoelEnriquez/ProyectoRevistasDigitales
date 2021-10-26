import { Component, OnInit } from '@angular/core';
import { Usuario } from '../../Objects/Persona/Usuario';
import { Etiqueta } from '../../Objects/Revista/Etiqueta';
import { RevistaService } from '../../Services/revista.service';
import { LocalStorageService } from '../../Services/local-storage.service';

@Component({
  selector: 'app-revistas-etiqueta',
  templateUrl: './revistas-etiqueta.component.html',
  styleUrls: ['./revistas-etiqueta.component.css']
})
export class RevistasEtiquetaComponent implements OnInit {

  _usuario:Usuario;
  _listadoEtiquetas!:Etiqueta[]

  constructor(private _revistaService:RevistaService,
    private localService: LocalStorageService) {
    this._usuario = JSON.parse(`${this.localService.obtenerData('usuario')}`); 
    this.obtenerListadoEtiquetasPreferentes();
  }

  ngOnInit(): void {
  }

  obtenerListadoEtiquetasPreferentes(){
    this._revistaService.obtenerListadoEtiquetasPreferencia(this._usuario.userName).subscribe(
      (_listadoEtiquetas:Etiqueta[]) => {
        this._listadoEtiquetas = _listadoEtiquetas;
      }
    )
  }

}
