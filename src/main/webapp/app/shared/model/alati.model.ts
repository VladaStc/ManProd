import { Moment } from 'moment';
import { IZahvati } from 'app/shared/model//zahvati.model';

export const enum JedMere {
    KOM = 'KOM',
    KG = 'KG',
    L = 'L',
    M2 = 'M2',
    M3 = 'M3',
    TABLA = 'TABLA'
}

export interface IAlati {
    id?: number;
    naziv?: string;
    vrsta?: string;
    jedMere?: JedMere;
    namena?: string;
    cena?: number;
    trajanje?: Moment;
    zahvati?: IZahvati;
}

export class Alati implements IAlati {
    constructor(
        public id?: number,
        public naziv?: string,
        public vrsta?: string,
        public jedMere?: JedMere,
        public namena?: string,
        public cena?: number,
        public trajanje?: Moment,
        public zahvati?: IZahvati
    ) {}
}
