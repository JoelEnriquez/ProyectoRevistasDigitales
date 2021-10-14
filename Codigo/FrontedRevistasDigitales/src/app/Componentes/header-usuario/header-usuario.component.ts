import { Component, Input, OnInit } from '@angular/core';
import { Usuario } from '../../Objects/Usuario';
import { LocalStorageService } from '../../Services/local-storage.service';
import { RedirigirService } from '../../Services/redirigir.service';

@Component({
  selector: 'app-header-usuario',
  templateUrl: './header-usuario.component.html',
  styleUrls: ['./header-usuario.component.css']
})
export class HeaderUsuarioComponent implements OnInit {

  @Input() usuario!:Usuario;
  
  constructor(private localService: LocalStorageService,
    private redirigir: RedirigirService) { }

  ngOnInit(): void {
  }

  cerrarSesion(event: Event){
    event.preventDefault();
    this.localService.clear();
    this.redirigir.redirigir("/");
  }
}
