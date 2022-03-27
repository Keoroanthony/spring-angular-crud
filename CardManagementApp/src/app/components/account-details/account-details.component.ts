import { Component, Input, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Account } from 'src/app/models/account.model';
import { AccountsService } from 'src/app/services/accounts.service';

@Component({
  selector: 'app-account-details',
  templateUrl: './account-details.component.html',
  styleUrls: ['./account-details.component.css']
})
export class AccountDetailsComponent implements OnInit {

  @Input() viewMode = false;
  @Input() currentAccount: Account = {
    iban: '',
    bicSwift: '',
    clientId: 0
  };

  message = '';
  constructor(
    private accountService: AccountsService,
    private route: ActivatedRoute,
    private router: Router
    ) { }

  ngOnInit(): void {
    if (!this.viewMode) {
      this.message = '';
      this.getAccount(this.route.snapshot.params["id"]);
    }
  }

  getAccount(id: string): void {
    this.accountService.getAccountByAccountId(id)
      .subscribe({
        next: (data) => {
          this.currentAccount = data;
          console.log(data);
        },
        error: (e) => console.error(e)
      });
  }

  updateAccount(value: any): void {
    const data = {
      iban: this.currentAccount.iban,
      description: this.currentAccount.bicSwift
    };
    this.message = '';
    this.accountService.update(this.currentAccount.accountId, data)
      .subscribe({
        next: (res) => {
          console.log(res);
          this.message = res.message ? res.message : 'The Account was updated successfully!';
        },
        error: (e) => console.error(e)
      });
  }

  deleteAccount(): void {
    this.accountService.delete(this.currentAccount.accountId)
      .subscribe({
        next: (res) => {
          console.log(res);
          this.router.navigate(['/account-list', this.currentAccount.accountId]);
        },
        error: (e) => console.error(e)
      });
  }

}
