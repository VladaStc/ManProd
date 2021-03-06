/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { ManProdTestModule } from '../../../test.module';
import { MasinaComponent } from 'app/entities/masina/masina.component';
import { MasinaService } from 'app/entities/masina/masina.service';
import { Masina } from 'app/shared/model/masina.model';

describe('Component Tests', () => {
    describe('Masina Management Component', () => {
        let comp: MasinaComponent;
        let fixture: ComponentFixture<MasinaComponent>;
        let service: MasinaService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ManProdTestModule],
                declarations: [MasinaComponent],
                providers: []
            })
                .overrideTemplate(MasinaComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(MasinaComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(MasinaService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new Masina(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.masinas[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
