/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { ManProdTestModule } from '../../../test.module';
import { RadnoMestoDetailComponent } from 'app/entities/radno-mesto/radno-mesto-detail.component';
import { RadnoMesto } from 'app/shared/model/radno-mesto.model';

describe('Component Tests', () => {
    describe('RadnoMesto Management Detail Component', () => {
        let comp: RadnoMestoDetailComponent;
        let fixture: ComponentFixture<RadnoMestoDetailComponent>;
        const route = ({ data: of({ radnoMesto: new RadnoMesto(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ManProdTestModule],
                declarations: [RadnoMestoDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(RadnoMestoDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(RadnoMestoDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.radnoMesto).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
