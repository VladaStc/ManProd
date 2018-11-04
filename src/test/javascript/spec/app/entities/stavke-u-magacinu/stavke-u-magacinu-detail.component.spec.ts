/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { ManProdTestModule } from '../../../test.module';
import { StavkeUMagacinuDetailComponent } from 'app/entities/stavke-u-magacinu/stavke-u-magacinu-detail.component';
import { StavkeUMagacinu } from 'app/shared/model/stavke-u-magacinu.model';

describe('Component Tests', () => {
    describe('StavkeUMagacinu Management Detail Component', () => {
        let comp: StavkeUMagacinuDetailComponent;
        let fixture: ComponentFixture<StavkeUMagacinuDetailComponent>;
        const route = ({ data: of({ stavkeUMagacinu: new StavkeUMagacinu(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ManProdTestModule],
                declarations: [StavkeUMagacinuDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(StavkeUMagacinuDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(StavkeUMagacinuDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.stavkeUMagacinu).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
