import { HttpClientModule } from '@angular/common/http';
import { NgModule } from '@angular/core';
import { ReactiveFormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginFormComponent } from './Componentes/login-form/login-form.component';
import { RegisterFormComponent } from './Modulos/register-form/register-form.component';
import { EscogerCategoriasComponent } from './Componentes/escoger-categoria/escoger-categorias.component';
import { InicioAdminComponent } from './Modulos/inicio-admin/inicio-admin.component';
import { InicioEditorComponent } from './Modulos/inicio-editor/inicio-editor.component';
import { InicioUsuarioComponent } from './Modulos/inicio-usuario/inicio-usuario.component';
import { HeaderEditorComponent } from './Componentes/header-editor/header-editor.component';
import { HeaderAdminComponent } from './Componentes/header-admin/header-admin.component';
import { HeaderUsuarioComponent } from './Componentes/header-usuario/header-usuario.component';
import { CrearRevistaComponent } from './Modulos/crear-revista/crear-revista.component';
import { StrictNumberOnlyDirective } from './Objects/Herramientas/StrictNumberOnlyDirective';
import { AsignarGastoComponent } from './Modulos/asignar-gasto/asignar-gasto.component';
import { RevistasPropiasComponent } from './Modulos/revistas-propias/revistas-propias.component';
import { CrearRevistaFormComponent } from './Componentes/crear-revista-form/crear-revista-form.component';
import { SubirPublicacionComponent } from './Componentes/subir-publicacion/subir-publicacion.component';
import { EditarPerfilComponent } from './Componentes/editar-perfil/editar-perfil.component';
import { ConfEtiquetasComponent } from './Componentes/conf-etiquetas/conf-etiquetas.component';
import { PrevisualizacionRevistaComponent } from './Modulos/previsualizacion-revista/previsualizacion-revista.component';
import { PrevisualizacionEditorComponent } from './Componentes/previsualizacion-editor/previsualizacion-editor.component';
import { RevistaCardComponent } from './Componentes/revista-card/revista-card.component';
import { EditProfileComponent } from './Modulos/edit-profile/edit-profile.component';
import { UserProfileComponent } from './Modulos/user-profile/user-profile.component';
import { ValoresInicialesComponent } from './Componentes/valores-iniciales/valores-iniciales.component';
import { EtiquetaRevistaCardComponent } from './Componentes/etiqueta-revista-card/etiqueta-revista-card.component';
import { CategoriaRevistaCardComponent } from './Componentes/categoria-revista-card/categoria-revista-card.component';
import { RevistasCategoriaComponent } from './Componentes/revistas-categoria/revistas-categoria.component';
import { BuscarRevistasComponent } from './Modulos/buscar-revistas/buscar-revistas.component';
import { RevistasSuscritasComponent } from './Componentes/revistas-suscritas/revistas-suscritas.component';
import { RouterModule } from '@angular/router';
import { RutasUsuario } from './Objects/Rutas/RutasUsuario';

@NgModule({
  declarations: [
    AppComponent,
    LoginFormComponent,
    RegisterFormComponent,
    EscogerCategoriasComponent,
    InicioAdminComponent,
    InicioEditorComponent,
    InicioUsuarioComponent,
    HeaderEditorComponent,
    HeaderAdminComponent,
    HeaderUsuarioComponent,
    CrearRevistaComponent,
    StrictNumberOnlyDirective,
    AsignarGastoComponent,
    RevistasPropiasComponent,
    CrearRevistaFormComponent,
    SubirPublicacionComponent,
    EditarPerfilComponent,
    ConfEtiquetasComponent,
    PrevisualizacionRevistaComponent,
    PrevisualizacionEditorComponent,
    RevistaCardComponent,
    EditProfileComponent,
    UserProfileComponent,
    ValoresInicialesComponent,
    EtiquetaRevistaCardComponent,
    CategoriaRevistaCardComponent,
    RevistasCategoriaComponent,
    BuscarRevistasComponent,
    RevistasSuscritasComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule,
    HttpClientModule,
    // RouterModule.forRoot([
    //   {path:RutasUsuario.SEARCH+'/:nombre/:action/:filtro', component: BuscarRevistasComponent},
    //   {path:'prev-magazine/:nombre', component: PrevisualizacionRevistaComponent},
    // ])
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
