/* tslint:disable max-line-length */
import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { of } from 'rxjs';
import { take, map } from 'rxjs/operators';
import { RadniciService } from 'app/entities/radnici/radnici.service';
import { IRadnici, Radnici, TipRadnika } from 'app/shared/model/radnici.model';

describe('Service Tests', () => {
    describe('Radnici Service', () => {
        let injector: TestBed;
        let service: RadniciService;
        let httpMock: HttpTestingController;
        let elemDefault: IRadnici;
        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [HttpClientTestingModule]
            });
            injector = getTestBed();
            service = injector.get(RadniciService);
            httpMock = injector.get(HttpTestingController);

            elemDefault = new Radnici(0, 'AAAAAAA', 'AAAAAAA', 0, 'AAAAAAA', 0, 'AAAAAAA', 'AAAAAAA', 'AAAAAAA', TipRadnika.SOPSTVENI);
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

            it('should create a Radnici', async () => {
                const returnedFromService = Object.assign(
                    {
                        id: 0
                    },
                    elemDefault
                );
                const expected = Object.assign({}, returnedFromService);
                service
                    .create(new Radnici(null))
                    .pipe(take(1))
                    .subscribe(resp => expect(resp).toMatchObject({ body: expected }));
                const req = httpMock.expectOne({ method: 'POST' });
                req.flush(JSON.stringify(returnedFromService));
            });

            it('should update a Radnici', async () => {
                const returnedFromService = Object.assign(
                    {
                        ime: 'BBBBBB',
                        prezime: 'BBBBBB',
                        jmbg: 1,
                        kvalifikacija: 'BBBBBB',
                        koeficijent: 1,
                        sertifikat: 'BBBBBB',
                        pol: 'BBBBBB',
                        napomena: 'BBBBBB',
                        tipRadnika: 'BBBBBB'
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

            it('should return a list of Radnici', async () => {
                const returnedFromService = Object.assign(
                    {
                        ime: 'BBBBBB',
                        prezime: 'BBBBBB',
                        jmbg: 1,
                        kvalifikacija: 'BBBBBB',
                        koeficijent: 1,
                        sertifikat: 'BBBBBB',
                        pol: 'BBBBBB',
                        napomena: 'BBBBBB',
                        tipRadnika: 'BBBBBB'
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

            it('should delete a Radnici', async () => {
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
