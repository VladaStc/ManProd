import { Moment } from 'moment';

export const enum Status {
    OTVOREN = 'OTVOREN',
    AKTIVAN = 'AKTIVAN',
    ZATVOREN = 'ZATVOREN'
}

export interface IRadniNalog {
    id?: number;
    naziv?: string;
    datumIVremeOtvaranja?: Moment;
    datumIVremeZatvaranja?: Moment;
    status?: Status;
    nosilac?: string;
    cena?: number;
    kolicina?: number;
}

export class RadniNalog implements IRadniNalog {
    constructor(
        public id?: number,
        public naziv?: string,
        public datumIVremeOtvaranja?: Moment,
        public datumIVremeZatvaranja?: Moment,
        public status?: Status,
        public nosilac?: string,
        public cena?: number,
        public kolicina?: number
    ) {}
}
