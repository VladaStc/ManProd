/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { ManProdTestModule } from '../../../test.module';
import { ZahvatiUpdateComponent } from 'app/entities/zahvati/zahvati-update.component';
import { ZahvatiService } from 'app/entities/zahvati/zahvati.service';
import { Zahvati } from 'app/shared/model/zahvati.model';

describe('Component Tests', () => {
    describe('Zahvati Management Update Component', () => {
        let comp: ZahvatiUpdateComponent;
        let fixture: ComponentFixture<ZahvatiUpdateComponent>;
        let service: ZahvatiService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ManProdTestModule],
                declarations: [ZahvatiUpdateComponent]
            })
                .overrideTemplate(ZahvatiUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(ZahvatiUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ZahvatiService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new Zahvati(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.zahvati = entity;
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
                    const entity = new Zahvati();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.zahvati = entity;
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
