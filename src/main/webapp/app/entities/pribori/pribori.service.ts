import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IPribori } from 'app/shared/model/pribori.model';

type EntityResponseType = HttpResponse<IPribori>;
type EntityArrayResponseType = HttpResponse<IPribori[]>;

@Injectable({ providedIn: 'root' })
export class PriboriService {
    public resourceUrl = SERVER_API_URL + 'api/priboris';

    constructor(private http: HttpClient) {}

    create(pribori: IPribori): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(pribori);
        return this.http
            .post<IPribori>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(pribori: IPribori): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(pribori);
        return this.http
            .put<IPribori>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IPribori>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IPribori[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    protected convertDateFromClient(pribori: IPribori): IPribori {
        const copy: IPribori = Object.assign({}, pribori, {
            trajanje: pribori.trajanje != null && pribori.trajanje.isValid() ? pribori.trajanje.toJSON() : null
        });
        return copy;
    }

    protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
        if (res.body) {
            res.body.trajanje = res.body.trajanje != null ? moment(res.body.trajanje) : null;
        }
        return res;
    }

    protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        if (res.body) {
            res.body.forEach((pribori: IPribori) => {
                pribori.trajanje = pribori.trajanje != null ? moment(pribori.trajanje) : null;
            });
        }
        return res;
    }
}
