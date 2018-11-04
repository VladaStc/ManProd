/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { ManProdTestModule } from '../../../test.module';
import { KonstruktivnaSastavnicaUpdateComponent } from 'app/entities/konstruktivna-sastavnica/konstruktivna-sastavnica-update.component';
import { KonstruktivnaSastavnicaService } from 'app/entities/konstruktivna-sastavnica/konstruktivna-sastavnica.service';
import { KonstruktivnaSastavnica } from 'app/shared/model/konstruktivna-sastavnica.model';

describe('Component Tests', () => {
    describe('KonstruktivnaSastavnica Management Update Component', () => {
        let comp: KonstruktivnaSastavnicaUpdateComponent;
        let fixture: ComponentFixture<KonstruktivnaSastavnicaUpdateComponent>;
        let service: KonstruktivnaSastavnicaService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ManProdTestModule],
                declarations: [KonstruktivnaSastavnicaUpdateComponent]
            })
                .overrideTemplate(KonstruktivnaSastavnicaUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(KonstruktivnaSastavnicaUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(KonstruktivnaSastavnicaService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new KonstruktivnaSastavnica(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.konstruktivnaSastavnica = entity;
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
                    const entity = new KonstruktivnaSastavnica();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.konstruktivnaSastavnica = entity;
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
