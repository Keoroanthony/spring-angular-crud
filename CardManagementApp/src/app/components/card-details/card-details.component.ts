import { Component, Input, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Card } from 'src/app/models/card.model';
import { CardService } from 'src/app/services/card.service';

@Component({
  selector: 'app-card-details',
  templateUrl: './card-details.component.html',
  styleUrls: ['./card-details.component.css']
})
export class CardDetailsComponent implements OnInit {

  @Input() viewMode = false;
  @Input() currentCard: Card = {
    cardId: 0,
    cardAlias: '',
    cardType: '',
    account: 0
  };

  message = '';

  constructor(
    private cardService: CardService,
    private route: ActivatedRoute,
    private router: Router) { }

  ngOnInit(): void {
    if (!this.viewMode) {
      this.message = '';
      this.getCard(this.route.snapshot.params["id"]);
    }
  }

  getCard(id: number): void {
    this.cardService.getCardByCardId(id)
      .subscribe({
        next: (data) => {
          this.currentCard = data;
          console.log(data);
        },
        error: (e) => console.error(e)
      });
  }

  updateCard(): void {
    const data = {
      cardId: this.currentCard.cardId,
      cardAlias: this.currentCard.cardAlias,
      cardType: this.currentCard.cardType,
      account: this.currentCard.account
    };
    this.message = '';
    this.cardService.update(this.currentCard.cardId, data)
      .subscribe({
        next: (res) => {
          console.log(res);
          this.message = res.message ? res.message : 'The Card was updated successfully!';
          // this.router.navigate(['/card-list', this.currentCard.account]);
        },
        error: (e) => console.error(e)
      });
  }

  deleteCard(): void {
    this.cardService.delete(this.currentCard.cardId)
      .subscribe({
        next: (res) => {
          console.log(res);
          this.router.navigate(['/card-list', this.currentCard.account]);
        },
        error: (e) => console.error(e)
      });
  }

}
