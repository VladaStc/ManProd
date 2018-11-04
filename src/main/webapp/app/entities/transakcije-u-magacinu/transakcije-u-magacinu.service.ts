import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { ITransakcijeUMagacinu } from 'app/shared/model/transakcije-u-magacinu.model';

type EntityResponseType = HttpResponse<ITransakcijeUMagacinu>;
type EntityArrayResponseType = HttpResponse<ITransakcijeUMagacinu[]>;

@Injectable({ providedIn: 'root' })
export class TransakcijeUMagacinuService {
    public resourceUrl = SERVER_API_URL + 'api/transakcije-u-magacinus';

    constructor(private http: HttpClient) {}

    create(transakcijeUMagacinu: ITransakcijeUMagacinu): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(transakcijeUMagacinu);
        return this.http
            .post<ITransakcijeUMagacinu>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(transakcijeUMagacinu: ITransakcijeUMagacinu): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(transakcijeUMagacinu);
        return this.http
            .put<ITransakcijeUMagacinu>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<ITransakcijeUMagacinu>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<ITransakcijeUMagacinu[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    protected convertDateFromClient(transakcijeUMagacinu: ITransakcijeUMagacinu): ITransakcijeUMagacinu {
        const copy: ITransakcijeUMagacinu = Object.assign({}, transakcijeUMagacinu, {
            datum: transakcijeUMagacinu.datum != null && transakcijeUMagacinu.datum.isValid() ? transakcijeUMagacinu.datum.toJSON() : null
        });
        return copy;
    }

    protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
        if (res.body) {
            res.body.datum = res.body.datum != null ? moment(res.body.datum) : null;
        }
        return res;
    }

    protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        if (res.body) {
            res.body.forEach((transakcijeUMagacinu: ITransakcijeUMagacinu) => {
                transakcijeUMagacinu.datum = transakcijeUMagacinu.datum != null ? moment(transakcijeUMagacinu.datum) : null;
            });
        }
        return res;
    }
}
