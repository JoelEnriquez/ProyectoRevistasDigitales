import { Component, OnInit } from '@angular/core';
import { Admin } from '../../Objects/Admin';

@Component({
  selector: 'app-inicio-admin',
  templateUrl: './inicio-admin.component.html',
  styleUrls: ['./inicio-admin.component.css']
})
export class InicioAdminComponent implements OnInit {

  admin!: Admin;

  constructor() { }

  ngOnInit(): void {
  }

}
