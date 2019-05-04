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
import { MatchAddComponent } from './html/match/match-add/match-add.component';
import { GoalsEditComponent } from './html/match/goals-edit/goals-edit.component';
import { TeamPickerComponent } from './html/match/team-picker/team-picker.component';


const  routes: Routes = [
  {path: 'clubs', component: ClubComponent, children:[
      {path: 'edit/:id', component: ClubEditComponent},
      {path: 'add', component: ClubEditComponent},
      {path: 'squad/:id', component: ClubSquadComponent}
    ]},
  {path: 'matches', component: MatchComponent, children: [
      {path: 'add', component: MatchAddComponent},
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
    ClubSquadComponent,
    MatchComponent,
    TeamComponent,
    MatchAddComponent,
    GoalsEditComponent,
    TeamPickerComponent
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
