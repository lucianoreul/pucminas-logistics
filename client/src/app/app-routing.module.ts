import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './pages/login/login.component';
import { HomeComponent } from './pages/home/home.component';
import { StockComponent } from './pages/stock/stock.component';
import { SettingsComponent } from './pages/settings/settings.component';
import { StockFormComponent } from './pages/stock-form/stock-form.component';
import { StockViewComponent } from './pages/stock-view/stock-view.component';
import {UserComponent} from "./pages/user/user.component";
import {UserFormComponent} from "./pages/user-form/user-form.component";

const routes: Routes = [
  {
    path: '',
    component: LoginComponent
  },
  {
    path: 'home',
    component: HomeComponent
  },
  {
    path: 'stock',
    component: StockComponent
  },
  {
    path: 'settings',
    component: SettingsComponent
  },
  {
    path: 'stock/new',
    component: StockFormComponent
  },
  {
    path: 'stock/view/:id',
    component: StockViewComponent
  },
  {
    path: 'user',
    component: UserComponent
  },
  {
    path: 'user/:id',
    component: UserFormComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
