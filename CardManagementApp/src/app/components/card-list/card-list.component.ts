import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Card } from 'src/app/models/card.model';
import { CardService } from 'src/app/services/card.service';

@Component({
  selector: 'app-card-list',
  templateUrl: './card-list.component.html',
  styleUrls: ['./card-list.component.css']
})
export class CardListComponent implements OnInit {

  cards?: Card[];
  currentCard: Card = {};
  currentIndex = -1;
  cardId = 0;

  constructor(
    private cardService: CardService,
    private route: ActivatedRoute,
    private router: Router) { }

  ngOnInit(): void {

    this.getCardByAccountId(this.route.snapshot.params["id"]);
  }

  getCardByAccountId(id: number): void {
    this.cardService.getCardByAccountId(id)
      .subscribe({
        next: (data) => {
          this.cards = data;
          console.log(data);
        },
        error: (e) => console.error(e)
      });
  }

  refreshList(id: number): void {
    this.getCardByAccountId(id);
    this.currentCard = {};
    this.currentIndex = -1;
  }

  setActiveCard(card: Card, index: number): void {
    this.currentCard = card;
    this.currentIndex = index;
  }

}
