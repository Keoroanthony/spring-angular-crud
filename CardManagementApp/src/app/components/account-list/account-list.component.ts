import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Account } from 'src/app/models/account.model';
import { AccountsService } from 'src/app/services/accounts.service';


@Component({
  selector: 'app-account-list',
  templateUrl: './account-list.component.html',
  styleUrls: ['./account-list.component.css']
})
export class AccountListComponent implements OnInit {

  accounts?: Account[];
  currentAccount: Account = {};
  currentIndex = -1;
  accountId = 0;

  constructor(
    private accountService: AccountsService,
    private route: ActivatedRoute,
    private router: Router) { }

  ngOnInit(): void {

      this.getAccountByClientId(this.route.snapshot.params["id"]);
  }

  getAccountByClientId(id: number): void {
    this.accountService.getAccountByClientId(id)
      .subscribe({
        next: (data) => {
          this.currentAccount = data;
          console.log(data);
        },
        error: (e) => console.error(e)
      });
  }

  refreshList(id: number): void {
    this.getAccountByClientId(id);
    this.currentAccount = {};
    this.currentIndex = -1;
  }

  setActiveAccount(account: Account, index: number): void {
    this.currentAccount = account;
    this.currentIndex = index;
  }

}
