import { Component, OnInit } from '@angular/core';
import { Client } from 'src/app/models/client.model';
import { ClientsService } from 'src/app/services/clients.service';

@Component({
  selector: 'app-clients-list',
  templateUrl: './clients-list.component.html',
  styleUrls: ['./clients-list.component.css']
})
export class ClientsListComponent implements OnInit {


  clients?: Client[];
  currentClient: Client = {};
  currentIndex = -1;
  clientId = 0;

 constructor(private clientService: ClientsService) { }

   ngOnInit(): void {
    this.retrieveClients();
   }

  retrieveClients(): void {
    this.clientService.getAllClients()
    .subscribe({
      next: (data) => {
        this.clients = data;
        console.log(data);
      },
      error: (e) => console.error(e)
    });
  }

  refreshList(): void {
    this.retrieveClients();
    this.currentClient = {};
    this.currentIndex = -1;
  }

  setActiveClient(client: Client, index: number): void {
    this.currentClient = client;
    this.currentIndex = index;
  }

}
