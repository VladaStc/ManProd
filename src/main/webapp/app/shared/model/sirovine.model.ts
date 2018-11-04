import { IOperacijeURadnomNalogu } from 'app/shared/model//operacije-u-radnom-nalogu.model';
import { IOperacije } from 'app/shared/model//operacije.model';
import { IZahvati } from 'app/shared/model//zahvati.model';

export const enum JedMere {
    KOM = 'KOM',
    KG = 'KG',
    L = 'L',
    M2 = 'M2',
    M3 = 'M3',
    TABLA = 'TABLA'
}

export interface ISirovine {
    id?: number;
    sifra?: string;
    naziv?: string;
    vrsta?: string;
    jedMere?: JedMere;
    namena?: string;
    nabavnaCena?: number;
    napomena?: string;
    operacijeURadnomNalogu?: IOperacijeURadnomNalogu;
    operacije?: IOperacije;
    zahvati?: IZahvati;
}

export class Sirovine implements ISirovine {
    constructor(
        public id?: number,
        public sifra?: string,
        public naziv?: string,
        public vrsta?: string,
        public jedMere?: JedMere,
        public namena?: string,
        public nabavnaCena?: number,
        public napomena?: string,
        public operacijeURadnomNalogu?: IOperacijeURadnomNalogu,
        public operacije?: IOperacije,
        public zahvati?: IZahvati
    ) {}
}
