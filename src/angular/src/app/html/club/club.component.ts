import {Component, OnInit } from '@angular/core';
import {ClubService} from "../../services/club.service";
import {Club} from "../../shared/club.model";
import {Router} from "@angular/router";
import {UpdateEmitterService} from "../../services/update-emitter.service";
import {Subscription} from "rxjs";


@Component({
  selector: 'app-club',
  templateUrl: './club.component.html',
  styleUrls: ['./club.component.css']
})
export class ClubComponent implements OnInit {

  public clubs : Club [];
  private sub: Subscription;

  constructor(private clubService : ClubService, private updateService: UpdateEmitterService) { }

  ngOnInit() {
    this.sub = this.clubService.getClubs().subscribe(
      (data : Club[]) => {
        this.clubs = data;
        this.clubs.sort(((a, b) => a.id-b.id))
      },
      error => { console.log(error) }
    )
    this.updateService.clubsUpdate.subscribe(
      data => {
        this.sub.unsubscribe();
        this.sub = this.clubService.getClubs().subscribe(
          (data : Club[]) => {
            this.clubs = data;
            this.clubs.sort(((a, b) => a.id-b.id))
          },
          error => { console.log(error) }
        )
      }
    )
  }

  removeClub(index: number){
    this.clubService.removeClub(this.clubs[index].id).subscribe(
      data => { this.clubs.splice(index,1)},
      error => { console.log(error) }
    )
  }


}
