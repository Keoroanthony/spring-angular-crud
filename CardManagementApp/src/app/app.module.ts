import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { AddAccountComponent } from './components/add-account/add-account.component';
import { AccountListComponent } from './components/account-list/account-list.component';
import { AccountDetailsComponent } from './components/account-details/account-details.component';
import { ClientsListComponent } from './components/clients-list/clients-list.component';
import { CardListComponent } from './components/card-list/card-list.component';
import { CardDetailsComponent } from './components/card-details/card-details.component';
import { AddCardComponent } from './components/add-card/add-card.component';

@NgModule({
  declarations: [
    AppComponent,
    AddAccountComponent,
    AccountListComponent,
    AccountDetailsComponent,
    ClientsListComponent,
    CardListComponent,
    CardDetailsComponent,
    AddCardComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
