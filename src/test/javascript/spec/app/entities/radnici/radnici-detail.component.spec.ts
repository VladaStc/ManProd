/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { ManProdTestModule } from '../../../test.module';
import { RadniciDetailComponent } from 'app/entities/radnici/radnici-detail.component';
import { Radnici } from 'app/shared/model/radnici.model';

describe('Component Tests', () => {
    describe('Radnici Management Detail Component', () => {
        let comp: RadniciDetailComponent;
        let fixture: ComponentFixture<RadniciDetailComponent>;
        const route = ({ data: of({ radnici: new Radnici(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ManProdTestModule],
                declarations: [RadniciDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(RadniciDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(RadniciDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.radnici).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
