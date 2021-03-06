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
import { RevistasSuscritasComponent } from './Modulos/revistas-suscritas/revistas-suscritas.component';
import { PrevisualizacionRevistaComponent } from './Modulos/previsualizacion-revista/previsualizacion-revista.component';
import { SuscribirseFormComponent } from './Modulos/suscribirse-form/suscribirse-form.component';
import { RevistaSuscritaComponent } from './Modulos/revista-suscrita/revista-suscrita.component';
import { LecturaRevistaComponent } from './Modulos/lectura-revista/lectura-revista.component';
import { ReporteComentariosComponent } from './Modulos/ReportesEditor/reporte-comentarios/reporte-comentarios.component';
import { ReporteSuscripcionesComponent } from './Modulos/ReportesEditor/reporte-suscripciones/reporte-suscripciones.component';
import { ReporteGananciasEditorComponent } from './Modulos/ReportesEditor/reporte-ganancias-editor/reporte-ganancias-editor.component';
import { ReporteRevistasGustadasComponent } from './Modulos/ReportesEditor/reporte-revistas-gustadas/reporte-revistas-gustadas.component';
import { ReporteRevistasComentadasComponent } from './Modulos/ReportesAdmin/reporte-revistas-comentadas/reporte-revistas-comentadas.component';
import { ReporteRevistasPopularesComponent } from './Modulos/ReportesAdmin/reporte-revistas-populares/reporte-revistas-populares.component';

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
    //path: RutasUsuario.SEARCH,
    path: RutasUsuario.SEARCH+"/:categoria/:etiqueta",
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
  },
  {
    path: RutasUsuario.READ_MAGAZINE+"/:path",
    component: LecturaRevistaComponent
  },
  {
    path: RutasEditor.COMENTS_REPORT,
    component: ReporteComentariosComponent
  },
  {
    path: RutasEditor.SUSCRIPTIONS_REPORT,
    component: ReporteSuscripcionesComponent
  },
  {
    path: RutasEditor.EARNING_MONEY_REPORT,
    component: ReporteGananciasEditorComponent
  },
  {
    path: RutasEditor.LIKES_REPORT,
    component: ReporteRevistasGustadasComponent
  },
  {
    path: RutasAdmin.MOST_COMENTED_MAGAZINES,
    component: ReporteRevistasComentadasComponent
  },
  {
    path: RutasAdmin.MOST_SUSCRIBED_MAGAZINES,
    component: ReporteRevistasPopularesComponent
  }

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
