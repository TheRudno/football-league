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
import { MatchComponent } from './html/match/match.component';
import { TeamComponent } from './html/match/team/team.component';
import { MatchEditComponent } from './html/match/match-edit/match-edit.component';
import { GoalsComponent } from './html/match/goals/goals.component';
import { TeamPickerComponent } from './html/match/team-picker/team-picker.component';
import {FootballerComponent} from "./html/footballer/footballer.component";
import {FootballerEditComponent} from "./html/footballer/footballer-edit/footballer-edit.component";
import {UpdateEmitterService} from "./services/update-emitter.service";
import {CommonModule} from "@angular/common";
import {ToastrModule} from "ngx-toastr";
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import { MatchOverviewComponent } from './html/match/match-overview/match-overview.component';


const  routes: Routes = [
  {path: 'clubs', component: ClubComponent, children:[
      {path: 'edit/:id', component: ClubEditComponent},
      {path: 'add', component: ClubEditComponent},
      {path: 'squad/:id', component: ClubSquadComponent}
      ]},
  {path: 'footballers', component: FootballerComponent, children:[
      {path: 'edit/:id', component: FootballerEditComponent},
      {path: 'add', component: FootballerEditComponent}
    ]},
  {path: 'matches', component: MatchComponent, children: [
      {path: 'add', component: MatchEditComponent},
      {path: ':id/edit', component: MatchEditComponent},
      {path: ':id', component: MatchOverviewComponent}
    ]},
  {path: '', component: ClubComponent, pathMatch: 'full'},
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
    ClubSquadComponent,
    MatchComponent,
    TeamComponent,
    MatchEditComponent,
    GoalsComponent,
    TeamPickerComponent,
    FootballerEditComponent,
    FootballerComponent,
    MatchOverviewComponent
  ],
  imports: [
    RouterModule.forRoot(routes),
    BrowserModule,
    FormsModule,
    HttpClientModule,
    CommonModule,
    BrowserAnimationsModule,
    ToastrModule.forRoot()
  ],
  providers: [UpdateEmitterService],
  bootstrap: [AppComponent]
})
export class AppModule { }
