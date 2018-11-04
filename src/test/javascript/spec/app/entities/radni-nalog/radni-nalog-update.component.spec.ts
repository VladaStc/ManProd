/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { ManProdTestModule } from '../../../test.module';
import { RadniNalogUpdateComponent } from 'app/entities/radni-nalog/radni-nalog-update.component';
import { RadniNalogService } from 'app/entities/radni-nalog/radni-nalog.service';
import { RadniNalog } from 'app/shared/model/radni-nalog.model';

describe('Component Tests', () => {
    describe('RadniNalog Management Update Component', () => {
        let comp: RadniNalogUpdateComponent;
        let fixture: ComponentFixture<RadniNalogUpdateComponent>;
        let service: RadniNalogService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ManProdTestModule],
                declarations: [RadniNalogUpdateComponent]
            })
                .overrideTemplate(RadniNalogUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(RadniNalogUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(RadniNalogService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new RadniNalog(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.radniNalog = entity;
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
                    const entity = new RadniNalog();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.radniNalog = entity;
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
