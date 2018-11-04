/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { ManProdTestModule } from '../../../test.module';
import { KupovniMaterijalComponent } from 'app/entities/kupovni-materijal/kupovni-materijal.component';
import { KupovniMaterijalService } from 'app/entities/kupovni-materijal/kupovni-materijal.service';
import { KupovniMaterijal } from 'app/shared/model/kupovni-materijal.model';

describe('Component Tests', () => {
    describe('KupovniMaterijal Management Component', () => {
        let comp: KupovniMaterijalComponent;
        let fixture: ComponentFixture<KupovniMaterijalComponent>;
        let service: KupovniMaterijalService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ManProdTestModule],
                declarations: [KupovniMaterijalComponent],
                providers: []
            })
                .overrideTemplate(KupovniMaterijalComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(KupovniMaterijalComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(KupovniMaterijalService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new KupovniMaterijal(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.kupovniMaterijals[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
