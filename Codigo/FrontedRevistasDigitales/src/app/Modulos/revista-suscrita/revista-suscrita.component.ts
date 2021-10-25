import { Component, OnInit } from '@angular/core';
import { SafeResourceUrl, DomSanitizer } from '@angular/platform-browser';
import { ActivatedRoute } from '@angular/router';
import { Revista } from '../../Objects/Revista/Revista';
import { RevistaService } from '../../Services/revista.service';
import { LocalStorageService } from '../../Services/local-storage.service';
import { Usuario } from '../../Objects/Persona/Usuario';
import { RedirigirService } from '../../Services/redirigir.service';
import { RutasUsuario } from '../../Objects/Rutas/RutasUsuario';

@Component({
  selector: 'app-revista-suscrita',
  templateUrl: './revista-suscrita.component.html',
  styleUrls: ['./revista-suscrita.component.css']
})
export class RevistaSuscritaComponent implements OnInit {

  _usuario:Usuario;
  _revista!: Revista;
  _mostrarInfoRevista:boolean =false;

  constructor(private _route: ActivatedRoute,
    private _revistaService: RevistaService,
    private _localStorage: LocalStorageService,
    private _satitizer: DomSanitizer,
    private _revistasService: RevistaService,
    private _redirigir: RedirigirService) {
      this._usuario = JSON.parse(`${this._localStorage.obtenerData('usuario')}`);
  }

  ngOnInit(): void {
    let _nombre = this._route.snapshot.paramMap.get('nombre');
    this.obtenerRevistaPorNombre(_nombre, this._usuario.userName);
  }

  infoRevista() {
    
  }

  obtenerRevistaPorNombre(_nombreRevista:string | null, _userNameUsuario:string){
    if (_nombreRevista!=null) {
      //Verificar si el usuario esta suscrito a esta revista
      this._revistasService.suscripcionActiva(_nombreRevista,_userNameUsuario).subscribe(
        (_suscripcionExistente:boolean) => {
          if (!_suscripcionExistente) {
            alert('No tienes acceso a esta revista porque no estas suscrito');
            this._redirigir.redirigir(RutasUsuario.MAIN_USER);
          } else {
            this._revistasService.obtenerRevistaPorNombre(_nombreRevista).subscribe(
              (_revista: Revista[]) => {
                this._revista = _revista[0];
                
                this._mostrarInfoRevista = true;
              }
            )
          }
        }
      )
    } 
  }

}
