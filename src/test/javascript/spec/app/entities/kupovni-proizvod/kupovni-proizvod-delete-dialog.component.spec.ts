/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { ManProdTestModule } from '../../../test.module';
import { KupovniProizvodDeleteDialogComponent } from 'app/entities/kupovni-proizvod/kupovni-proizvod-delete-dialog.component';
import { KupovniProizvodService } from 'app/entities/kupovni-proizvod/kupovni-proizvod.service';

describe('Component Tests', () => {
    describe('KupovniProizvod Management Delete Component', () => {
        let comp: KupovniProizvodDeleteDialogComponent;
        let fixture: ComponentFixture<KupovniProizvodDeleteDialogComponent>;
        let service: KupovniProizvodService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ManProdTestModule],
                declarations: [KupovniProizvodDeleteDialogComponent]
            })
                .overrideTemplate(KupovniProizvodDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(KupovniProizvodDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(KupovniProizvodService);
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
