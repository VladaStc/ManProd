/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { ManProdTestModule } from '../../../test.module';
import { MerniAlatiComponent } from 'app/entities/merni-alati/merni-alati.component';
import { MerniAlatiService } from 'app/entities/merni-alati/merni-alati.service';
import { MerniAlati } from 'app/shared/model/merni-alati.model';

describe('Component Tests', () => {
    describe('MerniAlati Management Component', () => {
        let comp: MerniAlatiComponent;
        let fixture: ComponentFixture<MerniAlatiComponent>;
        let service: MerniAlatiService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ManProdTestModule],
                declarations: [MerniAlatiComponent],
                providers: []
            })
                .overrideTemplate(MerniAlatiComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(MerniAlatiComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(MerniAlatiService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new MerniAlati(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.merniAlatis[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
