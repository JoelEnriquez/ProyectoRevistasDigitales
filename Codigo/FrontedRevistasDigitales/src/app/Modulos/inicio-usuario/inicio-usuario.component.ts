import { Component, OnInit } from '@angular/core';
import { Usuario } from '../../Objects/Usuario';
import { LocalStorageService } from '../../Services/local-storage.service';

@Component({
  selector: 'app-inicio-usuario',
  templateUrl: './inicio-usuario.component.html',
  styleUrls: ['./inicio-usuario.component.css']
})
export class InicioUsuarioComponent implements OnInit {

  usuario: Usuario;
  constructor(private localStorage: LocalStorageService) { 
    this.usuario = JSON.parse(`${this.localStorage.obtenerData('usuario')}`);
  }

  ngOnInit(): void {
  }

}
