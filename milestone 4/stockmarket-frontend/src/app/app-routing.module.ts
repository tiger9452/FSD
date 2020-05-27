import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { SigninGuard } from './guard/signin';
import { LoginComponent } from './pages/login/login.component';
import { RegisterComponent } from './pages/register/register.component';
import { VerifyComponent } from './pages/verify/verify.component';
import { CompareComponent } from './pages/compare/compare.component';
import { SearchComponent } from './pages/search/search.component';
import { ProfileEditComponent } from './pages/profile-edit/profile-edit.component';
import { ImportComponent } from './pages/import/import.component';
import { CompanyEditComponent } from './pages/company-edit/company-edit.component';

const routes: Routes = [
  { path: '', redirectTo: '/login', pathMatch: 'full' },
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'verify', component: VerifyComponent },
  { path: 'compare', component: CompareComponent, canActivate: [SigninGuard] },
  { path: 'search', component: SearchComponent, canActivate: [SigninGuard] },
  { path: 'profile-edit', component: ProfileEditComponent, canActivate: [SigninGuard] },
  { path: 'import', component: ImportComponent, canActivate: [SigninGuard] },
  { path: 'company-edit', component: CompanyEditComponent, canActivate: [SigninGuard] },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
