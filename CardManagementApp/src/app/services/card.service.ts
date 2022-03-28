import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Card } from '../models/card.model';
const baseUrl = 'http://localhost:8080/api/v1/cards';

@Injectable({
  providedIn: 'root'
})
export class CardService {

  constructor(private http: HttpClient) { }

  //get all cards by accountId
  getCardByAccountId(accountId: number): Observable<Card[]>{
    return this.http.get<Card[]>(`${baseUrl}/account-cards/${accountId}`);
  }

  //get card by cardId ok
  getCardByCardId(cardId: any): Observable<Card> {
    return this.http.get(`${baseUrl}/card/${cardId}`);
  }

  //create card
  create(card: any): Observable<any> {
    return this.http.post(baseUrl, card);
  }

  //update card by cardId 
  update(cardId: any, data: any): Observable<any> {
    return this.http.put(`${baseUrl}/${cardId}`, data);
  }

  //delete card by cardId
  delete(cardId: any): Observable<any> {
    return this.http.delete(`${baseUrl}/${cardId}`);
  }
}
