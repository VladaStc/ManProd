/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { ManProdTestModule } from '../../../test.module';
import { ObradniSistemUpdateComponent } from 'app/entities/obradni-sistem/obradni-sistem-update.component';
import { ObradniSistemService } from 'app/entities/obradni-sistem/obradni-sistem.service';
import { ObradniSistem } from 'app/shared/model/obradni-sistem.model';

describe('Component Tests', () => {
    describe('ObradniSistem Management Update Component', () => {
        let comp: ObradniSistemUpdateComponent;
        let fixture: ComponentFixture<ObradniSistemUpdateComponent>;
        let service: ObradniSistemService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ManProdTestModule],
                declarations: [ObradniSistemUpdateComponent]
            })
                .overrideTemplate(ObradniSistemUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(ObradniSistemUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ObradniSistemService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new ObradniSistem(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.obradniSistem = entity;
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
                    const entity = new ObradniSistem();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.obradniSistem = entity;
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
