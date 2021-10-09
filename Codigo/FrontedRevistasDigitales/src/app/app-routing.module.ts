import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginFormComponent } from './Componentes/login-form/login-form.component';
import { RegisterFormComponent } from './Componentes/register-form/register-form.component';
import { InicioUsuarioComponent } from './Modulos/inicio-usuario/inicio-usuario.component';
import { InicioAdminComponent } from './Modulos/inicio-admin/inicio-admin.component';
import { InicioEditorComponent } from './Modulos/inicio-editor/inicio-editor.component';

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
  }

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
