import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IOstaliMaterijali } from 'app/shared/model/ostali-materijali.model';

type EntityResponseType = HttpResponse<IOstaliMaterijali>;
type EntityArrayResponseType = HttpResponse<IOstaliMaterijali[]>;

@Injectable({ providedIn: 'root' })
export class OstaliMaterijaliService {
    public resourceUrl = SERVER_API_URL + 'api/ostali-materijalis';

    constructor(private http: HttpClient) {}

    create(ostaliMaterijali: IOstaliMaterijali): Observable<EntityResponseType> {
        return this.http.post<IOstaliMaterijali>(this.resourceUrl, ostaliMaterijali, { observe: 'response' });
    }

    update(ostaliMaterijali: IOstaliMaterijali): Observable<EntityResponseType> {
        return this.http.put<IOstaliMaterijali>(this.resourceUrl, ostaliMaterijali, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IOstaliMaterijali>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IOstaliMaterijali[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
