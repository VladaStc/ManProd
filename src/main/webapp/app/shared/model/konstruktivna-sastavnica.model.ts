export interface IKonstruktivnaSastavnica {
    id?: number;
    sifraDela?: string;
    nazivDela?: string;
    kolicina?: number;
}

export class KonstruktivnaSastavnica implements IKonstruktivnaSastavnica {
    constructor(public id?: number, public sifraDela?: string, public nazivDela?: string, public kolicina?: number) {}
}
