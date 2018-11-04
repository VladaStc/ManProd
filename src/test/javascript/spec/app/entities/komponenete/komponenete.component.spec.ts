/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { ManProdTestModule } from '../../../test.module';
import { KomponeneteComponent } from 'app/entities/komponenete/komponenete.component';
import { KomponeneteService } from 'app/entities/komponenete/komponenete.service';
import { Komponenete } from 'app/shared/model/komponenete.model';

describe('Component Tests', () => {
    describe('Komponenete Management Component', () => {
        let comp: KomponeneteComponent;
        let fixture: ComponentFixture<KomponeneteComponent>;
        let service: KomponeneteService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ManProdTestModule],
                declarations: [KomponeneteComponent],
                providers: []
            })
                .overrideTemplate(KomponeneteComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(KomponeneteComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(KomponeneteService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new Komponenete(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.komponenetes[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
