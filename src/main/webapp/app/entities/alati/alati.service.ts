import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IAlati } from 'app/shared/model/alati.model';

type EntityResponseType = HttpResponse<IAlati>;
type EntityArrayResponseType = HttpResponse<IAlati[]>;

@Injectable({ providedIn: 'root' })
export class AlatiService {
    public resourceUrl = SERVER_API_URL + 'api/alatis';

    constructor(private http: HttpClient) {}

    create(alati: IAlati): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(alati);
        return this.http
            .post<IAlati>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(alati: IAlati): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(alati);
        return this.http
            .put<IAlati>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IAlati>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IAlati[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    protected convertDateFromClient(alati: IAlati): IAlati {
        const copy: IAlati = Object.assign({}, alati, {
            trajanje: alati.trajanje != null && alati.trajanje.isValid() ? alati.trajanje.toJSON() : null
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
            res.body.forEach((alati: IAlati) => {
                alati.trajanje = alati.trajanje != null ? moment(alati.trajanje) : null;
            });
        }
        return res;
    }
}
