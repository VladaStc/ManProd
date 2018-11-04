import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IKomponenete } from 'app/shared/model/komponenete.model';

type EntityResponseType = HttpResponse<IKomponenete>;
type EntityArrayResponseType = HttpResponse<IKomponenete[]>;

@Injectable({ providedIn: 'root' })
export class KomponeneteService {
    public resourceUrl = SERVER_API_URL + 'api/komponenetes';

    constructor(private http: HttpClient) {}

    create(komponenete: IKomponenete): Observable<EntityResponseType> {
        return this.http.post<IKomponenete>(this.resourceUrl, komponenete, { observe: 'response' });
    }

    update(komponenete: IKomponenete): Observable<EntityResponseType> {
        return this.http.put<IKomponenete>(this.resourceUrl, komponenete, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IKomponenete>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IKomponenete[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
