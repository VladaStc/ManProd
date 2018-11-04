/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { ManProdTestModule } from '../../../test.module';
import { AlatiUpdateComponent } from 'app/entities/alati/alati-update.component';
import { AlatiService } from 'app/entities/alati/alati.service';
import { Alati } from 'app/shared/model/alati.model';

describe('Component Tests', () => {
    describe('Alati Management Update Component', () => {
        let comp: AlatiUpdateComponent;
        let fixture: ComponentFixture<AlatiUpdateComponent>;
        let service: AlatiService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ManProdTestModule],
                declarations: [AlatiUpdateComponent]
            })
                .overrideTemplate(AlatiUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(AlatiUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(AlatiService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new Alati(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.alati = entity;
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
                    const entity = new Alati();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.alati = entity;
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
