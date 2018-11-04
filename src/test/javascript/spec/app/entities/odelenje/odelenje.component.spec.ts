/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { ManProdTestModule } from '../../../test.module';
import { OdelenjeComponent } from 'app/entities/odelenje/odelenje.component';
import { OdelenjeService } from 'app/entities/odelenje/odelenje.service';
import { Odelenje } from 'app/shared/model/odelenje.model';

describe('Component Tests', () => {
    describe('Odelenje Management Component', () => {
        let comp: OdelenjeComponent;
        let fixture: ComponentFixture<OdelenjeComponent>;
        let service: OdelenjeService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ManProdTestModule],
                declarations: [OdelenjeComponent],
                providers: []
            })
                .overrideTemplate(OdelenjeComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(OdelenjeComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(OdelenjeService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new Odelenje(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.odelenjes[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
