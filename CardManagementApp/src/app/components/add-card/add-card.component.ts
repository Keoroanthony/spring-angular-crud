import { Component, OnInit } from '@angular/core';
import { Card } from 'src/app/models/card.model';
import { CardService } from 'src/app/services/card.service';

@Component({
  selector: 'app-add-card',
  templateUrl: './add-card.component.html',
  styleUrls: ['./add-card.component.css']
})
export class AddCardComponent implements OnInit {

  card: Card ={
    cardAlias: '',
    cardType: '',
    account: 0
  }

  submitted = false;
  cardType: any = ['PHYSICAL', 'VIRTUAL']

  constructor(private cardService: CardService) { }

  ngOnInit(): void {
  }

  saveCard(): void{
    const data = {
      cardAlias:this.card.cardAlias,
      cardType:this.card.cardType,
      account:this.card.account
    };
    this.cardService.create(data).subscribe({
      next: (res) => {
        console.log(res);
        this.submitted = true;
      },
      error: (e) => console.error(e)
    });

  }
  newCard(): void{
      this.submitted = false;
      this.card = {
        cardAlias: '',
        cardType: '',
        account: 0
    };

  }
}
