/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { ManProdTestModule } from '../../../test.module';
import { KupovniProizvodUpdateComponent } from 'app/entities/kupovni-proizvod/kupovni-proizvod-update.component';
import { KupovniProizvodService } from 'app/entities/kupovni-proizvod/kupovni-proizvod.service';
import { KupovniProizvod } from 'app/shared/model/kupovni-proizvod.model';

describe('Component Tests', () => {
    describe('KupovniProizvod Management Update Component', () => {
        let comp: KupovniProizvodUpdateComponent;
        let fixture: ComponentFixture<KupovniProizvodUpdateComponent>;
        let service: KupovniProizvodService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ManProdTestModule],
                declarations: [KupovniProizvodUpdateComponent]
            })
                .overrideTemplate(KupovniProizvodUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(KupovniProizvodUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(KupovniProizvodService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new KupovniProizvod(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.kupovniProizvod = entity;
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
                    const entity = new KupovniProizvod();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.kupovniProizvod = entity;
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
