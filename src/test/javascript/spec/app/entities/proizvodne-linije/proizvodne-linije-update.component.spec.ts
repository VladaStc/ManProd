/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { ManProdTestModule } from '../../../test.module';
import { ProizvodneLinijeUpdateComponent } from 'app/entities/proizvodne-linije/proizvodne-linije-update.component';
import { ProizvodneLinijeService } from 'app/entities/proizvodne-linije/proizvodne-linije.service';
import { ProizvodneLinije } from 'app/shared/model/proizvodne-linije.model';

describe('Component Tests', () => {
    describe('ProizvodneLinije Management Update Component', () => {
        let comp: ProizvodneLinijeUpdateComponent;
        let fixture: ComponentFixture<ProizvodneLinijeUpdateComponent>;
        let service: ProizvodneLinijeService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ManProdTestModule],
                declarations: [ProizvodneLinijeUpdateComponent]
            })
                .overrideTemplate(ProizvodneLinijeUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(ProizvodneLinijeUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ProizvodneLinijeService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new ProizvodneLinije(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.proizvodneLinije = entity;
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
                    const entity = new ProizvodneLinije();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.proizvodneLinije = entity;
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
