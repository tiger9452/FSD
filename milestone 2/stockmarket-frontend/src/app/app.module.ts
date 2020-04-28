import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { AppBootstrapModule } from './app-bootstrap/app-bootstrap.module';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HTTP_INTERCEPTORS, HttpClientModule, HttpClientJsonpModule } from '@angular/common/http';
import { LoggerModule, NgxLoggerLevel } from 'ngx-logger';
import { MessageService } from 'primeng/api';
import { PasswordModule } from 'primeng/password';
import { DropdownModule } from 'primeng/dropdown';
import { MultiSelectModule } from 'primeng/multiselect';
import { RadioButtonModule } from 'primeng/radiobutton';
import { CalendarModule } from 'primeng/calendar';
import { AutoCompleteModule } from 'primeng/autocomplete';
import { TableModule } from 'primeng/table';
import { ToastModule } from 'primeng/toast';
import { FileUploadModule } from 'primeng/fileupload';
import { FusionChartsModule } from "angular-fusioncharts";

// Import FusionCharts library and chart modules
import * as FusionCharts from "fusioncharts";
import * as charts from "fusioncharts/fusioncharts.charts";
import * as FusionTheme from "fusioncharts/themes/fusioncharts.theme.fusion";

// Pass the fusioncharts library and chart modules
FusionChartsModule.fcRoot(FusionCharts, charts, FusionTheme);

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './pages/login/login.component';
import { HeaderComponent } from './layout/header/header.component';
import { FooterComponent } from './layout/footer/footer.component';
import { RegisterComponent } from './pages/register/register.component';
import { Jwt } from './interceptor/jwt';
import { VerifyComponent } from './pages/verify/verify.component';
import { Currency } from './pipe/currency';
import { CompareComponent } from './pages/compare/compare.component';
import { SearchComponent } from './pages/search/search.component';
import { ProfileEditComponent } from './pages/profile-edit/profile-edit.component';
import { CompanyEditComponent } from './pages/company-edit/company-edit.component';
import { ImportComponent } from './pages/import/import.component';
import { CompanyComponent } from './component/company/company.component';
import { ProfileComponent } from './component/profile/profile.component';

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    FooterComponent,
    LoginComponent,
    RegisterComponent,
    VerifyComponent,
    Currency,
    CompareComponent,
    SearchComponent,
    ProfileEditComponent,
    CompanyEditComponent,
    ImportComponent,
    CompanyComponent,
    ProfileComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    HttpClientJsonpModule,
    LoggerModule.forRoot({serverLoggingUrl: '/api/logs', level: NgxLoggerLevel.DEBUG, 
                          serverLogLevel: NgxLoggerLevel.ERROR}),
    AppBootstrapModule,
    FontAwesomeModule,
    FormsModule,
    ReactiveFormsModule,
    FusionChartsModule,
    PasswordModule,
    DropdownModule,
    RadioButtonModule,
    MultiSelectModule,
    CalendarModule,
    AutoCompleteModule,
    TableModule,
    ToastModule,
    FileUploadModule,
  ],
  providers: [
    { provide: HTTP_INTERCEPTORS, useClass: Jwt, multi: true },
    { provide: MessageService }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
