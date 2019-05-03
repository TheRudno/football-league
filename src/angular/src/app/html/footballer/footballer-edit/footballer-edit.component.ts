import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {Footballer} from "../../../shared/footballer.model";
import {ActivatedRoute, Router} from "@angular/router";
import {ClubService} from "../../../services/club.service";
import {FootballerService} from "../../../services/footballer.service";
import {Club} from "../../../shared/club.model";
import {NgForm} from "@angular/forms";
import {UpdateEmitterService} from "../../../services/update-emitter.service";
import {ToastrService} from "ngx-toastr";

@Component({
  selector: 'app-footballer-edit',
  templateUrl: './footballer-edit.component.html',
  styleUrls: ['./footballer-edit.component.css']
})
export class FootballerEditComponent implements OnInit {

  public model: Footballer = new Footballer(null,null,null,null, null, null, null);
  public selectedClub = new Club(null,null,null,null);
  public clubs: Club[];
  footballerID: number;
  positions: String[];
  public btnText = 'EDYTUJ';
  public addMode: boolean;

  constructor(private route: ActivatedRoute, private clubService: ClubService, private router: Router, private footballerService: FootballerService,
              private updateEmitter: UpdateEmitterService, private toastr: ToastrService) { }

  ngOnInit() {
    this.footballerService.getPositions().subscribe(
      (data: String[]) => this.positions = data
    )
    this.route.params.subscribe(params => {
      if(params['id'] != null){
        this.footballerID = +params['id']
        this.model.id = this.footballerID;
        this.footballerService.getFootballer(this.footballerID).subscribe(
          data => {
            this.model = data;
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
    this.clubService.getClubs().subscribe(
      (data: Club[]) => {
        this.clubs = data;
        this.clubs.push(this.selectedClub);
      }
    )
  }

  onSubmit(form: NgForm){
    if(!this.addMode)
      this.footballerService.updateFootballer(this.model).subscribe(
        data => {
          this.updateEmitter.updateFootballers();
          this.toastr.success("Edytowano zawodnika", 'Success!');
          this.router.navigate(["./footballers"])
        },
        error => this.toastr.error(error.message, 'Warning!')
      );
    else
      this.footballerService.addFootballer(this.model).subscribe(
        data => {
          this.updateEmitter.updateFootballers();
          this.toastr.success("Dodano zawodnika", 'Success!');
          this.router.navigate(["./footballers"])
        },
        error => this.toastr.error(error.message, 'Warning!')

      );
  }

  onChangeClub(form: NgForm) {
    console.log(this.selectedClub);
    this.footballerService.assignClub(this.model.id, this.selectedClub.id).subscribe(
      data => {
        this.updateEmitter.updateFootballers();
        this.toastr.success("Zmienioni klub zawodnika", 'Success!');
        this.router.navigate(["./footballers"]);
      },
      error => this.toastr.error(error.message, 'Warning!')
    );
  }

}
