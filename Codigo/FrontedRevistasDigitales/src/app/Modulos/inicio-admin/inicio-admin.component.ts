import { Component, OnInit } from '@angular/core';
import { Admin } from '../../Objects/Persona/Admin';
import { LocalStorageService } from '../../Services/local-storage.service';

@Component({
  selector: 'app-inicio-admin',
  templateUrl: './inicio-admin.component.html',
  styleUrls: ['./inicio-admin.component.css']
})
export class InicioAdminComponent implements OnInit {

  admin: Admin;

  constructor(private localStorage: LocalStorageService) {
    this.admin = JSON.parse(`${this.localStorage.obtenerData('admin')}`);
   }

  ngOnInit(): void {
  }

}
