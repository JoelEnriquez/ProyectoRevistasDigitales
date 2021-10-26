import { Component, OnInit } from '@angular/core';
import { DomSanitizer, SafeResourceUrl } from '@angular/platform-browser';
import { ActivatedRoute } from '@angular/router';
import { Rutas } from 'src/app/Objects/Rutas/Rutas';
import { AppRoutingModule } from '../../app-routing.module';

@Component({
  selector: 'app-lectura-revista',
  templateUrl: './lectura-revista.component.html',
  styleUrls: ['./lectura-revista.component.css']
})
export class LecturaRevistaComponent implements OnInit {

  _pathFile!: string;
  _url!: SafeResourceUrl;

  constructor(
    private sanitizer: DomSanitizer,
    private _route: ActivatedRoute
    ) {
  }

  ngOnInit(): void {
      let _path = this._route.snapshot.paramMap.get('path');
      if (_path!=null) {
        this._pathFile = _path;
        this.obtenerLink();
      }
  }

  obtenerLink() {
    let _url = Rutas.API_URL + "FileControl?action=pdf&ruta_pdf=" + this._pathFile;
    this._url = this.sanitizer.bypassSecurityTrustResourceUrl(_url);
  }

  descargarPDF() {
    let _url = Rutas.API_URL + "FileControl?action=descargar&ruta_pdf=" + this._pathFile;
    this._url = this.sanitizer.bypassSecurityTrustResourceUrl(_url);
  }

  

}
