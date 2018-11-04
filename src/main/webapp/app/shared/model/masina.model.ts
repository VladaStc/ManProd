import { IObradniSistem } from 'app/shared/model//obradni-sistem.model';
import { IOperacijeURadnomNalogu } from 'app/shared/model//operacije-u-radnom-nalogu.model';
import { IOperacije } from 'app/shared/model//operacije.model';
import { IZahvati } from 'app/shared/model//zahvati.model';

export interface IMasina {
    id?: number;
    naziv?: string;
    lokacija?: string;
    struktura?: string;
    rukovodilac?: string;
    napomena?: string;
    obradniSistem?: IObradniSistem;
    operacijeURadnomNalogu?: IOperacijeURadnomNalogu;
    operacije?: IOperacije;
    zahvati?: IZahvati;
}

export class Masina implements IMasina {
    constructor(
        public id?: number,
        public naziv?: string,
        public lokacija?: string,
        public struktura?: string,
        public rukovodilac?: string,
        public napomena?: string,
        public obradniSistem?: IObradniSistem,
        public operacijeURadnomNalogu?: IOperacijeURadnomNalogu,
        public operacije?: IOperacije,
        public zahvati?: IZahvati
    ) {}
}
