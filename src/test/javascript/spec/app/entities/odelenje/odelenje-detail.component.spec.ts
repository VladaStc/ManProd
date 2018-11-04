/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { ManProdTestModule } from '../../../test.module';
import { OdelenjeDetailComponent } from 'app/entities/odelenje/odelenje-detail.component';
import { Odelenje } from 'app/shared/model/odelenje.model';

describe('Component Tests', () => {
    describe('Odelenje Management Detail Component', () => {
        let comp: OdelenjeDetailComponent;
        let fixture: ComponentFixture<OdelenjeDetailComponent>;
        const route = ({ data: of({ odelenje: new Odelenje(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ManProdTestModule],
                declarations: [OdelenjeDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(OdelenjeDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(OdelenjeDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.odelenje).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
