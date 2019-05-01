import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {NgForm} from "@angular/forms";
import {Club} from "../../../shared/club.model";
import {ClubService} from "../../../services/club.service";

@Component({
  selector: 'app-club-edit',
  templateUrl: './club-edit.component.html',
  styleUrls: ['./club-edit.component.css']
})
export class ClubEditComponent implements OnInit {

  public btnText = 'EDYTUJ';
  public clubID: number;
  public model: Club = new Club(null,null,null,null);
  private addMode: boolean;

  constructor(private route: ActivatedRoute, private clubService: ClubService, private router: Router) { }

  ngOnInit() {
    this.route.params.subscribe(params => {
      if(params['id'] != null){
        this.clubID = +params['id']
        this.model.id = this.clubID;
        this.clubService.getClub(this.clubID).subscribe(
          data => {
            this.model = data;
            console.log(this.model);
          }
        )
      }else{
        this.btnText = "DODAJ";
      }
    });
    if (this.router.url.includes("add")) {
      this.addMode = true;
    } else {
      this.addMode = false;
    }
  }

  onSubmit(form: NgForm){
    if(!this.addMode)
      this.clubService.updateClub(this.model).subscribe();
    else
      this.clubService.addClub(this.model).subscribe();
  }

}
