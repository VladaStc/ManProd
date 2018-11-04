/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { ManProdTestModule } from '../../../test.module';
import { PoluproizvodDetailComponent } from 'app/entities/poluproizvod/poluproizvod-detail.component';
import { Poluproizvod } from 'app/shared/model/poluproizvod.model';

describe('Component Tests', () => {
    describe('Poluproizvod Management Detail Component', () => {
        let comp: PoluproizvodDetailComponent;
        let fixture: ComponentFixture<PoluproizvodDetailComponent>;
        const route = ({ data: of({ poluproizvod: new Poluproizvod(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ManProdTestModule],
                declarations: [PoluproizvodDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(PoluproizvodDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(PoluproizvodDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.poluproizvod).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
