import { Component, OnInit } from '@angular/core';
import {Club} from '../../../shared/club.model';

@Component({
  selector: 'app-match-add',
  templateUrl: './match-add.component.html',
  styleUrls: ['./match-add.component.css']
})
export class MatchAddComponent implements OnInit {

  homeSide: Club;
  awaySide: Club;


  constructor() { }

  ngOnInit() {
    this.homeSide = new Club(null, null, null, null);
    this.awaySide = new Club(null, null, null, null);

  }

}
