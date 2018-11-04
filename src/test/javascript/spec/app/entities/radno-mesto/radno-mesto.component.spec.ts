/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { ManProdTestModule } from '../../../test.module';
import { RadnoMestoComponent } from 'app/entities/radno-mesto/radno-mesto.component';
import { RadnoMestoService } from 'app/entities/radno-mesto/radno-mesto.service';
import { RadnoMesto } from 'app/shared/model/radno-mesto.model';

describe('Component Tests', () => {
    describe('RadnoMesto Management Component', () => {
        let comp: RadnoMestoComponent;
        let fixture: ComponentFixture<RadnoMestoComponent>;
        let service: RadnoMestoService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ManProdTestModule],
                declarations: [RadnoMestoComponent],
                providers: []
            })
                .overrideTemplate(RadnoMestoComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(RadnoMestoComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(RadnoMestoService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new RadnoMesto(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.radnoMestos[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
