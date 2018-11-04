import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IOperacijeURadnomNalogu } from 'app/shared/model/operacije-u-radnom-nalogu.model';

type EntityResponseType = HttpResponse<IOperacijeURadnomNalogu>;
type EntityArrayResponseType = HttpResponse<IOperacijeURadnomNalogu[]>;

@Injectable({ providedIn: 'root' })
export class OperacijeURadnomNaloguService {
    public resourceUrl = SERVER_API_URL + 'api/operacije-u-radnom-nalogus';

    constructor(private http: HttpClient) {}

    create(operacijeURadnomNalogu: IOperacijeURadnomNalogu): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(operacijeURadnomNalogu);
        return this.http
            .post<IOperacijeURadnomNalogu>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(operacijeURadnomNalogu: IOperacijeURadnomNalogu): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(operacijeURadnomNalogu);
        return this.http
            .put<IOperacijeURadnomNalogu>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IOperacijeURadnomNalogu>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IOperacijeURadnomNalogu[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    protected convertDateFromClient(operacijeURadnomNalogu: IOperacijeURadnomNalogu): IOperacijeURadnomNalogu {
        const copy: IOperacijeURadnomNalogu = Object.assign({}, operacijeURadnomNalogu, {
            datumIVremePocetka:
                operacijeURadnomNalogu.datumIVremePocetka != null && operacijeURadnomNalogu.datumIVremePocetka.isValid()
                    ? operacijeURadnomNalogu.datumIVremePocetka.toJSON()
                    : null,
            datumIVremeZavrsetka:
                operacijeURadnomNalogu.datumIVremeZavrsetka != null && operacijeURadnomNalogu.datumIVremeZavrsetka.isValid()
                    ? operacijeURadnomNalogu.datumIVremeZavrsetka.toJSON()
                    : null
        });
        return copy;
    }

    protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
        if (res.body) {
            res.body.datumIVremePocetka = res.body.datumIVremePocetka != null ? moment(res.body.datumIVremePocetka) : null;
            res.body.datumIVremeZavrsetka = res.body.datumIVremeZavrsetka != null ? moment(res.body.datumIVremeZavrsetka) : null;
        }
        return res;
    }

    protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        if (res.body) {
            res.body.forEach((operacijeURadnomNalogu: IOperacijeURadnomNalogu) => {
                operacijeURadnomNalogu.datumIVremePocetka =
                    operacijeURadnomNalogu.datumIVremePocetka != null ? moment(operacijeURadnomNalogu.datumIVremePocetka) : null;
                operacijeURadnomNalogu.datumIVremeZavrsetka =
                    operacijeURadnomNalogu.datumIVremeZavrsetka != null ? moment(operacijeURadnomNalogu.datumIVremeZavrsetka) : null;
            });
        }
        return res;
    }
}
