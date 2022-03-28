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

  //create card
  create(card: any): Observable<any> {
    return this.http.post(baseUrl, card);
  }

  //update account by cardId 
  update(cardId: any, data: any): Observable<any> {
    return this.http.put(`${baseUrl}/${cardId}`, data);
  }

  //delete account by cardId
  delete(cardId: any): Observable<any> {
    return this.http.delete(`${baseUrl}/${cardId}`);
  }
}
