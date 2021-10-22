import { Component, Input, OnInit } from '@angular/core';
import { Usuario } from '../../Objects/Persona/Usuario';
import { LocalStorageService } from '../../Services/local-storage.service';
import { RedirigirService } from '../../Services/redirigir.service';
import { RutasUsuario } from '../../Objects/Rutas/RutasUsuario';

@Component({
  selector: 'app-header-usuario',
  templateUrl: './header-usuario.component.html',
  styleUrls: ['./header-usuario.component.css']
})
export class HeaderUsuarioComponent implements OnInit {

  @Input() _usuario!:Usuario;
  _rutasUsuario = RutasUsuario;

  constructor(private localService: LocalStorageService,
    private redirigir: RedirigirService) { 
      this._usuario = JSON.parse(`${this.localService.obtenerData('usuario')}`);
    }

  ngOnInit(): void {
  }

  cerrarSesion(event: Event){
    event.preventDefault();
    this.localService.clear();
    this.redirigir.redirigir(this._rutasUsuario.MAIN_USER);
  }
}
