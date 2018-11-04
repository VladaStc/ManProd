/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { ManProdTestModule } from '../../../test.module';
import { RadionicaDetailComponent } from 'app/entities/radionica/radionica-detail.component';
import { Radionica } from 'app/shared/model/radionica.model';

describe('Component Tests', () => {
    describe('Radionica Management Detail Component', () => {
        let comp: RadionicaDetailComponent;
        let fixture: ComponentFixture<RadionicaDetailComponent>;
        const route = ({ data: of({ radionica: new Radionica(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ManProdTestModule],
                declarations: [RadionicaDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(RadionicaDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(RadionicaDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.radionica).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
