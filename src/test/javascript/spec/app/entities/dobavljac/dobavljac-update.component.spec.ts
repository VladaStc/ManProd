/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { ManProdTestModule } from '../../../test.module';
import { DobavljacUpdateComponent } from 'app/entities/dobavljac/dobavljac-update.component';
import { DobavljacService } from 'app/entities/dobavljac/dobavljac.service';
import { Dobavljac } from 'app/shared/model/dobavljac.model';

describe('Component Tests', () => {
    describe('Dobavljac Management Update Component', () => {
        let comp: DobavljacUpdateComponent;
        let fixture: ComponentFixture<DobavljacUpdateComponent>;
        let service: DobavljacService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ManProdTestModule],
                declarations: [DobavljacUpdateComponent]
            })
                .overrideTemplate(DobavljacUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(DobavljacUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(DobavljacService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new Dobavljac(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.dobavljac = entity;
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
                    const entity = new Dobavljac();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.dobavljac = entity;
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
