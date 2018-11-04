/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { ManProdTestModule } from '../../../test.module';
import { OdelenjeUpdateComponent } from 'app/entities/odelenje/odelenje-update.component';
import { OdelenjeService } from 'app/entities/odelenje/odelenje.service';
import { Odelenje } from 'app/shared/model/odelenje.model';

describe('Component Tests', () => {
    describe('Odelenje Management Update Component', () => {
        let comp: OdelenjeUpdateComponent;
        let fixture: ComponentFixture<OdelenjeUpdateComponent>;
        let service: OdelenjeService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ManProdTestModule],
                declarations: [OdelenjeUpdateComponent]
            })
                .overrideTemplate(OdelenjeUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(OdelenjeUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(OdelenjeService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new Odelenje(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.odelenje = entity;
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
                    const entity = new Odelenje();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.odelenje = entity;
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
