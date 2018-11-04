/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { ManProdTestModule } from '../../../test.module';
import { OstaliMaterijaliComponent } from 'app/entities/ostali-materijali/ostali-materijali.component';
import { OstaliMaterijaliService } from 'app/entities/ostali-materijali/ostali-materijali.service';
import { OstaliMaterijali } from 'app/shared/model/ostali-materijali.model';

describe('Component Tests', () => {
    describe('OstaliMaterijali Management Component', () => {
        let comp: OstaliMaterijaliComponent;
        let fixture: ComponentFixture<OstaliMaterijaliComponent>;
        let service: OstaliMaterijaliService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ManProdTestModule],
                declarations: [OstaliMaterijaliComponent],
                providers: []
            })
                .overrideTemplate(OstaliMaterijaliComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(OstaliMaterijaliComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(OstaliMaterijaliService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new OstaliMaterijali(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.ostaliMaterijalis[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
