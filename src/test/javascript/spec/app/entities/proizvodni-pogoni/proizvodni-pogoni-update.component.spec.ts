/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { ManProdTestModule } from '../../../test.module';
import { ProizvodniPogoniUpdateComponent } from 'app/entities/proizvodni-pogoni/proizvodni-pogoni-update.component';
import { ProizvodniPogoniService } from 'app/entities/proizvodni-pogoni/proizvodni-pogoni.service';
import { ProizvodniPogoni } from 'app/shared/model/proizvodni-pogoni.model';

describe('Component Tests', () => {
    describe('ProizvodniPogoni Management Update Component', () => {
        let comp: ProizvodniPogoniUpdateComponent;
        let fixture: ComponentFixture<ProizvodniPogoniUpdateComponent>;
        let service: ProizvodniPogoniService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ManProdTestModule],
                declarations: [ProizvodniPogoniUpdateComponent]
            })
                .overrideTemplate(ProizvodniPogoniUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(ProizvodniPogoniUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ProizvodniPogoniService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new ProizvodniPogoni(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.proizvodniPogoni = entity;
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
                    const entity = new ProizvodniPogoni();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.proizvodniPogoni = entity;
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
