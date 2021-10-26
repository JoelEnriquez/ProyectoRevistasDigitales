import { Component, OnInit } from '@angular/core';
import { SafeResourceUrl, DomSanitizer } from '@angular/platform-browser';
import { ActivatedRoute } from '@angular/router';
import { Revista } from '../../Objects/Revista/Revista';
import { RevistaService } from '../../Services/revista.service';
import { LocalStorageService } from '../../Services/local-storage.service';
import { Usuario } from '../../Objects/Persona/Usuario';
import { RedirigirService } from '../../Services/redirigir.service';
import { RutasUsuario } from '../../Objects/Rutas/RutasUsuario';
import { Etiqueta } from '../../Objects/Revista/Etiqueta';
import { Comentario } from '../../Objects/Revista/Comentario';
import { MeGusta } from '../../Objects/Revista/MeGusta';
import { Publicacion } from '../../Objects/Revista/Publicacion';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';

@Component({
  selector: 'app-revista-suscrita',
  templateUrl: './revista-suscrita.component.html',
  styleUrls: ['./revista-suscrita.component.css']
})
export class RevistaSuscritaComponent implements OnInit {

  _usuario: Usuario;
  _revista!: Revista;
  _rutas = RutasUsuario;
  _listadoComentarios!: Comentario[];
  _listadoPublicaciones!:Publicacion[];
  _meGusta!: MeGusta;
  _classButton:string = '';
  _classPrimary:string = "btn btn-primary btn-lg btn-block";
  _classSecondary:string = 'btn btn-secondary btn-lg btn-block';
  _mostrarInfoRevista: boolean = false;
  _etiquetasRevista!: Etiqueta[];
  _numeroReacciones!: number;
  _numeroComentarios!: number;
  _numeroSuscripciones!: number;
  _comentForm!: FormGroup;

  constructor(private _route: ActivatedRoute,
    private _revistaService: RevistaService,
    private _localStorage: LocalStorageService,
    private _satitizer: DomSanitizer,
    private _revistasService: RevistaService,
    private _redirigir: RedirigirService,
    private _formBuilder: FormBuilder) {
    this._usuario = JSON.parse(`${this._localStorage.obtenerData('usuario')}`);
  }

  ngOnInit(): void {

    this._comentForm = this._formBuilder.group({
      mensaje: ['',Validators.required],
      fecha: ['',Validators.required]
    })

    let _nombre = this._route.snapshot.paramMap.get('nombre');
    this.obtenerRevistaPorNombre(_nombre, this._usuario.userName);
  }

  obtenerReaccion(){
    this._revistaService.obtenerEstadoReaccion(this._revista.nombre, this._usuario.userName).subscribe(
      (_meGusta:MeGusta) => {
        this._meGusta = _meGusta;
        if (_meGusta.nombreRevista.length>0&&_meGusta.userName.length>0) {
          this._classButton = this._classPrimary;
        } else {
          this._classButton = this._classSecondary;
        }
      }
    );
  }

  otorgarReaccion(){
    const _meGusta: MeGusta = new MeGusta(this._revista.nombre, this._usuario.userName);
    console.log(_meGusta.nombreRevista)
    this._revistaService.otorgarReaccion(_meGusta).subscribe(
      (_meGusta:MeGusta) => {
        this._meGusta = _meGusta;
        if (this._classButton == this._classPrimary) {
          this._classButton = this._classSecondary;
          this._numeroReacciones--;
        } else {
          this._classButton = this._classPrimary;
          this._numeroReacciones++;
        }
      }
    )
  }

  obtenerRevistaPorNombre(_nombreRevista: string | null, _userNameUsuario: string) {
    if (_nombreRevista != null) {
      //Verificar si el usuario esta suscrito a esta revista
      this._revistasService.suscripcionActiva(_nombreRevista, _userNameUsuario).subscribe(
        (_suscripcionExistente: boolean) => {
          if (!_suscripcionExistente) {
            alert('No tienes acceso a esta revista porque no estas suscrito');
            this._redirigir.redirigir(RutasUsuario.MAIN_USER);
          } else {
            this._revistasService.obtenerRevistaPorNombre(_nombreRevista).subscribe(
              (_revista: Revista[]) => {
                this._revista = _revista[0];
                this.obtenerEtiquetasRevista();
                this.obtenerEstadisticasRevista();
                this.obtenerReaccion();
                this.obtenerListadoPublicaciones();
                this.obtenerListadoComentarios();
                this._mostrarInfoRevista = true;
              }
            )
          }
        }
      )
    }
  }

  guardarComentario(){
    if (this._comentForm.valid) {
      const _mensaje = this._comentForm.value.mensaje;
      const _fecha = this._comentForm.value.fecha;
      let _comentario:Comentario = new Comentario(0,_mensaje,_fecha, this._revista.nombre,this._usuario.userName);
      console.log(_comentario)
      this._revistaService.registrarComentario(_comentario).subscribe(
        (_comentarioNuevo: Comentario) => {
          this._listadoComentarios.push(_comentarioNuevo);
          this._numeroComentarios++;
          this._comentForm.reset({
            mensaje: '',
            fecha: ''
          })
        },
        (error:any) => {
          console.log(error.error.message);
        }
      )
    }
  }

  obtenerEtiquetasRevista(){
    this._revistasService.etiquetasRevista(this._revista.nombre,'etiquetas_revista').subscribe(
      (_etiquetasRevista: Etiqueta[]) => {
        this._etiquetasRevista = _etiquetasRevista;
      }
    )
  }

  obtenerEstadisticasRevista(){
    this._revistasService.cantidadEstadisticaRevista(this._revista.nombre,'likes').subscribe(
      (_numeroReacciones: number) => {
        this._numeroReacciones = _numeroReacciones;
      }
    );

    this._revistasService.cantidadEstadisticaRevista(this._revista.nombre,'comentarios').subscribe(
      (_numeroComentarios: number) => {
        this._numeroComentarios = _numeroComentarios;
      }
    );

    this._revistasService.cantidadEstadisticaRevista(this._revista.nombre,'suscripciones').subscribe(
      (_numeroSuscripciones: number) => {
        this._numeroSuscripciones = _numeroSuscripciones;
      }
    );
  }

  obtenerListadoPublicaciones(){
    this._revistasService.obtenerListadoPublicaciones(this._revista.nombre).subscribe(
      (_listadoPublicaciones: Publicacion[]) => {
        console.log(_listadoPublicaciones)
        this._listadoPublicaciones = _listadoPublicaciones;
      }
    )
  }

  obtenerListadoComentarios(){
    this._revistasService.obtenerListadoComentarios(this._revista.nombre).subscribe(
      (_listadoComentarios: Comentario[]) => {
        this._listadoComentarios = _listadoComentarios;
      }
    )
  }

}
