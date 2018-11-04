/* tslint:disable max-line-length */
import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { of } from 'rxjs';
import { take, map } from 'rxjs/operators';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { RadniNalogService } from 'app/entities/radni-nalog/radni-nalog.service';
import { IRadniNalog, RadniNalog, Status } from 'app/shared/model/radni-nalog.model';

describe('Service Tests', () => {
    describe('RadniNalog Service', () => {
        let injector: TestBed;
        let service: RadniNalogService;
        let httpMock: HttpTestingController;
        let elemDefault: IRadniNalog;
        let currentDate: moment.Moment;
        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [HttpClientTestingModule]
            });
            injector = getTestBed();
            service = injector.get(RadniNalogService);
            httpMock = injector.get(HttpTestingController);
            currentDate = moment();

            elemDefault = new RadniNalog(0, 'AAAAAAA', currentDate, currentDate, Status.OTVOREN, 'AAAAAAA', 0, 0);
        });

        describe('Service methods', async () => {
            it('should find an element', async () => {
                const returnedFromService = Object.assign(
                    {
                        datumIVremeOtvaranja: currentDate.format(DATE_TIME_FORMAT),
                        datumIVremeZatvaranja: currentDate.format(DATE_TIME_FORMAT)
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

            it('should create a RadniNalog', async () => {
                const returnedFromService = Object.assign(
                    {
                        id: 0,
                        datumIVremeOtvaranja: currentDate.format(DATE_TIME_FORMAT),
                        datumIVremeZatvaranja: currentDate.format(DATE_TIME_FORMAT)
                    },
                    elemDefault
                );
                const expected = Object.assign(
                    {
                        datumIVremeOtvaranja: currentDate,
                        datumIVremeZatvaranja: currentDate
                    },
                    returnedFromService
                );
                service
                    .create(new RadniNalog(null))
                    .pipe(take(1))
                    .subscribe(resp => expect(resp).toMatchObject({ body: expected }));
                const req = httpMock.expectOne({ method: 'POST' });
                req.flush(JSON.stringify(returnedFromService));
            });

            it('should update a RadniNalog', async () => {
                const returnedFromService = Object.assign(
                    {
                        naziv: 'BBBBBB',
                        datumIVremeOtvaranja: currentDate.format(DATE_TIME_FORMAT),
                        datumIVremeZatvaranja: currentDate.format(DATE_TIME_FORMAT),
                        status: 'BBBBBB',
                        nosilac: 'BBBBBB',
                        cena: 1,
                        kolicina: 1
                    },
                    elemDefault
                );

                const expected = Object.assign(
                    {
                        datumIVremeOtvaranja: currentDate,
                        datumIVremeZatvaranja: currentDate
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

            it('should return a list of RadniNalog', async () => {
                const returnedFromService = Object.assign(
                    {
                        naziv: 'BBBBBB',
                        datumIVremeOtvaranja: currentDate.format(DATE_TIME_FORMAT),
                        datumIVremeZatvaranja: currentDate.format(DATE_TIME_FORMAT),
                        status: 'BBBBBB',
                        nosilac: 'BBBBBB',
                        cena: 1,
                        kolicina: 1
                    },
                    elemDefault
                );
                const expected = Object.assign(
                    {
                        datumIVremeOtvaranja: currentDate,
                        datumIVremeZatvaranja: currentDate
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

            it('should delete a RadniNalog', async () => {
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
