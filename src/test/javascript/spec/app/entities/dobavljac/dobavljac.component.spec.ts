/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { ManProdTestModule } from '../../../test.module';
import { DobavljacComponent } from 'app/entities/dobavljac/dobavljac.component';
import { DobavljacService } from 'app/entities/dobavljac/dobavljac.service';
import { Dobavljac } from 'app/shared/model/dobavljac.model';

describe('Component Tests', () => {
    describe('Dobavljac Management Component', () => {
        let comp: DobavljacComponent;
        let fixture: ComponentFixture<DobavljacComponent>;
        let service: DobavljacService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ManProdTestModule],
                declarations: [DobavljacComponent],
                providers: []
            })
                .overrideTemplate(DobavljacComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(DobavljacComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(DobavljacService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new Dobavljac(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.dobavljacs[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
