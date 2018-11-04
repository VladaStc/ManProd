/* tslint:disable max-line-length */
import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { of } from 'rxjs';
import { take, map } from 'rxjs/operators';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { PriboriService } from 'app/entities/pribori/pribori.service';
import { IPribori, Pribori, JedMere } from 'app/shared/model/pribori.model';

describe('Service Tests', () => {
    describe('Pribori Service', () => {
        let injector: TestBed;
        let service: PriboriService;
        let httpMock: HttpTestingController;
        let elemDefault: IPribori;
        let currentDate: moment.Moment;
        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [HttpClientTestingModule]
            });
            injector = getTestBed();
            service = injector.get(PriboriService);
            httpMock = injector.get(HttpTestingController);
            currentDate = moment();

            elemDefault = new Pribori(0, 'AAAAAAA', 'AAAAAAA', JedMere.KOM, 'AAAAAAA', 0, currentDate);
        });

        describe('Service methods', async () => {
            it('should find an element', async () => {
                const returnedFromService = Object.assign(
                    {
                        trajanje: currentDate.format(DATE_TIME_FORMAT)
                    },
                    elemDefault
                );
                service
                    .find(123)
                    .pipe(take(1))
                    .subscribe(resp => expect(resp).toMatchObject({ body: elemDefault }));

                const req = httpMock.expectOne({ method: 'GET' });
                req.flush(JSON.stringify(returnedFromService));
            });

            it('should create a Pribori', async () => {
                const returnedFromService = Object.assign(
                    {
                        id: 0,
                        trajanje: currentDate.format(DATE_TIME_FORMAT)
                    },
                    elemDefault
                );
                const expected = Object.assign(
                    {
                        trajanje: currentDate
                    },
                    returnedFromService
                );
                service
                    .create(new Pribori(null))
                    .pipe(take(1))
                    .subscribe(resp => expect(resp).toMatchObject({ body: expected }));
                const req = httpMock.expectOne({ method: 'POST' });
                req.flush(JSON.stringify(returnedFromService));
            });

            it('should update a Pribori', async () => {
                const returnedFromService = Object.assign(
                    {
                        naziv: 'BBBBBB',
                        vrsta: 'BBBBBB',
                        jedMere: 'BBBBBB',
                        namena: 'BBBBBB',
                        cena: 1,
                        trajanje: currentDate.format(DATE_TIME_FORMAT)
                    },
                    elemDefault
                );

                const expected = Object.assign(
                    {
                        trajanje: currentDate
                    },
                    returnedFromService
                );
                service
                    .update(expected)
                    .pipe(take(1))
                    .subscribe(resp => expect(resp).toMatchObject({ body: expected }));
                const req = httpMock.expectOne({ method: 'PUT' });
                req.flush(JSON.stringify(returnedFromService));
            });

            it('should return a list of Pribori', async () => {
                const returnedFromService = Object.assign(
                    {
                        naziv: 'BBBBBB',
                        vrsta: 'BBBBBB',
                        jedMere: 'BBBBBB',
                        namena: 'BBBBBB',
                        cena: 1,
                        trajanje: currentDate.format(DATE_TIME_FORMAT)
                    },
                    elemDefault
                );
                const expected = Object.assign(
                    {
                        trajanje: currentDate
                    },
                    returnedFromService
                );
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

            it('should delete a Pribori', async () => {
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
