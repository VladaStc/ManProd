/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { ManProdTestModule } from '../../../test.module';
import { KupovniProizvodDetailComponent } from 'app/entities/kupovni-proizvod/kupovni-proizvod-detail.component';
import { KupovniProizvod } from 'app/shared/model/kupovni-proizvod.model';

describe('Component Tests', () => {
    describe('KupovniProizvod Management Detail Component', () => {
        let comp: KupovniProizvodDetailComponent;
        let fixture: ComponentFixture<KupovniProizvodDetailComponent>;
        const route = ({ data: of({ kupovniProizvod: new KupovniProizvod(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ManProdTestModule],
                declarations: [KupovniProizvodDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(KupovniProizvodDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(KupovniProizvodDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.kupovniProizvod).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
