/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { ManProdTestModule } from '../../../test.module';
import { PotrosniMaterijalDetailComponent } from 'app/entities/potrosni-materijal/potrosni-materijal-detail.component';
import { PotrosniMaterijal } from 'app/shared/model/potrosni-materijal.model';

describe('Component Tests', () => {
    describe('PotrosniMaterijal Management Detail Component', () => {
        let comp: PotrosniMaterijalDetailComponent;
        let fixture: ComponentFixture<PotrosniMaterijalDetailComponent>;
        const route = ({ data: of({ potrosniMaterijal: new PotrosniMaterijal(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ManProdTestModule],
                declarations: [PotrosniMaterijalDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(PotrosniMaterijalDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(PotrosniMaterijalDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.potrosniMaterijal).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
