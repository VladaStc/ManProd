/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { ManProdTestModule } from '../../../test.module';
import { RadionicaUpdateComponent } from 'app/entities/radionica/radionica-update.component';
import { RadionicaService } from 'app/entities/radionica/radionica.service';
import { Radionica } from 'app/shared/model/radionica.model';

describe('Component Tests', () => {
    describe('Radionica Management Update Component', () => {
        let comp: RadionicaUpdateComponent;
        let fixture: ComponentFixture<RadionicaUpdateComponent>;
        let service: RadionicaService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ManProdTestModule],
                declarations: [RadionicaUpdateComponent]
            })
                .overrideTemplate(RadionicaUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(RadionicaUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(RadionicaService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new Radionica(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.radionica = entity;
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
                    const entity = new Radionica();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.radionica = entity;
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
