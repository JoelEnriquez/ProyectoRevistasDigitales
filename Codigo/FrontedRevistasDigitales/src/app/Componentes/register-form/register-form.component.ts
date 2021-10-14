import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { RedirigirService } from '../../Services/redirigir.service';
import { PersonaEnum } from '../../Objects/PersonaEnum';
import { RegistrarService } from '../../Services/registrar.service';
import { Rutas } from '../../Objects/Rutas';
import { Editor } from '../../Objects/Editor';
import { LocalStorageService } from '../../Services/local-storage.service';
import { LoginFormComponent } from '../login-form/login-form.component';
import { FilesService } from '../../Services/files.service';
import { DomSanitizer } from '@angular/platform-browser';
import { Base64Service } from '../../Services/base64.service';

@Component({
  selector: 'app-register-form',
  templateUrl: './register-form.component.html',
  styleUrls: ['./register-form.component.css']
})
export class RegisterFormComponent implements OnInit {

  mostrarError: boolean = false;
  mensaje: String = "";
  registerForm!: FormGroup;
  mostrarImagen: boolean = false;
  urlImagen!: String;
  PersonaEnum!: PersonaEnum;
  archivos: any = [];
  imagenSeleccionada: File | null = null;
  previsualizar: string = "";

  constructor(
    private formBuilder: FormBuilder,
    private redirigirService: RedirigirService,
    private registerService: RegistrarService,
    private localStorage: LocalStorageService,
    private filesService: FilesService,
    private sanitizer: DomSanitizer,
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

  cargarArchivo(event: Event) {
    const files = (event.target as HTMLInputElement).files;
    if (files != null) {
      this.imagenSeleccionada = files.item(0); //Guardamos en el atributo de la clase
      this.base64.extraerBase64(this.imagenSeleccionada).then((imagen: any) => {
        this.previsualizar = imagen.base;
      })
    }
  }

  guardarInfo() {
    if (this.registerForm.valid) {
      let userName: String = this.registerForm.value.userName;
      let nombre: String = this.registerForm.value.nombre;
      let password: String = this.registerForm.value.password;
      let tipo: PersonaEnum = this.registerForm.value.persona;
      //this.registerForm.value.foto;
      let hobbies = this.registerForm.value.hobbies;
      let descripcion = this.registerForm.value.descripcion;

      switch (tipo) {
        case PersonaEnum.EDITOR:
          const editor: Editor = new Editor(userName, password, nombre, tipo, hobbies,descripcion);
          this.registerService.registrarPersona(editor,this.imagenSeleccionada).subscribe(
            (editor: Editor) => {
              this.registerForm.reset({
                userName: ['', Validators.required],
                nombre: ['', Validators.required],
                password: ['', Validators.required],
                foto: [null],
                hobbies: [''],
                descripcion: [''],
              });
              this.mostrarError = false;
              //Guardar en local storage
              this.localStorage.setItem(editor, "editor");
              this.redirigirService.redirigir(Rutas.MAIN_EDIT);
            },
            (error: any) => {
              console.log(error);
              this.mostrarError = true;
              this.mensaje = error.error.text;
            }
          );
          break;
        case PersonaEnum.USUARIO:
          //Comprobar que no exista dicho nombre de usuario
          break;
        default:
          break;
      }
    }
  }

  

}
