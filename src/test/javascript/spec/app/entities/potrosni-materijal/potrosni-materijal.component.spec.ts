/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { ManProdTestModule } from '../../../test.module';
import { PotrosniMaterijalComponent } from 'app/entities/potrosni-materijal/potrosni-materijal.component';
import { PotrosniMaterijalService } from 'app/entities/potrosni-materijal/potrosni-materijal.service';
import { PotrosniMaterijal } from 'app/shared/model/potrosni-materijal.model';

describe('Component Tests', () => {
    describe('PotrosniMaterijal Management Component', () => {
        let comp: PotrosniMaterijalComponent;
        let fixture: ComponentFixture<PotrosniMaterijalComponent>;
        let service: PotrosniMaterijalService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ManProdTestModule],
                declarations: [PotrosniMaterijalComponent],
                providers: []
            })
                .overrideTemplate(PotrosniMaterijalComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(PotrosniMaterijalComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(PotrosniMaterijalService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new PotrosniMaterijal(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.potrosniMaterijals[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
