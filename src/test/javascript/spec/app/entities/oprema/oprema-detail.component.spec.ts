/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { ManProdTestModule } from '../../../test.module';
import { OpremaDetailComponent } from 'app/entities/oprema/oprema-detail.component';
import { Oprema } from 'app/shared/model/oprema.model';

describe('Component Tests', () => {
    describe('Oprema Management Detail Component', () => {
        let comp: OpremaDetailComponent;
        let fixture: ComponentFixture<OpremaDetailComponent>;
        const route = ({ data: of({ oprema: new Oprema(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ManProdTestModule],
                declarations: [OpremaDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(OpremaDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(OpremaDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.oprema).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
