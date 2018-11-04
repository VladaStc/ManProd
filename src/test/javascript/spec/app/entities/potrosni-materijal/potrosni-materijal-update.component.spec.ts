/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { ManProdTestModule } from '../../../test.module';
import { PotrosniMaterijalUpdateComponent } from 'app/entities/potrosni-materijal/potrosni-materijal-update.component';
import { PotrosniMaterijalService } from 'app/entities/potrosni-materijal/potrosni-materijal.service';
import { PotrosniMaterijal } from 'app/shared/model/potrosni-materijal.model';

describe('Component Tests', () => {
    describe('PotrosniMaterijal Management Update Component', () => {
        let comp: PotrosniMaterijalUpdateComponent;
        let fixture: ComponentFixture<PotrosniMaterijalUpdateComponent>;
        let service: PotrosniMaterijalService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ManProdTestModule],
                declarations: [PotrosniMaterijalUpdateComponent]
            })
                .overrideTemplate(PotrosniMaterijalUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(PotrosniMaterijalUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(PotrosniMaterijalService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new PotrosniMaterijal(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.potrosniMaterijal = entity;
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
                    const entity = new PotrosniMaterijal();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.potrosniMaterijal = entity;
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
