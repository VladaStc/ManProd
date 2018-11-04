/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { ManProdTestModule } from '../../../test.module';
import { OstaliMaterijaliUpdateComponent } from 'app/entities/ostali-materijali/ostali-materijali-update.component';
import { OstaliMaterijaliService } from 'app/entities/ostali-materijali/ostali-materijali.service';
import { OstaliMaterijali } from 'app/shared/model/ostali-materijali.model';

describe('Component Tests', () => {
    describe('OstaliMaterijali Management Update Component', () => {
        let comp: OstaliMaterijaliUpdateComponent;
        let fixture: ComponentFixture<OstaliMaterijaliUpdateComponent>;
        let service: OstaliMaterijaliService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ManProdTestModule],
                declarations: [OstaliMaterijaliUpdateComponent]
            })
                .overrideTemplate(OstaliMaterijaliUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(OstaliMaterijaliUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(OstaliMaterijaliService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new OstaliMaterijali(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.ostaliMaterijali = entity;
                    // WHEN
                    comp.save();
                    tick(); // simulate async

                    // THEN
                    expect(service.update).toHaveBeenCalledWith(entity);
                    expect(comp.isSaving).toEqual(false);
                })
            );

            it(
                'Should call create service on save for new entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new OstaliMaterijali();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.ostaliMaterijali = entity;
                    // WHEN
                    comp.save();
                    tick(); // simulate async

                    // THEN
                    expect(service.create).toHaveBeenCalledWith(entity);
                    expect(comp.isSaving).toEqual(false);
                })
            );
        });
    });
});
