import { Component, Input, OnInit } from '@angular/core';
import { Admin } from '../../Objects/Admin';
import { LocalStorageService } from '../../Services/local-storage.service';
import { RedirigirService } from '../../Services/redirigir.service';
import { Persona } from '../../Objects/Persona';

@Component({
  selector: 'app-header-admin',
  templateUrl: './header-admin.component.html',
  styleUrls: ['./header-admin.component.css']
})
export class HeaderAdminComponent implements OnInit {

  @Input() admin!: Admin;

  constructor(private localService: LocalStorageService,
    private redirigir: RedirigirService) {
  }

  ngOnInit(): void {
    console.log(this.admin)
  }

  cerrarSesion(event: Event) {
    event.preventDefault();
    this.localService.clear();
    this.redirigir.redirigir("/");
  }
}
