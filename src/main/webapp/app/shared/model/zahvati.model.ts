export interface IZahvati {
    id?: number;
    naziv?: string;
    vremeRada?: number;
    pomocnoVreme?: number;
    cena?: number;
    skica?: string;
}

export class Zahvati implements IZahvati {
    constructor(
        public id?: number,
        public naziv?: string,
        public vremeRada?: number,
        public pomocnoVreme?: number,
        public cena?: number,
        public skica?: string
    ) {}
}
