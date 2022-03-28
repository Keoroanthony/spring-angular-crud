import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AccountDetailsComponent } from './components/account-details/account-details.component';
import { AccountListComponent } from './components/account-list/account-list.component';
import { AddAccountComponent } from './components/add-account/add-account.component';
import { AddCardComponent } from './components/add-card/add-card.component';
import { CardDetailsComponent } from './components/card-details/card-details.component';
import { CardListComponent } from './components/card-list/card-list.component';
import { ClientsListComponent } from './components/clients-list/clients-list.component';

const routes: Routes = [
  { path: '', redirectTo: 'clients', pathMatch: 'full' },
  { path: 'clients', component: ClientsListComponent },
  { path: 'account-list/:id', component: AccountListComponent},
  { path: 'add-account', component: AddAccountComponent},
  { path: 'account-details/:id', component: AccountDetailsComponent},
  { path: 'card-list/:id', component: CardListComponent},
  { path: 'add-card', component: AddCardComponent},
  { path: 'card-details/:id', component: CardDetailsComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
