/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { ManProdTestModule } from '../../../test.module';
import { MasinaUpdateComponent } from 'app/entities/masina/masina-update.component';
import { MasinaService } from 'app/entities/masina/masina.service';
import { Masina } from 'app/shared/model/masina.model';

describe('Component Tests', () => {
    describe('Masina Management Update Component', () => {
        let comp: MasinaUpdateComponent;
        let fixture: ComponentFixture<MasinaUpdateComponent>;
        let service: MasinaService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ManProdTestModule],
                declarations: [MasinaUpdateComponent]
            })
                .overrideTemplate(MasinaUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(MasinaUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(MasinaService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new Masina(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.masina = entity;
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
                    const entity = new Masina();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.masina = entity;
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
