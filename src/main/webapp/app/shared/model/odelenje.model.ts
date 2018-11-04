import { IRadionica } from 'app/shared/model//radionica.model';
import { IObradniSistem } from 'app/shared/model//obradni-sistem.model';

export interface IOdelenje {
    id?: number;
    naziv?: string;
    lokacija?: string;
    povrsina?: number;
    rukovodilac?: string;
    napomena?: string;
    radionica?: IRadionica;
    obradniSistem?: IObradniSistem;
}

export class Odelenje implements IOdelenje {
    constructor(
        public id?: number,
        public naziv?: string,
        public lokacija?: string,
        public povrsina?: number,
        public rukovodilac?: string,
        public napomena?: string,
        public radionica?: IRadionica,
        public obradniSistem?: IObradniSistem
    ) {}
}
