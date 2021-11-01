import { Component, OnInit } from '@angular/core';
import { SafeResourceUrl, DomSanitizer } from '@angular/platform-browser';
import { ActivatedRoute } from '@angular/router';
import { Rutas } from '../../../Objects/Rutas/Rutas';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Editor } from '../../../Objects/Persona/Editor';
import { LocalStorageService } from '../../../Services/local-storage.service';
import { RevistaService } from '../../../Services/revista.service';
import { Revista } from '../../../Objects/Revista/Revista';
import { FilesService } from '../../../Services/files.service';

@Component({
  selector: 'app-reporte-comentarios',
  templateUrl: './reporte-comentarios.component.html',
  styleUrls: ['./reporte-comentarios.component.css']
})
export class ReporteComentariosComponent implements OnInit {

  _pathFile!: string;
  _url!: SafeResourceUrl;
  _mensajeError!: string;
  _mostrarError: boolean = false;
  _editor: Editor;
  _listadoRevistasPropias!: Revista[]
  _filtrarDatosForm!: FormGroup;

  constructor(
    private sanitizer: DomSanitizer,
    private _route: ActivatedRoute,
    private _formBuilder: FormBuilder,
    private _localService: LocalStorageService,
    private _revistaService: RevistaService,
    private _filesService: FilesService
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

  generarReporte() {
    this._mensajeError = '';
    this._mostrarError = false;
    const _fechaInicial = this._filtrarDatosForm.value.fecha1;
    const _fechaFinal = this._filtrarDatosForm.value.fecha2;
    const _revista = this._filtrarDatosForm.value.revista;

    let url = this._filesService.obtenerReporte(this._editor.userName, _fechaInicial, _fechaFinal, _revista, 'comentarios', 'EDITOR');
    this._url = this.sanitizer.bypassSecurityTrustResourceUrl(url);
  }

}
