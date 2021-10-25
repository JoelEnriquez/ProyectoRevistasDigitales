
import { Component, OnInit } from '@angular/core';
import { RevistaService } from '../../Services/revista.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Revista } from '../../Objects/Revista/Revista';
import { Suscripcion } from '../../Objects/Revista/Suscripcion';
import { LocalStorageService } from '../../Services/local-storage.service';
import { Usuario } from '../../Objects/Persona/Usuario';
import { RutasUsuario } from '../../Objects/Rutas/RutasUsuario';
import { RedirigirService } from '../../Services/redirigir.service';

@Component({
  selector: 'app-suscribirse-form',
  templateUrl: './suscribirse-form.component.html',
  styleUrls: ['./suscribirse-form.component.css']
})
export class SuscribirseFormComponent implements OnInit {

  _mostrarError: boolean = false;
  _mensaje:string = "";
  _usuario:Usuario
  _suscribirseForm!: FormGroup;
  _revista!: Revista;
  _mostrarInfoRevista:boolean =false;

  constructor(private _revistasService: RevistaService,
    private _formBuilder: FormBuilder,
    private _route: ActivatedRoute,
    private _localStorage:LocalStorageService,
    private _router: Router,
    private _redirigir: RedirigirService) {
      this._usuario = JSON.parse(`${this._localStorage.obtenerData('usuario')}`);
    }

  ngOnInit(): void {
    let nombre = this._route.snapshot.paramMap.get('nombre');
    this.obtenerRevistaPorNombre(nombre,this._usuario.userName);
  }

  obtenerRevistaPorNombre(_nombreRevista:string | null, _userNameUsuario:string){
    if (_nombreRevista!=null) {
      //Verificar si el usuario ya esta suscrito a dicha revista
      this._revistasService.suscripcionActiva(_nombreRevista,_userNameUsuario).subscribe(
        (_suscripcionExistente:boolean) => {
          if (_suscripcionExistente) {
            alert('Ya tienes una suscripcion activa');
            this._redirigir.redirigir(RutasUsuario.MAIN_USER);
          } else {
            console.log(_suscripcionExistente)
            this._revistasService.obtenerRevistaPorNombre(_nombreRevista).subscribe(
              (_revista: Revista[]) => {
                this._revista = _revista[0];
                if (this._revista.pago) {
                  this._suscribirseForm = this._formBuilder.group({
                    fecha: [null, Validators.required],
                    tipo_pago:['mensual',Validators.required],
                    cantidad_tiempo: ['1', Validators.compose(
                      [Validators.required, Validators.min(1)]
                    )]
                  })
                } else {
                  this._suscribirseForm = this._formBuilder.group({
                    fecha: [null, Validators.required]
                  })
                }
                this._mostrarInfoRevista = true;
              }
            )
          }
        }
      )
    } 
  }

  guardarSuscripcion(){
    if (this._suscribirseForm.valid) {
      this._mensaje = '';
      this._mostrarError = false;
      const _fecha= this._suscribirseForm.value.fecha;
      let _suscripcion: Suscripcion = new Suscripcion(_fecha, true, this._revista.nombre, this._usuario.userName);
      if (this._revista.pago) {
        const _tipo_pago = this._suscribirseForm.value.tipo_pago;
        const _tiempo = this._suscribirseForm.value.cantidad_tiempo;
        _suscripcion.tipoPago = _tipo_pago;
        _suscripcion.cantidadTiempo = _tiempo;
      }
      this._revistasService.registrarSuscripcion(_suscripcion).subscribe(
        () => {
          console.log('llego hasta aca')
          this._router.navigate([RutasUsuario.SUSCRIBED_MAGAZINE,this._revista.nombre]);
        },
        (error) => {
          this._mensaje = error.error.message;
          this._mostrarError = true;
        }
      )
    }
  }

}
