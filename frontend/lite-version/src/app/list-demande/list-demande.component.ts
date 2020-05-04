import { DemandeService } from './../services/demande.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-list-demande',
  templateUrl: './list-demande.component.html',
  styleUrls: ['./list-demande.component.css']
})
export class ListDemandeComponent implements OnInit {
demandes;
  constructor(private demandeSevice:DemandeService) { }

  ngOnInit() {
    this.demandeSevice.getDemandes()
    .subscribe( (data) => {
      this.demandes = data;
    });
  }

}
