export const enum TipMagacina {
    MAGACIN_SIROVINA = 'MAGACIN_SIROVINA',
    MAGACIN_GOTOVIH_PROIZVODA = 'MAGACIN_GOTOVIH_PROIZVODA',
    MAGACIN_PROIZVODNJE_U_TOKU = 'MAGACIN_PROIZVODNJE_U_TOKU'
}

export interface IStavkeUMagacinu {
    id?: number;
    stanje?: number;
    tipMagacina?: TipMagacina;
}

export class StavkeUMagacinu implements IStavkeUMagacinu {
    constructor(public id?: number, public stanje?: number, public tipMagacina?: TipMagacina) {}
}
