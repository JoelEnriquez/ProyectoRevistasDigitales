import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Persona } from '../../Objects/Persona';
import { EntidadLogin } from '../../Objects/EntidadLogin';
import { PersonaEnum } from 'src/app/Objects/PersonaEnum';
import { LoginService } from '../../Services/login.service';
import { RedirigirService } from '../../Services/redirigir.service';
import { Rutas } from '../../Objects/Rutas';
import { LocalStorageService } from '../../Services/local-storage.service';
import { ValoresInicialesService } from '../../Services/valores-iniciales.service';

@Component({
  selector: 'app-login-form',
  templateUrl: './login-form.component.html',
  styleUrls: ['./login-form.component.css'],
})
export class LoginFormComponent implements OnInit {
  persona!: Persona;
  loginForm!: FormGroup;
  mensaje: String = '';
  mostrarError: boolean = false;

  constructor(
    private formBuilder: FormBuilder,
    private loginService: LoginService,
    private redirigirService: RedirigirService,
    private localStorage: LocalStorageService,
    private valoresService: ValoresInicialesService
  ) {
    
  }

  ngOnInit(): void {
    this.loginForm = this.formBuilder.group({
      userName: ['', Validators.required],
      password: ['', Validators.required],
    });
  }

  public redirectRegister(evento: Event){
    evento.preventDefault();
    this.redirigirService.redirigir(Rutas.REGISTER);
  }

  public iniciarSesion() {
    if (this.loginForm.valid) {
      console.log('Enviar los datos al servidor');
      this.persona = new Persona(this.loginForm.value.userName, this.loginForm.value.password);

      this.loginService.validarPersona(this.persona).subscribe(
        (persona: Persona) => {
          this.loginForm.reset({
            userName: '',
            password: '',
          });
          this.mostrarError = false;
          //Verify tipe of person
          this.redirigirLogin(persona);
        },
        (error: any) => {
          console.log(error);
          this.mostrarError = true;
          this.mensaje = error.error.message;
        }
      );
    }
  }

  redirigirLogin(persona: Persona){
    switch (persona.tipo) {
      case PersonaEnum.ADMIN:
        //Redirect page admin
        this.redirigirService.redirigir(Rutas.MAIN_ADMIN);
        this.localStorage.setItem(persona,'admin');
        break;
      case PersonaEnum.EDITOR:
        //Redirect page editor
        this.redirigirService.redirigir(Rutas.MAIN_EDIT);
        this.localStorage.setItem(persona,'editor');
        break;
      case PersonaEnum.USUARIO:
        //Redirect page usuario
        this.redirigirService.redirigir(Rutas.MAIN_USER);
        this.localStorage.setItem(persona,'usuario');
        break;
      default:
        this.mostrarError = true;
        this.mensaje = "Codigo o contraseña incorrecta";
        break;
    }
  }
}
