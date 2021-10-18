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
import { StrictNumberOnlyDirective } from './Componentes/Herramientas/StrictNumberOnlyDirective';
import { AsignarGastoComponent } from './Modulos/asignar-gasto/asignar-gasto.component';

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
    AsignarGastoComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
