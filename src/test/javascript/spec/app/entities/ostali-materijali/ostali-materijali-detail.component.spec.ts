/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { ManProdTestModule } from '../../../test.module';
import { OstaliMaterijaliDetailComponent } from 'app/entities/ostali-materijali/ostali-materijali-detail.component';
import { OstaliMaterijali } from 'app/shared/model/ostali-materijali.model';

describe('Component Tests', () => {
    describe('OstaliMaterijali Management Detail Component', () => {
        let comp: OstaliMaterijaliDetailComponent;
        let fixture: ComponentFixture<OstaliMaterijaliDetailComponent>;
        const route = ({ data: of({ ostaliMaterijali: new OstaliMaterijali(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ManProdTestModule],
                declarations: [OstaliMaterijaliDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(OstaliMaterijaliDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(OstaliMaterijaliDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.ostaliMaterijali).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
