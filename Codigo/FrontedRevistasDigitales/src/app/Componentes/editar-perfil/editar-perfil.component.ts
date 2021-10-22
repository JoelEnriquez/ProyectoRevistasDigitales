import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Component, Input, OnInit } from '@angular/core';
import { Persona } from '../../Objects/Persona';
import { EditarPerfilService } from '../../Services/editar-perfil.service';
import { Base64Service } from '../../Services/base64.service';
import { Editor } from '../../Objects/Editor';
import { Usuario } from '../../Objects/Usuario';
import { PersonaEnum } from '../../Objects/PersonaEnum';

@Component({
  selector: 'app-editar-perfil',
  templateUrl: './editar-perfil.component.html',
  styleUrls: ['./editar-perfil.component.css']
})
export class EditarPerfilComponent implements OnInit {

  @Input() _tipoPersona!: PersonaEnum;
  @Input() _usuario!: Usuario
  @Input() _editor!: Editor
  @Input() _fotoPerfil!: string

  _editForm!: FormGroup;
  _mensaje: string = '';
  _mostrarError: boolean = false;
  _mostrarExito: boolean = false;
  _editUsuario: boolean = false; //Si es true, mostrar la opcion de escoger categorias de interes
  _claseDiv: string = '';
  _imagenSeleccionada: File | null = null;
  _archivoInvalido: string = '';

  constructor(private formBuilder: FormBuilder,
    private editarPerfilService: EditarPerfilService,
    private base64Service: Base64Service) {
  }

  ngOnInit(): void {
    this._editForm = this.formBuilder.group({
      userName: [''],
      nombre: ['', Validators.required],
      password: ['', Validators.required],
      foto: [null],
      hobbies: [''],
      descripcion: [''],
    });
  }

  inicializarFormGroup() {
    
  }

  setearFormEditor() {
    this._editForm.patchValue({
      userName: this._editor.userName,
      nombre: this._editor.nombre,
      password: this._editor.password,
      hobbies: this._editor.hobbies,
      descripcion: this._editor.descripcion,
    });
  }

  setearFormUsuario() {
    this._editForm.patchValue({
      userName: this._usuario.userName,
      nombre: this._usuario.nombre,
      password: this._usuario.password,
      hobbies: this._usuario.hobbies,
      descripcion: this._usuario.descripcion,
    });
  }

  seteartEditor(editor:Editor){
    this._editor = editor
  }



  cargarArchivo(event: Event) {
    const files = (event.target as HTMLInputElement).files;
    if (files != null) {
      this._imagenSeleccionada = files.item(0); //Guardamos en el atributo de la clase
      if (this._imagenSeleccionada?.type != 'image/jpeg') {
        this._archivoInvalido = 'is-invalid';
        this._fotoPerfil = '';
        this._imagenSeleccionada = null;
        this._editForm.patchValue({
          foto: null
        });
      } else {
        this._archivoInvalido = '';
        this.base64Service.extraerBase64(this._imagenSeleccionada).then((imagen: any) => {
          this._fotoPerfil = imagen.base;
        })
      }
    }
  }

  actualizarInfo(){

  }

  eliminarFoto() {
    this._editForm.patchValue({
      foto: null
    });
    this._fotoPerfil = '';
    this._imagenSeleccionada = null;
  }

}
