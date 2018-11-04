/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { ManProdTestModule } from '../../../test.module';
import { StavkeUMagacinuComponent } from 'app/entities/stavke-u-magacinu/stavke-u-magacinu.component';
import { StavkeUMagacinuService } from 'app/entities/stavke-u-magacinu/stavke-u-magacinu.service';
import { StavkeUMagacinu } from 'app/shared/model/stavke-u-magacinu.model';

describe('Component Tests', () => {
    describe('StavkeUMagacinu Management Component', () => {
        let comp: StavkeUMagacinuComponent;
        let fixture: ComponentFixture<StavkeUMagacinuComponent>;
        let service: StavkeUMagacinuService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ManProdTestModule],
                declarations: [StavkeUMagacinuComponent],
                providers: []
            })
                .overrideTemplate(StavkeUMagacinuComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(StavkeUMagacinuComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(StavkeUMagacinuService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new StavkeUMagacinu(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.stavkeUMagacinus[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
