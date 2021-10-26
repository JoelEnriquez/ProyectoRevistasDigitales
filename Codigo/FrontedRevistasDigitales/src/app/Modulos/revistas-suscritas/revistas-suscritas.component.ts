import { Component, OnInit } from '@angular/core';
import { Revista } from '../../Objects/Revista/Revista';
import { RutasUsuario } from '../../Objects/Rutas/RutasUsuario';
import { Usuario } from '../../Objects/Persona/Usuario';
import { LocalStorageService } from '../../Services/local-storage.service';
import { RevistaService } from '../../Services/revista.service';

@Component({
  selector: 'app-revistas-suscritas',
  templateUrl: './revistas-suscritas.component.html',
  styleUrls: ['./revistas-suscritas.component.css']
})
export class RevistasSuscritasComponent implements OnInit {

  _listadoRevistasSuscritas!: Revista[]
  _rutas = RutasUsuario;
  _usuario: Usuario;

  constructor(private localStorage: LocalStorageService,
    private _revistaService: RevistaService) { 
    this._usuario = JSON.parse(`${this.localStorage.obtenerData('usuario')}`);
  }

  ngOnInit(): void {
    this.obtenerListadoRevistasPorCategoria();
  }

  obtenerListadoRevistasPorCategoria() {
    this._revistaService.obtenerRevistasSuscritas(this._usuario.userName).subscribe(
      (_listadoRevistasSuscritas: Revista[]) => {
        this._listadoRevistasSuscritas = _listadoRevistasSuscritas;
      },
      (error) => {
        console.log(error.error.message);
      }
    )
  }

  mostrarMasRevistas(event: Event) {
    event.preventDefault();
  }
}
