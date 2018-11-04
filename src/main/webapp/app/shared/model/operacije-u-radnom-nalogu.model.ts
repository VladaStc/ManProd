import { Moment } from 'moment';
import { IRadniNalog } from 'app/shared/model//radni-nalog.model';

export interface IOperacijeURadnomNalogu {
    id?: number;
    naziv?: string;
    vremeRada?: number;
    pripremnoZavrsnoVreme?: number;
    cena?: number;
    skica?: string;
    datumIVremePocetka?: Moment;
    datumIVremeZavrsetka?: Moment;
    radniNalog?: IRadniNalog;
}

export class OperacijeURadnomNalogu implements IOperacijeURadnomNalogu {
    constructor(
        public id?: number,
        public naziv?: string,
        public vremeRada?: number,
        public pripremnoZavrsnoVreme?: number,
        public cena?: number,
        public skica?: string,
        public datumIVremePocetka?: Moment,
        public datumIVremeZavrsetka?: Moment,
        public radniNalog?: IRadniNalog
    ) {}
}
