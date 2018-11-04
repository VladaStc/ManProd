/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { ManProdTestModule } from '../../../test.module';
import { MagaciniComponent } from 'app/entities/magacini/magacini.component';
import { MagaciniService } from 'app/entities/magacini/magacini.service';
import { Magacini } from 'app/shared/model/magacini.model';

describe('Component Tests', () => {
    describe('Magacini Management Component', () => {
        let comp: MagaciniComponent;
        let fixture: ComponentFixture<MagaciniComponent>;
        let service: MagaciniService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ManProdTestModule],
                declarations: [MagaciniComponent],
                providers: []
            })
                .overrideTemplate(MagaciniComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(MagaciniComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(MagaciniService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new Magacini(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.magacinis[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
