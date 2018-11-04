/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { ManProdTestModule } from '../../../test.module';
import { PomocniProizvodUpdateComponent } from 'app/entities/pomocni-proizvod/pomocni-proizvod-update.component';
import { PomocniProizvodService } from 'app/entities/pomocni-proizvod/pomocni-proizvod.service';
import { PomocniProizvod } from 'app/shared/model/pomocni-proizvod.model';

describe('Component Tests', () => {
    describe('PomocniProizvod Management Update Component', () => {
        let comp: PomocniProizvodUpdateComponent;
        let fixture: ComponentFixture<PomocniProizvodUpdateComponent>;
        let service: PomocniProizvodService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ManProdTestModule],
                declarations: [PomocniProizvodUpdateComponent]
            })
                .overrideTemplate(PomocniProizvodUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(PomocniProizvodUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(PomocniProizvodService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new PomocniProizvod(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.pomocniProizvod = entity;
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
                    const entity = new PomocniProizvod();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.pomocniProizvod = entity;
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
