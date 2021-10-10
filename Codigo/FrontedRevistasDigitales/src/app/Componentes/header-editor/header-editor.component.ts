import { Component, Input, OnInit } from '@angular/core';
import { Persona } from '../../Objects/Persona';
import { Editor } from '../../Objects/Editor';
import { LocalStorageService } from '../../Services/local-storage.service';
import { RedirigirService } from '../../Services/redirigir.service';
import { Rutas } from '../../Objects/Rutas';

@Component({
  selector: 'app-header-editor',
  templateUrl: './header-editor.component.html',
  styleUrls: ['./header-editor.component.css']
})
export class HeaderEditorComponent implements OnInit {

  @Input() editor!: Editor
  constructor(private localService: LocalStorageService,
    private redirigir: RedirigirService) { }

  ngOnInit(): void {
  }

  cerrarSesion(event: Event){
    
    event.preventDefault();
    this.localService.clear();
    this.redirigir.redirigir("/");
    
  }
}
