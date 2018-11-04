/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { ManProdTestModule } from '../../../test.module';
import { SirovineUpdateComponent } from 'app/entities/sirovine/sirovine-update.component';
import { SirovineService } from 'app/entities/sirovine/sirovine.service';
import { Sirovine } from 'app/shared/model/sirovine.model';

describe('Component Tests', () => {
    describe('Sirovine Management Update Component', () => {
        let comp: SirovineUpdateComponent;
        let fixture: ComponentFixture<SirovineUpdateComponent>;
        let service: SirovineService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ManProdTestModule],
                declarations: [SirovineUpdateComponent]
            })
                .overrideTemplate(SirovineUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(SirovineUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(SirovineService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new Sirovine(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.sirovine = entity;
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
                    const entity = new Sirovine();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.sirovine = entity;
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
