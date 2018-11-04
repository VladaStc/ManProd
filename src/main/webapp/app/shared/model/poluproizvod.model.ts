export const enum JedMere {
    KOM = 'KOM',
    KG = 'KG',
    L = 'L',
    M2 = 'M2',
    M3 = 'M3',
    TABLA = 'TABLA'
}

export interface IPoluproizvod {
    id?: number;
    naziv?: string;
    vrsta?: string;
    jedMere?: JedMere;
    namena?: string;
    cena?: number;
    napomena?: string;
}

export class Poluproizvod implements IPoluproizvod {
    constructor(
        public id?: number,
        public naziv?: string,
        public vrsta?: string,
        public jedMere?: JedMere,
        public namena?: string,
        public cena?: number,
        public napomena?: string
    ) {}
}
