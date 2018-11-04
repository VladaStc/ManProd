/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { ManProdTestModule } from '../../../test.module';
import { AlatiDetailComponent } from 'app/entities/alati/alati-detail.component';
import { Alati } from 'app/shared/model/alati.model';

describe('Component Tests', () => {
    describe('Alati Management Detail Component', () => {
        let comp: AlatiDetailComponent;
        let fixture: ComponentFixture<AlatiDetailComponent>;
        const route = ({ data: of({ alati: new Alati(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ManProdTestModule],
                declarations: [AlatiDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(AlatiDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(AlatiDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.alati).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
