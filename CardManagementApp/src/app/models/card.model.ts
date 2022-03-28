import { Account } from "./account.model";

export class Card {
    cardId?: number;
    cardAlias?: string;
    cardType?: string;
    account?: Account["accountId"];
}
