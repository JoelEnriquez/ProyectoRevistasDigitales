import { Revista } from './../../Objects/Revista/Revista';
import { Component, Input, OnInit, EventEmitter, Output } from '@angular/core';
import { RutasUsuario } from '../../Objects/Rutas/RutasUsuario';

@Component({
  selector: 'app-revista-card',
  templateUrl: './revista-card.component.html',
  styleUrls: ['./revista-card.component.css']
})
export class RevistaCardComponent implements OnInit {

  @Input() _revista!:Revista;
  _revistaPrevisualizar!:Revista;
  _rutas = RutasUsuario

  constructor() {
    
  }

  ngOnInit(): void {
  }


  solicitarPrevisualizar(_revista: Revista) {
    this._revistaPrevisualizar = _revista;
  }

}
