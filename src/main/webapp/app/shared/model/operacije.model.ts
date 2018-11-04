import { IZahvati } from 'app/shared/model//zahvati.model';

export interface IOperacije {
    id?: number;
    naziv?: string;
    vremeRada?: number;
    pomocnoVreme?: number;
    cena?: number;
    skica?: string;
    zahvati?: IZahvati;
}

export class Operacije implements IOperacije {
    constructor(
        public id?: number,
        public naziv?: string,
        public vremeRada?: number,
        public pomocnoVreme?: number,
        public cena?: number,
        public skica?: string,
        public zahvati?: IZahvati
    ) {}
}
