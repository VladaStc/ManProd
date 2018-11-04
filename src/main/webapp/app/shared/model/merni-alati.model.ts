import { Moment } from 'moment';

export const enum JedMere {
    KOM = 'KOM',
    KG = 'KG',
    L = 'L',
    M2 = 'M2',
    M3 = 'M3',
    TABLA = 'TABLA'
}

export interface IMerniAlati {
    id?: number;
    naziv?: string;
    vrsta?: string;
    jedMere?: JedMere;
    namena?: string;
    cena?: number;
    trajanje?: Moment;
    bazdarenje?: Moment;
}

export class MerniAlati implements IMerniAlati {
    constructor(
        public id?: number,
        public naziv?: string,
        public vrsta?: string,
        public jedMere?: JedMere,
        public namena?: string,
        public cena?: number,
        public trajanje?: Moment,
        public bazdarenje?: Moment
    ) {}
}
