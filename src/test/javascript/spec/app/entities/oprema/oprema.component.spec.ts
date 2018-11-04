/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { ManProdTestModule } from '../../../test.module';
import { OpremaComponent } from 'app/entities/oprema/oprema.component';
import { OpremaService } from 'app/entities/oprema/oprema.service';
import { Oprema } from 'app/shared/model/oprema.model';

describe('Component Tests', () => {
    describe('Oprema Management Component', () => {
        let comp: OpremaComponent;
        let fixture: ComponentFixture<OpremaComponent>;
        let service: OpremaService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ManProdTestModule],
                declarations: [OpremaComponent],
                providers: []
            })
                .overrideTemplate(OpremaComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(OpremaComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(OpremaService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new Oprema(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.opremas[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
