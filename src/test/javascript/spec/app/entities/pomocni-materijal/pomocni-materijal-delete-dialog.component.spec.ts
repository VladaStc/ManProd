/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { ManProdTestModule } from '../../../test.module';
import { PomocniMaterijalDeleteDialogComponent } from 'app/entities/pomocni-materijal/pomocni-materijal-delete-dialog.component';
import { PomocniMaterijalService } from 'app/entities/pomocni-materijal/pomocni-materijal.service';

describe('Component Tests', () => {
    describe('PomocniMaterijal Management Delete Component', () => {
        let comp: PomocniMaterijalDeleteDialogComponent;
        let fixture: ComponentFixture<PomocniMaterijalDeleteDialogComponent>;
        let service: PomocniMaterijalService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ManProdTestModule],
                declarations: [PomocniMaterijalDeleteDialogComponent]
            })
                .overrideTemplate(PomocniMaterijalDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(PomocniMaterijalDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(PomocniMaterijalService);
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
