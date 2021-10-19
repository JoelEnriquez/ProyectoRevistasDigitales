import { Component, OnInit } from '@angular/core';
import { Revista } from '../../Objects/Revista';

@Component({
  selector: 'app-revistas-propias',
  templateUrl: './revistas-propias.component.html',
  styleUrls: ['./revistas-propias.component.css']
})
export class RevistasPropiasComponent implements OnInit {

  _listRevistas!: Revista[];

  constructor() { }

  ngOnInit(): void {
  }

  cambiarStatusSuscripcion(nombreRevista: string, suscribir: boolean){

  }
  cambiarStatusComentar(nombreRevista: string, comentar: boolean){

  }
  cambiarStatusMeGusta(nombreRevista: string, reaccionar: boolean){

  }
}
