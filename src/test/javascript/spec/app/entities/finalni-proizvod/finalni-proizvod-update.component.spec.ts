/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { ManProdTestModule } from '../../../test.module';
import { FinalniProizvodUpdateComponent } from 'app/entities/finalni-proizvod/finalni-proizvod-update.component';
import { FinalniProizvodService } from 'app/entities/finalni-proizvod/finalni-proizvod.service';
import { FinalniProizvod } from 'app/shared/model/finalni-proizvod.model';

describe('Component Tests', () => {
    describe('FinalniProizvod Management Update Component', () => {
        let comp: FinalniProizvodUpdateComponent;
        let fixture: ComponentFixture<FinalniProizvodUpdateComponent>;
        let service: FinalniProizvodService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ManProdTestModule],
                declarations: [FinalniProizvodUpdateComponent]
            })
                .overrideTemplate(FinalniProizvodUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(FinalniProizvodUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(FinalniProizvodService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new FinalniProizvod(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.finalniProizvod = entity;
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
                    const entity = new FinalniProizvod();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.finalniProizvod = entity;
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
