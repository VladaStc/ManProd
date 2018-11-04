/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { ManProdTestModule } from '../../../test.module';
import { SirovineDetailComponent } from 'app/entities/sirovine/sirovine-detail.component';
import { Sirovine } from 'app/shared/model/sirovine.model';

describe('Component Tests', () => {
    describe('Sirovine Management Detail Component', () => {
        let comp: SirovineDetailComponent;
        let fixture: ComponentFixture<SirovineDetailComponent>;
        const route = ({ data: of({ sirovine: new Sirovine(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ManProdTestModule],
                declarations: [SirovineDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(SirovineDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(SirovineDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.sirovine).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
