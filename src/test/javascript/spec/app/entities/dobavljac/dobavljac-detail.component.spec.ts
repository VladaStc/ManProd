/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { ManProdTestModule } from '../../../test.module';
import { DobavljacDetailComponent } from 'app/entities/dobavljac/dobavljac-detail.component';
import { Dobavljac } from 'app/shared/model/dobavljac.model';

describe('Component Tests', () => {
    describe('Dobavljac Management Detail Component', () => {
        let comp: DobavljacDetailComponent;
        let fixture: ComponentFixture<DobavljacDetailComponent>;
        const route = ({ data: of({ dobavljac: new Dobavljac(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ManProdTestModule],
                declarations: [DobavljacDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(DobavljacDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(DobavljacDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.dobavljac).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
