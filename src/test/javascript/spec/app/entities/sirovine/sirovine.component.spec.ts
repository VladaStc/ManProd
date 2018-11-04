/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { ManProdTestModule } from '../../../test.module';
import { SirovineComponent } from 'app/entities/sirovine/sirovine.component';
import { SirovineService } from 'app/entities/sirovine/sirovine.service';
import { Sirovine } from 'app/shared/model/sirovine.model';

describe('Component Tests', () => {
    describe('Sirovine Management Component', () => {
        let comp: SirovineComponent;
        let fixture: ComponentFixture<SirovineComponent>;
        let service: SirovineService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ManProdTestModule],
                declarations: [SirovineComponent],
                providers: []
            })
                .overrideTemplate(SirovineComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(SirovineComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(SirovineService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new Sirovine(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.sirovines[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
