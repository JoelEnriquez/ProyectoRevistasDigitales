import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Component, Input, OnInit } from '@angular/core';
import { Persona } from '../../Objects/Persona';
import { EditarPerfilService } from '../../Services/editar-perfil.service';
import { Base64Service } from '../../Services/base64.service';
import { Editor } from '../../Objects/Editor';
import { Usuario } from '../../Objects/Usuario';
import { PersonaEnum } from '../../Objects/PersonaEnum';
import { Categoria } from '../../Objects/Categoria';
import { RegistrarService } from '../../Services/registrar.service';
import { LocalStorageService } from '../../Services/local-storage.service';

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

  _listadoCategorias!: Categoria[]; //Todas las categorias
  _categoriasPreferencia!: Categoria[]; //Categorias Escogidas
  _number_categories: number = 0;
  _editForm!: FormGroup;
  _mensaje: string = '';
  _mostrarError: boolean = false;
  _mostrarExito: boolean = false;
  _editUsuario: boolean = false; //Si es true, mostrar la opcion de escoger categorias de interes
  _claseDiv: string = '';
  _imagenSeleccionada: File | null = null;
  _archivoInvalido: string = '';

  constructor(private _formBuilder: FormBuilder,
    private _editarPerfilService: EditarPerfilService,
    private _base64Service: Base64Service,
    private _registerService: RegistrarService,
    private _localStorage: LocalStorageService) {
      this.resetValues();
  }

  ngOnInit(): void {
    this._editForm = this._formBuilder.group({
      userName: [''],
      nombre: ['', Validators.required],
      password: ['', Validators.required],
      foto: [null],
      hobbies: [''],
      descripcion: [''],
    });
  }

  getListadoCategorias() {
    //Mostrar listado de categorias
    this._registerService.listadoCategorias('list_categories').subscribe(
      (listadoCategorias: Categoria[]) => {
        this._listadoCategorias = listadoCategorias;
      }
    )
  }

  getListadoCategoriasUsuario() {
    //Mostrar listado de categorias
    this._registerService.listadoCategoriasPreferencia('list_categories',this._usuario.userName).subscribe(
      (listadoCategorias: Categoria[]) => {
        this._categoriasPreferencia = listadoCategorias;
      }
    )
  }

  agregarCategoria(categoria: Categoria) {
    if (this._categoriasPreferencia === undefined) {
      this._categoriasPreferencia = new Array();
    }
    const position = this._categoriasPreferencia.indexOf(categoria);
    if (position === -1) {
      this._categoriasPreferencia.push(categoria);
      this._number_categories++;
    } else {
      this._categoriasPreferencia.splice(position, 1);
      this._number_categories--;
    }
    //Check is there 3 categories at least
    if (this._number_categories >= 3) {
      this._editForm.valid;
    } else {
      this._editForm.invalid;
    }
  }

  setearFormEditor() {
    this._editForm.patchValue({
      userName: this._editor.userName,
      nombre: this._editor.nombre,
      password: this._editor.password,
      hobbies: this._editor.hobbies,
      descripcion: this._editor.descripcion,
    });

    this._claseDiv = 'col-12'
  }

  setearFormUsuario() {
    this._editForm.patchValue({
      userName: this._usuario.userName,
      nombre: this._usuario.nombre,
      password: this._usuario.password,
      hobbies: this._usuario.hobbies,
      descripcion: this._usuario.descripcion,
    });
    this._claseDiv = 'col-9'
    this.getListadoCategorias();
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
        this._base64Service.extraerBase64(this._imagenSeleccionada).then((imagen: any) => {
          this._fotoPerfil = imagen.base;
        })
      }
    }
  }

  actualizarInfo(){
    if(this._editForm.valid){
      this._mostrarError = false;
      this._mensaje = '';

      const nombre = this._editForm.value.nombre;
      const password = this._editForm.value.password;
      const hobbies = this._editForm.value.hobbies;
      const descripcion = this._editForm.value.descripcion;

      switch (this._tipoPersona) {
        case PersonaEnum.EDITOR:
          const editor = new Editor(this._editor.userName, password, nombre, this._tipoPersona, hobbies, descripcion);
          this._editarPerfilService.actualizarInformacionPersona(editor, this._imagenSeleccionada).subscribe(
            (editor: Editor) => {
              //Guardar en local storage
              this._localStorage.setItem(editor, "editor");
              this._mensaje = 'Se ha actualizado la informacion correctamente';
              this._mostrarError = false;
              this._mostrarExito = true;
            },
            (error: any) => {
              this.showError(error);
            }
          );
          break;
        case PersonaEnum.USUARIO:
          break;
      }
    }
  }

  eliminarFoto() {
    this._editForm.patchValue({
      foto: null
    });
    this._fotoPerfil = '';
    this._imagenSeleccionada = null;
  }

  showError(error: any) {
    this._mostrarError = true;
    this._mostrarExito = false;
    this._mensaje = error.error.message;
  }

  resetValues(){
    this._mensaje = '';
    this._mostrarError = false;
    this._mostrarExito = false;
  }

}
