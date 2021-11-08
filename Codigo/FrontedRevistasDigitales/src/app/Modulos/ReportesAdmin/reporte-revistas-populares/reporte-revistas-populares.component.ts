import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { DomSanitizer, SafeResourceUrl } from '@angular/platform-browser';
import { ActivatedRoute } from '@angular/router';
import { Admin } from 'src/app/Objects/Persona/Admin';
import { RevistaSuscripcion } from 'src/app/Objects/ReportsObjets/RevistaSuscripcion';
import { FilesService } from 'src/app/Services/files.service';
import { LocalStorageService } from 'src/app/Services/local-storage.service';
import { ReportesService } from 'src/app/Services/reportes.service';
import { RevistaService } from 'src/app/Services/revista.service';

@Component({
  selector: 'app-reporte-revistas-populares',
  templateUrl: './reporte-revistas-populares.component.html',
  styleUrls: ['./reporte-revistas-populares.component.css']
})
export class ReporteRevistasPopularesComponent implements OnInit {

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
  _listadoRevistaSuscripciones!: RevistaSuscripcion[] | null;

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

  generarReporte() {
    this._mensajeError = '';
    this._mostrarError = false;
    this._fechaInicial = this._filtrarDatosForm.value.fecha1;
    this._fechaFinal = this._filtrarDatosForm.value.fecha2;

    this._reportesService
      .obtenerReporteRevistasPopulares(
        this._fechaInicial,
        this._fechaFinal,
      )
      .subscribe(
        (_listadoRevistaSuscripciones: RevistaSuscripcion[]) => {
          this._listadoRevistaSuscripciones = _listadoRevistaSuscripciones;
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
    this._listadoRevistaSuscripciones = null;
  }

}
