import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { DomSanitizer, SafeResourceUrl } from '@angular/platform-browser';
import { ActivatedRoute } from '@angular/router';
import { Admin } from 'src/app/Objects/Persona/Admin';
import { RevistaComentario } from 'src/app/Objects/ReportsObjets/RevistaComentario';
import { Rutas } from 'src/app/Objects/Rutas/Rutas';
import { FilesService } from 'src/app/Services/files.service';
import { LocalStorageService } from 'src/app/Services/local-storage.service';
import { ReportesService } from 'src/app/Services/reportes.service';
import { RevistaService } from 'src/app/Services/revista.service';

@Component({
  selector: 'app-reporte-revistas-comentadas',
  templateUrl: './reporte-revistas-comentadas.component.html',
  styleUrls: ['./reporte-revistas-comentadas.component.css']
})
export class ReporteRevistasComentadasComponent implements OnInit {

  _pathFile!: string;
  _url!: SafeResourceUrl;
  _mensajeError!: string;
  _mostrarError: boolean = false;
  _mensaje: string = '';
  _admin: Admin;
  _filtrarDatosForm!: FormGroup;

  _fechaInicial!: string;
  _fechaFinal!: string;
  _revista!: string;
  _dataSource!: any;
  _listadoRevistaComentarios!: RevistaComentario[] | null;

  constructor(
    private sanitizer: DomSanitizer,
    private _route: ActivatedRoute,
    private _formBuilder: FormBuilder,
    private _localService: LocalStorageService,
    private _revistaService: RevistaService,
    private _filesService: FilesService,
    private _reportesService: ReportesService
  ) {
    this._admin = JSON.parse(`${this._localService.obtenerData('editor')}`);
  }

  ngOnInit(): void {
    this._filtrarDatosForm = this._formBuilder.group({
      fecha1: [''],
      fecha2: [''],
    });
  }


  obtenerLink(){
    let _url = Rutas.API_URL + "JasperControl?&fecha_inicio="+this._fechaInicial+"&fecha_fin="+this._fechaFinal+"&tipo=ADMIN&action=revistas_comentadas";
    this._url = _url;
  }

  generarReporte() {
    this._mensajeError = '';
    this._mostrarError = false;
    this._fechaInicial = this._filtrarDatosForm.value.fecha1;
    this._fechaFinal = this._filtrarDatosForm.value.fecha2;

    this._reportesService
      .obtenerReporteRevistasComentadas(
        this._fechaInicial,
        this._fechaFinal,
      )
      .subscribe(
        (_listadoRevistaComentarios: RevistaComentario[]) => {
          this._listadoRevistaComentarios = _listadoRevistaComentarios;
          this.obtenerLink();
        },
        (error:any) => {
          this._mensajeError = error.error.message;
          this._mostrarError = true;
          this.limpiarDatos();
        }
      );
  }

  imprimirReporte(){

  }

  limpiarDatos(){
    this._fechaInicial = '';
    this._fechaFinal = '';
    this._revista = '';
    this._listadoRevistaComentarios = null;
  }

}
