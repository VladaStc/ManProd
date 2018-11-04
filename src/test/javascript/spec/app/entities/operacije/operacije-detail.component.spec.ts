/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { ManProdTestModule } from '../../../test.module';
import { OperacijeDetailComponent } from 'app/entities/operacije/operacije-detail.component';
import { Operacije } from 'app/shared/model/operacije.model';

describe('Component Tests', () => {
    describe('Operacije Management Detail Component', () => {
        let comp: OperacijeDetailComponent;
        let fixture: ComponentFixture<OperacijeDetailComponent>;
        const route = ({ data: of({ operacije: new Operacije(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ManProdTestModule],
                declarations: [OperacijeDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(OperacijeDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(OperacijeDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.operacije).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
