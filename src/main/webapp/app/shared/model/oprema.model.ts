import { IObradniSistem } from 'app/shared/model//obradni-sistem.model';

export interface IOprema {
    id?: number;
    naziv?: string;
    lokacija?: string;
    struktura?: string;
    rukovodilac?: string;
    napomena?: string;
    obradniSistem?: IObradniSistem;
}

export class Oprema implements IOprema {
    constructor(
        public id?: number,
        public naziv?: string,
        public lokacija?: string,
        public struktura?: string,
        public rukovodilac?: string,
        public napomena?: string,
        public obradniSistem?: IObradniSistem
    ) {}
}
