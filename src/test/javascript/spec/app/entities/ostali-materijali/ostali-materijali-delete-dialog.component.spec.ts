/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { ManProdTestModule } from '../../../test.module';
import { OstaliMaterijaliDeleteDialogComponent } from 'app/entities/ostali-materijali/ostali-materijali-delete-dialog.component';
import { OstaliMaterijaliService } from 'app/entities/ostali-materijali/ostali-materijali.service';

describe('Component Tests', () => {
    describe('OstaliMaterijali Management Delete Component', () => {
        let comp: OstaliMaterijaliDeleteDialogComponent;
        let fixture: ComponentFixture<OstaliMaterijaliDeleteDialogComponent>;
        let service: OstaliMaterijaliService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ManProdTestModule],
                declarations: [OstaliMaterijaliDeleteDialogComponent]
            })
                .overrideTemplate(OstaliMaterijaliDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(OstaliMaterijaliDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(OstaliMaterijaliService);
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
