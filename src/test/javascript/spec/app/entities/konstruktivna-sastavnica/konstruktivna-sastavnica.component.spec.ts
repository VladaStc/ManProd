/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { ActivatedRoute, Data } from '@angular/router';

import { ManProdTestModule } from '../../../test.module';
import { KonstruktivnaSastavnicaComponent } from 'app/entities/konstruktivna-sastavnica/konstruktivna-sastavnica.component';
import { KonstruktivnaSastavnicaService } from 'app/entities/konstruktivna-sastavnica/konstruktivna-sastavnica.service';
import { KonstruktivnaSastavnica } from 'app/shared/model/konstruktivna-sastavnica.model';

describe('Component Tests', () => {
    describe('KonstruktivnaSastavnica Management Component', () => {
        let comp: KonstruktivnaSastavnicaComponent;
        let fixture: ComponentFixture<KonstruktivnaSastavnicaComponent>;
        let service: KonstruktivnaSastavnicaService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ManProdTestModule],
                declarations: [KonstruktivnaSastavnicaComponent],
                providers: [
                    {
                        provide: ActivatedRoute,
                        useValue: {
                            data: {
                                subscribe: (fn: (value: Data) => void) =>
                                    fn({
                                        pagingParams: {
                                            predicate: 'id',
                                            reverse: false,
                                            page: 0
                                        }
                                    })
                            }
                        }
                    }
                ]
            })
                .overrideTemplate(KonstruktivnaSastavnicaComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(KonstruktivnaSastavnicaComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(KonstruktivnaSastavnicaService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new KonstruktivnaSastavnica(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.konstruktivnaSastavnicas[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });

        it('should load a page', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new KonstruktivnaSastavnica(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.loadPage(1);

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.konstruktivnaSastavnicas[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });

        it('should re-initialize the page', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new KonstruktivnaSastavnica(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.loadPage(1);
            comp.reset();

            // THEN
            expect(comp.page).toEqual(0);
            expect(service.query).toHaveBeenCalledTimes(2);
            expect(comp.konstruktivnaSastavnicas[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
        it('should calculate the sort attribute for an id', () => {
            // WHEN
            const result = comp.sort();

            // THEN
            expect(result).toEqual(['id,asc']);
        });

        it('should calculate the sort attribute for a non-id attribute', () => {
            // GIVEN
            comp.predicate = 'name';

            // WHEN
            const result = comp.sort();

            // THEN
            expect(result).toEqual(['name,asc', 'id']);
        });
    });
});
