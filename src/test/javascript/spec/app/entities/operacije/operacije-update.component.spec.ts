/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { ManProdTestModule } from '../../../test.module';
import { OperacijeUpdateComponent } from 'app/entities/operacije/operacije-update.component';
import { OperacijeService } from 'app/entities/operacije/operacije.service';
import { Operacije } from 'app/shared/model/operacije.model';

describe('Component Tests', () => {
    describe('Operacije Management Update Component', () => {
        let comp: OperacijeUpdateComponent;
        let fixture: ComponentFixture<OperacijeUpdateComponent>;
        let service: OperacijeService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ManProdTestModule],
                declarations: [OperacijeUpdateComponent]
            })
                .overrideTemplate(OperacijeUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(OperacijeUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(OperacijeService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new Operacije(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.operacije = entity;
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
                    const entity = new Operacije();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.operacije = entity;
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
