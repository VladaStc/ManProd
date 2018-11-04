/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { ManProdTestModule } from '../../../test.module';
import { KupovniMaterijalDeleteDialogComponent } from 'app/entities/kupovni-materijal/kupovni-materijal-delete-dialog.component';
import { KupovniMaterijalService } from 'app/entities/kupovni-materijal/kupovni-materijal.service';

describe('Component Tests', () => {
    describe('KupovniMaterijal Management Delete Component', () => {
        let comp: KupovniMaterijalDeleteDialogComponent;
        let fixture: ComponentFixture<KupovniMaterijalDeleteDialogComponent>;
        let service: KupovniMaterijalService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ManProdTestModule],
                declarations: [KupovniMaterijalDeleteDialogComponent]
            })
                .overrideTemplate(KupovniMaterijalDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(KupovniMaterijalDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(KupovniMaterijalService);
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
