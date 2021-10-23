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
    
  }

  @Input('_categoria') _categoria!:Categoria
  _listadoRevistas!: Revista[]

  ngOnInit(): void {
    this.obtenerListadoRevistasPorCategoria();
  }

  obtenerListadoRevistasPorCategoria(){
      this._revistaService.obtenerRevistasPorCategoria(this._categoria.nombre).subscribe(
        (_listadoRevistas:Revista[]) => {
          this._listadoRevistas = _listadoRevistas;
          console.log(this._listadoRevistas);
        },
        (error) => {
          console.log(error.error.message);
        }
      )
  }

  mostrarMasRevistas(event: Event){
    event.preventDefault();
    
  }

}
