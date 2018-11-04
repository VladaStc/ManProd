import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IRadnici } from 'app/shared/model/radnici.model';

type EntityResponseType = HttpResponse<IRadnici>;
type EntityArrayResponseType = HttpResponse<IRadnici[]>;

@Injectable({ providedIn: 'root' })
export class RadniciService {
    public resourceUrl = SERVER_API_URL + 'api/radnicis';

    constructor(private http: HttpClient) {}

    create(radnici: IRadnici): Observable<EntityResponseType> {
        return this.http.post<IRadnici>(this.resourceUrl, radnici, { observe: 'response' });
    }

    update(radnici: IRadnici): Observable<EntityResponseType> {
        return this.http.put<IRadnici>(this.resourceUrl, radnici, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IRadnici>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IRadnici[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
