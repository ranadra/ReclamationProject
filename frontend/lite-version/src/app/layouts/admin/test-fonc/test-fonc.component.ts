import { FoncService } from './fonc.service';

import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-test-fonc',
  templateUrl: './test-fonc.component.html',
  styleUrls: ['./test-fonc.component.css']
})
export class TestFoncComponent implements OnInit {
foncs;
  constructor(private foncService: FoncService) { }

  ngOnInit() {
    this.getFonc();
  }
  getFonc() {
    this.foncService.getFonc("/fonctionnalites")
    .subscribe( data  => {
     this.foncs = data;
    }, err => {
      console.log(err);
    }
    )
  }
}
