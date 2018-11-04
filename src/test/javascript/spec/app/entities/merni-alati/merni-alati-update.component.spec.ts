/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { ManProdTestModule } from '../../../test.module';
import { MerniAlatiUpdateComponent } from 'app/entities/merni-alati/merni-alati-update.component';
import { MerniAlatiService } from 'app/entities/merni-alati/merni-alati.service';
import { MerniAlati } from 'app/shared/model/merni-alati.model';

describe('Component Tests', () => {
    describe('MerniAlati Management Update Component', () => {
        let comp: MerniAlatiUpdateComponent;
        let fixture: ComponentFixture<MerniAlatiUpdateComponent>;
        let service: MerniAlatiService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ManProdTestModule],
                declarations: [MerniAlatiUpdateComponent]
            })
                .overrideTemplate(MerniAlatiUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(MerniAlatiUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(MerniAlatiService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new MerniAlati(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.merniAlati = entity;
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
                    const entity = new MerniAlati();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.merniAlati = entity;
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
