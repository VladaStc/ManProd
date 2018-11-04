/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { ManProdTestModule } from '../../../test.module';
import { ObradniSistemDetailComponent } from 'app/entities/obradni-sistem/obradni-sistem-detail.component';
import { ObradniSistem } from 'app/shared/model/obradni-sistem.model';

describe('Component Tests', () => {
    describe('ObradniSistem Management Detail Component', () => {
        let comp: ObradniSistemDetailComponent;
        let fixture: ComponentFixture<ObradniSistemDetailComponent>;
        const route = ({ data: of({ obradniSistem: new ObradniSistem(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ManProdTestModule],
                declarations: [ObradniSistemDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(ObradniSistemDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(ObradniSistemDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.obradniSistem).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
