import { Component, Input, OnInit } from '@angular/core';
import { Admin } from '../../Objects/Admin';
import { LocalStorageService } from '../../Services/local-storage.service';
import { RedirigirService } from '../../Services/redirigir.service';
import { Persona } from '../../Objects/Persona';
import { RutasAdmin } from '../../Objects/RutasAdmin';

@Component({
  selector: 'app-header-admin',
  templateUrl: './header-admin.component.html',
  styleUrls: ['./header-admin.component.css']
})
export class HeaderAdminComponent implements OnInit {

  admin: Admin;
  rutasAdmin = RutasAdmin;

  constructor(private localService: LocalStorageService,
    private redirigir: RedirigirService) {
      this.admin = JSON.parse(`${this.localService.obtenerData('admin')}`);
  }

  ngOnInit(): void {
    
  }

  cerrarSesion(event: Event) {
    event.preventDefault();
    this.localService.clear();
    this.redirigir.redirigir("/");
  }
}
