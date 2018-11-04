import { IOperacijeURadnomNalogu } from 'app/shared/model//operacije-u-radnom-nalogu.model';
import { IOperacije } from 'app/shared/model//operacije.model';
import { IZahvati } from 'app/shared/model//zahvati.model';

export const enum TipRadnika {
    SOPSTVENI = 'SOPSTVENI',
    ANGAZOVANI = 'ANGAZOVANI',
    POMOCNI = 'POMOCNI'
}

export interface IRadnici {
    id?: number;
    ime?: string;
    prezime?: string;
    jmbg?: number;
    kvalifikacija?: string;
    koeficijent?: number;
    sertifikat?: string;
    pol?: string;
    napomena?: string;
    tipRadnika?: TipRadnika;
    operacijeURadnomNalogu?: IOperacijeURadnomNalogu;
    operacije?: IOperacije;
    zahvati?: IZahvati;
}

export class Radnici implements IRadnici {
    constructor(
        public id?: number,
        public ime?: string,
        public prezime?: string,
        public jmbg?: number,
        public kvalifikacija?: string,
        public koeficijent?: number,
        public sertifikat?: string,
        public pol?: string,
        public napomena?: string,
        public tipRadnika?: TipRadnika,
        public operacijeURadnomNalogu?: IOperacijeURadnomNalogu,
        public operacije?: IOperacije,
        public zahvati?: IZahvati
    ) {}
}
