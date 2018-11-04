/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { ManProdTestModule } from '../../../test.module';
import { ProizvodneLinijeDetailComponent } from 'app/entities/proizvodne-linije/proizvodne-linije-detail.component';
import { ProizvodneLinije } from 'app/shared/model/proizvodne-linije.model';

describe('Component Tests', () => {
    describe('ProizvodneLinije Management Detail Component', () => {
        let comp: ProizvodneLinijeDetailComponent;
        let fixture: ComponentFixture<ProizvodneLinijeDetailComponent>;
        const route = ({ data: of({ proizvodneLinije: new ProizvodneLinije(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ManProdTestModule],
                declarations: [ProizvodneLinijeDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(ProizvodneLinijeDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(ProizvodneLinijeDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.proizvodneLinije).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
