import { Component, Input, OnInit } from '@angular/core';
import { Etiqueta } from '../../Objects/Revista/Etiqueta';

@Component({
  selector: 'app-etiqueta-revista-card',
  templateUrl: './etiqueta-revista-card.component.html',
  styleUrls: ['./etiqueta-revista-card.component.css']
})
export class EtiquetaRevistaCardComponent implements OnInit {

  @Input() _etiqueta!:Etiqueta;

  constructor() { }

  ngOnInit(): void {
  }

}
