import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IRadnoMesto } from 'app/shared/model/radno-mesto.model';

type EntityResponseType = HttpResponse<IRadnoMesto>;
type EntityArrayResponseType = HttpResponse<IRadnoMesto[]>;

@Injectable({ providedIn: 'root' })
export class RadnoMestoService {
    public resourceUrl = SERVER_API_URL + 'api/radno-mestos';

    constructor(private http: HttpClient) {}

    create(radnoMesto: IRadnoMesto): Observable<EntityResponseType> {
        return this.http.post<IRadnoMesto>(this.resourceUrl, radnoMesto, { observe: 'response' });
    }

    update(radnoMesto: IRadnoMesto): Observable<EntityResponseType> {
        return this.http.put<IRadnoMesto>(this.resourceUrl, radnoMesto, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IRadnoMesto>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IRadnoMesto[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
