import { Component, OnInit } from '@angular/core';
import { Etiqueta } from '../../Objects/Revista/Etiqueta';
import { Categoria } from '../../Objects/Revista/Categoria';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { FiltroEnum } from '../../Objects/Revista/FiltroEnum';
import { RevistaService } from '../../Services/revista.service';
import { Revista } from 'src/app/Objects/Revista/Revista';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-buscar-revistas',
  templateUrl: './buscar-revistas.component.html',
  styleUrls: ['./buscar-revistas.component.css']
})
export class BuscarRevistasComponent implements OnInit {

  _formBusqueda!: FormGroup;
  _listadoEtiquetas!: Etiqueta[];
  _listadoCategorias!: Categoria[];
  _listadoRevistas!: Revista[];
  _revistaPrevisualizar!: Revista;
  _filtro = FiltroEnum;

  constructor(private _formBuilder: FormBuilder, private _revistaService: RevistaService,
    private _router: Router,
    private _route: ActivatedRoute) { }

  ngOnInit(): void {
    this._formBusqueda = this._formBuilder.group({
      filtro: [this._filtro.CATEGORIA, Validators.required],
      text: ['', Validators.required]
    })

    let categoria = this._route.snapshot.paramMap.get('categoria');
    let etiqueta = this._route.snapshot.paramMap.get('etiqueta');
    if (categoria!=null && etiqueta!=null) {
      if (categoria?.length != 0 && categoria != null) {
        this.realizarBusqueda(categoria, FiltroEnum.CATEGORIA);
      } else if (etiqueta?.length != 0 && etiqueta != null) {
        this.realizarBusqueda(etiqueta, FiltroEnum.ETIQUETA);
      }
    }
  }

  obtenerDatos() {
    if (this._formBusqueda.valid) {
      //Realizar busqueda con los filtros dados
      let filtro = this._formBusqueda.value.filtro;
      const texto = this._formBusqueda.value.text;
      this.realizarBusqueda(texto, filtro);
    }
  }

  realizarBusqueda(texto: string, filtro: FiltroEnum) {
    this._revistaService.obtenerRevistasPorFiltro(texto, filtro).subscribe(
      (_listadoRevistas: Revista[]) => {
        this._listadoRevistas = _listadoRevistas;
      }
    )
  }

}
