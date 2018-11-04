/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { ManProdTestModule } from '../../../test.module';
import { RadionicaComponent } from 'app/entities/radionica/radionica.component';
import { RadionicaService } from 'app/entities/radionica/radionica.service';
import { Radionica } from 'app/shared/model/radionica.model';

describe('Component Tests', () => {
    describe('Radionica Management Component', () => {
        let comp: RadionicaComponent;
        let fixture: ComponentFixture<RadionicaComponent>;
        let service: RadionicaService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ManProdTestModule],
                declarations: [RadionicaComponent],
                providers: []
            })
                .overrideTemplate(RadionicaComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(RadionicaComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(RadionicaService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new Radionica(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.radionicas[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
