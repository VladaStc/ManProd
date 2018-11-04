/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { ManProdTestModule } from '../../../test.module';
import { ProizvodniPogoniDetailComponent } from 'app/entities/proizvodni-pogoni/proizvodni-pogoni-detail.component';
import { ProizvodniPogoni } from 'app/shared/model/proizvodni-pogoni.model';

describe('Component Tests', () => {
    describe('ProizvodniPogoni Management Detail Component', () => {
        let comp: ProizvodniPogoniDetailComponent;
        let fixture: ComponentFixture<ProizvodniPogoniDetailComponent>;
        const route = ({ data: of({ proizvodniPogoni: new ProizvodniPogoni(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ManProdTestModule],
                declarations: [ProizvodniPogoniDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(ProizvodniPogoniDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(ProizvodniPogoniDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.proizvodniPogoni).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
