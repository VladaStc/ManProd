import { IRadnoMesto } from 'app/shared/model//radno-mesto.model';

export interface IRadionica {
    id?: number;
    naziv?: string;
    lokacija?: string;
    povrsina?: number;
    rukovodilac?: string;
    napomena?: string;
    radnoMesto?: IRadnoMesto;
}

export class Radionica implements IRadionica {
    constructor(
        public id?: number,
        public naziv?: string,
        public lokacija?: string,
        public povrsina?: number,
        public rukovodilac?: string,
        public napomena?: string,
        public radnoMesto?: IRadnoMesto
    ) {}
}
