/* tslint:disable max-line-length */
import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { of } from 'rxjs';
import { take, map } from 'rxjs/operators';
import { ProizvodniPogoniService } from 'app/entities/proizvodni-pogoni/proizvodni-pogoni.service';
import { IProizvodniPogoni, ProizvodniPogoni } from 'app/shared/model/proizvodni-pogoni.model';

describe('Service Tests', () => {
    describe('ProizvodniPogoni Service', () => {
        let injector: TestBed;
        let service: ProizvodniPogoniService;
        let httpMock: HttpTestingController;
        let elemDefault: IProizvodniPogoni;
        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [HttpClientTestingModule]
            });
            injector = getTestBed();
            service = injector.get(ProizvodniPogoniService);
            httpMock = injector.get(HttpTestingController);

            elemDefault = new ProizvodniPogoni(0, 'AAAAAAA', 'AAAAAAA', 0, 'AAAAAAA', 'AAAAAAA');
        });

        describe('Service methods', async () => {
            it('should find an element', async () => {
                const returnedFromService = Object.assign({}, elemDefault);
                service
                    .find(123)
                    .pipe(take(1))
                    .subscribe(resp => expect(resp).toMatchObject({ body: elemDefault }));

                const req = httpMock.expectOne({ method: 'GET' });
                req.flush(JSON.stringify(returnedFromService));
            });

            it('should create a ProizvodniPogoni', async () => {
                const returnedFromService = Object.assign(
                    {
                        id: 0
                    },
                    elemDefault
                );
                const expected = Object.assign({}, returnedFromService);
                service
                    .create(new ProizvodniPogoni(null))
                    .pipe(take(1))
                    .subscribe(resp => expect(resp).toMatchObject({ body: expected }));
                const req = httpMock.expectOne({ method: 'POST' });
                req.flush(JSON.stringify(returnedFromService));
            });

            it('should update a ProizvodniPogoni', async () => {
                const returnedFromService = Object.assign(
                    {
                        naziv: 'BBBBBB',
                        lokacija: 'BBBBBB',
                        povrsina: 1,
                        rukovodilac: 'BBBBBB',
                        napomena: 'BBBBBB'
                    },
                    elemDefault
                );

                const expected = Object.assign({}, returnedFromService);
                service
                    .update(expected)
                    .pipe(take(1))
                    .subscribe(resp => expect(resp).toMatchObject({ body: expected }));
                const req = httpMock.expectOne({ method: 'PUT' });
                req.flush(JSON.stringify(returnedFromService));
            });

            it('should return a list of ProizvodniPogoni', async () => {
                const returnedFromService = Object.assign(
                    {
                        naziv: 'BBBBBB',
                        lokacija: 'BBBBBB',
                        povrsina: 1,
                        rukovodilac: 'BBBBBB',
                        napomena: 'BBBBBB'
                    },
                    elemDefault
                );
                const expected = Object.assign({}, returnedFromService);
                service
                    .query(expected)
                    .pipe(
                        take(1),
                        map(resp => resp.body)
                    )
                    .subscribe(body => expect(body).toContainEqual(expected));
                const req = httpMock.expectOne({ method: 'GET' });
                req.flush(JSON.stringify([returnedFromService]));
                httpMock.verify();
            });

            it('should delete a ProizvodniPogoni', async () => {
                const rxPromise = service.delete(123).subscribe(resp => expect(resp.ok));

                const req = httpMock.expectOne({ method: 'DELETE' });
                req.flush({ status: 200 });
            });
        });

        afterEach(() => {
            httpMock.verify();
        });
    });
});
