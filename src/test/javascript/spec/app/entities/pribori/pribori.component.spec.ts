/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { ManProdTestModule } from '../../../test.module';
import { PriboriComponent } from 'app/entities/pribori/pribori.component';
import { PriboriService } from 'app/entities/pribori/pribori.service';
import { Pribori } from 'app/shared/model/pribori.model';

describe('Component Tests', () => {
    describe('Pribori Management Component', () => {
        let comp: PriboriComponent;
        let fixture: ComponentFixture<PriboriComponent>;
        let service: PriboriService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ManProdTestModule],
                declarations: [PriboriComponent],
                providers: []
            })
                .overrideTemplate(PriboriComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(PriboriComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(PriboriService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new Pribori(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.priboris[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
