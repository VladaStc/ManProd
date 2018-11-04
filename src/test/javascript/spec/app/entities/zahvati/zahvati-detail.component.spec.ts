/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { ManProdTestModule } from '../../../test.module';
import { ZahvatiDetailComponent } from 'app/entities/zahvati/zahvati-detail.component';
import { Zahvati } from 'app/shared/model/zahvati.model';

describe('Component Tests', () => {
    describe('Zahvati Management Detail Component', () => {
        let comp: ZahvatiDetailComponent;
        let fixture: ComponentFixture<ZahvatiDetailComponent>;
        const route = ({ data: of({ zahvati: new Zahvati(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ManProdTestModule],
                declarations: [ZahvatiDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(ZahvatiDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(ZahvatiDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.zahvati).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
