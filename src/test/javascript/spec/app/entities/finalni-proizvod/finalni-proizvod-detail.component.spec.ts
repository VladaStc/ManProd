/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { ManProdTestModule } from '../../../test.module';
import { FinalniProizvodDetailComponent } from 'app/entities/finalni-proizvod/finalni-proizvod-detail.component';
import { FinalniProizvod } from 'app/shared/model/finalni-proizvod.model';

describe('Component Tests', () => {
    describe('FinalniProizvod Management Detail Component', () => {
        let comp: FinalniProizvodDetailComponent;
        let fixture: ComponentFixture<FinalniProizvodDetailComponent>;
        const route = ({ data: of({ finalniProizvod: new FinalniProizvod(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ManProdTestModule],
                declarations: [FinalniProizvodDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(FinalniProizvodDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(FinalniProizvodDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.finalniProizvod).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
