/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { ManProdTestModule } from '../../../test.module';
import { ZahvatiComponent } from 'app/entities/zahvati/zahvati.component';
import { ZahvatiService } from 'app/entities/zahvati/zahvati.service';
import { Zahvati } from 'app/shared/model/zahvati.model';

describe('Component Tests', () => {
    describe('Zahvati Management Component', () => {
        let comp: ZahvatiComponent;
        let fixture: ComponentFixture<ZahvatiComponent>;
        let service: ZahvatiService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ManProdTestModule],
                declarations: [ZahvatiComponent],
                providers: []
            })
                .overrideTemplate(ZahvatiComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(ZahvatiComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ZahvatiService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new Zahvati(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.zahvatis[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
