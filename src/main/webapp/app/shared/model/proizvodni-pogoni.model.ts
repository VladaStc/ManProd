import { IProizvodneLinije } from 'app/shared/model//proizvodne-linije.model';
import { IObradniSistem } from 'app/shared/model//obradni-sistem.model';

export interface IProizvodniPogoni {
    id?: number;
    naziv?: string;
    lokacija?: string;
    povrsina?: number;
    rukovodilac?: string;
    napomena?: string;
    proizvodneLinije?: IProizvodneLinije;
    obradniSistem?: IObradniSistem;
}

export class ProizvodniPogoni implements IProizvodniPogoni {
    constructor(
        public id?: number,
        public naziv?: string,
        public lokacija?: string,
        public povrsina?: number,
        public rukovodilac?: string,
        public napomena?: string,
        public proizvodneLinije?: IProizvodneLinije,
        public obradniSistem?: IObradniSistem
    ) {}
}
