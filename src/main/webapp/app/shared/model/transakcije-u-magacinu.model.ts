import { Moment } from 'moment';

export const enum TipMagacina {
    MAGACIN_SIROVINA = 'MAGACIN_SIROVINA',
    MAGACIN_GOTOVIH_PROIZVODA = 'MAGACIN_GOTOVIH_PROIZVODA',
    MAGACIN_PROIZVODNJE_U_TOKU = 'MAGACIN_PROIZVODNJE_U_TOKU'
}

export interface ITransakcijeUMagacinu {
    id?: number;
    ulaz?: number;
    izlaz?: number;
    stanje?: number;
    napomena?: string;
    datum?: Moment;
    tipMagacina?: TipMagacina;
}

export class TransakcijeUMagacinu implements ITransakcijeUMagacinu {
    constructor(
        public id?: number,
        public ulaz?: number,
        public izlaz?: number,
        public stanje?: number,
        public napomena?: string,
        public datum?: Moment,
        public tipMagacina?: TipMagacina
    ) {}
}
