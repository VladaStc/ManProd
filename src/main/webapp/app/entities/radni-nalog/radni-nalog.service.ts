import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IRadniNalog } from 'app/shared/model/radni-nalog.model';

type EntityResponseType = HttpResponse<IRadniNalog>;
type EntityArrayResponseType = HttpResponse<IRadniNalog[]>;

@Injectable({ providedIn: 'root' })
export class RadniNalogService {
    public resourceUrl = SERVER_API_URL + 'api/radni-nalogs';

    constructor(private http: HttpClient) {}

    create(radniNalog: IRadniNalog): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(radniNalog);
        return this.http
            .post<IRadniNalog>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(radniNalog: IRadniNalog): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(radniNalog);
        return this.http
            .put<IRadniNalog>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IRadniNalog>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IRadniNalog[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    protected convertDateFromClient(radniNalog: IRadniNalog): IRadniNalog {
        const copy: IRadniNalog = Object.assign({}, radniNalog, {
            datumIVremeOtvaranja:
                radniNalog.datumIVremeOtvaranja != null && radniNalog.datumIVremeOtvaranja.isValid()
                    ? radniNalog.datumIVremeOtvaranja.toJSON()
                    : null,
            datumIVremeZatvaranja:
                radniNalog.datumIVremeZatvaranja != null && radniNalog.datumIVremeZatvaranja.isValid()
                    ? radniNalog.datumIVremeZatvaranja.toJSON()
                    : null
        });
        return copy;
    }

    protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
        if (res.body) {
            res.body.datumIVremeOtvaranja = res.body.datumIVremeOtvaranja != null ? moment(res.body.datumIVremeOtvaranja) : null;
            res.body.datumIVremeZatvaranja = res.body.datumIVremeZatvaranja != null ? moment(res.body.datumIVremeZatvaranja) : null;
        }
        return res;
    }

    protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        if (res.body) {
            res.body.forEach((radniNalog: IRadniNalog) => {
                radniNalog.datumIVremeOtvaranja = radniNalog.datumIVremeOtvaranja != null ? moment(radniNalog.datumIVremeOtvaranja) : null;
                radniNalog.datumIVremeZatvaranja =
                    radniNalog.datumIVremeZatvaranja != null ? moment(radniNalog.datumIVremeZatvaranja) : null;
            });
        }
        return res;
    }
}
