/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { ManProdTestModule } from '../../../test.module';
import { ProizvodniPogoniDeleteDialogComponent } from 'app/entities/proizvodni-pogoni/proizvodni-pogoni-delete-dialog.component';
import { ProizvodniPogoniService } from 'app/entities/proizvodni-pogoni/proizvodni-pogoni.service';

describe('Component Tests', () => {
    describe('ProizvodniPogoni Management Delete Component', () => {
        let comp: ProizvodniPogoniDeleteDialogComponent;
        let fixture: ComponentFixture<ProizvodniPogoniDeleteDialogComponent>;
        let service: ProizvodniPogoniService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ManProdTestModule],
                declarations: [ProizvodniPogoniDeleteDialogComponent]
            })
                .overrideTemplate(ProizvodniPogoniDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(ProizvodniPogoniDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ProizvodniPogoniService);
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
