/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { ManProdTestModule } from '../../../test.module';
import { MagaciniUpdateComponent } from 'app/entities/magacini/magacini-update.component';
import { MagaciniService } from 'app/entities/magacini/magacini.service';
import { Magacini } from 'app/shared/model/magacini.model';

describe('Component Tests', () => {
    describe('Magacini Management Update Component', () => {
        let comp: MagaciniUpdateComponent;
        let fixture: ComponentFixture<MagaciniUpdateComponent>;
        let service: MagaciniService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ManProdTestModule],
                declarations: [MagaciniUpdateComponent]
            })
                .overrideTemplate(MagaciniUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(MagaciniUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(MagaciniService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new Magacini(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.magacini = entity;
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
                    const entity = new Magacini();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.magacini = entity;
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
