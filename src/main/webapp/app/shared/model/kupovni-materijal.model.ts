export const enum JedMere {
    KOM = 'KOM',
    KG = 'KG',
    L = 'L',
    M2 = 'M2',
    M3 = 'M3',
    TABLA = 'TABLA'
}

export interface IKupovniMaterijal {
    id?: number;
    sifra?: string;
    naziv?: string;
    vrsta?: string;
    jedMere?: JedMere;
    namena?: string;
    nabavnaCena?: number;
    napomena?: string;
}

export class KupovniMaterijal implements IKupovniMaterijal {
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
