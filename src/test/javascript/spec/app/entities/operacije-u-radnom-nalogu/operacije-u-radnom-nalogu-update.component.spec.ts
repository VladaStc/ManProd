/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { ManProdTestModule } from '../../../test.module';
import { OperacijeURadnomNaloguUpdateComponent } from 'app/entities/operacije-u-radnom-nalogu/operacije-u-radnom-nalogu-update.component';
import { OperacijeURadnomNaloguService } from 'app/entities/operacije-u-radnom-nalogu/operacije-u-radnom-nalogu.service';
import { OperacijeURadnomNalogu } from 'app/shared/model/operacije-u-radnom-nalogu.model';

describe('Component Tests', () => {
    describe('OperacijeURadnomNalogu Management Update Component', () => {
        let comp: OperacijeURadnomNaloguUpdateComponent;
        let fixture: ComponentFixture<OperacijeURadnomNaloguUpdateComponent>;
        let service: OperacijeURadnomNaloguService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ManProdTestModule],
                declarations: [OperacijeURadnomNaloguUpdateComponent]
            })
                .overrideTemplate(OperacijeURadnomNaloguUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(OperacijeURadnomNaloguUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(OperacijeURadnomNaloguService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new OperacijeURadnomNalogu(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.operacijeURadnomNalogu = entity;
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
                    const entity = new OperacijeURadnomNalogu();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.operacijeURadnomNalogu = entity;
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
