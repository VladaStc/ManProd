/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { ManProdTestModule } from '../../../test.module';
import { PomocniProizvodComponent } from 'app/entities/pomocni-proizvod/pomocni-proizvod.component';
import { PomocniProizvodService } from 'app/entities/pomocni-proizvod/pomocni-proizvod.service';
import { PomocniProizvod } from 'app/shared/model/pomocni-proizvod.model';

describe('Component Tests', () => {
    describe('PomocniProizvod Management Component', () => {
        let comp: PomocniProizvodComponent;
        let fixture: ComponentFixture<PomocniProizvodComponent>;
        let service: PomocniProizvodService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ManProdTestModule],
                declarations: [PomocniProizvodComponent],
                providers: []
            })
                .overrideTemplate(PomocniProizvodComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(PomocniProizvodComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(PomocniProizvodService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new PomocniProizvod(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.pomocniProizvods[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
