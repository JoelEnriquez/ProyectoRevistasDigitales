import { Component, Input, OnInit } from '@angular/core';
import { Revista } from '../../Objects/Revista/Revista';
import { Etiqueta } from '../../Objects/Revista/Etiqueta';
import { RevistaService } from '../../Services/revista.service';
import { FormBuilder, FormGroup } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-previsualizacion-revista',
  templateUrl: './previsualizacion-revista.component.html',
  styleUrls: ['./previsualizacion-revista.component.css']
})
export class PrevisualizacionRevistaComponent implements OnInit {

   _revista!: Revista;
  _etiquetasRevista!: Etiqueta[];
  _numeroReacciones!: number;
  _numeroComentarios!: number;
  _numeroSuscripciones!: number;
  _nombreRevista:string = "";
  _nombreAutor:string = "";
  _prevRevistaForm!: FormGroup;
  _mostrarInfoRevista:boolean =false;

  constructor(private _revistasService: RevistaService,
    private _formBuilder: FormBuilder,
    private _route: ActivatedRoute) {
  }

  ngOnInit(): void {
    // this.actualizarInfo();
    // this.obtenerCantidadReacciones();
    // console.log(this._revista.nombre)
    // console.log(this._revista.userName)
    let nombre = this._route.snapshot.paramMap.get('nombre');
    this.obtenerRevistaPorNombre(nombre);
  }

  actualizarInfo(){
      this._prevRevistaForm = this._formBuilder.group({
        nombre: [this._revista.nombre],
        autor: [this._revista.userName]
      })
  }

  obtenerRevistaPorNombre(_nombreRevista:string | null){
    if (_nombreRevista!=null) {
      this._revistasService.obtenerRevistaPorNombre(_nombreRevista).subscribe(
        (_revista: Revista[]) => {
          this._revista = _revista[0];
          this.obtenerEtiquetasRevista();
          this.obtenerEstadisticasRevista();
          this._mostrarInfoRevista = true;
        }
      )
    } 
  }

  obtenerEtiquetasRevista(){
    this._revistasService.etiquetasRevista(this._revista.nombre,'etiquetas_revista').subscribe(
      (_etiquetasRevista: Etiqueta[]) => {
        this._etiquetasRevista = _etiquetasRevista;
      }
    )
  }

  obtenerEstadisticasRevista(){
    this._revistasService.cantidadEstadisticaRevista(this._revista.nombre,'likes').subscribe(
      (_numeroReacciones: number) => {
        this._numeroReacciones = _numeroReacciones;
      }
    );

    this._revistasService.cantidadEstadisticaRevista(this._revista.nombre,'comentarios').subscribe(
      (_numeroComentarios: number) => {
        this._numeroComentarios = _numeroComentarios;
      }
    );

    this._revistasService.cantidadEstadisticaRevista(this._revista.nombre,'suscripciones').subscribe(
      (_numeroSuscripciones: number) => {
        this._numeroSuscripciones = _numeroSuscripciones;
      }
    );
  }

}
