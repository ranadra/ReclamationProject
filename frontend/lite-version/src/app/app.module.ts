import { BasicRegComponent } from './auth/registration/basic-reg/basic-reg.component';
import { BasicLoginComponent } from './auth/login/basic-login/basic-login.component';
import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { AdminComponent } from './layouts/admin/admin.component';
import { TitleComponent } from './layouts/admin/title/title.component';
import { BreadcrumbsComponent } from './layouts/admin/breadcrumbs/breadcrumbs.component';
import { AuthComponent } from './layouts/auth/auth.component';
import {SharedModule} from './shared/shared.module';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import { TestFoncComponent } from './layouts/admin/test-fonc/test-fonc.component';
import { ListDemandeComponent } from './list-demande/list-demande.component';
import { FonctionnaliteComponent } from './fonctionnalite/fonctionnalite.component';
import { ModelisationComponent } from './modelisation/modelisation.component';
import { UploadFilesComponent } from './components/upload-files/upload-files.component';


@NgModule({
  declarations: [
    AppComponent,
    AdminComponent,
    TitleComponent,
    BreadcrumbsComponent,
    AuthComponent,
    BasicLoginComponent,
    BasicRegComponent,
    TestFoncComponent,
    ListDemandeComponent,
    FonctionnaliteComponent,
    ModelisationComponent,
    UploadFilesComponent,
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    AppRoutingModule,
    SharedModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
