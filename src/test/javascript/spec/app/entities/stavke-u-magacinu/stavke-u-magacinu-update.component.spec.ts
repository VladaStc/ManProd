/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { ManProdTestModule } from '../../../test.module';
import { StavkeUMagacinuUpdateComponent } from 'app/entities/stavke-u-magacinu/stavke-u-magacinu-update.component';
import { StavkeUMagacinuService } from 'app/entities/stavke-u-magacinu/stavke-u-magacinu.service';
import { StavkeUMagacinu } from 'app/shared/model/stavke-u-magacinu.model';

describe('Component Tests', () => {
    describe('StavkeUMagacinu Management Update Component', () => {
        let comp: StavkeUMagacinuUpdateComponent;
        let fixture: ComponentFixture<StavkeUMagacinuUpdateComponent>;
        let service: StavkeUMagacinuService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ManProdTestModule],
                declarations: [StavkeUMagacinuUpdateComponent]
            })
                .overrideTemplate(StavkeUMagacinuUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(StavkeUMagacinuUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(StavkeUMagacinuService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new StavkeUMagacinu(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.stavkeUMagacinu = entity;
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
                    const entity = new StavkeUMagacinu();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.stavkeUMagacinu = entity;
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
