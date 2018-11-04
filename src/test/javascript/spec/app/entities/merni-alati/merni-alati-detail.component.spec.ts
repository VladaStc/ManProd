/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { ManProdTestModule } from '../../../test.module';
import { MerniAlatiDetailComponent } from 'app/entities/merni-alati/merni-alati-detail.component';
import { MerniAlati } from 'app/shared/model/merni-alati.model';

describe('Component Tests', () => {
    describe('MerniAlati Management Detail Component', () => {
        let comp: MerniAlatiDetailComponent;
        let fixture: ComponentFixture<MerniAlatiDetailComponent>;
        const route = ({ data: of({ merniAlati: new MerniAlati(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ManProdTestModule],
                declarations: [MerniAlatiDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(MerniAlatiDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(MerniAlatiDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.merniAlati).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
