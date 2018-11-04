/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { ManProdTestModule } from '../../../test.module';
import { KupovniMaterijalUpdateComponent } from 'app/entities/kupovni-materijal/kupovni-materijal-update.component';
import { KupovniMaterijalService } from 'app/entities/kupovni-materijal/kupovni-materijal.service';
import { KupovniMaterijal } from 'app/shared/model/kupovni-materijal.model';

describe('Component Tests', () => {
    describe('KupovniMaterijal Management Update Component', () => {
        let comp: KupovniMaterijalUpdateComponent;
        let fixture: ComponentFixture<KupovniMaterijalUpdateComponent>;
        let service: KupovniMaterijalService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ManProdTestModule],
                declarations: [KupovniMaterijalUpdateComponent]
            })
                .overrideTemplate(KupovniMaterijalUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(KupovniMaterijalUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(KupovniMaterijalService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new KupovniMaterijal(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.kupovniMaterijal = entity;
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
                    const entity = new KupovniMaterijal();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.kupovniMaterijal = entity;
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
