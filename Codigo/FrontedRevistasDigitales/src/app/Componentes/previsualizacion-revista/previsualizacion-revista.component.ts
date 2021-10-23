import { Component, Input, OnInit } from '@angular/core';
import { Revista } from '../../Objects/Revista/Revista';
import { Etiqueta } from '../../Objects/Revista/Etiqueta';
import { RevistaService } from '../../Services/revista.service';

@Component({
  selector: 'app-previsualizacion-revista',
  templateUrl: './previsualizacion-revista.component.html',
  styleUrls: ['./previsualizacion-revista.component.css']
})
export class PrevisualizacionRevistaComponent implements OnInit {

  @Input() _revista!: Revista;
  _etiquetasRevista!: Etiqueta[];
  _numeroReacciones!: number;

  constructor(private _revistasService: RevistaService) { }

  ngOnInit(): void {
    this.obtenerCantidadReacciones();
  }

  obtenerEtiquetasRevista(){
    this._revistasService.etiquetasRevista(this._revista.nombre,'etiquetas_revista').subscribe(
      (_etiquetasRevista: Etiqueta[]) => {
        this._etiquetasRevista = _etiquetasRevista;
      }
    )
  }

  obtenerCantidadReacciones(){
    this._revistasService.cantidadReaccionesRevista(this._revista.nombre,'reacciones').subscribe(
      (_numeroReacciones: number) => {
        this._numeroReacciones = _numeroReacciones;
      }
    )
  }

}
