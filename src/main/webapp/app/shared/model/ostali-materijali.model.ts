export const enum JedMere {
    KOM = 'KOM',
    KG = 'KG',
    L = 'L',
    M2 = 'M2',
    M3 = 'M3',
    TABLA = 'TABLA'
}

export interface IOstaliMaterijali {
    id?: number;
    sifra?: string;
    naziv?: string;
    vrsta?: string;
    jedMere?: JedMere;
    namena?: string;
    nabavnaCena?: number;
    napomena?: string;
}

export class OstaliMaterijali implements IOstaliMaterijali {
    constructor(
        public id?: number,
        public sifra?: string,
        public naziv?: string,
        public vrsta?: string,
        public jedMere?: JedMere,
        public namena?: string,
        public nabavnaCena?: number,
        public napomena?: string
    ) {}
}
