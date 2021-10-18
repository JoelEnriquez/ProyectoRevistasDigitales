import { Component, OnInit } from '@angular/core';
import { Editor } from '../../Objects/Editor';
import { LocalStorageService } from '../../Services/local-storage.service';
import { RutasEditor } from '../../Objects/RutasEditor';

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
