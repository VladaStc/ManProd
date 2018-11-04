import { IRadnici } from 'app/shared/model//radnici.model';
import { IOperacijeURadnomNalogu } from 'app/shared/model//operacije-u-radnom-nalogu.model';
import { IOperacije } from 'app/shared/model//operacije.model';
import { IZahvati } from 'app/shared/model//zahvati.model';

export interface IRadnoMesto {
    id?: number;
    naziv?: string;
    lokacija?: string;
    povrsina?: number;
    rukovodilac?: string;
    normaSat?: number;
    radnici?: IRadnici;
    operacijeURadnomNalogu?: IOperacijeURadnomNalogu;
    operacije?: IOperacije;
    zahvati?: IZahvati;
}

export class RadnoMesto implements IRadnoMesto {
    constructor(
        public id?: number,
        public naziv?: string,
        public lokacija?: string,
        public povrsina?: number,
        public rukovodilac?: string,
        public normaSat?: number,
        public radnici?: IRadnici,
        public operacijeURadnomNalogu?: IOperacijeURadnomNalogu,
        public operacije?: IOperacije,
        public zahvati?: IZahvati
    ) {}
}
