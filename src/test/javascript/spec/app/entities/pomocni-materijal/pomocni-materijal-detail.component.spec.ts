/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { ManProdTestModule } from '../../../test.module';
import { PomocniMaterijalDetailComponent } from 'app/entities/pomocni-materijal/pomocni-materijal-detail.component';
import { PomocniMaterijal } from 'app/shared/model/pomocni-materijal.model';

describe('Component Tests', () => {
    describe('PomocniMaterijal Management Detail Component', () => {
        let comp: PomocniMaterijalDetailComponent;
        let fixture: ComponentFixture<PomocniMaterijalDetailComponent>;
        const route = ({ data: of({ pomocniMaterijal: new PomocniMaterijal(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ManProdTestModule],
                declarations: [PomocniMaterijalDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(PomocniMaterijalDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(PomocniMaterijalDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.pomocniMaterijal).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
