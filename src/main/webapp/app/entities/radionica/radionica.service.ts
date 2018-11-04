import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IRadionica } from 'app/shared/model/radionica.model';

type EntityResponseType = HttpResponse<IRadionica>;
type EntityArrayResponseType = HttpResponse<IRadionica[]>;

@Injectable({ providedIn: 'root' })
export class RadionicaService {
    public resourceUrl = SERVER_API_URL + 'api/radionicas';

    constructor(private http: HttpClient) {}

    create(radionica: IRadionica): Observable<EntityResponseType> {
        return this.http.post<IRadionica>(this.resourceUrl, radionica, { observe: 'response' });
    }

    update(radionica: IRadionica): Observable<EntityResponseType> {
        return this.http.put<IRadionica>(this.resourceUrl, radionica, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IRadionica>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IRadionica[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
