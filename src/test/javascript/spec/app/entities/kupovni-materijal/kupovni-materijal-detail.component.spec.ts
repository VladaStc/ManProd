/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { ManProdTestModule } from '../../../test.module';
import { KupovniMaterijalDetailComponent } from 'app/entities/kupovni-materijal/kupovni-materijal-detail.component';
import { KupovniMaterijal } from 'app/shared/model/kupovni-materijal.model';

describe('Component Tests', () => {
    describe('KupovniMaterijal Management Detail Component', () => {
        let comp: KupovniMaterijalDetailComponent;
        let fixture: ComponentFixture<KupovniMaterijalDetailComponent>;
        const route = ({ data: of({ kupovniMaterijal: new KupovniMaterijal(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ManProdTestModule],
                declarations: [KupovniMaterijalDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(KupovniMaterijalDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(KupovniMaterijalDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.kupovniMaterijal).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
