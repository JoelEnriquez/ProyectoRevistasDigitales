import { Editor } from './../../Objects/Persona/Editor';
import { Component, Input, OnInit } from '@angular/core';
import { LocalStorageService } from '../../Services/local-storage.service';
import { RedirigirService } from '../../Services/redirigir.service';
import { Rutas } from '../../Objects/Rutas/Rutas';
import { RutasEditor } from '../../Objects/Rutas/RutasEditor';

@Component({
  selector: 'app-header-editor',
  templateUrl: './header-editor.component.html',
  styleUrls: ['./header-editor.component.css']
})
export class HeaderEditorComponent implements OnInit {

  editor: Editor
  rutasEditor = RutasEditor;

  constructor(private localService: LocalStorageService,
    private redirigir: RedirigirService,) {
      this.editor = JSON.parse(`${this.localService.obtenerData('editor')}`);
    }

  ngOnInit(): void {
    
  }

  cerrarSesion(event: Event){
    
    event.preventDefault();
    this.localService.clear();
    this.redirigir.redirigir("/");
    
  }
}
