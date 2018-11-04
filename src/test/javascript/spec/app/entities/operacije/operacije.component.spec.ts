/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { ManProdTestModule } from '../../../test.module';
import { OperacijeComponent } from 'app/entities/operacije/operacije.component';
import { OperacijeService } from 'app/entities/operacije/operacije.service';
import { Operacije } from 'app/shared/model/operacije.model';

describe('Component Tests', () => {
    describe('Operacije Management Component', () => {
        let comp: OperacijeComponent;
        let fixture: ComponentFixture<OperacijeComponent>;
        let service: OperacijeService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ManProdTestModule],
                declarations: [OperacijeComponent],
                providers: []
            })
                .overrideTemplate(OperacijeComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(OperacijeComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(OperacijeService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new Operacije(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.operacijes[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
