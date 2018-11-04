/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { ManProdTestModule } from '../../../test.module';
import { OperacijeURadnomNaloguDeleteDialogComponent } from 'app/entities/operacije-u-radnom-nalogu/operacije-u-radnom-nalogu-delete-dialog.component';
import { OperacijeURadnomNaloguService } from 'app/entities/operacije-u-radnom-nalogu/operacije-u-radnom-nalogu.service';

describe('Component Tests', () => {
    describe('OperacijeURadnomNalogu Management Delete Component', () => {
        let comp: OperacijeURadnomNaloguDeleteDialogComponent;
        let fixture: ComponentFixture<OperacijeURadnomNaloguDeleteDialogComponent>;
        let service: OperacijeURadnomNaloguService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ManProdTestModule],
                declarations: [OperacijeURadnomNaloguDeleteDialogComponent]
            })
                .overrideTemplate(OperacijeURadnomNaloguDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(OperacijeURadnomNaloguDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(OperacijeURadnomNaloguService);
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
