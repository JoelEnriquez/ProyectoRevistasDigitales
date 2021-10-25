import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginFormComponent } from './Componentes/login-form/login-form.component';
import { InicioUsuarioComponent } from './Modulos/inicio-usuario/inicio-usuario.component';
import { InicioAdminComponent } from './Modulos/inicio-admin/inicio-admin.component';
import { InicioEditorComponent } from './Modulos/inicio-editor/inicio-editor.component';
import { Rutas } from './Objects/Rutas/Rutas';
import { EscogerCategoriasComponent } from './Componentes/escoger-categoria/escoger-categorias.component';
import { RegisterFormComponent } from './Modulos/register-form/register-form.component';
import { RutasEditor } from './Objects/Rutas/RutasEditor';
import { CrearRevistaComponent } from './Modulos/crear-revista/crear-revista.component';
import { RutasAdmin } from './Objects/Rutas/RutasAdmin';
import { AsignarGastoComponent } from './Modulos/asignar-gasto/asignar-gasto.component';
import { RevistasPropiasComponent } from './Modulos/revistas-propias/revistas-propias.component';
import { EditProfileComponent } from './Modulos/edit-profile/edit-profile.component';
import { RutasUsuario } from './Objects/Rutas/RutasUsuario';
import { UserProfileComponent } from './Modulos/user-profile/user-profile.component';
import { ValoresInicialesComponent } from './Componentes/valores-iniciales/valores-iniciales.component';
import { BuscarRevistasComponent } from './Modulos/buscar-revistas/buscar-revistas.component';
import { RevistasSuscritasComponent } from './Componentes/revistas-suscritas/revistas-suscritas.component';
import { PrevisualizacionRevistaComponent } from './Modulos/previsualizacion-revista/previsualizacion-revista.component';
import { SuscribirseFormComponent } from './Modulos/suscribirse-form/suscribirse-form.component';
import { RevistaSuscritaComponent } from './Modulos/revista-suscrita/revista-suscrita.component';

const routes: Routes = [
  {
    path:"",
    component:ValoresInicialesComponent
  },
  {
    path: "login",
    component: LoginFormComponent
  },
  {
    path: "register",
    component: RegisterFormComponent
  },
  {
    path: "main-user",
    component: InicioUsuarioComponent
  },
  {
    path: "main-admin",
    component: InicioAdminComponent
  },
  {
    path: "main-edit",
    component: InicioEditorComponent
  },
  {
    path: Rutas.REGISTER + "/choose-categories",
    component: EscogerCategoriasComponent
  },
  {
    path: RutasEditor.CREATE_REVISTA,
    component: CrearRevistaComponent
  },
  {
    path: RutasAdmin.ASIGN_COST,
    component: AsignarGastoComponent
  },
  {
    path: RutasEditor.REVISTAS_PROPIAS,
    component: RevistasPropiasComponent
  },
  {
    path: RutasEditor.EDIT_PROFILE,
    component: EditProfileComponent
  },
  {
    path: RutasUsuario.EDIT_PROFILE,
    component: UserProfileComponent
  },
  {
    path: RutasUsuario.SEARCH,
    component: BuscarRevistasComponent
  },
  {
    path: RutasUsuario.SUSCRIBED_MAGAZINES,
    component: RevistasSuscritasComponent
  },
  {
    path: RutasUsuario.PREV_MAGAZINE+"/:nombre",
    component: PrevisualizacionRevistaComponent
  },
  {
    path: RutasUsuario.SUSCRIBE+"/:nombre",
    component: SuscribirseFormComponent
  },
  {
    path: RutasUsuario.SUSCRIBED_MAGAZINE+"/:nombre",
    component: RevistaSuscritaComponent
  }

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
