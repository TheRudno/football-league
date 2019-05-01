import {Component, OnInit } from '@angular/core';
import {ClubService} from "../../services/club.service";
import {Club} from "../../shared/club.model";
import {Router} from "@angular/router";


@Component({
  selector: 'app-club',
  templateUrl: './club.component.html',
  styleUrls: ['./club.component.css']
})
export class ClubComponent implements OnInit {

  public clubs : Club [];

  constructor(private clubService : ClubService, private router: Router) { }

  ngOnInit() {
    this.clubService.getClubs().subscribe(
      (data : Club[]) => this.clubs = data,
      error => { console.log(error) }
    )
  }

  removeClub(index: number){
    this.clubService.removeClub(this.clubs[index].id).subscribe(
      data => { if(data.status==204) this.clubs.splice(index,1)},
      error => { console.log(error) }
    )
  }


}
