import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { Categoria } from '../../Objects/Revista/Categoria';

@Component({
  selector: 'app-escoger-categorias',
  templateUrl: './escoger-categorias.component.html',
  styleUrls: ['./escoger-categorias.component.css']
})
export class EscogerCategoriasComponent implements OnInit {

  @Input() categoria!: Categoria;
  @Output() nombreCategoria = new EventEmitter<Categoria>();
  readonly _colorBlue: string = 'rgb(15,140,223)';
  readonly _colorGreen: string = 'rgb(50,205,50)';
  color: string;
  colorPintado:boolean = false;

  constructor() {
    this.color = this._colorBlue;
  }

  ngOnInit(): void {

  }

  pintarSeleccionado() {
    if (this.color!=undefined && this.colorPintado==false) {
      this.color = this._colorGreen;
      this.colorPintado = true;
    }
  }

  agregarCategoria(nombreCategoria: Categoria) {
    this.nombreCategoria.emit(nombreCategoria);
    this.cambiarColor();
  }

  cambiarColor() {
    if (this.color === this._colorBlue) {
      this.color = this._colorGreen;
    } else {
      this.color = this._colorBlue;
    }
  }

}
