import { IZahvati } from 'app/shared/model//zahvati.model';

export const enum JedMere {
    KOM = 'KOM',
    KG = 'KG',
    L = 'L',
    M2 = 'M2',
    M3 = 'M3',
    TABLA = 'TABLA'
}

export interface IPomocniMaterijal {
    id?: number;
    sifra?: string;
    naziv?: string;
    vrsta?: string;
    jedMere?: JedMere;
    namena?: string;
    nabavnaCena?: number;
    napomena?: string;
    zahvati?: IZahvati;
}

export class PomocniMaterijal implements IPomocniMaterijal {
    constructor(
        public id?: number,
        public sifra?: string,
        public naziv?: string,
        public vrsta?: string,
        public jedMere?: JedMere,
        public namena?: string,
        public nabavnaCena?: number,
        public napomena?: string,
        public zahvati?: IZahvati
    ) {}
}
