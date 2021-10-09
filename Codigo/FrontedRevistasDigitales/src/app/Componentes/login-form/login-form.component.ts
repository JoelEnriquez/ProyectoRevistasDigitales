import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Persona } from '../../Objects/Persona';
import { EntidadLogin } from '../../Objects/EntidadLogin';
import { PersonaEnum } from 'src/app/Objects/PersonaEnum';
import { LoginService } from '../../Services/login.service';
import { RedirigirService } from '../../Services/redirigir.service';
import { Rutas } from '../../Objects/Rutas';

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
    private redirigirService: RedirigirService
  ) { }

  ngOnInit(): void {
    this.loginForm = this.formBuilder.group({
      codigo: ['', Validators.required],
      password: ['', Validators.required],
    });
  }

  public iniciarSesion() {
    if (this.loginForm.valid) {
      console.log('Enviar los datos al servidor');
      this.persona = new Persona(this.loginForm.value.codigo, this.loginForm.value.password);

      this.loginService.validarPersona(this.persona).subscribe(
        (persona: Persona) => {
          this.loginForm.reset({
            codigo: '',
            password: '',
          });
          this.mostrarError = false;
          //Verify tipe of person
          switch (persona.tipo) {
            case PersonaEnum.ADMIN:
              //Redirect page admin
              this.redirigirService.redirigir(Rutas.MAIN_ADMIN);
              console.log('Admin');
              break;
            case PersonaEnum.EDITOR:
              //Redirect page editor
              this.redirigirService.redirigir(Rutas.MAIN_EDIT);
              console.log('Editor');
              break;
            case PersonaEnum.USUARIO:
              //Redirect page usuario
              this.redirigirService.redirigir(Rutas.MAIN_USER);
              console.log('Usuario');
              break;
            default:
              console.log('No one');
              this.mostrarError = true;
              this.mensaje = "Codigo o contraseña incorrecta";
              break;
          }
        },
        (error: any) => {
          console.log(error);
          this.mostrarError = true;
          this.mensaje = error.error.message;
        }
      );
    }
  }
}
