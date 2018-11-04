/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { ManProdTestModule } from '../../../test.module';
import { TransakcijeUMagacinuComponent } from 'app/entities/transakcije-u-magacinu/transakcije-u-magacinu.component';
import { TransakcijeUMagacinuService } from 'app/entities/transakcije-u-magacinu/transakcije-u-magacinu.service';
import { TransakcijeUMagacinu } from 'app/shared/model/transakcije-u-magacinu.model';

describe('Component Tests', () => {
    describe('TransakcijeUMagacinu Management Component', () => {
        let comp: TransakcijeUMagacinuComponent;
        let fixture: ComponentFixture<TransakcijeUMagacinuComponent>;
        let service: TransakcijeUMagacinuService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ManProdTestModule],
                declarations: [TransakcijeUMagacinuComponent],
                providers: []
            })
                .overrideTemplate(TransakcijeUMagacinuComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(TransakcijeUMagacinuComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(TransakcijeUMagacinuService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new TransakcijeUMagacinu(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.transakcijeUMagacinus[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
