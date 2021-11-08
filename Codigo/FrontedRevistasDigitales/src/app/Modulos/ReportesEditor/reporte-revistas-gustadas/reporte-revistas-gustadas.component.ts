import { MatTableDataSource } from '@angular/material/table';
import { MatSort } from '@angular/material/sort';
import { MatPaginator } from '@angular/material/paginator';
import {
  AfterViewChecked,
  AfterViewInit,
  Component,
  OnInit,
  ViewChild,
} from '@angular/core';
import { SafeResourceUrl, DomSanitizer } from '@angular/platform-browser';
import { Editor } from '../../../Objects/Persona/Editor';
import { Revista } from '../../../Objects/Revista/Revista';
import { FormGroup, FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { LocalStorageService } from '../../../Services/local-storage.service';
import { RevistaService } from '../../../Services/revista.service';
import { FilesService } from '../../../Services/files.service';
import { ReportesService } from '../../../Services/reportes.service';
import { RevistaReaccion } from '../../../Objects/ReportsObjets/RevistaMeGusta';
import { MeGusta } from 'src/app/Objects/Revista/MeGusta';
import { Rutas } from 'src/app/Objects/Rutas/Rutas';

@Component({
  selector: 'app-reporte-revistas-gustadas',
  templateUrl: './reporte-revistas-gustadas.component.html',
  styleUrls: ['./reporte-revistas-gustadas.component.css'],
})
export class ReporteRevistasGustadasComponent implements OnInit {
  _pathFile!: string;
  _url!: SafeResourceUrl;
  _mensajeError!: string;
  _mostrarError: boolean = false;
  _mensaje: string = '';
  _editor: Editor;
  _listadoRevistasPropias!: Revista[];
  _filtrarDatosForm!: FormGroup;

  _fechaInicial!: string;
  _fechaFinal!: string;
  _revista!: string;
  _dataSource!: any;
  _listadoRevistasReaccion!: RevistaReaccion[] | null;
  _listadoRevistas!: Revista[];

  ngOnInit() {
    this._filtrarDatosForm = this._formBuilder.group({
      fecha1: [''],
      fecha2: [''],
      revista: [null],
    });
    this.obtenerListadoDeRevistas();
  }

  obtenerListadoDeRevistas() {
    this._revistaService
      .revistasPropias(this._editor.userName, 'revistas_propias')
      .subscribe((_listadoRevistas: Revista[]) => {
        this._listadoRevistasPropias = _listadoRevistas;
      });
  }

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

  generarReporte() {
    this._mensajeError = '';
    this._mostrarError = false;
    this._fechaInicial = this._filtrarDatosForm.value.fecha1;
    this._fechaFinal = this._filtrarDatosForm.value.fecha2;
    this._revista = this._filtrarDatosForm.value.revista;

    this._reportesService
      .obtenerReporteMeGusta(
        this._editor.userName,
        this._fechaInicial,
        this._fechaFinal,
        this._revista
      )
      .subscribe(
        (_listadoRevistasReaccion: RevistaReaccion[]) => {
          this._listadoRevistasReaccion = _listadoRevistasReaccion;
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

  obtenerLink(){
    let _url = Rutas.API_URL + "JasperControl?user_name="+this._editor.userName+"&fecha_inicio="+this._fechaInicial+"&fecha_fin="+this._fechaFinal+"&filtro="+this._revista+"&tipo=EDITOR&action=mas_gustadas";
    this._url = _url;
  }

  limpiarDatos(){
    this._fechaInicial = '';
    this._fechaFinal = '';
    this._revista = '';
    this._listadoRevistasReaccion = null;
  }
}

