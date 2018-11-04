/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { ManProdTestModule } from '../../../test.module';
import { OperacijeURadnomNaloguDetailComponent } from 'app/entities/operacije-u-radnom-nalogu/operacije-u-radnom-nalogu-detail.component';
import { OperacijeURadnomNalogu } from 'app/shared/model/operacije-u-radnom-nalogu.model';

describe('Component Tests', () => {
    describe('OperacijeURadnomNalogu Management Detail Component', () => {
        let comp: OperacijeURadnomNaloguDetailComponent;
        let fixture: ComponentFixture<OperacijeURadnomNaloguDetailComponent>;
        const route = ({ data: of({ operacijeURadnomNalogu: new OperacijeURadnomNalogu(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ManProdTestModule],
                declarations: [OperacijeURadnomNaloguDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(OperacijeURadnomNaloguDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(OperacijeURadnomNaloguDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.operacijeURadnomNalogu).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
