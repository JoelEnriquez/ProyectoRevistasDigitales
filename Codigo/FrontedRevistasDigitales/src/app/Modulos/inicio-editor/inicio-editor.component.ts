import { Component, OnInit } from '@angular/core';
import { Editor } from '../../Objects/Editor';
import { LocalStorageService } from '../../Services/local-storage.service';

@Component({
  selector: 'app-inicio-editor',
  templateUrl: './inicio-editor.component.html',
  styleUrls: ['./inicio-editor.component.css']
})
export class InicioEditorComponent implements OnInit {

  editor: Editor;
  
  constructor(private localStorage: LocalStorageService) {
    this.editor = JSON.parse(`${this.localStorage.obtenerData('editor')}`);
    console.log(this.editor);
  }

  ngOnInit(): void {
  }

}
