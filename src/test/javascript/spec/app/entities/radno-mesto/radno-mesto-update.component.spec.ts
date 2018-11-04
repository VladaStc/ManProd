/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { ManProdTestModule } from '../../../test.module';
import { RadnoMestoUpdateComponent } from 'app/entities/radno-mesto/radno-mesto-update.component';
import { RadnoMestoService } from 'app/entities/radno-mesto/radno-mesto.service';
import { RadnoMesto } from 'app/shared/model/radno-mesto.model';

describe('Component Tests', () => {
    describe('RadnoMesto Management Update Component', () => {
        let comp: RadnoMestoUpdateComponent;
        let fixture: ComponentFixture<RadnoMestoUpdateComponent>;
        let service: RadnoMestoService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ManProdTestModule],
                declarations: [RadnoMestoUpdateComponent]
            })
                .overrideTemplate(RadnoMestoUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(RadnoMestoUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(RadnoMestoService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new RadnoMesto(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.radnoMesto = entity;
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
                    const entity = new RadnoMesto();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.radnoMesto = entity;
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
