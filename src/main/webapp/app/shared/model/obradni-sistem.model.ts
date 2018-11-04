import { IKonstruktivnaSastavnica } from 'app/shared/model//konstruktivna-sastavnica.model';
import { IMasina } from 'app/shared/model//masina.model';
import { IOprema } from 'app/shared/model//oprema.model';

export interface IObradniSistem {
    id?: number;
    naziv?: string;
    lokacija?: string;
    struktura?: string;
    rukovodilac?: string;
    napomena?: string;
    konstruktivnaSastavnica?: IKonstruktivnaSastavnica;
    masinas?: IMasina[];
    opremas?: IOprema[];
}

export class ObradniSistem implements IObradniSistem {
    constructor(
        public id?: number,
        public naziv?: string,
        public lokacija?: string,
        public struktura?: string,
        public rukovodilac?: string,
        public napomena?: string,
        public konstruktivnaSastavnica?: IKonstruktivnaSastavnica,
        public masinas?: IMasina[],
        public opremas?: IOprema[]
    ) {}
}
