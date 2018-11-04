import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IOperacije } from 'app/shared/model/operacije.model';

type EntityResponseType = HttpResponse<IOperacije>;
type EntityArrayResponseType = HttpResponse<IOperacije[]>;

@Injectable({ providedIn: 'root' })
export class OperacijeService {
    public resourceUrl = SERVER_API_URL + 'api/operacijes';

    constructor(private http: HttpClient) {}

    create(operacije: IOperacije): Observable<EntityResponseType> {
        return this.http.post<IOperacije>(this.resourceUrl, operacije, { observe: 'response' });
    }

    update(operacije: IOperacije): Observable<EntityResponseType> {
        return this.http.put<IOperacije>(this.resourceUrl, operacije, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IOperacije>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IOperacije[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
