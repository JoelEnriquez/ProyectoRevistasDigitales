import { Component, OnInit } from '@angular/core';
import { SafeResourceUrl, DomSanitizer } from '@angular/platform-browser';
import { Editor } from '../../../Objects/Persona/Editor';
import { Revista } from '../../../Objects/Revista/Revista';
import { FormGroup, FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { LocalStorageService } from '../../../Services/local-storage.service';
import { RevistaService } from '../../../Services/revista.service';
import { FilesService } from '../../../Services/files.service';
import { Rutas } from '../../../Objects/Rutas/Rutas';
import { GananciaEditor } from 'src/app/Objects/ReportsObjets/GananciaEditor';
import { ReportesService } from 'src/app/Services/reportes.service';
import { MontoRevista } from 'src/app/Objects/ReportsObjets/MontoRevista';

@Component({
  selector: 'app-reporte-ganancias-editor',
  templateUrl: './reporte-ganancias-editor.component.html',
  styleUrls: ['./reporte-ganancias-editor.component.css']
})
export class ReporteGananciasEditorComponent implements OnInit {

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
  _listadoGananciaEditor!: GananciaEditor[] | null;
  _listadoGananciasTotales!: MontoRevista[] | null;

  constructor(
    private sanitizer: DomSanitizer,
    private _route: ActivatedRoute,
    private _formBuilder: FormBuilder,
    private _localService: LocalStorageService,
    private _revistaService: RevistaService,
    private _filesService: FilesService,
    private _reportesService: ReportesService
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

  obtenerLink() {
    let _url = Rutas.API_URL + "FileControl?action=pdf&ruta_pdf=" + this._pathFile;
    this._url = this.sanitizer.bypassSecurityTrustResourceUrl(_url);
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

  calcularGanancias(){
    if (this._listadoGananciaEditor!=null) {
      this._listadoGananciaEditor.forEach(_gananciaEditor => {
        let _ganancia:number = 0;
        _gananciaEditor.suscripciones.forEach(_suscripcion => {
          if (_suscripcion.montoGanancia!=undefined) {
            _ganancia += _suscripcion.montoGanancia;
          }
        });
        if (this._listadoGananciasTotales!=null) {
          this._listadoGananciasTotales.push(new MontoRevista(_ganancia,_gananciaEditor.revista.nombre));
        }
      });
    }
  }

  obtenerGananciaPorNombre(_nombreRevista:string){
    if (this._listadoGananciasTotales!=null) {
      return this._listadoGananciasTotales.find(g => g.nombreRevista==_nombreRevista);
    }
    return null;
  }
  

  generarReporte() {
    this._listadoGananciasTotales =null;
    this._mensajeError = '';
    this._mostrarError = false;
    this._fechaInicial = this._filtrarDatosForm.value.fecha1;
    this._fechaFinal = this._filtrarDatosForm.value.fecha2;
    this._revista = this._filtrarDatosForm.value.revista;

    this._reportesService
      .obtenerReporteGananciasEditor(
        this._editor.userName,
        this._fechaInicial,
        this._fechaFinal,
        this._revista
      )
      .subscribe(
        (_listadoGananciaEditor: GananciaEditor[]) => {
          this._listadoGananciaEditor = _listadoGananciaEditor;
          if (this._listadoGananciasTotales ==undefined || this._listadoGananciasTotales==null) {
            this._listadoGananciasTotales = new Array();
          }
          this.calcularGanancias();
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
    this._listadoGananciaEditor = null;
    this._listadoGananciasTotales = null;
  }

}
