import { Component, Input, OnInit } from '@angular/core';
import { RevistaService } from '../../Services/revista.service';
import { Revista } from '../../Objects/Revista/Revista';
import { RutasUsuario } from '../../Objects/Rutas/RutasUsuario';
import { Etiqueta } from '../../Objects/Revista/Etiqueta';

@Component({
  selector: 'app-revista-suscrita-card',
  templateUrl: './revista-suscrita-card.component.html',
  styleUrls: ['./revista-suscrita-card.component.css']
})
export class RevistaSuscritaCardComponent implements OnInit {

  @Input() _revista!:Revista;
  _rutas = RutasUsuario;
  _revistaCancelarSuscripcion!:Revista;
  

  constructor(private _revistaService: RevistaService) { 
  }

  ngOnInit(): void {
    
  }

  setRevistaCancelar(_revista: Revista){
    this._revistaCancelarSuscripcion = _revista;
  }

  cancelarSuscripcion(){

  }
}
