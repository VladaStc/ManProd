/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { ManProdTestModule } from '../../../test.module';
import { RadnoMestoDeleteDialogComponent } from 'app/entities/radno-mesto/radno-mesto-delete-dialog.component';
import { RadnoMestoService } from 'app/entities/radno-mesto/radno-mesto.service';

describe('Component Tests', () => {
    describe('RadnoMesto Management Delete Component', () => {
        let comp: RadnoMestoDeleteDialogComponent;
        let fixture: ComponentFixture<RadnoMestoDeleteDialogComponent>;
        let service: RadnoMestoService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ManProdTestModule],
                declarations: [RadnoMestoDeleteDialogComponent]
            })
                .overrideTemplate(RadnoMestoDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(RadnoMestoDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(RadnoMestoService);
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
