/* tslint:disable max-line-length */
import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { of } from 'rxjs';
import { take, map } from 'rxjs/operators';
import { FinalniProizvodService } from 'app/entities/finalni-proizvod/finalni-proizvod.service';
import { IFinalniProizvod, FinalniProizvod, JedMere } from 'app/shared/model/finalni-proizvod.model';

describe('Service Tests', () => {
    describe('FinalniProizvod Service', () => {
        let injector: TestBed;
        let service: FinalniProizvodService;
        let httpMock: HttpTestingController;
        let elemDefault: IFinalniProizvod;
        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [HttpClientTestingModule]
            });
            injector = getTestBed();
            service = injector.get(FinalniProizvodService);
            httpMock = injector.get(HttpTestingController);

            elemDefault = new FinalniProizvod(0, 'AAAAAAA', 'AAAAAAA', JedMere.KOM, 'AAAAAAA', 0, 'AAAAAAA');
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

            it('should create a FinalniProizvod', async () => {
                const returnedFromService = Object.assign(
                    {
                        id: 0
                    },
                    elemDefault
                );
                const expected = Object.assign({}, returnedFromService);
                service
                    .create(new FinalniProizvod(null))
                    .pipe(take(1))
                    .subscribe(resp => expect(resp).toMatchObject({ body: expected }));
                const req = httpMock.expectOne({ method: 'POST' });
                req.flush(JSON.stringify(returnedFromService));
            });

            it('should update a FinalniProizvod', async () => {
                const returnedFromService = Object.assign(
                    {
                        naziv: 'BBBBBB',
                        vrsta: 'BBBBBB',
                        jedMere: 'BBBBBB',
                        namena: 'BBBBBB',
                        cena: 1,
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

            it('should return a list of FinalniProizvod', async () => {
                const returnedFromService = Object.assign(
                    {
                        naziv: 'BBBBBB',
                        vrsta: 'BBBBBB',
                        jedMere: 'BBBBBB',
                        namena: 'BBBBBB',
                        cena: 1,
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

            it('should delete a FinalniProizvod', async () => {
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
