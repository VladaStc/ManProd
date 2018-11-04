/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { ManProdTestModule } from '../../../test.module';
import { TransakcijeUMagacinuUpdateComponent } from 'app/entities/transakcije-u-magacinu/transakcije-u-magacinu-update.component';
import { TransakcijeUMagacinuService } from 'app/entities/transakcije-u-magacinu/transakcije-u-magacinu.service';
import { TransakcijeUMagacinu } from 'app/shared/model/transakcije-u-magacinu.model';

describe('Component Tests', () => {
    describe('TransakcijeUMagacinu Management Update Component', () => {
        let comp: TransakcijeUMagacinuUpdateComponent;
        let fixture: ComponentFixture<TransakcijeUMagacinuUpdateComponent>;
        let service: TransakcijeUMagacinuService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ManProdTestModule],
                declarations: [TransakcijeUMagacinuUpdateComponent]
            })
                .overrideTemplate(TransakcijeUMagacinuUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(TransakcijeUMagacinuUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(TransakcijeUMagacinuService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new TransakcijeUMagacinu(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.transakcijeUMagacinu = entity;
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
                    const entity = new TransakcijeUMagacinu();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.transakcijeUMagacinu = entity;
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
