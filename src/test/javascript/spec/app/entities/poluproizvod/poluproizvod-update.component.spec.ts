/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { ManProdTestModule } from '../../../test.module';
import { PoluproizvodUpdateComponent } from 'app/entities/poluproizvod/poluproizvod-update.component';
import { PoluproizvodService } from 'app/entities/poluproizvod/poluproizvod.service';
import { Poluproizvod } from 'app/shared/model/poluproizvod.model';

describe('Component Tests', () => {
    describe('Poluproizvod Management Update Component', () => {
        let comp: PoluproizvodUpdateComponent;
        let fixture: ComponentFixture<PoluproizvodUpdateComponent>;
        let service: PoluproizvodService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ManProdTestModule],
                declarations: [PoluproizvodUpdateComponent]
            })
                .overrideTemplate(PoluproizvodUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(PoluproizvodUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(PoluproizvodService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new Poluproizvod(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.poluproizvod = entity;
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
                    const entity = new Poluproizvod();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.poluproizvod = entity;
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
