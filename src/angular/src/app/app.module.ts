import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { NavbarComponent } from './html/navbar/navbar.component';
import { HomePanelComponent } from './html/home-panel/home-panel.component';
import { ClubComponent } from './html/club/club.component';
import { HttpClientModule } from "@angular/common/http";
import { ClubEditComponent } from './html/club/club-edit/club-edit.component';
import {RouterModule, Routes} from "@angular/router";
import { PageNotFoundComponent } from './html/page-not-found/page-not-found.component';
import { ClubSquadComponent } from './html/club/club-squad/club-squad.component';
import {FormsModule} from "@angular/forms";


const  routes: Routes = [
  {path: 'clubs', component: ClubComponent, children:[
      {path: 'edit/:id', component: ClubEditComponent},
      {path: 'add', component: ClubEditComponent},
      {path: 'squad/:id', component: ClubSquadComponent}
    ]},
  {path: '**', component: PageNotFoundComponent}
]


@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    HomePanelComponent,
    ClubComponent,
    ClubEditComponent,
    PageNotFoundComponent,
    ClubSquadComponent
  ],
  imports: [
    RouterModule.forRoot(routes),
    BrowserModule,
    FormsModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
