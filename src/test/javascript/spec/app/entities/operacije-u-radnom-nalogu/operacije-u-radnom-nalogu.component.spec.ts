/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { ManProdTestModule } from '../../../test.module';
import { OperacijeURadnomNaloguComponent } from 'app/entities/operacije-u-radnom-nalogu/operacije-u-radnom-nalogu.component';
import { OperacijeURadnomNaloguService } from 'app/entities/operacije-u-radnom-nalogu/operacije-u-radnom-nalogu.service';
import { OperacijeURadnomNalogu } from 'app/shared/model/operacije-u-radnom-nalogu.model';

describe('Component Tests', () => {
    describe('OperacijeURadnomNalogu Management Component', () => {
        let comp: OperacijeURadnomNaloguComponent;
        let fixture: ComponentFixture<OperacijeURadnomNaloguComponent>;
        let service: OperacijeURadnomNaloguService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ManProdTestModule],
                declarations: [OperacijeURadnomNaloguComponent],
                providers: []
            })
                .overrideTemplate(OperacijeURadnomNaloguComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(OperacijeURadnomNaloguComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(OperacijeURadnomNaloguService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new OperacijeURadnomNalogu(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.operacijeURadnomNalogus[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
