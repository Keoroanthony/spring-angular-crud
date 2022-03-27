import { Component, OnInit } from '@angular/core';
import { Account } from 'src/app/models/account.model';
import { AccountsService } from 'src/app/services/accounts.service';

@Component({
  selector: 'app-add-account',
  templateUrl: './add-account.component.html',
  styleUrls: ['./add-account.component.css']
})
export class AddAccountComponent implements OnInit {

  account: Account = {
    iban: '',
    bicSwift: '',
    clientId: 0

  };

  submitted = false;

  constructor(private accountsSevices: AccountsService ) { }

  ngOnInit(): void {
  }

  saveAccount(): void{
    const data = {
      iBan:this.account.iban,
      bicSwift:this.account.bicSwift,
      clientId:this.account.clientId
    };
    this.accountsSevices.create(data).subscribe({
      next: (res) => {
        console.log(res);
        this.submitted = true;
      },
      error: (e) => console.error(e)
    });

    }
    newAccount(): void{
      this.submitted = false;
      this.account = {
        iban: '',
        bicSwift: '',
        clientId: 0
    };

  }
}
