import { Component, OnInit, ViewChild } from '@angular/core';
import { LocalStorageService } from '../../Services/local-storage.service';
import { EditarPerfilService } from '../../Services/editar-perfil.service';
import { FilesService } from '../../Services/files.service';
import { FormBuilder } from '@angular/forms';
import { Base64Service } from '../../Services/base64.service';
import { RegistrarService } from '../../Services/registrar.service';
import { EditarPerfilComponent } from '../../Componentes/editar-perfil/editar-perfil.component';
import { Usuario } from '../../Objects/Persona/Usuario';
import { PersonaEnum } from '../../Objects/Persona/PersonaEnum';

@Component({
  selector: 'app-user-profile',
  templateUrl: './user-profile.component.html',
  styleUrls: ['./user-profile.component.css']
})
export class UserProfileComponent implements OnInit {

  @ViewChild(EditarPerfilComponent) child: EditarPerfilComponent ; 
  _usuario: Usuario;
  _fotoPerfil!: string;
  _tipoPersona!:PersonaEnum;

  constructor(private _localService: LocalStorageService,
    private _filesService: FilesService,
    private formBuilder: FormBuilder,
    private editarPerfilService: EditarPerfilService,
    private base64Service: Base64Service,
    private registerService: RegistrarService) {
    this._usuario = JSON.parse(`${this._localService.obtenerData('usuario')}`);
    this.obtenerInfoUsuario();
    this._tipoPersona = PersonaEnum.USUARIO;
    this.child = new EditarPerfilComponent(formBuilder,editarPerfilService,base64Service,registerService,_localService);
   }

  ngOnInit(): void {
  }

  obtenerInfoUsuario(){
    this.editarPerfilService.obtenerInfoUsuario(this._usuario.userName).subscribe(
      (usuario:Usuario) => {
        this._usuario.descripcion = usuario.descripcion;
        this._usuario.hobbies = usuario.hobbies;
        this._fotoPerfil = this._filesService.getImage(this._usuario.userName);
        //Set data in form
        this.child.setearFormUsuario();
      }
    );
  }

}
