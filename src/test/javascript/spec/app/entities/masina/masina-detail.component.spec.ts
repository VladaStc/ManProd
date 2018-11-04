/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { ManProdTestModule } from '../../../test.module';
import { MasinaDetailComponent } from 'app/entities/masina/masina-detail.component';
import { Masina } from 'app/shared/model/masina.model';

describe('Component Tests', () => {
    describe('Masina Management Detail Component', () => {
        let comp: MasinaDetailComponent;
        let fixture: ComponentFixture<MasinaDetailComponent>;
        const route = ({ data: of({ masina: new Masina(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ManProdTestModule],
                declarations: [MasinaDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(MasinaDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(MasinaDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.masina).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
