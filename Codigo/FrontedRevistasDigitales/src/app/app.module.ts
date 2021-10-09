import { HttpClientModule } from '@angular/common/http';
import { NgModule } from '@angular/core';
import { ReactiveFormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginFormComponent } from './Componentes/login-form/login-form.component';
import { RegisterFormComponent } from './Componentes/register-form/register-form.component';
import { EscogerCategoriasComponent } from './Modulos/escoger-categorias/escoger-categorias.component';
import { InicioAdminComponent } from './Modulos/inicio-admin/inicio-admin.component';
import { InicioEditorComponent } from './Modulos/inicio-editor/inicio-editor.component';
import { InicioUsuarioComponent } from './Modulos/inicio-usuario/inicio-usuario.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginFormComponent,
    RegisterFormComponent,
    EscogerCategoriasComponent,
    InicioAdminComponent,
    InicioEditorComponent,
    InicioUsuarioComponent,
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
