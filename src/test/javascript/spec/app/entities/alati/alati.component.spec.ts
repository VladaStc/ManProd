/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { ManProdTestModule } from '../../../test.module';
import { AlatiComponent } from 'app/entities/alati/alati.component';
import { AlatiService } from 'app/entities/alati/alati.service';
import { Alati } from 'app/shared/model/alati.model';

describe('Component Tests', () => {
    describe('Alati Management Component', () => {
        let comp: AlatiComponent;
        let fixture: ComponentFixture<AlatiComponent>;
        let service: AlatiService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ManProdTestModule],
                declarations: [AlatiComponent],
                providers: []
            })
                .overrideTemplate(AlatiComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(AlatiComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(AlatiService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new Alati(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.alatis[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
