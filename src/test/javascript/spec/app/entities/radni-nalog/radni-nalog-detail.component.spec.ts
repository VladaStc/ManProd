/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { ManProdTestModule } from '../../../test.module';
import { RadniNalogDetailComponent } from 'app/entities/radni-nalog/radni-nalog-detail.component';
import { RadniNalog } from 'app/shared/model/radni-nalog.model';

describe('Component Tests', () => {
    describe('RadniNalog Management Detail Component', () => {
        let comp: RadniNalogDetailComponent;
        let fixture: ComponentFixture<RadniNalogDetailComponent>;
        const route = ({ data: of({ radniNalog: new RadniNalog(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ManProdTestModule],
                declarations: [RadniNalogDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(RadniNalogDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(RadniNalogDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.radniNalog).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
