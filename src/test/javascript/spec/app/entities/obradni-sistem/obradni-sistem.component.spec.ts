/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { ManProdTestModule } from '../../../test.module';
import { ObradniSistemComponent } from 'app/entities/obradni-sistem/obradni-sistem.component';
import { ObradniSistemService } from 'app/entities/obradni-sistem/obradni-sistem.service';
import { ObradniSistem } from 'app/shared/model/obradni-sistem.model';

describe('Component Tests', () => {
    describe('ObradniSistem Management Component', () => {
        let comp: ObradniSistemComponent;
        let fixture: ComponentFixture<ObradniSistemComponent>;
        let service: ObradniSistemService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ManProdTestModule],
                declarations: [ObradniSistemComponent],
                providers: []
            })
                .overrideTemplate(ObradniSistemComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(ObradniSistemComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ObradniSistemService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new ObradniSistem(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.obradniSistems[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
