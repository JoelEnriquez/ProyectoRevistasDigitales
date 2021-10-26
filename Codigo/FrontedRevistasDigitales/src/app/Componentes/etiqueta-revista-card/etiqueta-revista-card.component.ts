import { Component, Input, OnInit } from '@angular/core';
import { Etiqueta } from '../../Objects/Revista/Etiqueta';
import { Revista } from '../../Objects/Revista/Revista';
import { RutasUsuario } from '../../Objects/Rutas/RutasUsuario';
import { RevistaService } from '../../Services/revista.service';

@Component({
  selector: 'app-etiqueta-revista-card',
  templateUrl: './etiqueta-revista-card.component.html',
  styleUrls: ['./etiqueta-revista-card.component.css']
})
export class EtiquetaRevistaCardComponent implements OnInit {

  @Input() _etiqueta!:Etiqueta;
  _listadoRevistas!: Revista[]
  _rutas = RutasUsuario;

  constructor(private _revistaService: RevistaService) { 
  }
  

  ngOnInit(): void {
    this.obtenerListadoRevistasPorCategoria();
  }

  obtenerListadoRevistasPorCategoria(){
      this._revistaService.obtenerRevistasPorEtiqueta(this._etiqueta.nombre).subscribe(
        (_listadoRevistas:Revista[]) => {
          this._listadoRevistas = _listadoRevistas;
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
