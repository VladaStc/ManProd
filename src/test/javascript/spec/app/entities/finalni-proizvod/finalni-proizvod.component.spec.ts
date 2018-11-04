/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { ManProdTestModule } from '../../../test.module';
import { FinalniProizvodComponent } from 'app/entities/finalni-proizvod/finalni-proizvod.component';
import { FinalniProizvodService } from 'app/entities/finalni-proizvod/finalni-proizvod.service';
import { FinalniProizvod } from 'app/shared/model/finalni-proizvod.model';

describe('Component Tests', () => {
    describe('FinalniProizvod Management Component', () => {
        let comp: FinalniProizvodComponent;
        let fixture: ComponentFixture<FinalniProizvodComponent>;
        let service: FinalniProizvodService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ManProdTestModule],
                declarations: [FinalniProizvodComponent],
                providers: []
            })
                .overrideTemplate(FinalniProizvodComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(FinalniProizvodComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(FinalniProizvodService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new FinalniProizvod(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.finalniProizvods[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
