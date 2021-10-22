import { Editor } from './../../Objects/Persona/Editor';
import { EditarPerfilComponent } from './../../Componentes/editar-perfil/editar-perfil.component';
import { Component, Input, OnInit, ViewChild } from '@angular/core';
import { LocalStorageService } from '../../Services/local-storage.service';
import { EditarPerfilService } from '../../Services/editar-perfil.service';
import { FilesService } from '../../Services/files.service';
import { FormBuilder } from '@angular/forms';
import { Base64Service } from '../../Services/base64.service';
import { PersonaEnum } from '../../Objects/Persona/PersonaEnum';
import { RegistrarService } from '../../Services/registrar.service';

@Component({
  selector: 'app-edit-profile',
  templateUrl: './edit-profile.component.html',
  styleUrls: ['./edit-profile.component.css'],
  
})
export class EditProfileComponent implements OnInit {

  @ViewChild(EditarPerfilComponent) child: EditarPerfilComponent; 
  _editor: Editor;
  _fotoPerfil!: string;
  _tipoPersona!:PersonaEnum;

  constructor(
    private _localService: LocalStorageService,
    private _filesService: FilesService,
    private formBuilder: FormBuilder,
    private editarPerfilService: EditarPerfilService,
    private base64Service: Base64Service,
    private registerService: RegistrarService
  ) {
    this._editor = JSON.parse(`${this._localService.obtenerData('editor')}`);
    this.obtenerInfoEditor();
    this._tipoPersona = PersonaEnum.EDITOR;
    this.child = new EditarPerfilComponent(formBuilder,editarPerfilService,base64Service,registerService,_localService);
   }

  ngOnInit(): void {
  }

  obtenerInfoEditor(){
    this.editarPerfilService.obtenerInfoEditor(this._editor.userName).subscribe(
      (editor:Editor) => {
        this._editor.descripcion = editor.descripcion;
        this._editor.hobbies = editor.hobbies;
        this._fotoPerfil = this._filesService.getImage(this._editor.userName);
        //Set data in form
        this.child.setearFormEditor();
      }
    );
  }

  



}
