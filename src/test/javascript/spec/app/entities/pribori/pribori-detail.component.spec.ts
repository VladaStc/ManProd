/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { ManProdTestModule } from '../../../test.module';
import { PriboriDetailComponent } from 'app/entities/pribori/pribori-detail.component';
import { Pribori } from 'app/shared/model/pribori.model';

describe('Component Tests', () => {
    describe('Pribori Management Detail Component', () => {
        let comp: PriboriDetailComponent;
        let fixture: ComponentFixture<PriboriDetailComponent>;
        const route = ({ data: of({ pribori: new Pribori(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ManProdTestModule],
                declarations: [PriboriDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(PriboriDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(PriboriDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.pribori).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
