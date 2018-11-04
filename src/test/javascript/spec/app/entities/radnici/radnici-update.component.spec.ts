/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { ManProdTestModule } from '../../../test.module';
import { RadniciUpdateComponent } from 'app/entities/radnici/radnici-update.component';
import { RadniciService } from 'app/entities/radnici/radnici.service';
import { Radnici } from 'app/shared/model/radnici.model';

describe('Component Tests', () => {
    describe('Radnici Management Update Component', () => {
        let comp: RadniciUpdateComponent;
        let fixture: ComponentFixture<RadniciUpdateComponent>;
        let service: RadniciService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ManProdTestModule],
                declarations: [RadniciUpdateComponent]
            })
                .overrideTemplate(RadniciUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(RadniciUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(RadniciService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new Radnici(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.radnici = entity;
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
                    const entity = new Radnici();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.radnici = entity;
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
