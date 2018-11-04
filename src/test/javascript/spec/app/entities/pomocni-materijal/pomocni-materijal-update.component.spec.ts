/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { ManProdTestModule } from '../../../test.module';
import { PomocniMaterijalUpdateComponent } from 'app/entities/pomocni-materijal/pomocni-materijal-update.component';
import { PomocniMaterijalService } from 'app/entities/pomocni-materijal/pomocni-materijal.service';
import { PomocniMaterijal } from 'app/shared/model/pomocni-materijal.model';

describe('Component Tests', () => {
    describe('PomocniMaterijal Management Update Component', () => {
        let comp: PomocniMaterijalUpdateComponent;
        let fixture: ComponentFixture<PomocniMaterijalUpdateComponent>;
        let service: PomocniMaterijalService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ManProdTestModule],
                declarations: [PomocniMaterijalUpdateComponent]
            })
                .overrideTemplate(PomocniMaterijalUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(PomocniMaterijalUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(PomocniMaterijalService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new PomocniMaterijal(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.pomocniMaterijal = entity;
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
                    const entity = new PomocniMaterijal();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.pomocniMaterijal = entity;
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
