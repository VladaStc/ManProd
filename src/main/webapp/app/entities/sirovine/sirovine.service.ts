import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { ISirovine } from 'app/shared/model/sirovine.model';

type EntityResponseType = HttpResponse<ISirovine>;
type EntityArrayResponseType = HttpResponse<ISirovine[]>;

@Injectable({ providedIn: 'root' })
export class SirovineService {
    public resourceUrl = SERVER_API_URL + 'api/sirovines';

    constructor(private http: HttpClient) {}

    create(sirovine: ISirovine): Observable<EntityResponseType> {
        return this.http.post<ISirovine>(this.resourceUrl, sirovine, { observe: 'response' });
    }

    update(sirovine: ISirovine): Observable<EntityResponseType> {
        return this.http.put<ISirovine>(this.resourceUrl, sirovine, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<ISirovine>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<ISirovine[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
