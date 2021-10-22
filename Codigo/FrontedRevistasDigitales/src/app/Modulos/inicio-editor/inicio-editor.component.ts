import { Component, OnInit } from '@angular/core';
import { LocalStorageService } from '../../Services/local-storage.service';
import { RutasEditor } from '../../Objects/Rutas/RutasEditor';

@Component({
  selector: 'app-inicio-editor',
  templateUrl: './inicio-editor.component.html',
  styleUrls: ['./inicio-editor.component.css']
})
export class InicioEditorComponent implements OnInit {

  rutasEditor = RutasEditor;

  constructor(private localStorage: LocalStorageService) {
    
  }

  ngOnInit(): void {
  }

}
