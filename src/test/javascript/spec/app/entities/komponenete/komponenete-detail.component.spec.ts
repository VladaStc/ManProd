/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { ManProdTestModule } from '../../../test.module';
import { KomponeneteDetailComponent } from 'app/entities/komponenete/komponenete-detail.component';
import { Komponenete } from 'app/shared/model/komponenete.model';

describe('Component Tests', () => {
    describe('Komponenete Management Detail Component', () => {
        let comp: KomponeneteDetailComponent;
        let fixture: ComponentFixture<KomponeneteDetailComponent>;
        const route = ({ data: of({ komponenete: new Komponenete(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ManProdTestModule],
                declarations: [KomponeneteDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(KomponeneteDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(KomponeneteDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.komponenete).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
