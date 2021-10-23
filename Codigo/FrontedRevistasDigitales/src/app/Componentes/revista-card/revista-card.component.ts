import { Component, Input, OnInit } from '@angular/core';
import { Revista } from '../../Objects/Revista/Revista';

@Component({
  selector: 'app-revista-card',
  templateUrl: './revista-card.component.html',
  styleUrls: ['./revista-card.component.css']
})
export class RevistaCardComponent implements OnInit {

  @Input() _revista!:Revista;

  constructor() {
    
   }

  ngOnInit(): void {
  }

}
