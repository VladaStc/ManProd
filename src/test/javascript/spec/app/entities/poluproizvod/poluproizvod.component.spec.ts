/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { ManProdTestModule } from '../../../test.module';
import { PoluproizvodComponent } from 'app/entities/poluproizvod/poluproizvod.component';
import { PoluproizvodService } from 'app/entities/poluproizvod/poluproizvod.service';
import { Poluproizvod } from 'app/shared/model/poluproizvod.model';

describe('Component Tests', () => {
    describe('Poluproizvod Management Component', () => {
        let comp: PoluproizvodComponent;
        let fixture: ComponentFixture<PoluproizvodComponent>;
        let service: PoluproizvodService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ManProdTestModule],
                declarations: [PoluproizvodComponent],
                providers: []
            })
                .overrideTemplate(PoluproizvodComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(PoluproizvodComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(PoluproizvodService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new Poluproizvod(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.poluproizvods[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
