import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IPotrosniMaterijal } from 'app/shared/model/potrosni-materijal.model';

type EntityResponseType = HttpResponse<IPotrosniMaterijal>;
type EntityArrayResponseType = HttpResponse<IPotrosniMaterijal[]>;

@Injectable({ providedIn: 'root' })
export class PotrosniMaterijalService {
    public resourceUrl = SERVER_API_URL + 'api/potrosni-materijals';

    constructor(private http: HttpClient) {}

    create(potrosniMaterijal: IPotrosniMaterijal): Observable<EntityResponseType> {
        return this.http.post<IPotrosniMaterijal>(this.resourceUrl, potrosniMaterijal, { observe: 'response' });
    }

    update(potrosniMaterijal: IPotrosniMaterijal): Observable<EntityResponseType> {
        return this.http.put<IPotrosniMaterijal>(this.resourceUrl, potrosniMaterijal, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IPotrosniMaterijal>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IPotrosniMaterijal[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
