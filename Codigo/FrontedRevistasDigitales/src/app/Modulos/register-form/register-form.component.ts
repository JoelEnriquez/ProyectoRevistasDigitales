import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { RedirigirService } from '../../Services/redirigir.service';
import { PersonaEnum } from '../../Objects/PersonaEnum';
import { RegistrarService } from '../../Services/registrar.service';
import { Rutas } from '../../Objects/Rutas';
import { Editor } from '../../Objects/Editor';
import { LocalStorageService } from '../../Services/local-storage.service';
import { Base64Service } from '../../Services/base64.service';
import { Categoria } from '../../Objects/Categoria';
import { Usuario } from '../../Objects/Usuario';
import { Persona } from '../../Objects/Persona';

@Component({
  selector: 'app-register-form',
  templateUrl: './register-form.component.html',
  styleUrls: ['./register-form.component.css']
})
export class RegisterFormComponent implements OnInit {

  _archivoInvalido: string  ='';
  mostrarError: boolean = false;
  mensaje: String = "";
  registerForm!: FormGroup;
  mostrarImagen: boolean = false;
  urlImagen!: String;
  PersonaEnum!: PersonaEnum;
  archivos: any = [];
  imagenSeleccionada: File | null = null;
  previsualizar: string = "";
  hide_register: boolean = false;
  listadoCategorias!: Categoria[];
  categoriasPreferencia!: Categoria[];
  print_categories: boolean = false;
  _number_categories: number = 0;
  clase_div: string = "col-12";
  persona!: Persona;

  constructor(
    private formBuilder: FormBuilder,
    private redirigirService: RedirigirService,
    private registerService: RegistrarService,
    private localStorage: LocalStorageService,
    private base64: Base64Service
  ) { }

  ngOnInit(): void {
    this.registerForm = this.formBuilder.group({
      userName: ['', Validators.required],
      nombre: ['', Validators.required],
      password: ['', Validators.required],
      persona: [PersonaEnum.USUARIO, Validators.required],
      foto: [null],
      hobbies: [''],
      descripcion: [''],
    });

  }

  agregarCategoria(categoria: Categoria) {
    if (this.categoriasPreferencia === undefined) {
      this.categoriasPreferencia = new Array();
    }
    const position = this.categoriasPreferencia.indexOf(categoria);
    if (position === -1) {
      this.categoriasPreferencia.push(categoria);
      this._number_categories++;
    } else {
      this.categoriasPreferencia.splice(position, 1);
      this._number_categories--;
    }
    //Check is there 3 categories at least
    if (this._number_categories >= 3) {
      this.hide_register = false;
    } else {
      this.hide_register = true;
    }
  }

  cargarArchivo(event: Event) {
    const files = (event.target as HTMLInputElement).files;
    if (files != null) {
      this.imagenSeleccionada = files.item(0); //Guardamos en el atributo de la clase
      if (this.imagenSeleccionada?.type != 'image/jpeg') {
        this._archivoInvalido = 'is-invalid';
        this.previsualizar = '';
        this.imagenSeleccionada = null;
        this.registerForm.patchValue({
          foto: null
        });
      } else {
        this._archivoInvalido = '';
        this.base64.extraerBase64(this.imagenSeleccionada).then((imagen: any) => {
          this.previsualizar = imagen.base;
        })
      }

    }
  }

  guardarInfo() {
    if (this.registerForm.valid) {
      this.mostrarError = false;
      this.mensaje = '';

      const userName = this.registerForm.value.userName;
      const nombre = this.registerForm.value.nombre;
      const password = this.registerForm.value.password;
      const tipo = this.registerForm.value.persona;
      const hobbies = this.registerForm.value.hobbies;
      const descripcion = this.registerForm.value.descripcion;

      switch (tipo) {
        case PersonaEnum.EDITOR:
          this.persona = new Editor(userName, password, nombre, tipo, hobbies, descripcion);
          this.registerService.registrarPersona(this.persona, this.imagenSeleccionada).subscribe(
            (editor: Editor) => {
              this.resetForm();
              //Guardar en local storage
              this.localStorage.setItem(editor, "editor");
              this.redirigirService.redirigir(Rutas.MAIN_EDIT);
            },
            (error: any) => {
              this.showError(error);
            }
          );
          break;
        case PersonaEnum.USUARIO:
          this.persona = new Usuario(userName, password, nombre, tipo, hobbies, descripcion);
          this.registerService.existenciaNombreUsuario(this.persona, 'checkuser').subscribe(
            (existencia: boolean) => {
              if (existencia == false) {
                this.registerForm.disable();
                this.hide_register = true;
                //Mostrar listado de categorias
                this.registerService.listadoCategorias('list_categories').subscribe(
                  (listadoCategorias: Categoria[]) => {
                    this.listadoCategorias = listadoCategorias;
                    this.print_categories = true;
                    this.clase_div = "col-7";
                  }
                )
              } else {
                this.mostrarError = true;
                this.mensaje = "Ya existe dicho nombre de nepe";
              }
            },
            (error: any) => {
              this.showError(error);
            }
          );
          break;
        default:
          break;
      }
    }
  }

  saveUser() {
    this.registerService.registrarPersona(this.persona, this.imagenSeleccionada, this.categoriasPreferencia).subscribe(
      (usuario: Usuario) => {
        this.resetForm();
        this.categoriasPreferencia = []; //Clear Array
        this._number_categories = 0; //Set 0 number of categories
        //Guardar en local storage
        this.localStorage.setItem(usuario, "usuario");
        this.redirigirService.redirigir(Rutas.MAIN_USER);
      },
      (error: any) => {
        this.showError(error);
      }
    );
  }

  resetForm() {
    this.registerForm.reset({
      userName: ['', Validators.required],
      nombre: ['', Validators.required],
      password: ['', Validators.required],
      foto: [null],
      hobbies: [''],
      descripcion: [''],
    });
    this.mostrarError = false;
  }

  showError(error: any) {
    this.mostrarError = true;
    this.mensaje = error.error.message;
  }



}
