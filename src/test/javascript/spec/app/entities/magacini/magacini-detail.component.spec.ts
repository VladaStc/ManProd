/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { ManProdTestModule } from '../../../test.module';
import { MagaciniDetailComponent } from 'app/entities/magacini/magacini-detail.component';
import { Magacini } from 'app/shared/model/magacini.model';

describe('Component Tests', () => {
    describe('Magacini Management Detail Component', () => {
        let comp: MagaciniDetailComponent;
        let fixture: ComponentFixture<MagaciniDetailComponent>;
        const route = ({ data: of({ magacini: new Magacini(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ManProdTestModule],
                declarations: [MagaciniDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(MagaciniDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(MagaciniDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.magacini).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
