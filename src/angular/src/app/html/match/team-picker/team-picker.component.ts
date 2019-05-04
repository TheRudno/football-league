import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {Club} from "../../../shared/club.model";

@Component({
  selector: 'app-team-picker',
  templateUrl: './team-picker.component.html',
  styleUrls: ['./team-picker.component.css']
})
export class TeamPickerComponent implements OnInit {


  @Input()
  team: Club;

  @Output()
  teamChanged = new EventEmitter<string>();


  constructor() { }

  ngOnInit() {
  }

}
