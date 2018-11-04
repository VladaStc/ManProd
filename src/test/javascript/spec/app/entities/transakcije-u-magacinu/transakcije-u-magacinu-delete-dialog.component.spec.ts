/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { ManProdTestModule } from '../../../test.module';
import { TransakcijeUMagacinuDeleteDialogComponent } from 'app/entities/transakcije-u-magacinu/transakcije-u-magacinu-delete-dialog.component';
import { TransakcijeUMagacinuService } from 'app/entities/transakcije-u-magacinu/transakcije-u-magacinu.service';

describe('Component Tests', () => {
    describe('TransakcijeUMagacinu Management Delete Component', () => {
        let comp: TransakcijeUMagacinuDeleteDialogComponent;
        let fixture: ComponentFixture<TransakcijeUMagacinuDeleteDialogComponent>;
        let service: TransakcijeUMagacinuService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ManProdTestModule],
                declarations: [TransakcijeUMagacinuDeleteDialogComponent]
            })
                .overrideTemplate(TransakcijeUMagacinuDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(TransakcijeUMagacinuDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(TransakcijeUMagacinuService);
            mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
            mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
        });

        describe('confirmDelete', () => {
            it('Should call delete service on confirmDelete', inject(
                [],
                fakeAsync(() => {
                    // GIVEN
                    spyOn(service, 'delete').and.returnValue(of({}));

                    // WHEN
                    comp.confirmDelete(123);
                    tick();

                    // THEN
                    expect(service.delete).toHaveBeenCalledWith(123);
                    expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    expect(mockEventManager.broadcastSpy).toHaveBeenCalled();
                })
            ));
        });
    });
});
