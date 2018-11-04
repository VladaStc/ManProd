import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IMerniAlati } from 'app/shared/model/merni-alati.model';

type EntityResponseType = HttpResponse<IMerniAlati>;
type EntityArrayResponseType = HttpResponse<IMerniAlati[]>;

@Injectable({ providedIn: 'root' })
export class MerniAlatiService {
    public resourceUrl = SERVER_API_URL + 'api/merni-alatis';

    constructor(private http: HttpClient) {}

    create(merniAlati: IMerniAlati): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(merniAlati);
        return this.http
            .post<IMerniAlati>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(merniAlati: IMerniAlati): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(merniAlati);
        return this.http
            .put<IMerniAlati>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IMerniAlati>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IMerniAlati[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    protected convertDateFromClient(merniAlati: IMerniAlati): IMerniAlati {
        const copy: IMerniAlati = Object.assign({}, merniAlati, {
            trajanje: merniAlati.trajanje != null && merniAlati.trajanje.isValid() ? merniAlati.trajanje.toJSON() : null,
            bazdarenje: merniAlati.bazdarenje != null && merniAlati.bazdarenje.isValid() ? merniAlati.bazdarenje.toJSON() : null
        });
        return copy;
    }

    protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
        if (res.body) {
            res.body.trajanje = res.body.trajanje != null ? moment(res.body.trajanje) : null;
            res.body.bazdarenje = res.body.bazdarenje != null ? moment(res.body.bazdarenje) : null;
        }
        return res;
    }

    protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        if (res.body) {
            res.body.forEach((merniAlati: IMerniAlati) => {
                merniAlati.trajanje = merniAlati.trajanje != null ? moment(merniAlati.trajanje) : null;
                merniAlati.bazdarenje = merniAlati.bazdarenje != null ? moment(merniAlati.bazdarenje) : null;
            });
        }
        return res;
    }
}
