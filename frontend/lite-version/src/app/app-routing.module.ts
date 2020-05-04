import { UploadFilesComponent } from './components/upload-files/upload-files.component';
import { ModelisationComponent } from './modelisation/modelisation.component';
import { BasicRegComponent } from './auth/registration/basic-reg/basic-reg.component';
import { BasicLoginComponent } from './auth/login/basic-login/basic-login.component';
import { TestFoncComponent } from './layouts/admin/test-fonc/test-fonc.component';
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {AdminComponent} from './layouts/admin/admin.component';
import {AuthComponent} from './layouts/auth/auth.component';

const routes: Routes = [
  {
    path: 'upload',
    component: UploadFilesComponent,
  },
  {
    path: 'modelisation',
    component: ModelisationComponent,
  },
  {
    path: 'fonc',
    component: TestFoncComponent,
  },
  {
    path: '',
    component: BasicRegComponent,
  },
  {
    path: 'index',
    component: AdminComponent,
  },
  {
    path: 'login',
    component: BasicLoginComponent,
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
