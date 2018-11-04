/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { ManProdTestModule } from '../../../test.module';
import { PomocniMaterijalComponent } from 'app/entities/pomocni-materijal/pomocni-materijal.component';
import { PomocniMaterijalService } from 'app/entities/pomocni-materijal/pomocni-materijal.service';
import { PomocniMaterijal } from 'app/shared/model/pomocni-materijal.model';

describe('Component Tests', () => {
    describe('PomocniMaterijal Management Component', () => {
        let comp: PomocniMaterijalComponent;
        let fixture: ComponentFixture<PomocniMaterijalComponent>;
        let service: PomocniMaterijalService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ManProdTestModule],
                declarations: [PomocniMaterijalComponent],
                providers: []
            })
                .overrideTemplate(PomocniMaterijalComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(PomocniMaterijalComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(PomocniMaterijalService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new PomocniMaterijal(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.pomocniMaterijals[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
