/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { ManProdTestModule } from '../../../test.module';
import { KonstruktivnaSastavnicaDetailComponent } from 'app/entities/konstruktivna-sastavnica/konstruktivna-sastavnica-detail.component';
import { KonstruktivnaSastavnica } from 'app/shared/model/konstruktivna-sastavnica.model';

describe('Component Tests', () => {
    describe('KonstruktivnaSastavnica Management Detail Component', () => {
        let comp: KonstruktivnaSastavnicaDetailComponent;
        let fixture: ComponentFixture<KonstruktivnaSastavnicaDetailComponent>;
        const route = ({ data: of({ konstruktivnaSastavnica: new KonstruktivnaSastavnica(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ManProdTestModule],
                declarations: [KonstruktivnaSastavnicaDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(KonstruktivnaSastavnicaDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(KonstruktivnaSastavnicaDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.konstruktivnaSastavnica).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
