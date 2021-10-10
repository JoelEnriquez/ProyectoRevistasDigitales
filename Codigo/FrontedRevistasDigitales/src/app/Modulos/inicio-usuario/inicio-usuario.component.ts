import { Component, OnInit } from '@angular/core';
import { Usuario } from '../../Objects/Usuario';

@Component({
  selector: 'app-inicio-usuario',
  templateUrl: './inicio-usuario.component.html',
  styleUrls: ['./inicio-usuario.component.css']
})
export class InicioUsuarioComponent implements OnInit {

  usuario!: Usuario;
  constructor() { }

  ngOnInit(): void {
  }

}
