import { Component, OnInit } from '@angular/core';
import { Rutas } from '../../../Objects/Rutas/Rutas';
import { Revista } from '../../../Objects/Revista/Revista';
import { FilesService } from '../../../Services/files.service';
import { RevistaService } from '../../../Services/revista.service';
import { LocalStorageService } from '../../../Services/local-storage.service';
import { FormBuilder, FormGroup } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { DomSanitizer, SafeResourceUrl } from '@angular/platform-browser';
import { Editor } from '../../../Objects/Persona/Editor';
import { Suscripcion } from 'src/app/Objects/Revista/Suscripcion';
import { ReportesService } from 'src/app/Services/reportes.service';

@Component({
  selector: 'app-reporte-suscripciones',
  templateUrl: './reporte-suscripciones.component.html',
  styleUrls: ['./reporte-suscripciones.component.css']
})
export class ReporteSuscripcionesComponent implements OnInit {

  _pathFile!: string;
  _url!: SafeResourceUrl;
  _mensajeError!: string;
  _mostrarError: boolean = false;
  _editor: Editor;
  _listadoRevistasPropias!: Revista[]
  _filtrarDatosForm!: FormGroup;

  _fechaInicial!: string;
  _fechaFinal!: string;
  _revista!: string;
  _dataSource!: any;
  _listadoSuscripciones!: Suscripcion[] | null;

  constructor(
    private sanitizer: DomSanitizer,
    private _route: ActivatedRoute,
    private _formBuilder: FormBuilder,
    private _localService: LocalStorageService,
    private _revistaService: RevistaService,
    private _filesService: FilesService,
    private _reportesService:ReportesService
  ) {
    this._editor = JSON.parse(`${this._localService.obtenerData('editor')}`);
   }

  ngOnInit(): void {
    this._filtrarDatosForm = this._formBuilder.group({
      fecha1: [''],
      fecha2: [''],
      revista: [null],
    })
    this.obtenerListadoDeRevistas();
  }

  obtenerLink(){
    let _url = Rutas.API_URL + "JasperControl?user_name="+this._editor.userName+"&fecha_inicio="+this._fechaInicial+"&fecha_fin="+this._fechaFinal+"&filtro="+this._revista+"&tipo=EDITOR&action=suscripciones_revista";
    this._url = _url;
  }

  descargarPDF() {
    let _url = Rutas.API_URL + "FileControl?action=descargar&ruta_pdf=" + this._pathFile;
    this._url = this.sanitizer.bypassSecurityTrustResourceUrl(_url);
  }

  obtenerListadoDeRevistas() {
    this._revistaService.revistasPropias(this._editor.userName, "revistas_propias").subscribe(
      (_listadoRevistas: Revista[]) => {
        this._listadoRevistasPropias = _listadoRevistas;
      }
    )
  }

  generarReporte() {
    this._mensajeError = '';
    this._mostrarError = false;
    this._fechaInicial = this._filtrarDatosForm.value.fecha1;
    this._fechaFinal = this._filtrarDatosForm.value.fecha2;
    this._revista = this._filtrarDatosForm.value.revista;

    this._reportesService
      .obtenerReporteSuscripciones(
        this._editor.userName,
        this._fechaInicial,
        this._fechaFinal,
        this._revista
      )
      .subscribe(
        (_listadoSuscripciones: Suscripcion[]) => {
          this._listadoSuscripciones = _listadoSuscripciones;
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
    this._listadoSuscripciones = null;
  }

}
