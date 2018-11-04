/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { ManProdTestModule } from '../../../test.module';
import { KomponeneteUpdateComponent } from 'app/entities/komponenete/komponenete-update.component';
import { KomponeneteService } from 'app/entities/komponenete/komponenete.service';
import { Komponenete } from 'app/shared/model/komponenete.model';

describe('Component Tests', () => {
    describe('Komponenete Management Update Component', () => {
        let comp: KomponeneteUpdateComponent;
        let fixture: ComponentFixture<KomponeneteUpdateComponent>;
        let service: KomponeneteService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ManProdTestModule],
                declarations: [KomponeneteUpdateComponent]
            })
                .overrideTemplate(KomponeneteUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(KomponeneteUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(KomponeneteService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new Komponenete(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.komponenete = entity;
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
                    const entity = new Komponenete();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.komponenete = entity;
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
