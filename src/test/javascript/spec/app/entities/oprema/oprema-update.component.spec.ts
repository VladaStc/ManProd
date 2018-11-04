/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { ManProdTestModule } from '../../../test.module';
import { OpremaUpdateComponent } from 'app/entities/oprema/oprema-update.component';
import { OpremaService } from 'app/entities/oprema/oprema.service';
import { Oprema } from 'app/shared/model/oprema.model';

describe('Component Tests', () => {
    describe('Oprema Management Update Component', () => {
        let comp: OpremaUpdateComponent;
        let fixture: ComponentFixture<OpremaUpdateComponent>;
        let service: OpremaService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ManProdTestModule],
                declarations: [OpremaUpdateComponent]
            })
                .overrideTemplate(OpremaUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(OpremaUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(OpremaService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new Oprema(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.oprema = entity;
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
                    const entity = new Oprema();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.oprema = entity;
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
