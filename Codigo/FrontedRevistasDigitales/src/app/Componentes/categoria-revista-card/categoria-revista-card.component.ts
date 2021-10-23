import { RevistaService } from './../../Services/revista.service';
import { Component, Input, OnInit } from '@angular/core';
import { Revista } from '../../Objects/Revista/Revista';
import { Categoria } from '../../Objects/Revista/Categoria';

@Component({
  selector: 'app-categoria-revista-card',
  templateUrl: './categoria-revista-card.component.html',
  styleUrls: ['./categoria-revista-card.component.css']
})
export class CategoriaRevistaCardComponent implements OnInit {

  constructor(private _revistaService: RevistaService) { 
    this.obtenerListadoRevistasPorCategoria();
  }

  @Input('_categoria') _categoria!:Categoria
  _listadoRevistas!: Revista[]

  ngOnInit(): void {
  }

  obtenerListadoRevistasPorCategoria(){
    if (this._categoria!=undefined) {
      this._revistaService.obtenerRevistasPorCategoria(this._categoria.nombre).subscribe(
        (_listadoRevistas:Revista[]) => {
          this._listadoRevistas = _listadoRevistas;
        },
        (error) => {
          console.log(error.error.message);
        }
      )
    }
    
  }

  mostrarMasRevistas(event: Event){
    event.preventDefault();
    
  }

}
