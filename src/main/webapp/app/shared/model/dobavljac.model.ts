import { IAlati } from 'app/shared/model//alati.model';
import { IPribori } from 'app/shared/model//pribori.model';
import { IMerniAlati } from 'app/shared/model//merni-alati.model';

export interface IDobavljac {
    id?: number;
    naziv?: string;
    lokacija?: string;
    kolicina?: number;
    alati?: IAlati;
    pribori?: IPribori;
    merniAlati?: IMerniAlati;
}

export class Dobavljac implements IDobavljac {
    constructor(
        public id?: number,
        public naziv?: string,
        public lokacija?: string,
        public kolicina?: number,
        public alati?: IAlati,
        public pribori?: IPribori,
        public merniAlati?: IMerniAlati
    ) {}
}
