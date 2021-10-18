import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginFormComponent } from './Componentes/login-form/login-form.component';
import { InicioUsuarioComponent } from './Modulos/inicio-usuario/inicio-usuario.component';
import { InicioAdminComponent } from './Modulos/inicio-admin/inicio-admin.component';
import { InicioEditorComponent } from './Modulos/inicio-editor/inicio-editor.component';
import { Rutas } from './Objects/Rutas';
import { EscogerCategoriasComponent } from './Componentes/escoger-categoria/escoger-categorias.component';
import { RegisterFormComponent } from './Modulos/register-form/register-form.component';
import { RutasEditor } from './Objects/RutasEditor';
import { CrearRevistaComponent } from './Modulos/crear-revista/crear-revista.component';

const routes: Routes = [
  {
    path: "",
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
  }

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
