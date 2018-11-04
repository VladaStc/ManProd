import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IPomocniMaterijal } from 'app/shared/model/pomocni-materijal.model';

type EntityResponseType = HttpResponse<IPomocniMaterijal>;
type EntityArrayResponseType = HttpResponse<IPomocniMaterijal[]>;

@Injectable({ providedIn: 'root' })
export class PomocniMaterijalService {
    public resourceUrl = SERVER_API_URL + 'api/pomocni-materijals';

    constructor(private http: HttpClient) {}

    create(pomocniMaterijal: IPomocniMaterijal): Observable<EntityResponseType> {
        return this.http.post<IPomocniMaterijal>(this.resourceUrl, pomocniMaterijal, { observe: 'response' });
    }

    update(pomocniMaterijal: IPomocniMaterijal): Observable<EntityResponseType> {
        return this.http.put<IPomocniMaterijal>(this.resourceUrl, pomocniMaterijal, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IPomocniMaterijal>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IPomocniMaterijal[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
