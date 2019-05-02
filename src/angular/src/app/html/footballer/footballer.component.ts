import {Component, OnInit, ViewContainerRef} from '@angular/core';
import {Footballer} from "../../shared/footballer.model";
import {FootballerService} from "../../services/footballer.service";
import {Subscription} from "rxjs";
import {UpdateEmitterService} from "../../services/update-emitter.service";


@Component({
  selector: 'app-footballer',
  templateUrl: './footballer.component.html',
  styleUrls: ['./footballer.component.css']
})
export class FootballerComponent implements OnInit {

   public footballers: Footballer[];
   private sub: Subscription;

  constructor(private footballerService: FootballerService, private updateService: UpdateEmitterService,
              ) { }


  ngOnInit() {
    this.sub = this.footballerService.getFootballers().subscribe(
      (data: Footballer[]) => {
        this.footballers = data;
        this.footballers.sort((a: Footballer,  b: Footballer) => a.id-b.id)
      },
      error1 => console.log(error1.toString())
    );

    this.updateService.footballersUpdate.subscribe(
      data => {
        this.sub.unsubscribe();
        this.footballerService.getFootballers().subscribe(
          (data: Footballer[]) => {
            this.footballers = data;
            this.footballers.sort((a: Footballer,  b: Footballer) => a.id-b.id);
          },
          error1 => console.log(error1.toString())
        );
      }
    )
  }

  removeFootballer(index: number){
    this.footballerService.removeFootballer(this.footballers[index].id).subscribe(
      data => { this.footballers.splice(index,1)},
      error => { console.log(error) }
    )
  }

  updateList(){
    console.log("emmiter");
    this.footballerService.getFootballers().subscribe(
      (data: Footballer[]) => this.footballers = data,
      error1 => console.log(error1.toString())
    )
  }


}
