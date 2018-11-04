/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { ManProdTestModule } from '../../../test.module';
import { ProizvodneLinijeComponent } from 'app/entities/proizvodne-linije/proizvodne-linije.component';
import { ProizvodneLinijeService } from 'app/entities/proizvodne-linije/proizvodne-linije.service';
import { ProizvodneLinije } from 'app/shared/model/proizvodne-linije.model';

describe('Component Tests', () => {
    describe('ProizvodneLinije Management Component', () => {
        let comp: ProizvodneLinijeComponent;
        let fixture: ComponentFixture<ProizvodneLinijeComponent>;
        let service: ProizvodneLinijeService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ManProdTestModule],
                declarations: [ProizvodneLinijeComponent],
                providers: []
            })
                .overrideTemplate(ProizvodneLinijeComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(ProizvodneLinijeComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ProizvodneLinijeService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new ProizvodneLinije(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.proizvodneLinijes[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
