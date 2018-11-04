/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { ManProdTestModule } from '../../../test.module';
import { PomocniProizvodDetailComponent } from 'app/entities/pomocni-proizvod/pomocni-proizvod-detail.component';
import { PomocniProizvod } from 'app/shared/model/pomocni-proizvod.model';

describe('Component Tests', () => {
    describe('PomocniProizvod Management Detail Component', () => {
        let comp: PomocniProizvodDetailComponent;
        let fixture: ComponentFixture<PomocniProizvodDetailComponent>;
        const route = ({ data: of({ pomocniProizvod: new PomocniProizvod(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ManProdTestModule],
                declarations: [PomocniProizvodDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(PomocniProizvodDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(PomocniProizvodDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.pomocniProizvod).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
