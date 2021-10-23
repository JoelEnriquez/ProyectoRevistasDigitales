import { RevistaService } from './../../Services/revista.service';
import { Component, OnInit } from '@angular/core';
import { Categoria } from '../../Objects/Revista/Categoria';
import { Usuario } from '../../Objects/Persona/Usuario';
import { LocalStorageService } from '../../Services/local-storage.service';

@Component({
  selector: 'app-revistas-categoria',
  templateUrl: './revistas-categoria.component.html',
  styleUrls: ['./revistas-categoria.component.css']
})
export class RevistasCategoriaComponent implements OnInit {

  _usuario:Usuario;
  _listadoCategorias!:Categoria[]

  constructor(private _revistaService:RevistaService,
    private localService: LocalStorageService) {
    this._usuario = JSON.parse(`${this.localService.obtenerData('usuario')}`); 
    this.obtenerListadoCategoriasPreferentes();
  }

  ngOnInit(): void {
  }

  obtenerListadoCategoriasPreferentes(){
    this._revistaService.obtenerListadoCategoriasPreferencia(this._usuario.userName).subscribe(
      (_listadoCategorias:Categoria[]) => {
        this._listadoCategorias = _listadoCategorias;
      }
    )
  }

}
