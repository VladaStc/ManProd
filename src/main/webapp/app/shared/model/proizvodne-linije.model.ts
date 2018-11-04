import { IOdelenje } from 'app/shared/model//odelenje.model';
import { IObradniSistem } from 'app/shared/model//obradni-sistem.model';

export interface IProizvodneLinije {
    id?: number;
    naziv?: string;
    lokacija?: string;
    povrsina?: number;
    rukovodilac?: string;
    napomena?: string;
    odelenje?: IOdelenje;
    obradniSistem?: IObradniSistem;
}

export class ProizvodneLinije implements IProizvodneLinije {
    constructor(
        public id?: number,
        public naziv?: string,
        public lokacija?: string,
        public povrsina?: number,
        public rukovodilac?: string,
        public napomena?: string,
        public odelenje?: IOdelenje,
        public obradniSistem?: IObradniSistem
    ) {}
}
